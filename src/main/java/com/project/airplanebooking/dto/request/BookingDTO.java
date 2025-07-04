package com.project.airplanebooking.dto.request;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookingDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Passenger count is required")
    @Min(value = 1, message = "At least one passenger is required")
    private Integer passengerCount;

    @Size(max = 20, message = "Status must be at most 20 characters")
    private String status = "PENDING";

    @Size(max = 20, message = "Trip type must be at most 20 characters")
    private String tripType = "ONE_WAY";

    private List<PassengerDTO> passengers;

    @NotNull(message = "Seat flight IDs are required")
    private List<Long> seatFlightIds;

    @NotNull(message = "Flight IDs are required")
    private List<Long> flightIds;

    private LocalDate bookingDate;

    @Size(max = 50, message = "Booking source must be at most 50 characters")
    private String bookingSource;

    @Size(max = 20, message = "Promotion code must be at most 20 characters")
    private String promotionCode;

    @Size(max = 255, message = "Cancellation reason must be at most 255 characters")
    private String cancellationReason;

    @Size(max = 1000, message = "Note must be at most 1000 characters")
    private String note;
}