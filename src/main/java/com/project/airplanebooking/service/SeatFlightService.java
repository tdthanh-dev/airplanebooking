package com.project.airplanebooking.service;

import java.util.List;

import com.project.airplanebooking.dto.request.SeatFlightRequest;
import com.project.airplanebooking.dto.response.SeatFlightResponse;

public interface SeatFlightService {
    SeatFlightResponse createSeatFlight(SeatFlightRequest seatFlightRequest);

    SeatFlightResponse updateSeatFlight(Long id, SeatFlightRequest seatFlightRequest);

    void deleteSeatFlight(Long id);

    SeatFlightResponse getSeatFlightById(Long id);

    List<SeatFlightResponse> getSeatFlightsByFlightId(Long flightId);

    List<SeatFlightResponse> generateSeatsForFlight(Long flightId);

    void changeSeatsToHold(List<Long> seatIds, Long flightId);

    int updateFlightAvailableSeats(Long flightId);
}