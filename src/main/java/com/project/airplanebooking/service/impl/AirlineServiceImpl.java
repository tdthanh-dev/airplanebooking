package com.project.airplanebooking.service.impl;

import com.project.airplanebooking.dto.request.AirlineDTO;
import com.project.airplanebooking.model.Airline;
import com.project.airplanebooking.repository.AirlineRepository;
import com.project.airplanebooking.service.AirlineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    public AirlineServiceImpl(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Override
    public Airline createAirline(AirlineDTO airlineDTO) {
        Airline airline = new Airline();
        airline.setName(airlineDTO.getName());
        airline.setIataCode(airlineDTO.getIataCode());
        airline.setIcaoCode(airlineDTO.getIcaoCode());
        airline.setCallSign(airlineDTO.getCallSign());
        airline.setCountry(airlineDTO.getCountry());
        airline.setWebsite(airlineDTO.getWebsite());
        airline.setHotline(airlineDTO.getHotline());

        return airlineRepository.save(airline);
    }

    @Override
    public Airline updateAirline(Long id, AirlineDTO airlineDTO) {
        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airline not found with id: " + id));

        airline.setName(airlineDTO.getName());
        airline.setIataCode(airlineDTO.getIataCode());
        airline.setIcaoCode(airlineDTO.getIcaoCode());
        airline.setCallSign(airlineDTO.getCallSign());
        airline.setCountry(airlineDTO.getCountry());
        airline.setWebsite(airlineDTO.getWebsite());
        airline.setHotline(airlineDTO.getHotline());

        return airlineRepository.save(airline);
    }

    @Override
    public void deleteAirline(Long id) {
        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airline not found with id: " + id));

        airlineRepository.delete(airline);
    }

    @Override
    public Airline getAirlineById(Long id) {
        return airlineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airline not found with id: " + id));
    }

    @Override
    public Airline getAirlineByCode(String code) {
        return airlineRepository.findByIataCode(code)
                .orElseThrow(() -> new RuntimeException("Airline not found with code: " + code));
    }

    @Override
    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    @Override
    public boolean existsByCode(String code) {
        return airlineRepository.existsByIataCode(code);
    }
}