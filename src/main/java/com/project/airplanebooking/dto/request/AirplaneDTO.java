package com.project.airplanebooking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirplaneDTO {
    @NotBlank(message = "Model is required")
    @JsonProperty("model")
    private String model;

    @NotNull(message = "Airline ID is required")
    @JsonProperty("airline_id")
    private Long airlineId;

    @NotBlank(message = "Registration number is required")
    @JsonProperty("registration_number")
    private String registrationNumber;

    @NotNull(message = "Seat capacity is required")
    @JsonProperty("seat_capacity")
    private Integer seatCapacity;
}
