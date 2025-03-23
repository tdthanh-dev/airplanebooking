package com.project.airplanebooking.repository;

import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    List<Passenger> findByBooking(Booking booking);

    List<Passenger> findByLastName(String lastName);

    List<Passenger> findByFullNameContaining(String lastName);

    List<Passenger> findByPassportNumber(String passportNumber);
}
