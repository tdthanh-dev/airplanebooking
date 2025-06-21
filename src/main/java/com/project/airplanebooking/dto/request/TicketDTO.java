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
    @NotNull(message = "Seat Flight ID is required")
    private Long seatFlightId;

    // Optional - sẽ tự động lấy từ SeatFlight nếu không có
    @Positive(message = "Ticket price must be positive")
    private Double ticketPrice;

    // Optional - sẽ tự động lấy từ Booking nếu không có
    private String ticketType;

    // Optional - sẽ tự động lấy từ SeatFlight nếu không có
    private String ticketClass;

    // Optional - sẽ tự động tính toán nếu không có
    private Integer legNumber;

    private Long relatedTicketId;

    private String status = "ACTIVE";
}