package com.project.airplanebooking.dto.simpleresponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatFlightSimpleResponse {
    private Long id;
    private Long flightId;
    private String flightNo;
    private String seatNumber;
    private String seatType;
    private String status;
}