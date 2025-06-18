package com.project.airplanebooking.service;

import java.util.List;

import com.project.airplanebooking.dto.request.SeatDTO;
import com.project.airplanebooking.model.Seat;

public interface SeatService {
    Seat createSeat(SeatDTO seatDTO);

    Seat updateSeat(Long id, SeatDTO seatDTO);

    void deleteSeat(Long id);

    Seat getSeatById(Long id);

    List<Seat> getAllSeats();

    void checkAndUpdateExpiredHoldSeats();

    void changeSeatsToHold(List<Long> seatIds, Long flightId);

    List<Seat> findSeatByAirplane(Long airplaneId);
}