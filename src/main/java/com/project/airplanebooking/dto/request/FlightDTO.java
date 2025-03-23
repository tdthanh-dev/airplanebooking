package com.project.airplanebooking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
    @NotBlank(message = "Flight number is required")
    @JsonProperty("flight_no")
    private String flightNo;

    @NotNull(message = "Airline ID is required")
    @JsonProperty("airline_id")
    private Long airlineId;

    @NotNull(message = "Airplane ID is required")
    @JsonProperty("airplane_id")
    private Long airplaneId;

    @NotNull(message = "Departure airport ID is required")
    @JsonProperty("departure_airport_id")
    private Long departureAirportId;

    @NotNull(message = "Arrival airport ID is required")
    @JsonProperty("arrival_airport_id")
    private Long arrivalAirportId;

    @NotNull(message = "Departure time is required")
    @JsonProperty("departure_time")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time is required")
    @JsonProperty("arrival_time")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Base price is required")
    @Positive(message = "Base price must be positive")
    @JsonProperty("base_price")
    private Double basePrice;

    @JsonProperty("status")
    private String status = "SCHEDULED";
}