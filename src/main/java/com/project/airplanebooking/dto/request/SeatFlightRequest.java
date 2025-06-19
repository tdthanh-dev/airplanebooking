package com.project.airplanebooking.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatFlightRequest {
    private Long id;

    @NotNull(message = "ID chuyến bay không được để trống")
    private Long flightId;

    @NotBlank(message = "Số ghế không được để trống")
    private String seatNumber;

    @NotBlank(message = "Loại ghế không được để trống")
    private String seatType;

    @NotBlank(message = "Vị trí ghế không được để trống")
    private String seatPosition;

    @NotNull(message = "Giá không được để trống")
    @Positive(message = "Giá phải là số dương")
    private Double price;

    private LocalDateTime dateHold;

    @NotBlank(message = "Trạng thái không được để trống")
    private String status;

}