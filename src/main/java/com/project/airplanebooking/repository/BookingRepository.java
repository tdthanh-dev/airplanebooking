package com.project.airplanebooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByBookingReference(String bookingReference);

    List<Booking> findByUser(User user);

    List<Booking> findByStatus(String status);

}
