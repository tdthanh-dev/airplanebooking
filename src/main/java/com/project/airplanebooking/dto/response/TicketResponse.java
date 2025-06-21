package com.project.airplanebooking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.project.airplanebooking.model.Ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private Long id;
    private String ticketNumber;
    private BookingResponse booking;
    private FlightResponse flight;
    private PassengerResponse passenger;
    private SeatFlightResponse seatFlight;
    private String status;
    private BigDecimal price;
    private LocalDateTime issueDate;
    private LocalDateTime cancelDate;
    private String barcode;
    private String ticketType;
    private String ticketClass;
    private Integer legNumber;
    private Long relatedTicketId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TicketResponse(Ticket ticket) {
        this.id = ticket.getId();
        this.ticketNumber = ticket.getTicketNumber();
        this.booking = new BookingResponse(ticket.getBooking());
        this.flight = new FlightResponse(ticket.getFlight());
        this.passenger = new PassengerResponse(ticket.getPassenger());
        this.seatFlight = new SeatFlightResponse(ticket.getSeatFlight());
        this.status = ticket.getStatus();
        this.price = BigDecimal.valueOf(ticket.getTicketPrice());
        this.issueDate = ticket.getCreatedAt();
        this.cancelDate = null;
        this.barcode = ticket.getTicketNumber();
        this.ticketType = ticket.getTicketType();
        this.ticketClass = ticket.getTicketClass();
        this.legNumber = ticket.getLegNumber();
        this.relatedTicketId = ticket.getRelatedTicketId();
        this.createdAt = ticket.getCreatedAt();
        this.updatedAt = ticket.getUpdatedAt();
    }
}