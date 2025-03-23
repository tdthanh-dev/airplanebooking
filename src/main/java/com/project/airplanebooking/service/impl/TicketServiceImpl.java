package com.project.airplanebooking.service.impl;

import com.project.airplanebooking.dto.request.TicketDTO;
import com.project.airplanebooking.model.Ticket;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.model.Seat;
import com.project.airplanebooking.repository.TicketRepository;
import com.project.airplanebooking.repository.BookingRepository;
import com.project.airplanebooking.repository.FlightRepository;
import com.project.airplanebooking.repository.PassengerRepository;
import com.project.airplanebooking.repository.SeatRepository;
import com.project.airplanebooking.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final SeatRepository seatRepository;

    public TicketServiceImpl(TicketRepository ticketRepository,
            BookingRepository bookingRepository,
            FlightRepository flightRepository,
            PassengerRepository passengerRepository,
            SeatRepository seatRepository) {
        this.ticketRepository = ticketRepository;
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public Ticket createTicket(TicketDTO ticketDTO) {
        Booking booking = bookingRepository.findById(ticketDTO.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + ticketDTO.getBookingId()));

        Flight flight = flightRepository.findById(ticketDTO.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + ticketDTO.getFlightId()));

        Passenger passenger = passengerRepository.findById(ticketDTO.getPassengerId())
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + ticketDTO.getPassengerId()));

        Seat seat = seatRepository.findById(ticketDTO.getSeatId())
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + ticketDTO.getSeatId()));

        // Check if seat is already booked for this flight
        if (ticketRepository.findByFlightAndSeat(flight, seat).isPresent()) {
            throw new RuntimeException("Seat " + seat.getSeatNumber() + " is already booked for this flight");
        }

        Ticket ticket = new Ticket();
        ticket.setBooking(booking);
        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        ticket.setSeat(seat);
        ticket.setTicketPrice(ticketDTO.getTicketPrice());
        ticket.setTicketType(ticketDTO.getTicketType());
        ticket.setLegNumber(ticketDTO.getLegNumber());
        ticket.setRelatedTicketId(ticketDTO.getRelatedTicketId());
        ticket.setStatus("CONFIRMED");

        // Update seat availability
        seat.setIsAvailable(false);
        seatRepository.save(seat);

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Long id, TicketDTO ticketDTO) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));

        if (ticketDTO.getBookingId() != null) {
            Booking booking = bookingRepository.findById(ticketDTO.getBookingId())
                    .orElseThrow(() -> new RuntimeException("Booking not found with id: " + ticketDTO.getBookingId()));
            ticket.setBooking(booking);
        }

        if (ticketDTO.getFlightId() != null) {
            Flight flight = flightRepository.findById(ticketDTO.getFlightId())
                    .orElseThrow(() -> new RuntimeException("Flight not found with id: " + ticketDTO.getFlightId()));
            ticket.setFlight(flight);
        }

        if (ticketDTO.getPassengerId() != null) {
            Passenger passenger = passengerRepository.findById(ticketDTO.getPassengerId())
                    .orElseThrow(
                            () -> new RuntimeException("Passenger not found with id: " + ticketDTO.getPassengerId()));
            ticket.setPassenger(passenger);
        }

        if (ticketDTO.getSeatId() != null && !ticketDTO.getSeatId().equals(ticket.getSeat().getId())) {
            // First, make the current seat available
            Seat currentSeat = ticket.getSeat();
            currentSeat.setIsAvailable(true);
            seatRepository.save(currentSeat);

            // Then set the new seat
            Seat newSeat = seatRepository.findById(ticketDTO.getSeatId())
                    .orElseThrow(() -> new RuntimeException("Seat not found with id: " + ticketDTO.getSeatId()));

            // Check if the new seat is already booked
            if (ticketRepository.findByFlightAndSeat(ticket.getFlight(), newSeat).isPresent()) {
                throw new RuntimeException("Seat " + newSeat.getSeatNumber() + " is already booked for this flight");
            }

            ticket.setSeat(newSeat);
            newSeat.setIsAvailable(false);
            seatRepository.save(newSeat);
        }

        if (ticketDTO.getTicketPrice() != null) {
            ticket.setTicketPrice(ticketDTO.getTicketPrice());
        }

        if (ticketDTO.getTicketClass() != null) {
            ticket.setTicketClass(ticketDTO.getTicketClass());
        }

        if (ticketDTO.getTicketType() != null) {
            ticket.setTicketType(ticketDTO.getTicketType());
        }

        if (ticketDTO.getLegNumber() != null) {
            ticket.setLegNumber(ticketDTO.getLegNumber());
        }

        if (ticketDTO.getRelatedTicketId() != null) {
            ticket.setRelatedTicketId(ticketDTO.getRelatedTicketId());
        }

        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));

        // Make the seat available again
        Seat seat = ticket.getSeat();
        seat.setIsAvailable(true);
        seatRepository.save(seat);

        ticketRepository.delete(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
    }

    @Override
    public Ticket getTicketByTicketNumber(String ticketNumber) {
        return ticketRepository.findByTicketNumber(ticketNumber)
                .orElseThrow(() -> new RuntimeException("Ticket not found with ticket number: " + ticketNumber));
    }

    @Override
    public List<Ticket> getTicketsByBooking(Booking booking) {
        return ticketRepository.findByBooking(booking);
    }

    @Override
    public List<Ticket> getTicketsByPassenger(Passenger passenger) {
        return ticketRepository.findByPassenger(passenger);
    }

    @Override
    public List<Ticket> getTicketsByFlight(Flight flight) {
        return ticketRepository.findByFlight(flight);
    }

    @Override
    public List<Ticket> getTicketsBySeat(Seat seat) {
        return ticketRepository.findBySeat(seat);
    }

    @Override
    public Ticket getTicketByFlightAndSeat(Flight flight, Seat seat) {
        return ticketRepository.findByFlightAndSeat(flight, seat)
                .orElseThrow(() -> new RuntimeException("Ticket not found for the specified flight and seat"));
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public void updateTicketStatus(Long id, String status) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));

        ticket.setStatus(status);

        // If the ticket is cancelled, make the seat available again
        if (status.equals("CANCELLED")) {
            Seat seat = ticket.getSeat();
            seat.setIsAvailable(true);
            seatRepository.save(seat);
        }

        ticketRepository.save(ticket);
    }
}