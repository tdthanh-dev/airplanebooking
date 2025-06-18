package com.project.airplanebooking.repository;

import com.project.airplanebooking.model.Airplane;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByAirplane(Airplane airplane);

    List<Seat> findByAirplaneAndStatus(Airplane airplane, String status);

    Optional<Seat> findByAirplaneAndSeatNumber(Airplane airplane, String seatNumber);

    @Query("SELECT s FROM Seat s WHERE s.airplane = (SELECT f.airplane FROM Flight f WHERE f = :flight) AND s.seatNumber = :seatNumber")
    Optional<Seat> findBySeatNumberAndFlight(@Param("seatNumber") String seatNumber, @Param("flight") Flight flight);

    @Query("SELECT s FROM Seat s WHERE s.airplane = (SELECT f.airplane FROM Flight f WHERE f = :flight) AND s.status = :status")
    List<Seat> findByFlightAndStatus(@Param("flight") Flight flight, @Param("status") String status);

    List<Seat> findByStatus(String status);

    Optional<Seat> findBySeatNumber(String seatNumber);
}
