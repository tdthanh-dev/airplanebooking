package com.project.airplanebooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.airplanebooking.dto.request.AirportDTO;
import com.project.airplanebooking.dto.response.AirportResponse;
import com.project.airplanebooking.model.Airport;
import com.project.airplanebooking.service.impl.AirportServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/airports")
public class AirportController {

    @Autowired
    private AirportServiceImpl airportServiceImpl;

    @PostMapping("/")
    public ResponseEntity<?> createAirport(@Valid @RequestBody AirportDTO airportDTO) {
        try {
            Airport airport = airportServiceImpl.createAirport(airportDTO);
            return ResponseEntity.ok(new AirportResponse(airport));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllAirports() {
        try {
            List<Airport> airports = airportServiceImpl.getAllAirports();
            List<AirportResponse> responseList = airports.stream()
                    .map(AirportResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAirportById(@PathVariable Long id) {
        try {
            Airport airport = airportServiceImpl.getAirportById(id);
            return ResponseEntity.ok(new AirportResponse(airport));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/code")
    public ResponseEntity<?> getAirportByCode(@RequestParam String code) {
        try {
            Airport airport = airportServiceImpl.getAirportByCode(code);
            return ResponseEntity.ok(new AirportResponse(airport));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAirport(@PathVariable Long id, @Valid @RequestBody AirportDTO airportDTO) {
        try {
            Airport airport = airportServiceImpl.updateAirport(id, airportDTO);
            return ResponseEntity.ok(new AirportResponse(airport));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAirport(@PathVariable Long id) {
        try {
            airportServiceImpl.deleteAirport(id);
            return ResponseEntity.ok("Airport deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}