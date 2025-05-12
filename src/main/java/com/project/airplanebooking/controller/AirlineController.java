package com.project.airplanebooking.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.airplanebooking.dto.request.AirlineDTO;
import com.project.airplanebooking.dto.response.AirlineResponse;
import com.project.airplanebooking.model.Airline;
import com.project.airplanebooking.service.impl.AirlineServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/airlines") // localhost:8080/api/v1/airlines
public class AirlineController {

    @Autowired
    private AirlineServiceImpl airlineServiceImpl;

    @PostMapping("/")
    public ResponseEntity<?> createAirline(@Valid @RequestBody AirlineDTO airlineDTO

    ) {
        try {
            Airline airline = airlineServiceImpl.createAirline(airlineDTO);
            return ResponseEntity.ok(new AirlineResponse(airline));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllAirline() {
        try {
            List<Airline> list = airlineServiceImpl.getAllAirlines();
            List<AirlineResponse> responseList = list.stream()
                    .map(AirlineResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAirlineById(@PathVariable long id) {

        try {
            Airline airline = airlineServiceImpl.getAirlineById(id);
            return ResponseEntity.ok(new AirlineResponse(airline));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/code")
    public ResponseEntity<?> getAirlineByCode(@RequestParam String code, String token) {
        try {
            Airline airline = airlineServiceImpl.getAirlineByCode(code);
            return ResponseEntity.ok(new AirlineResponse(airline));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAirline(@PathVariable long id, @RequestBody AirlineDTO airlineDTO) {
        try {
            Airline airline = airlineServiceImpl.updateAirline(id, airlineDTO);
            return ResponseEntity.ok(new AirlineResponse(airline));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAirline(@PathVariable long id) {
        try {
            airlineServiceImpl.deleteAirline(id);
            return ResponseEntity.ok("Airline deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
