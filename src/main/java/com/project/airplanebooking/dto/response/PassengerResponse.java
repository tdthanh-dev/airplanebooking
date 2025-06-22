package com.project.airplanebooking.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Passenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String idNumber;
    private List<Long> bookingIds;
    private LocalDateTime dateOfBirth;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PassengerResponse(Passenger passenger) {
        this.id = passenger.getId();
        this.firstName = passenger.getFirstName();
        this.lastName = passenger.getLastName();
        this.idNumber = passenger.getPersonalId();
        this.gender = passenger.getGender();

        // Lấy tất cả bookingIds (nếu có)
        if (passenger.getBookings() != null && !passenger.getBookings().isEmpty()) {
            this.bookingIds = passenger.getBookings().stream()
                    .map(Booking::getId)
                    .collect(Collectors.toList());
        }

        this.dateOfBirth = passenger.getDateOfBirth() != null ? passenger.getDateOfBirth().atStartOfDay() : null;
        this.createdAt = passenger.getCreatedAt();
        this.updatedAt = passenger.getUpdatedAt();
    }
}