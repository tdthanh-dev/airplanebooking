package com.project.airplanebooking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.airplanebooking.dto.request.SeatDTO;
import com.project.airplanebooking.model.Airplane;
import com.project.airplanebooking.model.Seat;
import com.project.airplanebooking.repository.AirplaneRepository;
import com.project.airplanebooking.repository.SeatRepository;
import com.project.airplanebooking.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Override
    public Seat createSeat(SeatDTO seatDTO) {
        Airplane airplane = airplaneRepository.findById(seatDTO.getAirplaneId())
                .orElseThrow(() -> new RuntimeException("Airplane not found"));

        Seat seat = new Seat();
        seat.setAirplane(airplane);
        seat.setSeatNumber(seatDTO.getSeatNumber());
        seat.setSeatType(seatDTO.getSeatType());
        seat.setSeatPosition(seatDTO.getSeatPosition());
        seat.setPrice(seatDTO.getPrice());
        seat.setIsAvailable(seatDTO.getIsAvailable());

        return seatRepository.save(seat);
    }

    @Override
    public Seat updateSeat(Long id, SeatDTO seatDTO) {
        Seat seat = getSeatById(id);

        if (seatDTO.getAirplaneId() != null) {
            Airplane airplane = airplaneRepository.findById(seatDTO.getAirplaneId())
                    .orElseThrow(() -> new RuntimeException("Airplane not found"));
            seat.setAirplane(airplane);
        }

        if (seatDTO.getSeatNumber() != null) {
            seat.setSeatNumber(seatDTO.getSeatNumber());
        }

        if (seatDTO.getSeatType() != null) {
            seat.setSeatType(seatDTO.getSeatType());
        }

        if (seatDTO.getSeatPosition() != null) {
            seat.setSeatPosition(seatDTO.getSeatPosition());
        }

        if (seatDTO.getPrice() != null) {
            seat.setPrice(seatDTO.getPrice());
        }

        if (seatDTO.getIsAvailable() != null) {
            seat.setIsAvailable(seatDTO.getIsAvailable());
        }

        return seatRepository.save(seat);
    }

    @Override
    public void deleteSeat(Long id) {
        Seat seat = getSeatById(id);
        seatRepository.delete(seat);
    }

    @Override
    public Seat getSeatById(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public List<Seat> getSeatsByAirplane(Airplane airplane) {
        return seatRepository.findByAirplane(airplane);
    }

    @Override
    public List<Seat> getAvailableSeatsByAirplane(Airplane airplane) {
        return seatRepository.findByAirplaneAndIsAvailable(airplane, true);
    }

    @Override
    public Seat getSeatBySeatNumber(String seatNumber, Airplane airplane) {
        return seatRepository.findByAirplaneAndSeatNumber(airplane, seatNumber)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
    }

    @Override
    public void updateSeatAvailability(Long id, boolean isAvailable) {
        Seat seat = getSeatById(id);
        seat.setIsAvailable(isAvailable);
        seatRepository.save(seat);
    }
}