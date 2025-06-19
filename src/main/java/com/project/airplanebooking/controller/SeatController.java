package com.project.airplanebooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.airplanebooking.dto.request.SeatDTO;
import com.project.airplanebooking.dto.response.SeatResponse;
import com.project.airplanebooking.model.Seat;
import com.project.airplanebooking.service.impl.SeatServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/seats")
public class SeatController {

    private final SeatServiceImpl seatServiceImpl;

    @Autowired
    public SeatController(SeatServiceImpl seatServiceImpl) {
        this.seatServiceImpl = seatServiceImpl;
    }

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

    @GetMapping("/airplane/{airplaneId}")
    public ResponseEntity<?> getSeatByAirplane(@PathVariable Long airplaneId) {
        try {
            List<Seat> seats = seatServiceImpl.findSeatByAirplane(airplaneId);
            List<SeatResponse> responseList = seats.stream()
                    .map(SeatResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
