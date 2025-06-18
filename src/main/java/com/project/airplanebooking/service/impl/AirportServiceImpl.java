package com.project.airplanebooking.service.impl;

import com.project.airplanebooking.dto.request.AirportDTO;
import com.project.airplanebooking.exception.EntityNotFoundException;
import com.project.airplanebooking.exception.ResourceNotFoundException;
import com.project.airplanebooking.model.Airport;
import com.project.airplanebooking.repository.AirportRepository;
import com.project.airplanebooking.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public Airport createAirport(AirportDTO airportDTO) {
        Airport airport = new Airport();
        airport.setName(airportDTO.getName());
        airport.setIataCode(airportDTO.getIataCode());
        airport.setCity(airportDTO.getCity());
        airport.setCountry(airportDTO.getCountry());
        airport.setState(airportDTO.getState()); // Default to city if state not provided in DTO
        airport.setIcaoCode(airportDTO.getIcaoCode()); // Default empty, should be updated with proper logic

        return airportRepository.save(airport);
    }

    @Override
    public Airport updateAirport(Long id, AirportDTO airportDTO) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Airport.class, id));

        airport.setName(airportDTO.getName());
        airport.setIataCode(airportDTO.getIataCode());
        airport.setCity(airportDTO.getCity());
        airport.setCountry(airportDTO.getCountry());
        airport.setState(airportDTO.getState());
        airport.setIcaoCode(airportDTO.getIcaoCode());

        return airportRepository.save(airport);
    }

    @Override
    public void deleteAirport(Long id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Airport.class, id));

        airportRepository.delete(airport);
    }

    @Override
    public Airport getAirportById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Airport.class, id));
    }

    @Override
    public Airport getAirportByCode(String code) {
        return airportRepository.findByIataCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Airport", "IATA code", code));
    }

    @Override
    public List<Airport> getAirportsByCity(String city) {
        return airportRepository.findByCity(city);
    }

    @Override
    public List<Airport> getAirportsByCountry(String country) {
        return airportRepository.findByCountry(country);
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public boolean existsByCode(String code) {
        return airportRepository.existsByIataCode(code);
    }
}