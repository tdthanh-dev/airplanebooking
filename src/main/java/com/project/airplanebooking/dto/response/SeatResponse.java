package com.project.airplanebooking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.project.airplanebooking.model.Seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponse {
    private Long id;
    private String seatNumber;
    private AirplaneResponse airplane;
    private String seatClass;
    private String status;
    private BigDecimal extraPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SeatResponse(Seat seat) {
        this.id = seat.getId();
        this.seatNumber = seat.getSeatNumber();
        this.airplane = new AirplaneResponse(seat.getAirplane());
        this.seatClass = seat.getSeatType();
        this.status = seat.getIsAvailable() ? "AVAILABLE" : "OCCUPIED";
        this.extraPrice = BigDecimal.valueOf(seat.getPrice());
        this.createdAt = seat.getCreatedAt();
        this.updatedAt = seat.getUpdatedAt();
    }
}
