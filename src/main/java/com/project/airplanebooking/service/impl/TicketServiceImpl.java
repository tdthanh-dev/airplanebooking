package com.project.airplanebooking.service.impl;

import java.time.LocalDateTime;
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
import com.project.airplanebooking.model.Seat;
import com.project.airplanebooking.model.Ticket;
import com.project.airplanebooking.repository.BookingRepository;
import com.project.airplanebooking.repository.FlightRepository;
import com.project.airplanebooking.repository.PassengerRepository;
import com.project.airplanebooking.repository.SeatRepository;
import com.project.airplanebooking.repository.TicketRepository;
import com.project.airplanebooking.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public TicketServiceImpl(
            TicketRepository ticketRepository,
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
                .orElseThrow(() -> new EntityNotFoundException(Booking.class, ticketDTO.getBookingId()));

        Flight flight = flightRepository.findById(ticketDTO.getFlightId())
                .orElseThrow(() -> new EntityNotFoundException(Flight.class, ticketDTO.getFlightId()));

        Passenger passenger = passengerRepository.findById(ticketDTO.getPassengerId())
                .orElseThrow(() -> new EntityNotFoundException(Passenger.class, ticketDTO.getPassengerId()));

        Seat seat = seatRepository.findById(ticketDTO.getSeatId())
                .orElseThrow(() -> new EntityNotFoundException(Seat.class, ticketDTO.getSeatId()));

        if (!"AVAILABLE".equals(seat.getStatus())) {
            throw new BusinessLogicException("Ghế đã có người đặt");
        }

        Ticket ticket = new Ticket();
        ticket.setTicketNumber(generateTicketNumber());
        ticket.setBooking(booking);
        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        ticket.setSeat(seat);
        ticket.setTicketPrice(ticketDTO.getTicketPrice());
        ticket.setTicketClass(ticketDTO.getTicketClass());
        ticket.setTicketType(ticketDTO.getTicketType());
        ticket.setLegNumber(ticketDTO.getLegNumber());
        ticket.setRelatedTicketId(ticketDTO.getRelatedTicketId());
        ticket.setStatus(ticketDTO.getStatus());

        seat.setStatus("BOOKED");
        seatRepository.save(seat);

        return ticketRepository.save(ticket);
    }

    private String generateTicketNumber() {
        return "TICKET-" + UUID.randomUUID().toString().substring(0, 10).toUpperCase();
    }

    @Override
    public Ticket updateTicket(Long id, TicketDTO ticketDTO) {
        Ticket ticket = getTicketById(id);

        if (ticketDTO.getBookingId() != null) {
            Booking booking = bookingRepository.findById(ticketDTO.getBookingId())
                    .orElseThrow(() -> new EntityNotFoundException(Booking.class, ticketDTO.getBookingId()));
            ticket.setBooking(booking);
        }

        if (ticketDTO.getFlightId() != null) {
            Flight flight = flightRepository.findById(ticketDTO.getFlightId())
                    .orElseThrow(() -> new EntityNotFoundException(Flight.class, ticketDTO.getFlightId()));
            ticket.setFlight(flight);
        }

        if (ticketDTO.getPassengerId() != null) {
            Passenger passenger = passengerRepository.findById(ticketDTO.getPassengerId())
                    .orElseThrow(() -> new EntityNotFoundException(Passenger.class, ticketDTO.getPassengerId()));
            ticket.setPassenger(passenger);
        }

        if (ticketDTO.getSeatId() != null) {
            // Make the current seat available
            Seat currentSeat = ticket.getSeat();
            currentSeat.setStatus("AVAILABLE");
            seatRepository.save(currentSeat);

            // Set the new seat
            Seat newSeat = seatRepository.findById(ticketDTO.getSeatId())
                    .orElseThrow(() -> new EntityNotFoundException(Seat.class, ticketDTO.getSeatId()));

            // Check if new seat is available
            if (!"AVAILABLE".equals(newSeat.getStatus())) {
                throw new BusinessLogicException("Ghế không khả dụng");
            }

            ticket.setSeat(newSeat);

            // Update new seat status
            newSeat.setStatus("BOOKED");
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

        if (ticketDTO.getStatus() != null) {
            ticket.setStatus(ticketDTO.getStatus());
        }

        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket ticket = getTicketById(id);

        // Make the seat available again
        Seat seat = ticket.getSeat();
        seat.setStatus("AVAILABLE");
        seatRepository.save(seat);

        ticketRepository.delete(ticket);
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
}