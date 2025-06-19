package com.project.airplanebooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.airplanebooking.dto.request.SeatFlightRequest;
import com.project.airplanebooking.dto.response.SeatFlightResponse;
import com.project.airplanebooking.service.SeatFlightService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/seat-flights")
public class SeatFlightController {

    @Autowired
    private SeatFlightService seatFlightService;

    @PostMapping
    public ResponseEntity<SeatFlightResponse> createSeatFlight(@Valid @RequestBody SeatFlightRequest request) {
        return new ResponseEntity<>(seatFlightService.createSeatFlight(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatFlightResponse> updateSeatFlight(@PathVariable Long id,
            @Valid @RequestBody SeatFlightRequest request) {
        return ResponseEntity.ok(seatFlightService.updateSeatFlight(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeatFlight(@PathVariable Long id) {
        seatFlightService.deleteSeatFlight(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatFlightResponse> getSeatFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(seatFlightService.getSeatFlightById(id));
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<SeatFlightResponse>> getSeatFlightsByFlightId(@PathVariable Long flightId) {
        return ResponseEntity.ok(seatFlightService.getSeatFlightsByFlightId(flightId));
    }

    @PostMapping("/generate-seats/flight/{flightId}")
    public ResponseEntity<List<SeatFlightResponse>> generateSeatsForFlight(@PathVariable Long flightId) {
        return ResponseEntity.ok(seatFlightService.generateSeatsForFlight(flightId));
    }

    @PostMapping("/hold-seats/{flightId}")
    public ResponseEntity<String> holdSeats(@PathVariable Long flightId, @RequestBody List<Long> seatIds) {
        seatFlightService.changeSeatsToHold(seatIds, flightId);
        return ResponseEntity.ok("Đã giữ ghế thành công");
    }
}