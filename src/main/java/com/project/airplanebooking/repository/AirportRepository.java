package com.project.airplanebooking.repository;

import com.project.airplanebooking.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByIataCode(String iataCode);

    List<Airport> findByCity(String city);

    List<Airport> findByCountry(String country);

    boolean existsByIataCode(String iataCode);
}
