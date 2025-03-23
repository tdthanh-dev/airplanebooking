package com.project.airplanebooking.repository;

import com.project.airplanebooking.model.Airplane;
import com.project.airplanebooking.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    Optional<Airplane> findByRegistrationNumber(String registrationNumber);

    List<Airplane> findByAirline(Airline airline);

    List<Airplane> findByModel(String model);
}
