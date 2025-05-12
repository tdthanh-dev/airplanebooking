package com.project.airplanebooking.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    @NotNull(message = "Booking ID is required")
    private Long bookingId;

    @NotNull(message = "Flight ID is required")
    private Long flightId;

    @NotNull(message = "Passenger ID is required")
    private Long passengerId;

    @NotNull(message = "Seat ID is required")
    private Long seatId;

    @NotNull(message = "Ticket price is required")
    @Positive(message = "Ticket price must be positive")
    private Double ticketPrice;

    @NotNull(message = "Ticket type is required")
    private String ticketType;

    @NotNull(message = "Ticket class is required")
    private String ticketClass;

    @NotNull(message = "Leg number is required")
    private Integer legNumber = 1; // Default to 1

    private Long relatedTicketId;

    private String status = "CONFIRMED";
}