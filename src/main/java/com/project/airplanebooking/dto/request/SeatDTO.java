package com.project.airplanebooking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {

    @NotNull(message = "Airplane ID is required")
    @JsonProperty("airplane_id")
    private Long airplaneId;

    @NotBlank(message = "Seat number is required")
    @JsonProperty("seat_number")
    private String seatNumber;

    @NotBlank(message = "Seat type is required")
    @JsonProperty("seat_type")
    private String seatType;

    @NotBlank(message = "Seat position is required")
    @JsonProperty("seat_position")
    private String seatPosition;

    @NotNull(message = "Seat price is required")
    @Positive(message = "Seat price must be positive")
    @JsonProperty("price")
    private Double price;

    @JsonProperty("is_available")
    private Boolean isAvailable = true;

}