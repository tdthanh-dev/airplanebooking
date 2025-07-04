package com.project.airplanebooking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.project.airplanebooking.dto.simpleresponse.SeatFlightSimpleResponse;
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
        private Long userId;
        private String userFullName;
        private String status;
        private BigDecimal totalPrice;
        private List<Long> passengerIds;
        private List<Long> flightIds;
        private List<SeatFlightSimpleResponse> seatFlights;
        private LocalDate bookingDate;
        private Integer passengerCount;
        private String tripType;
        private String bookingSource;
        private String note;
        private String promotionCode;
        private String cancellationReason;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public BookingResponse(Booking booking) {
                this.id = booking.getId();
                this.bookingReference = booking.getBookingReference();

                if (booking.getUser() != null) {
                        this.userId = booking.getUser().getId();
                        this.userFullName = booking.getUser().getFirstName() + " " + booking.getUser().getLastName();
                }

                this.status = booking.getStatus();
                this.totalPrice = BigDecimal.valueOf(booking.getTotalPrice());

                this.passengerIds = booking.getPassengers() != null
                                ? booking.getPassengers().stream().map(passenger -> passenger.getId())
                                                .collect(Collectors.toList())
                                : null;

                this.flightIds = booking.getFlights() != null
                                ? booking.getFlights().stream().map(flight -> flight.getId())
                                                .collect(Collectors.toList())
                                : null;

                this.seatFlights = booking.getSeatFlights() != null
                                ? booking.getSeatFlights().stream()
                                                .map(seatFlight -> new SeatFlightSimpleResponse(
                                                                seatFlight.getId(),
                                                                seatFlight.getFlight().getId(),
                                                                seatFlight.getFlight().getFlightNo(),
                                                                seatFlight.getSeatNumber(),
                                                                seatFlight.getSeatType(),
                                                                seatFlight.getStatus()))
                                                .collect(Collectors.toList())
                                : null;

                this.bookingDate = booking.getBookingDate() != null ? booking.getBookingDate().toLocalDate() : null;
                this.passengerCount = booking.getPassengerCount();
                this.tripType = booking.getTripType();
                this.bookingSource = booking.getBookingSource();
                this.note = booking.getNote();
                this.promotionCode = booking.getPromotionCode();
                this.cancellationReason = booking.getCancellationReason();
                this.createdAt = booking.getCreatedAt();
                this.updatedAt = booking.getUpdatedAt();
        }
}