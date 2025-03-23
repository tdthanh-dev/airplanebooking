package com.project.airplanebooking.service;

import com.project.airplanebooking.dto.request.SeatDTO;
import com.project.airplanebooking.model.Seat;
import com.project.airplanebooking.model.Airplane;

import java.util.List;

public interface SeatService {
    Seat createSeat(SeatDTO seatDTO);

    Seat updateSeat(Long id, SeatDTO seatDTO);

    void deleteSeat(Long id);

    Seat getSeatById(Long id);

    Seat getSeatByAirplaneAndSeatNumber(Airplane airplane, String seatNumber);

    List<Seat> getSeatsByAirplane(Airplane airplane);

    List<Seat> getSeatsByAirplaneAndSeatClass(Airplane airplane, String seatClass);

    List<Seat> getSeatsBySeatClass(String seatClass);

    List<Seat> getAllSeats();

    void updateSeatAvailability(Long id, boolean isAvailable);
}