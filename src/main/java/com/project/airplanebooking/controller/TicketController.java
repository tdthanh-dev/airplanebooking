package com.project.airplanebooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.airplanebooking.dto.request.TicketDTO;
import com.project.airplanebooking.dto.response.TicketResponse;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.model.Ticket;
import com.project.airplanebooking.service.impl.BookingServiceImpl;
import com.project.airplanebooking.service.impl.FlightServiceImpl;
import com.project.airplanebooking.service.impl.PassengerServiceImpl;
import com.project.airplanebooking.service.impl.TicketServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketServiceImpl;

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @Autowired
    private PassengerServiceImpl passengerServiceImpl;

    @Autowired
    private FlightServiceImpl flightServiceImpl;

    @PostMapping("/")
    public ResponseEntity<?> createTicket(@Valid @RequestBody TicketDTO ticketDTO) {
        try {
            Ticket ticket = ticketServiceImpl.createTicket(ticketDTO);
            return ResponseEntity.ok(new TicketResponse(ticket));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllTickets() {
        try {
            List<Ticket> tickets = ticketServiceImpl.getAllTickets();
            List<TicketResponse> responseList = tickets.stream()
                    .map(TicketResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable Long id) {
        try {
            Ticket ticket = ticketServiceImpl.getTicketById(id);
            return ResponseEntity.ok(new TicketResponse(ticket));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getTicketsByBooking(@PathVariable Long bookingId) {
        try {
            Booking booking = bookingServiceImpl.getBookingById(bookingId);
            List<Ticket> tickets = ticketServiceImpl.getTicketsByBooking(booking);
            List<TicketResponse> responseList = tickets.stream()
                    .map(TicketResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/passenger/{passengerId}")
    public ResponseEntity<?> getTicketsByPassenger(@PathVariable Long passengerId) {
        try {
            Passenger passenger = passengerServiceImpl.getPassengerById(passengerId);
            List<Ticket> tickets = ticketServiceImpl.getTicketsByPassenger(passenger);
            List<TicketResponse> responseList = tickets.stream()
                    .map(TicketResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<?> getTicketsByFlight(@PathVariable Long flightId) {
        try {
            Flight flight = flightServiceImpl.getFlightById(flightId);
            List<Ticket> tickets = ticketServiceImpl.getTicketsByFlight(flight);
            List<TicketResponse> responseList = tickets.stream()
                    .map(TicketResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable Long id, @Valid @RequestBody TicketDTO ticketDTO) {
        try {
            Ticket ticket = ticketServiceImpl.updateTicket(id, ticketDTO);
            return ResponseEntity.ok(new TicketResponse(ticket));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Long id) {
        try {
            ticketServiceImpl.deleteTicket(id);
            return ResponseEntity.ok("Ticket deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/number/{ticketNumber}")
    public ResponseEntity<?> getTicketByNumber(@PathVariable String ticketNumber) {
        try {
            Ticket ticket = ticketServiceImpl.getTicketByTicketNumber(ticketNumber);
            return ResponseEntity.ok(new TicketResponse(ticket));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<?> cancelTicket(@PathVariable Long id) {
        try {
            ticketServiceImpl.cancelTicket(id);
            return ResponseEntity.ok("Ticket cancelled successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}