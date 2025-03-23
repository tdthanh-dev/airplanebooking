package com.project.airplanebooking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("booking_id")
    private Long bookingId;

    @NotNull(message = "Flight ID is required")
    @JsonProperty("flight_id")
    private Long flightId;

    @NotNull(message = "Passenger ID is required")
    @JsonProperty("passenger_id")
    private Long passengerId;

    @NotNull(message = "Seat ID is required")
    @JsonProperty("seat_id")
    private Long seatId;

    @NotNull(message = "Ticket price is required")
    @Positive(message = "Ticket price must be positive")
    @JsonProperty("ticket_price")
    private Double ticketPrice;

    @JsonProperty("ticket_class")
    private String ticketClass;

    @JsonProperty("ticket_type")
    private String ticketType;

    @JsonProperty("leg_number")
    private Integer legNumber = 1; // Default to 1

    @JsonProperty("related_ticket_id")
    private Long relatedTicketId;
}