package com.project.airplanebooking.repository;

import com.project.airplanebooking.model.Airplane;
import com.project.airplanebooking.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByAirplane(Airplane airplane);

    List<Seat> findBySeatClass(String seatClass);

    Optional<Seat> findByAirplaneAndSeatNumber(Airplane airplane, String seatNumber);

    List<Seat> findByAirplaneAndSeatClass(Airplane airplane, String seatClass);
}
