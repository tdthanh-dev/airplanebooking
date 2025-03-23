package com.project.airplanebooking.repository;

import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.User;
import com.project.airplanebooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByBookingReference(String bookingReference);

    List<Booking> findByUser(User user);

    List<Booking> findByBookingDateBetween(LocalDateTime start, LocalDateTime end);

    List<Booking> findByStatus(String status);

    List<Booking> findByFlight(Flight flight);
}
