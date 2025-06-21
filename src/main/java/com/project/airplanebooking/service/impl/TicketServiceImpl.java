package com.project.airplanebooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return "TK-" + System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
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

    @Transactional
    public Ticket generateTicketForBooking(Long bookingId, Long passengerId, Long flightId, Long seatFlightId) {
        return createTicketAutomatically(bookingId, passengerId, flightId, seatFlightId);
    }

    @Override
    @Transactional
    public List<Ticket> generateAllTicketsForBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException(Booking.class, bookingId));

        if (!"CONFIRMED".equals(booking.getStatus())) {
            throw new BusinessLogicException("Chỉ có thể xuất vé cho booking đã được xác nhận");
        }

        List<Ticket> tickets = new ArrayList<>();

        List<Passenger> passengers = booking.getPassengers();
        List<SeatFlight> seatFlights = booking.getSeatFlights();

        for (Passenger passenger : passengers) {
            for (Flight flight : booking.getFlights()) {
                List<SeatFlight> passengerSeatsOnFlight = seatFlights.stream()
                        .filter(sf -> sf.getFlight().getId().equals(flight.getId()))
                        .toList();

                if (!passengerSeatsOnFlight.isEmpty()) {
                    SeatFlight seatFlight = passengerSeatsOnFlight.get(0);
                    try {
                        Ticket ticket = generateTicketForBooking(bookingId, passenger.getId(),
                                flight.getId(), seatFlight.getId());
                        tickets.add(ticket);
                    } catch (BusinessLogicException e) {
                        System.out.println("Ticket already exists: " + e.getMessage());
                    }
                }
            }
        }

        return tickets;
    }

    @Override
    @Transactional
    public List<Ticket> generateTicketsForBookingAndFlight(Long bookingId, Long flightId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException(Booking.class, bookingId));

        flightRepository.findById(flightId)
                .orElseThrow(() -> new EntityNotFoundException(Flight.class, flightId));

        List<Ticket> tickets = new ArrayList<>();
        List<Passenger> passengers = booking.getPassengers();
        List<SeatFlight> seatFlights = booking.getSeatFlights().stream()
                .filter(sf -> sf.getFlight().getId().equals(flightId))
                .toList();

        for (int i = 0; i < passengers.size() && i < seatFlights.size(); i++) {
            Passenger passenger = passengers.get(i);
            SeatFlight seatFlight = seatFlights.get(i);

            try {
                Ticket ticket = generateTicketForBooking(bookingId, passenger.getId(),
                        flightId, seatFlight.getId());
                tickets.add(ticket);
            } catch (BusinessLogicException e) {
                System.out.println("Ticket generation failed: " + e.getMessage());
            }
        }

        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByBookingReference(String bookingReference) {
        Booking booking = bookingRepository.findByBookingReference(bookingReference)
                .orElseThrow(
                        () -> new EntityNotFoundException("Booking not found with reference: " + bookingReference));
        return ticketRepository.findByBooking(booking);
    }

    @Override
    public List<Ticket> getTicketsByPassenger(Long passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new EntityNotFoundException(Passenger.class, passengerId));
        return ticketRepository.findByPassenger(passenger);
    }

    @Transactional
    public Ticket createTicketAutomatically(Long bookingId, Long passengerId, Long flightId, Long seatFlightId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException(Booking.class, bookingId));

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new EntityNotFoundException(Flight.class, flightId));

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new EntityNotFoundException(Passenger.class, passengerId));

        SeatFlight seatFlight = seatFlightRepository.findById(seatFlightId)
                .orElseThrow(() -> new EntityNotFoundException(SeatFlight.class, seatFlightId));

        if (!"BOOKED".equals(seatFlight.getStatus())) {
            throw new BusinessLogicException("Ghế chưa được book hoặc không khả dụng để xuất vé");
        }

        Ticket ticket = new Ticket();
        ticket.setTicketNumber(generateTicketNumber());
        ticket.setBooking(booking);
        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        ticket.setSeatFlight(seatFlight);

        ticket.setTicketPrice(seatFlight.getPrice());
        ticket.setTicketClass(seatFlight.getSeatType());
        ticket.setTicketType(booking.getTripType().equals("ROUND_TRIP") ? "ROUND_TRIP" : "ONE_WAY");

        List<Flight> bookingFlights = new ArrayList<>(booking.getFlights());
        bookingFlights.sort((f1, f2) -> f1.getDepartureTime().compareTo(f2.getDepartureTime()));
        int legNumber = bookingFlights.indexOf(flight) + 1;
        ticket.setLegNumber(legNumber);

        ticket.setStatus("ACTIVE");

        return ticketRepository.save(ticket);
    }
}