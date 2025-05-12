package com.project.airplanebooking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SeatDTO {

    @NotNull(message = "Airplane ID is required")
    private Long airplaneId;

    @NotBlank(message = "Seat number is required")
    private String seatNumber;

    @NotBlank(message = "Seat type is required")
    private String seatType;

    @NotBlank(message = "Seat position is required")
    private String seatPosition;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    private Boolean isAvailable = true;
}