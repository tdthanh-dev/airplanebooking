package com.project.airplanebooking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "passengers")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Passenger extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;
    @Column(name = "phone_number", nullable = false, length = 13)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    @Column(name = "nationality", nullable = false, length = 50)
    private String nationality;

    @Column(name = "passport_number", nullable = false, length = 50)
    private String passportNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", foreignKey = @ForeignKey(name = "fk_passenger_booking"))
    private Booking booking;
}
