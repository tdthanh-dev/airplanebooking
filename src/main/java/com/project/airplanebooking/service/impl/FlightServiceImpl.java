package com.project.airplanebooking.service.impl;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.airplanebooking.dto.request.FlightDTO;
import com.project.airplanebooking.exception.EntityNotFoundException;
import com.project.airplanebooking.exception.ResourceNotFoundException;
import com.project.airplanebooking.model.Airline;
import com.project.airplanebooking.model.Airport;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.SeatFlight;
import com.project.airplanebooking.repository.AirlineRepository;
import com.project.airplanebooking.repository.AirportRepository;
import com.project.airplanebooking.repository.FlightRepository;
import com.project.airplanebooking.repository.SeatFlightRepository;
import com.project.airplanebooking.service.FlightService;
import com.project.airplanebooking.service.SeatFlightService;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;
    private final SeatFlightRepository seatFlightRepository;
    private final SeatFlightService seatFlightService;

    @Autowired
    public FlightServiceImpl(
            FlightRepository flightRepository,
            AirportRepository airportRepository,
            AirlineRepository airlineRepository,
            SeatFlightRepository seatFlightRepository,
            SeatFlightService seatFlightService) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
        this.seatFlightRepository = seatFlightRepository;
        this.seatFlightService = seatFlightService;
    }

    @Override
    public Flight createFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();
        Airport departureAirport = airportRepository.findByIataCode(flightDTO.getDepartureAirportCode())
                .orElseThrow(() -> new ResourceNotFoundException("Airport", "IATA code",
                        flightDTO.getDepartureAirportCode()));
        Airport arrivalAirport = airportRepository.findByIataCode(flightDTO.getArrivalAirportCode())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Airport", "IATA code", flightDTO.getArrivalAirportCode()));
        Airline airline = airlineRepository.findByIataCode(flightDTO.getAirlineCode())
                .orElseThrow(() -> new ResourceNotFoundException("Airline", "IATA code", flightDTO.getAirlineCode()));

        flight.setFlightNo(flightDTO.getFlightNo());
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setAirline(airline);
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setBaseFare(flightDTO.getBaseFare().doubleValue());
        flight.setStatus(flightDTO.getStatus());
        flight.setAvailableSeats(flightDTO.getAvailableSeats());
        flight.setCurrentPrice(flightDTO.getCurrentPrice());
        flight.setIsFull(flightDTO.getIsFull());
        flight.setDelayMinutes(flightDTO.getDelayMinutes());
        flight.setFlightType(flightDTO.getFlightType());

        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Flight.class, id));
    }

    @Override
    public Flight getFlightByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightNo(flightNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Flight", "flight number", flightNumber));
    }

    @Override
    @Transactional
    public List<Flight> searchFlights(String departureAirport, String arrivalAirport, LocalDate departureDate) {
        LocalDateTime startOfDay = departureDate.atStartOfDay();
        LocalDateTime endOfDay = departureDate.atTime(23, 59, 59);

        List<Flight> flights = flightRepository
                .findByDepartureAirportIataCodeAndArrivalAirportIataCodeAndDepartureTimeBetween(
                        departureAirport, arrivalAirport, startOfDay, endOfDay);

        List<Flight> availableFlights = new ArrayList<>();
        for (Flight flight : flights) {
            int availableCount = seatFlightService.updateFlightAvailableSeats(flight.getId());

            if (availableCount > 0) {
                availableFlights.add(flight);
            }
        }

        return availableFlights;
    }

    @Override
    public List<Flight> getFlightsByAirline(String airlineCode) {
        Airline airline = airlineRepository.findByIataCode(airlineCode)
                .orElseThrow(() -> new ResourceNotFoundException("Airline", "IATA code", airlineCode));
        return flightRepository.findByAirline(airline);
    }

    @Override
    public Flight updateFlight(Long id, FlightDTO flightDTO) {
        Flight flight = getFlightById(id);

        if (flightDTO.getFlightNo() != null) {
            flight.setFlightNo(flightDTO.getFlightNo());
        }
        if (flightDTO.getDepartureAirportCode() != null) {
            Airport departureAirport = airportRepository.findByIataCode(flightDTO.getDepartureAirportCode())
                    .orElseThrow(() -> new ResourceNotFoundException("Airport", "IATA code",
                            flightDTO.getDepartureAirportCode()));
            flight.setDepartureAirport(departureAirport);
        }

        if (flightDTO.getArrivalAirportCode() != null) {
            Airport arrivalAirport = airportRepository.findByIataCode(flightDTO.getArrivalAirportCode())
                    .orElseThrow(() -> new ResourceNotFoundException("Airport", "IATA code",
                            flightDTO.getArrivalAirportCode()));
            flight.setArrivalAirport(arrivalAirport);
        }

        if (flightDTO.getAirlineCode() != null) {
            Airline airline = airlineRepository.findByIataCode(flightDTO.getAirlineCode())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Airline", "IATA code", flightDTO.getAirlineCode()));
            flight.setAirline(airline);
        }

        if (flightDTO.getDepartureTime() != null) {
            flight.setDepartureTime(flightDTO.getDepartureTime());
        }

        if (flightDTO.getArrivalTime() != null) {
            flight.setArrivalTime(flightDTO.getArrivalTime());
        }

        if (flightDTO.getBaseFare() != null) {
            flight.setBaseFare(flightDTO.getBaseFare().doubleValue());
        }

        if (flightDTO.getStatus() != null) {
            flight.setStatus(flightDTO.getStatus());
        }

        if (flightDTO.getCurrentPrice() != null) {
            flight.setCurrentPrice(flightDTO.getCurrentPrice());
        }

        if (flightDTO.getIsFull() != null) {
            flight.setIsFull(flightDTO.getIsFull());
        }

        if (flightDTO.getDelayMinutes() != null) {
            flight.setDelayMinutes(flightDTO.getDelayMinutes());
        }

        if (flightDTO.getFlightType() != null) {
            flight.setFlightType(flightDTO.getFlightType());
        }

        return flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(Long id) {
        Flight flight = getFlightById(id);
        flightRepository.delete(flight);
    }

    @Override
    public void updateFlightStatus(Long id, String status) {
        Flight flight = getFlightById(id);
        flight.setStatus(status);
        flightRepository.save(flight);
    }

    @Override
    public List<Flight> searchFlightsOneWay(String departureAirport, String arrivalAirport, LocalDate departureDate,
            Integer totalPassengers) {
        LocalDateTime startOfDay = departureDate.atStartOfDay();
        LocalDateTime endOfDay = departureDate.atTime(23, 59, 59);

        List<Flight> listFlights = flightRepository
                .findByDepartureAirportIataCodeAndArrivalAirportIataCodeAndDepartureTimeBetween(
                        departureAirport, arrivalAirport, startOfDay, endOfDay);

        return listFlights.stream()
                .filter(flight -> totalPassengers <= flight.getAvailableSeats())
                .toList();
    }

    @Override
    public List<Flight> searchFlightsRoundTrip(String departureAirport, String arrivalAirport, LocalDate departureDate,
            LocalDate returnDate, Integer totalPassengers) {
        LocalDateTime startOfDay = departureDate.atStartOfDay();
        LocalDateTime endOfDay = departureDate.atTime(23, 59, 59);

        List<Flight> listFlights = flightRepository
                .findByDepartureAirportIataCodeAndArrivalAirportIataCodeAndDepartureTimeBetween(
                        departureAirport, arrivalAirport, startOfDay, endOfDay);

        return listFlights.stream()
                .filter(flight -> totalPassengers <= flight.getAvailableSeats())
                .toList();
    }

    @Override
    public List<Flight> findByDepartureAirportAndArrivalAirport(long departureAirportId, long arrivalAirportId) {
        Airport departureAirport = airportRepository.findById(departureAirportId)
                .orElseThrow(() -> new EntityNotFoundException(Airport.class, departureAirportId));
        Airport arrivalAirport = airportRepository.findById(arrivalAirportId)
                .orElseThrow(() -> new EntityNotFoundException(Airport.class, arrivalAirportId));
        return flightRepository.findByDepartureAirportAndArrivalAirport(departureAirport, arrivalAirport);
    }
}
