package com.project.airplanebooking.service;

import java.util.List;

import com.project.airplanebooking.dto.request.SeatDTO;
import com.project.airplanebooking.model.Airplane;
import com.project.airplanebooking.model.Seat;

public interface SeatService {
    Seat createSeat(SeatDTO seatDTO);

    Seat updateSeat(Long id, SeatDTO seatDTO);

    void deleteSeat(Long id);

    Seat getSeatById(Long id);

    List<Seat> getAllSeats();

    List<Seat> getSeatsByAirplane(Airplane airplane);

    List<Seat> getAvailableSeatsByAirplane(Airplane airplane);

    Seat getSeatBySeatNumber(String seatNumber, Airplane airplane);

    void updateSeatAvailability(Long id, boolean isAvailable);
}