package com.project.airplanebooking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.project.airplanebooking.model.Booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private Long id;
    private String bookingReference;
    private RegisterResponse user;
    private String status;
    private BigDecimal totalAmount;
    private List<PassengerResponse> passengers;
    private List<FlightResponse> flights;
    private List<SeatFlightResponse> seatFlights;
    private LocalDate bookingDate;
    private Integer passengerCount;
    private String tripType;
    private String bookingSource;
    private String promotionCode;
    private String cancellationReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BookingResponse(Booking booking) {
        this.id = booking.getId();
        this.bookingReference = booking.getBookingReference();
        this.user = booking.getUser() != null ? new RegisterResponse(booking.getUser()) : null;
        this.status = booking.getStatus();
        this.totalAmount = BigDecimal.valueOf(booking.getTotalPrice());
        this.passengers = booking.getPassengers() != null
                ? booking.getPassengers().stream().map(PassengerResponse::new).collect(Collectors.toList())
                : null;
        this.flights = booking.getFlights() != null
                ? booking.getFlights().stream().map(FlightResponse::new).collect(Collectors.toList())
                : null;
        this.seatFlights = booking.getSeatFlights() != null
                ? booking.getSeatFlights().stream().map(SeatFlightResponse::new).collect(Collectors.toList())
                : null;
        this.bookingDate = booking.getBookingDate() != null ? booking.getBookingDate().toLocalDate() : null;
        this.passengerCount = booking.getPassengerCount();
        this.tripType = booking.getTripType();
        this.bookingSource = booking.getBookingSource();
        this.promotionCode = booking.getPromotionCode();
        this.cancellationReason = booking.getCancellationReason();
        this.createdAt = booking.getCreatedAt();
        this.updatedAt = booking.getUpdatedAt();
    }
}