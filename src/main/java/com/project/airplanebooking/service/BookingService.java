package com.project.airplanebooking.service;

import java.util.List;

import com.project.airplanebooking.dto.request.BookingDTO;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.User;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Passenger;

public interface BookingService {
    // Tạo booking với DTO
    Booking createBooking(BookingDTO bookingDTO);

    // Tạo booking với thông tin chi tiết (phù hợp cho luồng API)
    Booking createBooking(Long flightId, Long userId, List<Passenger> passengers, String tripType);

    // Tạo booking với hành khách (phiên bản đặc biệt cho quy trình đặt vé)
    Booking createBookingWithPassengers(BookingDTO bookingDTO);

    // Lấy danh sách booking
    List<Booking> getAllBookings();

    // Lấy booking theo ID
    Booking getBookingById(Long id);

    // Lấy danh sách booking của user
    List<Booking> getBookingsByUser(User user);

    // Lấy danh sách booking theo trạng thái
    List<Booking> getBookingsByStatus(String status);

    // Tìm booking theo mã đặt chỗ
    Booking getBookingByBookingReference(String bookingReference);

    // Cập nhật booking
    Booking updateBooking(Long id, BookingDTO bookingDTO);

    // Cập nhật trạng thái booking
    Booking updateBookingStatus(Long id, String status);

    // Cập nhật số ghế của booking
    void updateBookingSeats(Long id, Integer seats);

    // Hủy booking đơn giản
    void cancelBooking(Long id);

    // Hủy booking với lý do
    boolean cancelBooking(Long id, String reason);
}