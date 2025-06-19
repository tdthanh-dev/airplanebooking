package com.project.airplanebooking.service.impl;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.airplanebooking.dto.request.SeatFlightRequest;
import com.project.airplanebooking.dto.response.SeatFlightResponse;
import com.project.airplanebooking.exception.ResourceNotFoundException;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Seat;
import com.project.airplanebooking.model.SeatFlight;
import com.project.airplanebooking.repository.FlightRepository;
import com.project.airplanebooking.repository.SeatFlightRepository;
import com.project.airplanebooking.repository.SeatRepository;
import com.project.airplanebooking.service.SeatFlightService;

@Service
public class SeatFlightServiceImpl implements SeatFlightService {

    @Autowired
    private SeatFlightRepository seatFlightRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    @Transactional
    public SeatFlightResponse createSeatFlight(SeatFlightRequest request) {
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + request.getFlightId()));

        SeatFlight seatFlight = new SeatFlight();
        seatFlight.setFlight(flight);
        seatFlight.setSeatNumber(request.getSeatNumber());
        seatFlight.setSeatType(request.getSeatType());
        seatFlight.setSeatPosition(request.getSeatPosition());
        seatFlight.setPrice(request.getPrice());
        seatFlight.setDateHold(request.getDateHold());
        seatFlight.setStatus(request.getStatus());

        SeatFlight savedSeatFlight = seatFlightRepository.save(seatFlight);
        return mapToResponse(savedSeatFlight);
    }

    @Override
    @Transactional
    public SeatFlightResponse updateSeatFlight(Long id, SeatFlightRequest request) {
        SeatFlight seatFlight = seatFlightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SeatFlight not found with id: " + id));

        if (request.getFlightId() != null) {
            Flight flight = flightRepository.findById(request.getFlightId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Flight not found with id: " + request.getFlightId()));
            seatFlight.setFlight(flight);
        }

        if (request.getSeatNumber() != null) {
            seatFlight.setSeatNumber(request.getSeatNumber());
        }

        if (request.getSeatType() != null) {
            seatFlight.setSeatType(request.getSeatType());
        }

        if (request.getSeatPosition() != null) {
            seatFlight.setSeatPosition(request.getSeatPosition());
        }

        if (request.getPrice() != null) {
            seatFlight.setPrice(request.getPrice());
        }

        seatFlight.setDateHold(request.getDateHold());

        if (request.getStatus() != null) {
            seatFlight.setStatus(request.getStatus());
        }

        SeatFlight updatedSeatFlight = seatFlightRepository.save(seatFlight);
        return mapToResponse(updatedSeatFlight);
    }

    @Override
    @Transactional
    public void deleteSeatFlight(Long id) {
        if (!seatFlightRepository.existsById(id)) {
            throw new ResourceNotFoundException("SeatFlight not found with id: " + id);
        }
        seatFlightRepository.deleteById(id);
    }

    @Override
    public SeatFlightResponse getSeatFlightById(Long id) {
        SeatFlight seatFlight = seatFlightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SeatFlight not found with id: " + id));
        return mapToResponse(seatFlight);
    }

    /**
     * Phương thức tiện ích để cập nhật số ghế trống của một chuyến bay
     * 
     * @param flightId ID của chuyến bay cần cập nhật
     * @return Số ghế trống hiện tại
     */
    @Transactional
    public int updateFlightAvailableSeats(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + flightId));

        List<SeatFlight> seatFlights = seatFlightRepository.findByFlightId(flightId);
        int availableSeatsCount = 0;

        for (SeatFlight seatFlight : seatFlights) {
            // Kiểm tra và giải phóng các ghế HOLD quá 15 phút
            if ("HOLD".equals(seatFlight.getStatus())) {
                LocalDateTime holdTime = seatFlight.getDateHold();
                if (holdTime != null) {
                    Duration holdDuration = Duration.between(holdTime, LocalDateTime.now());
                    if (holdDuration.toMinutes() > 15) {
                        seatFlight.setStatus("AVAILABLE");
                        seatFlightRepository.save(seatFlight);
                    }
                }
            }

            // Đếm số ghế có sẵn
            if ("AVAILABLE".equals(seatFlight.getStatus())) {
                availableSeatsCount++;
            }
        }

        flight.setAvailableSeats(availableSeatsCount);
        flightRepository.save(flight);

        return availableSeatsCount;
    }

    @Override
    @Transactional
    public List<SeatFlightResponse> getSeatFlightsByFlightId(Long flightId) {
        updateFlightAvailableSeats(flightId);

        return seatFlightRepository.findByFlightId(flightId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void changeSeatsToHold(List<Long> seatIds, Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + flightId));

        for (Long seatId : seatIds) {
            SeatFlight seatFlight = seatFlightRepository.findById(seatId)
                    .orElseThrow(() -> new ResourceNotFoundException("SeatFlight not found with id: " + seatId));

            if (!seatFlight.getFlight().getId().equals(flightId)) {
                throw new IllegalArgumentException(
                        "SeatFlight với ID " + seatId + " không thuộc chuyến bay " + flightId);
            }

            if ("AVAILABLE".equals(seatFlight.getStatus())) {
                seatFlight.setStatus("HOLD");
                seatFlight.setDateHold(LocalDateTime.now());

                flight.setAvailableSeats(flight.getAvailableSeats() - 1);

                seatFlightRepository.save(seatFlight);
            } else {
                throw new IllegalStateException("SeatFlight với ID " + seatId + " không có sẵn để giữ chỗ");
            }
        }

        flightRepository.save(flight);
    }

    @Override
    @Transactional
    public List<SeatFlightResponse> generateSeatsForFlight(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy chuyến bay với ID: " + flightId));

        if (flight.getAirplane() == null) {
            throw new ResourceNotFoundException("Chuyến bay không có thông tin máy bay");
        }

        List<Seat> airplaneSeats = seatRepository.findByAirplane(flight.getAirplane());

        if (airplaneSeats.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Không có thông tin ghế cho máy bay: " + flight.getAirplane().getModel());
        }

        List<SeatFlight> existingSeats = seatFlightRepository.findByFlightId(flightId);
        if (!existingSeats.isEmpty()) {
            throw new IllegalStateException("Chuyến bay đã có ghế. Vui lòng xóa ghế hiện tại trước khi tạo mới.");
        }

        List<SeatFlight> createdSeats = new ArrayList<>();

        for (Seat seat : airplaneSeats) {
            SeatFlight seatFlight = new SeatFlight();
            seatFlight.setFlight(flight);
            seatFlight.setSeatNumber(seat.getSeatNumber());
            seatFlight.setSeatType(seat.getSeatType());
            seatFlight.setSeatPosition(seat.getSeatPosition());
            seatFlight.setPrice(seat.getPrice());
            seatFlight.setStatus("AVAILABLE");

            createdSeats.add(seatFlight);
        }

        List<SeatFlight> savedSeats = seatFlightRepository.saveAll(createdSeats);

        return savedSeats.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private SeatFlightResponse mapToResponse(SeatFlight seatFlight) {
        return SeatFlightResponse.builder()
                .id(seatFlight.getId())
                .flightId(seatFlight.getFlight().getId())
                .flightNo(seatFlight.getFlight().getFlightNo())
                .seatNumber(seatFlight.getSeatNumber())
                .seatType(seatFlight.getSeatType())
                .seatPosition(seatFlight.getSeatPosition())
                .price(seatFlight.getPrice())
                .dateHold(seatFlight.getDateHold())
                .status(seatFlight.getStatus())
                .createdAt(seatFlight.getCreatedAt())
                .updatedAt(seatFlight.getUpdatedAt())
                .build();
    }
}