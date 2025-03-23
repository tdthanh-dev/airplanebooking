package com.project.airplanebooking.service;

import com.project.airplanebooking.dto.request.BookingDTO;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.User;
import com.project.airplanebooking.model.Flight;
import java.util.List;

public interface BookingService {
    Booking createBooking(BookingDTO bookingDTO);

    Booking updateBooking(Long id, BookingDTO bookingDTO);

    void deleteBooking(Long id);

    Booking getBookingById(Long id);

    Booking getBookingByBookingNumber(String bookingNumber);

    List<Booking> getBookingsByUser(User user);

    List<Booking> getBookingsByStatus(String status);

    List<Booking> getAllBookings();

    void updateBookingStatus(Long id, String status);

    List<Booking> getBookingsByFlight(Flight flight);
}