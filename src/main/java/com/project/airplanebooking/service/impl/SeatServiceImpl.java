package com.project.airplanebooking.service.impl;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.airplanebooking.dto.request.SeatDTO;
import com.project.airplanebooking.exception.EntityNotFoundException;
import com.project.airplanebooking.model.Airplane;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Seat;
import com.project.airplanebooking.repository.AirplaneRepository;
import com.project.airplanebooking.repository.FlightRepository;
import com.project.airplanebooking.repository.SeatRepository;
import com.project.airplanebooking.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final AirplaneRepository airplaneRepository;
    private final FlightRepository flightRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository, AirplaneRepository airplaneRepository,
            FlightRepository flightRepository) {
        this.seatRepository = seatRepository;
        this.airplaneRepository = airplaneRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public Seat createSeat(SeatDTO seatDTO) {
        Airplane airplane = airplaneRepository.findById(seatDTO.getAirplaneId())
                .orElseThrow(() -> new EntityNotFoundException(Airplane.class, seatDTO.getAirplaneId()));

        Seat seat = new Seat();
        seat.setAirplane(airplane);
        seat.setSeatNumber(seatDTO.getSeatNumber());
        seat.setSeatType(seatDTO.getSeatType());
        seat.setSeatPosition(seatDTO.getSeatPosition());
        seat.setPrice(seatDTO.getPrice());
        seat.setStatus(seatDTO.getStatus());

        return seatRepository.save(seat);
    }

    @Override
    public Seat updateSeat(Long id, SeatDTO seatDTO) {
        Seat seat = getSeatById(id);

        if (seatDTO.getAirplaneId() != null) {
            Airplane airplane = airplaneRepository.findById(seatDTO.getAirplaneId())
                    .orElseThrow(() -> new EntityNotFoundException(Airplane.class, seatDTO.getAirplaneId()));
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

        if (seatDTO.getStatus() != null) {
            seat.setStatus(seatDTO.getStatus());
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
                .orElseThrow(() -> new EntityNotFoundException(Seat.class, id));
    }

    @Override
    public List<Seat> getAllSeats() {
        List<Seat> seats = seatRepository.findAll();

        for (Seat seat : seats) {
            if (seat.getStatus().equals("HOLD")) {
                LocalDateTime holdTime = seat.getDateHold();
                if (holdTime != null) {
                    Duration holdDuration = Duration.between(holdTime, LocalDateTime.now());
                    if (holdDuration.toMinutes() > 15) {
                        seat.setStatus("AVAILABLE");
                        seatRepository.save(seat);
                    }
                }
            }
        }
        return seatRepository.findAll();
    }

    @Override
    @Transactional
    public void checkAndUpdateExpiredHoldSeats() {
        List<Seat> holdSeats = seatRepository.findByStatus("HOLD");
        LocalDateTime currentTime = LocalDateTime.now();

        for (Seat seat : holdSeats) {
            LocalDateTime holdTime = seat.getDateHold();
            if (holdTime != null) {
                Duration holdDuration = Duration.between(holdTime, currentTime);

                if (holdDuration.toMinutes() > 15) {
                    seat.setStatus("AVAILABLE");
                    seat.setDateHold(null);
                    seatRepository.save(seat);
                }
            }
        }
    }

    @Override
    public void changeSeatsToHold(List<Long> seatIds, Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new EntityNotFoundException(Flight.class, flightId));
        for (Long seatId : seatIds) {
            Seat seat = getSeatById(seatId);
            seat.setStatus("HOLD");
            seat.setDateHold(LocalDateTime.now());
            flight.setAvailableSeats(flight.getAvailableSeats() - 1);
            seatRepository.save(seat);
            flightRepository.save(flight);
        }
    }

    @Override
    public List<Seat> findSeatByAirplane(Long airplaneId) {
        Airplane airplane = airplaneRepository.findById(airplaneId)
                .orElseThrow(() -> new EntityNotFoundException(Airplane.class, airplaneId));
        return seatRepository.findByAirplane(airplane);
    }

}