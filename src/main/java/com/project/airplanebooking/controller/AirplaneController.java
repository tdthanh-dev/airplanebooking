package com.project.airplanebooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.airplanebooking.dto.request.AirplaneDTO;
import com.project.airplanebooking.dto.response.AirplaneResponse;
import com.project.airplanebooking.model.Airplane;
import com.project.airplanebooking.service.impl.AirplaneServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/airplanes")
public class AirplaneController {

    private final AirplaneServiceImpl airplaneServiceImpl;

    @Autowired
    public AirplaneController(AirplaneServiceImpl airplaneServiceImpl) {
        this.airplaneServiceImpl = airplaneServiceImpl;
    }

    @PostMapping("/")
    public ResponseEntity<?> createAirplane(@Valid @RequestBody AirplaneDTO airplaneDTO) {
        try {
            Airplane airplane = airplaneServiceImpl.createAirplane(airplaneDTO);
            return ResponseEntity.ok(new AirplaneResponse(airplane));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllAirplanes() {
        try {
            List<Airplane> airplanes = airplaneServiceImpl.getAllAirplanes();
            List<AirplaneResponse> responseList = airplanes.stream()
                    .map(AirplaneResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAirplaneById(@PathVariable Long id) {
        try {
            Airplane airplane = airplaneServiceImpl.getAirplaneById(id);
            return ResponseEntity.ok(new AirplaneResponse(airplane));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/registration")
    public ResponseEntity<?> getAirplaneByRegistrationNumber(@RequestParam String registrationNumber) {
        try {
            Airplane airplane = airplaneServiceImpl.getAirplaneByRegistrationNumber(registrationNumber);
            return ResponseEntity.ok(new AirplaneResponse(airplane));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAirplane(@PathVariable Long id, @Valid @RequestBody AirplaneDTO airplaneDTO) {
        try {
            Airplane airplane = airplaneServiceImpl.updateAirplane(id, airplaneDTO);
            return ResponseEntity.ok(new AirplaneResponse(airplane));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAirplane(@PathVariable Long id) {
        try {
            airplaneServiceImpl.deleteAirplane(id);
            return ResponseEntity.ok("Airplane deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}