package com.project.airplanebooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.airplanebooking.model.SeatFlight;

@Repository
public interface SeatFlightRepository extends JpaRepository<SeatFlight, Long> {
    List<SeatFlight> findByFlightId(Long flightId);

    List<SeatFlight> findByStatus(String status);

    List<SeatFlight> findByFlightIdAndStatus(Long flightId, String status);

    List<SeatFlight> findBySeatType(String seatType);
}