package com.project.airplanebooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.airplanebooking.dto.request.SeatDTO;
import com.project.airplanebooking.dto.response.SeatResponse;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Seat;
import com.project.airplanebooking.service.impl.FlightServiceImpl;
import com.project.airplanebooking.service.impl.SeatServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/seats")
public class SeatController {

    @Autowired
    private SeatServiceImpl seatServiceImpl;

    @Autowired
    private FlightServiceImpl flightServiceImpl;

    @PostMapping("/")
    public ResponseEntity<?> createSeat(@Valid @RequestBody SeatDTO seatDTO) {
        try {
            Seat seat = seatServiceImpl.createSeat(seatDTO);
            return ResponseEntity.ok(new SeatResponse(seat));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllSeats() {
        try {
            List<Seat> seats = seatServiceImpl.getAllSeats();
            List<SeatResponse> responseList = seats.stream()
                    .map(SeatResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSeatById(@PathVariable Long id) {
        try {
            Seat seat = seatServiceImpl.getSeatById(id);
            return ResponseEntity.ok(new SeatResponse(seat));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<?> getSeatsByFlight(@PathVariable Long flightId) {
        try {
            Flight flight = flightServiceImpl.getFlightById(flightId);
            List<Seat> seats = seatServiceImpl.getSeatsByAirplane(flight.getAirplane());
            List<SeatResponse> responseList = seats.stream()
                    .map(SeatResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/available/flight/{flightId}")
    public ResponseEntity<?> getAvailableSeatsByFlight(@PathVariable Long flightId) {
        try {
            Flight flight = flightServiceImpl.getFlightById(flightId);
            List<Seat> seats = seatServiceImpl.getAvailableSeatsByAirplane(flight.getAirplane());
            List<SeatResponse> responseList = seats.stream()
                    .map(SeatResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSeat(@PathVariable Long id, @Valid @RequestBody SeatDTO seatDTO) {
        try {
            Seat seat = seatServiceImpl.updateSeat(id, seatDTO);
            return ResponseEntity.ok(new SeatResponse(seat));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeat(@PathVariable Long id) {
        try {
            seatServiceImpl.deleteSeat(id);
            return ResponseEntity.ok("Seat deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/number/{seatNumber}/flight/{flightId}")
    public ResponseEntity<?> getSeatByNumberAndFlight(@PathVariable String seatNumber, @PathVariable Long flightId) {
        try {
            Flight flight = flightServiceImpl.getFlightById(flightId);
            Seat seat = seatServiceImpl.getSeatBySeatNumber(seatNumber, flight.getAirplane());
            return ResponseEntity.ok(new SeatResponse(seat));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}