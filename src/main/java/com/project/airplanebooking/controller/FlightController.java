package com.project.airplanebooking.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.airplanebooking.dto.request.FlightDTO;
import com.project.airplanebooking.dto.response.FlightResponse;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.service.impl.FlightServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/flights")
public class FlightController {

    @Autowired
    private FlightServiceImpl flightServiceImpl;

    @PostMapping("/")
    public ResponseEntity<?> createFlight(@Valid @RequestBody FlightDTO flightDTO) {
        try {
            Flight flight = flightServiceImpl.createFlight(flightDTO);
            return ResponseEntity.ok(new FlightResponse(flight));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllFlights() {
        try {
            List<Flight> flights = flightServiceImpl.getAllFlights();
            List<FlightResponse> responseList = flights.stream()
                    .map(FlightResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable Long id) {
        try {
            Flight flight = flightServiceImpl.getFlightById(id);
            return ResponseEntity.ok(new FlightResponse(flight));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/number/{flightNumber}")
    public ResponseEntity<?> getFlightByFlightNumber(@PathVariable String flightNumber) {
        try {
            Flight flight = flightServiceImpl.getFlightByFlightNumber(flightNumber);
            return ResponseEntity.ok(new FlightResponse(flight));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFlights(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
        try {
            List<Flight> flights = flightServiceImpl.searchFlights(departureAirport, arrivalAirport, departureDate);
            if (flights.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No flights found for the specified criteria");
            }
            List<FlightResponse> responseList = flights.stream()
                    .map(FlightResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/airline/{airlineCode}")
    public ResponseEntity<?> getFlightsByAirline(@PathVariable String airlineCode) {
        try {
            List<Flight> flights = flightServiceImpl.getFlightsByAirline(airlineCode);
            List<FlightResponse> responseList = flights.stream()
                    .map(FlightResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable Long id, @Valid @RequestBody FlightDTO flightDTO) {
        try {
            Flight flight = flightServiceImpl.updateFlight(id, flightDTO);
            return ResponseEntity.ok(new FlightResponse(flight));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        try {
            flightServiceImpl.deleteFlight(id);
            return ResponseEntity.ok("Flight deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateFlightStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            flightServiceImpl.updateFlightStatus(id, status);
            Flight flight = flightServiceImpl.getFlightById(id);
            return ResponseEntity.ok(new FlightResponse(flight));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/current-price")
    public ResponseEntity<?> updateCurrentPrice(@PathVariable Long id, @RequestParam Double currentPrice) {
        try {
            Flight flight = flightServiceImpl.getFlightById(id);
            flight.setCurrentPrice(currentPrice);
            flight = flightServiceImpl.updateFlight(id, convertToDTO(flight));
            return ResponseEntity.ok(new FlightResponse(flight));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/is-full")
    public ResponseEntity<?> updateIsFullStatus(@PathVariable Long id, @RequestParam Boolean isFull) {
        try {
            Flight flight = flightServiceImpl.getFlightById(id);
            flight.setIsFull(isFull);
            flight = flightServiceImpl.updateFlight(id, convertToDTO(flight));
            return ResponseEntity.ok(new FlightResponse(flight));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/delay")
    public ResponseEntity<?> updateDelayMinutes(@PathVariable Long id, @RequestParam Integer delayMinutes) {
        try {
            Flight flight = flightServiceImpl.getFlightById(id);
            flight.setDelayMinutes(delayMinutes);
            flight = flightServiceImpl.updateFlight(id, convertToDTO(flight));
            return ResponseEntity.ok(new FlightResponse(flight));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/flight-type")
    public ResponseEntity<?> updateFlightType(@PathVariable Long id, @RequestParam String flightType) {
        try {
            Flight flight = flightServiceImpl.getFlightById(id);
            flight.setFlightType(flightType);
            flight = flightServiceImpl.updateFlight(id, convertToDTO(flight));
            return ResponseEntity.ok(new FlightResponse(flight));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Helper method to convert Flight to FlightDTO
    private FlightDTO convertToDTO(Flight flight) {
        FlightDTO dto = new FlightDTO();
        dto.setFlightNo(flight.getFlightNo());
        dto.setAirlineCode(flight.getAirline().getIataCode());
        dto.setDepartureAirportCode(flight.getDepartureAirport().getIataCode());
        dto.setArrivalAirportCode(flight.getArrivalAirport().getIataCode());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setArrivalTime(flight.getArrivalTime());
        dto.setBaseFare(flight.getBaseFare());
        dto.setStatus(flight.getStatus());
        dto.setCurrentPrice(flight.getCurrentPrice());
        dto.setIsFull(flight.getIsFull());
        dto.setDelayMinutes(flight.getDelayMinutes());
        dto.setFlightType(flight.getFlightType());
        return dto;
    }

    @GetMapping("/search/one-way")
    public ResponseEntity<?> searchFlightsOneWay(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam Integer totalPassengers) {
        try {
            List<Flight> flights = flightServiceImpl.searchFlightsOneWay(departureAirport, arrivalAirport,
                    departureDate,
                    totalPassengers);
            if (flights.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No flights found for the specified criteria");
            }
            List<FlightResponse> responseList = flights.stream()
                    .map(FlightResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/search/round-trip")
    public ResponseEntity<?> searchFlightsRoundTrip(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate,
            @RequestParam Integer totalPassengers) {
        try {
            List<Flight> flights = flightServiceImpl.searchFlightsRoundTrip(departureAirport, arrivalAirport,
                    departureDate, returnDate, totalPassengers);
            if (flights.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No flights found for the specified criteria");
            }
            List<FlightResponse> responseList = flights.stream()
                    .map(FlightResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}