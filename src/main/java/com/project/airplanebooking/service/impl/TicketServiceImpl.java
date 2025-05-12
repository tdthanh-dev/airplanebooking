package com.project.airplanebooking.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.airplanebooking.dto.request.TicketDTO;
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

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Ticket createTicket(TicketDTO ticketDTO) {
        Booking booking = bookingRepository.findById(ticketDTO.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Flight flight = flightRepository.findById(ticketDTO.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        Passenger passenger = passengerRepository.findById(ticketDTO.getPassengerId())
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        Seat seat = seatRepository.findById(ticketDTO.getSeatId())
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        // Check if seat is available
        if (!seat.getIsAvailable()) {
            throw new RuntimeException("Seat is not available");
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

        // Update seat availability
        seat.setIsAvailable(false);
        seatRepository.save(seat);

        return ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public Ticket createTicket(Booking booking, Passenger passenger, String seatNumber) {
        // Lấy Flight từ Booking
        Flight flight = booking.getFlight();

        // Tìm Seat dựa trên số ghế
        Seat seat = seatRepository.findBySeatNumberAndFlight(seatNumber, flight)
                .orElseThrow(() -> new RuntimeException("Seat not found: " + seatNumber));

        // Check if seat is available
        if (!seat.getIsAvailable()) {
            throw new RuntimeException("Seat is not available");
        }

        // Tạo ticket mới
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(generateTicketNumber());
        ticket.setBooking(booking);
        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        ticket.setSeat(seat);
        ticket.setTicketPrice(flight.getCurrentPrice()); // Giá mặc định từ chuyến bay
        ticket.setTicketClass("ECONOMY"); // Mặc định là Economy
        ticket.setTicketType("REGULAR"); // Mặc định là Regular
        ticket.setLegNumber(1); // Mặc định là chặng đầu tiên
        ticket.setStatus("CONFIRMED");

        // Update seat availability
        seat.setIsAvailable(false);
        seatRepository.save(seat);

        return ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public List<Ticket> generateTicketsForBooking(Long bookingId) {
        // Lấy booking từ ID
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Lấy danh sách hành khách
        List<Passenger> passengers = passengerRepository.findByBooking(booking);

        // Tạo vé cho từng hành khách
        List<Ticket> tickets = new ArrayList<>();

        // Lấy danh sách ghế trống
        List<Seat> availableSeats = seatRepository.findByFlightAndIsAvailable(booking.getFlight(), true);

        if (availableSeats.size() < passengers.size()) {
            throw new RuntimeException("Not enough available seats for all passengers");
        }

        for (int i = 0; i < passengers.size(); i++) {
            Passenger passenger = passengers.get(i);
            Seat seat = availableSeats.get(i);

            Ticket ticket = new Ticket();
            ticket.setTicketNumber(generateTicketNumber());
            ticket.setBooking(booking);
            ticket.setFlight(booking.getFlight());
            ticket.setPassenger(passenger);
            ticket.setSeat(seat);
            ticket.setTicketPrice(booking.getFlight().getCurrentPrice());
            ticket.setTicketClass("ECONOMY");
            ticket.setTicketType("REGULAR");
            ticket.setLegNumber(1);
            ticket.setStatus("CONFIRMED");

            // Update seat availability
            seat.setIsAvailable(false);
            seatRepository.save(seat);

            tickets.add(ticketRepository.save(ticket));
        }

        return tickets;
    }

    @Override
    public Ticket updateTicket(Long id, TicketDTO ticketDTO) {
        Ticket ticket = getTicketById(id);

        if (ticketDTO.getBookingId() != null) {
            Booking booking = bookingRepository.findById(ticketDTO.getBookingId())
                    .orElseThrow(() -> new RuntimeException("Booking not found"));
            ticket.setBooking(booking);
        }

        if (ticketDTO.getFlightId() != null) {
            Flight flight = flightRepository.findById(ticketDTO.getFlightId())
                    .orElseThrow(() -> new RuntimeException("Flight not found"));
            ticket.setFlight(flight);
        }

        if (ticketDTO.getPassengerId() != null) {
            Passenger passenger = passengerRepository.findById(ticketDTO.getPassengerId())
                    .orElseThrow(() -> new RuntimeException("Passenger not found"));
            ticket.setPassenger(passenger);
        }

        if (ticketDTO.getSeatId() != null) {
            // Make the current seat available
            ticket.getSeat().setIsAvailable(true);
            seatRepository.save(ticket.getSeat());

            // Set the new seat
            Seat seat = seatRepository.findById(ticketDTO.getSeatId())
                    .orElseThrow(() -> new RuntimeException("Seat not found"));

            // Check if new seat is available
            if (!seat.getIsAvailable()) {
                throw new RuntimeException("Seat is not available");
            }

            ticket.setSeat(seat);

            // Update new seat availability
            seat.setIsAvailable(false);
            seatRepository.save(seat);
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
    @Transactional
    public Ticket updateTicketStatus(Long id, String status) {
        Ticket ticket = getTicketById(id);
        ticket.setStatus(status);
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket ticket = getTicketById(id);

        // Make the seat available again
        Seat seat = ticket.getSeat();
        seat.setIsAvailable(true);
        seatRepository.save(seat);

        ticketRepository.delete(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getTicketsByBooking(Booking booking) {
        return ticketRepository.findByBooking(booking);
    }

    @Override
    public List<Ticket> getTicketsByBookingId(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return ticketRepository.findByBooking(booking);
    }

    @Override
    public List<Ticket> getTicketsByFlight(Flight flight) {
        return ticketRepository.findByFlight(flight);
    }

    @Override
    public List<Ticket> getTicketsByPassenger(Passenger passenger) {
        return ticketRepository.findByPassenger(passenger);
    }

    @Override
    public void cancelTicket(Long id) {
        Ticket ticket = getTicketById(id);
        ticket.setStatus("CANCELLED");

        // Make the seat available again
        Seat seat = ticket.getSeat();
        seat.setIsAvailable(true);
        seatRepository.save(seat);

        ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public boolean cancelTicketWithResult(Long id) {
        try {
            cancelTicket(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Ticket getTicketByTicketNumber(String ticketNumber) {
        return ticketRepository.findByTicketNumber(ticketNumber)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    private String generateTicketNumber() {
        return "T" + UUID.randomUUID().toString().substring(0, 10).toUpperCase();
    }
}