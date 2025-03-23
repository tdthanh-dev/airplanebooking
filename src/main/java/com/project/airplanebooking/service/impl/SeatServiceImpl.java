package com.project.airplanebooking.service.impl;

import com.project.airplanebooking.dto.request.SeatDTO;
import com.project.airplanebooking.model.Seat;
import com.project.airplanebooking.model.Airplane;
import com.project.airplanebooking.repository.SeatRepository;
import com.project.airplanebooking.repository.AirplaneRepository;
import com.project.airplanebooking.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final AirplaneRepository airplaneRepository;

    public SeatServiceImpl(SeatRepository seatRepository, AirplaneRepository airplaneRepository) {
        this.seatRepository = seatRepository;
        this.airplaneRepository = airplaneRepository;
    }

    @Override
    public Seat createSeat(SeatDTO seatDTO) {
        Airplane airplane = airplaneRepository.findById(seatDTO.getAirplaneId())
                .orElseThrow(() -> new RuntimeException("Airplane not found with id: " + seatDTO.getAirplaneId()));

        Seat seat = new Seat();
        seat.setSeatNumber(seatDTO.getSeatNumber());
        seat.setAirplane(airplane);
        seat.setSeatType(seatDTO.getSeatType());
        seat.setSeatPosition(seatDTO.getSeatPosition());
        seat.setPrice(seatDTO.getPrice());
        seat.setIsAvailable(true); // By default, the seat is available

        return seatRepository.save(seat);
    }

    @Override
    public Seat updateSeat(Long id, SeatDTO seatDTO) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + id));

        if (seatDTO.getAirplaneId() != null) {
            Airplane airplane = airplaneRepository.findById(seatDTO.getAirplaneId())
                    .orElseThrow(() -> new RuntimeException("Airplane not found with id: " + seatDTO.getAirplaneId()));
            seat.setAirplane(airplane);
        }

        seat.setSeatNumber(seatDTO.getSeatNumber());
        seat.setSeatType(seatDTO.getSeatType());
        seat.setSeatPosition(seatDTO.getSeatPosition());
        seat.setPrice(seatDTO.getPrice());
        seat.setIsAvailable(seatDTO.getIsAvailable());

        return seatRepository.save(seat);
    }

    @Override
    public void deleteSeat(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + id));

        seatRepository.delete(seat);
    }

    @Override
    public Seat getSeatById(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + id));
    }

    @Override
    public Seat getSeatByAirplaneAndSeatNumber(Airplane airplane, String seatNumber) {
        return seatRepository.findByAirplaneAndSeatNumber(airplane, seatNumber)
                .orElseThrow(() -> new RuntimeException("Seat not found with airplane and seat number: " + seatNumber));
    }

    @Override
    public List<Seat> getSeatsByAirplane(Airplane airplane) {
        return seatRepository.findByAirplane(airplane);
    }

    @Override
    public List<Seat> getSeatsByAirplaneAndSeatClass(Airplane airplane, String seatClass) {
        return seatRepository.findByAirplaneAndSeatClass(airplane, seatClass);
    }

    @Override
    public List<Seat> getSeatsBySeatClass(String seatClass) {
        return seatRepository.findBySeatClass(seatClass);
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public void updateSeatAvailability(Long id, boolean isAvailable) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + id));

        seat.setIsAvailable(isAvailable);
        seatRepository.save(seat);
    }
}