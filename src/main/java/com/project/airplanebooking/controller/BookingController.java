package com.project.airplanebooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.airplanebooking.dto.request.BookingDTO;
import com.project.airplanebooking.dto.response.BookingResponse;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.User;
import com.project.airplanebooking.service.impl.BookingServiceImpl;
import com.project.airplanebooking.service.impl.FlightServiceImpl;
import com.project.airplanebooking.service.impl.UserServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private FlightServiceImpl flightServiceImpl;

    @PostMapping("/")
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        try {
            Booking booking = bookingServiceImpl.createBooking(bookingDTO);
            return ResponseEntity.ok(new BookingResponse(booking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllBookings() {
        try {
            List<Booking> bookings = bookingServiceImpl.getAllBookings();
            List<BookingResponse> responseList = bookings.stream()
                    .map(BookingResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        try {
            Booking booking = bookingServiceImpl.getBookingById(id);
            return ResponseEntity.ok(new BookingResponse(booking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBookingsByUser(@PathVariable Long userId) {
        try {
            User user = userServiceImpl.getUserById(userId);
            List<Booking> bookings = bookingServiceImpl.getBookingsByUser(user);
            List<BookingResponse> responseList = bookings.stream()
                    .map(BookingResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getBookingsByStatus(@PathVariable String status) {
        try {
            List<Booking> bookings = bookingServiceImpl.getBookingsByStatus(status);
            List<BookingResponse> responseList = bookings.stream()
                    .map(BookingResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<?> getBookingsByFlight(@PathVariable Long flightId) {
        try {
            Flight flight = flightServiceImpl.getFlightById(flightId);
            List<Booking> bookings = bookingServiceImpl.getBookingsByFlight(flight);
            List<BookingResponse> responseList = bookings.stream()
                    .map(BookingResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Long id, @Valid @RequestBody BookingDTO bookingDTO) {
        try {
            Booking booking = bookingServiceImpl.updateBooking(id, bookingDTO);
            return ResponseEntity.ok(new BookingResponse(booking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateBookingStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            bookingServiceImpl.updateBookingStatus(id, status);
            Booking booking = bookingServiceImpl.getBookingById(id);
            return ResponseEntity.ok(new BookingResponse(booking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/booking-source")
    public ResponseEntity<?> updateBookingSource(@PathVariable Long id, @RequestParam String bookingSource) {
        try {
            Booking booking = bookingServiceImpl.getBookingById(id);
            booking.setBookingSource(bookingSource);

            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setBookingSource(bookingSource);

            booking = bookingServiceImpl.updateBooking(id, bookingDTO);
            return ResponseEntity.ok(new BookingResponse(booking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/promotion-code")
    public ResponseEntity<?> updatePromotionCode(@PathVariable Long id, @RequestParam String promotionCode) {
        try {
            Booking booking = bookingServiceImpl.getBookingById(id);
            booking.setPromotionCode(promotionCode);

            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setPromotionCode(promotionCode);

            booking = bookingServiceImpl.updateBooking(id, bookingDTO);
            return ResponseEntity.ok(new BookingResponse(booking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/cancellation-reason")
    public ResponseEntity<?> updateCancellationReason(@PathVariable Long id, @RequestParam String cancellationReason) {
        try {
            Booking booking = bookingServiceImpl.getBookingById(id);
            booking.setCancellationReason(cancellationReason);

            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setCancellationReason(cancellationReason);

            booking = bookingServiceImpl.updateBooking(id, bookingDTO);
            return ResponseEntity.ok(new BookingResponse(booking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id) {
        try {
            bookingServiceImpl.cancelBooking(id);
            return ResponseEntity.ok("Booking cancelled successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/reference/{referenceNumber}")
    public ResponseEntity<?> getBookingByBookingReference(@PathVariable String referenceNumber) {
        try {
            Booking booking = bookingServiceImpl.getBookingByBookingReference(referenceNumber);
            return ResponseEntity.ok(new BookingResponse(booking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/with-passengers")
    public ResponseEntity<?> createBookingWithPassengers(@Valid @RequestBody BookingDTO bookingDTO) {
        try {
            Booking booking = bookingServiceImpl.createBookingWithPassengers(bookingDTO);
            return ResponseEntity.ok(new BookingResponse(booking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}