package com.project.airplanebooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    List<Passenger> findByBooking(Booking booking);

    @Query("SELECT p FROM Passenger p WHERE p.lastName LIKE %:lastName% OR p.firstName LIKE %:lastName%")
    List<Passenger> findByLastNameContaining(@Param("lastName") String lastName);

    List<Passenger> findByPassportNumber(String passportNumber);
}
