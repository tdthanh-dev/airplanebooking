package com.project.airplanebooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.airplanebooking.dto.request.TicketDTO;
import com.project.airplanebooking.exception.BusinessLogicException;
import com.project.airplanebooking.exception.EntityNotFoundException;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.model.SeatFlight;
import com.project.airplanebooking.model.Ticket;
import com.project.airplanebooking.repository.BookingRepository;
import com.project.airplanebooking.repository.FlightRepository;
import com.project.airplanebooking.repository.PassengerRepository;
import com.project.airplanebooking.repository.SeatFlightRepository;
import com.project.airplanebooking.repository.TicketRepository;
import com.project.airplanebooking.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final SeatFlightRepository seatFlightRepository;

    @Autowired
    public TicketServiceImpl(
            TicketRepository ticketRepository,
            BookingRepository bookingRepository,
            FlightRepository flightRepository,
            PassengerRepository passengerRepository,
            SeatFlightRepository seatFlightRepository) {
        this.ticketRepository = ticketRepository;
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.seatFlightRepository = seatFlightRepository;
    }

    @Override
    public Ticket createTicket(TicketDTO ticketDTO) {
        Booking booking = bookingRepository.findById(ticketDTO.getBookingId())
                .orElseThrow(() -> new EntityNotFoundException(Booking.class, ticketDTO.getBookingId()));

        Flight flight = flightRepository.findById(ticketDTO.getFlightId())
                .orElseThrow(() -> new EntityNotFoundException(Flight.class, ticketDTO.getFlightId()));

        Passenger passenger = passengerRepository.findById(ticketDTO.getPassengerId())
                .orElseThrow(() -> new EntityNotFoundException(Passenger.class, ticketDTO.getPassengerId()));

        SeatFlight seatFlight = seatFlightRepository.findById(ticketDTO.getSeatFlightId())
                .orElseThrow(() -> new EntityNotFoundException(SeatFlight.class, ticketDTO.getSeatFlightId()));

        if (!"BOOKED".equals(seatFlight.getStatus())) {
            throw new BusinessLogicException("Ghế chưa được book hoặc không khả dụng để xuất vé");
        }
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(generateTicketNumber());
        ticket.setBooking(booking);
        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        ticket.setSeatFlight(seatFlight);

        ticket.setTicketPrice(seatFlight.getPrice() != null ? seatFlight.getPrice() : ticketDTO.getTicketPrice());
        ticket.setTicketClass(seatFlight.getSeatType() != null ? seatFlight.getSeatType() : ticketDTO.getTicketClass());
        ticket.setTicketType(
                booking.getTripType() != null ? (booking.getTripType().equals("ROUND_TRIP") ? "ROUND_TRIP" : "ONE_WAY")
                        : ticketDTO.getTicketType());
        List<Flight> bookingFlights = new ArrayList<>(booking.getFlights());
        bookingFlights.sort((f1, f2) -> f1.getDepartureTime().compareTo(f2.getDepartureTime()));
        int legNumber = bookingFlights.indexOf(flight) + 1;
        ticket.setLegNumber(legNumber > 0 ? legNumber : ticketDTO.getLegNumber());

        ticket.setRelatedTicketId(ticketDTO.getRelatedTicketId());
        ticket.setStatus("ACTIVE");

        return ticketRepository.save(ticket);
    }

    private String generateTicketNumber() {
        return "TK" + System.currentTimeMillis() % 10000000
                + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
    }

    @Override
    public Ticket updateTicket(Long id, TicketDTO ticketDTO) {
        Ticket ticket = getTicketById(id);

        if (ticketDTO.getTicketPrice() != null) {
            ticket.setTicketPrice(ticketDTO.getTicketPrice());
        }
        if (ticketDTO.getStatus() != null) {
            ticket.setStatus(ticketDTO.getStatus());
        }

        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket ticket = getTicketById(id);
        ticket.setStatus("CANCELLED");
        ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Ticket.class, id));
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Ticket> generateTicket(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException(Booking.class, bookingId));

        if (!"CONFIRMED".equals(booking.getStatus())) {
            throw new BusinessLogicException("Chỉ có thể tạo vé cho booking đã được xác nhận (CONFIRMED)");
        }

        List<Ticket> generatedTickets = new ArrayList<>();
        List<Passenger> passengers = new ArrayList<>(booking.getPassengers());
        List<Flight> flights = new ArrayList<>(booking.getFlights());
        List<SeatFlight> seatFlights = new ArrayList<>(booking.getSeatFlights());

        flights.sort((f1, f2) -> f1.getDepartureTime().compareTo(f2.getDepartureTime()));

        for (Passenger passenger : passengers) {
            int passengerFlightCount = "ROUND_TRIP".equals(booking.getTripType()) ? 2 : 1;

            for (int i = 0; i < passengerFlightCount; i++) {
                Flight flight = flights.get(i);

                SeatFlight passengerSeat = null;
                for (SeatFlight seatFlight : seatFlights) {
                    if (seatFlight.getFlight().getId().equals(flight.getId())
                            && "BOOKED".equals(seatFlight.getStatus())) {
                        if (passengerSeat == null || seatFlight.getDateHold().isBefore(passengerSeat.getDateHold())) {
                            passengerSeat = seatFlight;
                        }
                    }
                }

                if (passengerSeat == null) {
                    throw new BusinessLogicException("Không tìm thấy ghế đã đặt cho hành khách " + passenger.getId() +
                            " trên chuyến bay " + flight.getFlightNo());
                }

                seatFlights.remove(passengerSeat);

                Ticket ticket = new Ticket();
                ticket.setTicketNumber(generateTicketNumber());
                ticket.setBooking(booking);
                ticket.setFlight(flight);
                ticket.setPassenger(passenger);
                ticket.setSeatFlight(passengerSeat);
                ticket.setTicketPrice(passengerSeat.getPrice());
                ticket.setTicketClass(passengerSeat.getSeatType());
                ticket.setTicketType(booking.getTripType());
                ticket.setLegNumber(i + 1); // 1 là đi, 2 là về
                ticket.setStatus("ACTIVE");

                Ticket savedTicket = ticketRepository.save(ticket);
                generatedTickets.add(savedTicket);
            }
        }

        return generatedTickets;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Ticket> generateAllTicketsForBooking(Long bookingId) {
        return generateTicket(bookingId);
    }

    @Override
    public List<Ticket> getTicketsByBookingId(Long bookingId) {
        return ticketRepository.findByBookingId(bookingId);
    }
}