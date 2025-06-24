package com.project.airplanebooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.airplanebooking.dto.request.TicketDTO;
import com.project.airplanebooking.dto.response.TicketResponse;
import com.project.airplanebooking.model.Ticket;
import com.project.airplanebooking.service.impl.TicketServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/tickets")
public class TicketController {

    private final TicketServiceImpl ticketServiceImpl;

    @Autowired
    public TicketController(TicketServiceImpl ticketServiceImpl) {
        this.ticketServiceImpl = ticketServiceImpl;
    }

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

    @PostMapping("/booking/{bookingId}/generate")
    public ResponseEntity<?> generateTicket(@PathVariable Long bookingId) {
        try {
            List<Ticket> tickets = ticketServiceImpl.generateTicket(bookingId);
            List<TicketResponse> responseList = tickets.stream()
                    .map(TicketResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/booking/{bookingId}/generate-all")
    public ResponseEntity<?> generateAllTickets(@PathVariable Long bookingId) {
        try {
            List<Ticket> tickets = ticketServiceImpl.generateAllTicketsForBooking(bookingId);
            List<TicketResponse> responseList = tickets.stream()
                    .map(TicketResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getTicketsByBookingId(@PathVariable Long bookingId) {
        try {
            List<Ticket> tickets = ticketServiceImpl.getTicketsByBookingId(bookingId);
            List<TicketResponse> responseList = tickets.stream()
                    .map(TicketResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}