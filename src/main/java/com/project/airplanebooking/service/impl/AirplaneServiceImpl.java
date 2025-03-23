package com.project.airplanebooking.service.impl;

import com.project.airplanebooking.dto.request.AirplaneDTO;
import com.project.airplanebooking.model.Airplane;
import com.project.airplanebooking.model.Airline;
import com.project.airplanebooking.repository.AirplaneRepository;
import com.project.airplanebooking.repository.AirlineRepository;
import com.project.airplanebooking.service.AirplaneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final AirlineRepository airlineRepository;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository, AirlineRepository airlineRepository) {
        this.airplaneRepository = airplaneRepository;
        this.airlineRepository = airlineRepository;
    }

    @Override
    public Airplane createAirplane(AirplaneDTO airplaneDTO) {
        Airline airline = airlineRepository.findById(airplaneDTO.getAirlineId())
                .orElseThrow(() -> new RuntimeException("Airline not found with id: " + airplaneDTO.getAirlineId()));

        Airplane airplane = new Airplane();
        airplane.setModel(airplaneDTO.getModel());
        airplane.setAirline(airline);
        airplane.setRegistrationNumber(airplaneDTO.getRegistrationNumber());
        airplane.setSeatCapacity(airplaneDTO.getSeatCapacity());

        return airplaneRepository.save(airplane);
    }

    @Override
    public Airplane updateAirplane(Long id, AirplaneDTO airplaneDTO) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airplane not found with id: " + id));

        if (airplaneDTO.getAirlineId() != null) {
            Airline airline = airlineRepository.findById(airplaneDTO.getAirlineId())
                    .orElseThrow(
                            () -> new RuntimeException("Airline not found with id: " + airplaneDTO.getAirlineId()));
            airplane.setAirline(airline);
        }

        airplane.setModel(airplaneDTO.getModel());
        airplane.setRegistrationNumber(airplaneDTO.getRegistrationNumber());
        airplane.setSeatCapacity(airplaneDTO.getSeatCapacity());

        return airplaneRepository.save(airplane);
    }

    @Override
    public void deleteAirplane(Long id) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airplane not found with id: " + id));

        airplaneRepository.delete(airplane);
    }

    @Override
    public Airplane getAirplaneById(Long id) {
        return airplaneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airplane not found with id: " + id));
    }

    @Override
    public Airplane getAirplaneByRegistrationNumber(String registrationNumber) {
        return airplaneRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new RuntimeException(
                        "Airplane not found with registration number: " + registrationNumber));
    }

    @Override
    public List<Airplane> getAirplanesByAirline(Airline airline) {
        return airplaneRepository.findByAirline(airline);
    }

    @Override
    public List<Airplane> getAirplanesByModel(String model) {
        return airplaneRepository.findByModel(model);
    }

    @Override
    public List<Airplane> getAllAirplanes() {
        return airplaneRepository.findAll();
    }
}