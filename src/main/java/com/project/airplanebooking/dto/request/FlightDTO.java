package com.project.airplanebooking.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class FlightDTO {
    @NotNull(message = "Flight number is required")
    @Size(min = 2, max = 10, message = "Flight number must be between 2 and 10 characters")
    private String flightNo;

    @NotNull(message = "Airline code is required")
    private String airlineCode;

    @NotNull(message = "Airplane id is required")
    private Long airplaneId;

    @NotNull(message = "Departure airport code is required")
    private String departureAirportCode;

    @NotNull(message = "Arrival airport code is required")
    private String arrivalAirportCode;

    @NotNull(message = "Departure time is required")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time is required")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Base fare is required")
    @Min(value = 0, message = "Base fare must be greater than or equal to 0")
    private Double baseFare;

    private Double currentPrice;

    private Boolean isFull;

    private Integer delayMinutes;

    private Integer availableSeats;
    @Size(max = 20, message = "Status must be at most 20 characters")
    private String status;

    @Size(max = 20, message = "Flight type must be at most 20 characters")
    private String flightType;

    private Integer durationMinutes;
}