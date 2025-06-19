package com.project.airplanebooking.dto.response;

import java.time.LocalDateTime;

import com.project.airplanebooking.model.SeatFlight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatFlightResponse {
    private Long id;
    private Long flightId;
    private String flightNo;
    private String seatNumber;
    private String seatType;
    private String seatPosition;
    private Double price;
    private LocalDateTime dateHold;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SeatFlightResponse(SeatFlight seatFlight) {
        this.id = seatFlight.getId();
        this.flightId = seatFlight.getFlight().getId();
        this.flightNo = seatFlight.getFlight().getFlightNo();
        this.seatNumber = seatFlight.getSeatNumber();
        this.seatType = seatFlight.getSeatType();
        this.seatPosition = seatFlight.getSeatPosition();
        this.status = seatFlight.getStatus();
        this.price = seatFlight.getPrice();
        this.dateHold = seatFlight.getDateHold();
        this.createdAt = seatFlight.getCreatedAt();
        this.updatedAt = seatFlight.getUpdatedAt();
    }
}