package com.project.airplanebooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.airplanebooking.dto.request.PassengerDTO;
import com.project.airplanebooking.dto.response.PassengerResponse;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.service.impl.BookingServiceImpl;
import com.project.airplanebooking.service.impl.PassengerServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/passengers")
public class PassengerController {

    private final PassengerServiceImpl passengerServiceImpl;
    private final BookingServiceImpl bookingServiceImpl;

    @Autowired
    public PassengerController(PassengerServiceImpl passengerServiceImpl, BookingServiceImpl bookingServiceImpl) {
        this.passengerServiceImpl = passengerServiceImpl;
        this.bookingServiceImpl = bookingServiceImpl;
    }

    @PostMapping("/")
    public ResponseEntity<?> createPassenger(@Valid @RequestBody PassengerDTO passengerDTO) {
        try {
            Passenger passenger = passengerServiceImpl.createPassenger(passengerDTO);
            return ResponseEntity.ok(new PassengerResponse(passenger));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllPassengers() {
        try {
            List<Passenger> passengers = passengerServiceImpl.getAllPassengers();
            List<PassengerResponse> responseList = passengers.stream()
                    .map(PassengerResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPassengerById(@PathVariable Long id) {
        try {
            Passenger passenger = passengerServiceImpl.getPassengerById(id);
            return ResponseEntity.ok(new PassengerResponse(passenger));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePassenger(@PathVariable Long id, @Valid @RequestBody PassengerDTO passengerDTO) {
        try {
            Passenger passenger = passengerServiceImpl.updatePassenger(id, passengerDTO);
            return ResponseEntity.ok(new PassengerResponse(passenger));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePassenger(@PathVariable Long id) {
        try {
            passengerServiceImpl.deletePassenger(id);
            return ResponseEntity.ok("Passenger deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
