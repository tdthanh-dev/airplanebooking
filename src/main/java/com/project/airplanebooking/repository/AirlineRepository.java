package com.project.airplanebooking.repository;

import com.project.airplanebooking.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Optional<Airline> findByIataCode(String iataCode);

    Optional<Airline> findByName(String name);

    boolean existsByIataCode(String iataCode);
}