package com.project.airplanebooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Passenger> passengers;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    @Column(name = "booking_reference", nullable = false, length = 50)
    private String bookingReference;

    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "passenger_count", nullable = false)
    private Integer passengerCount;

    @Column(name = "trip_type", nullable = false, length = 50)
    private String tripType;

    @Column(name = "booking_source", nullable = false, length = 50)
    private String bookingSource;

    @Column(name = "promotion_code", nullable = false, length = 50)
    private String promotionCode;

    @Column(name = "cancellation_reason", nullable = false, length = 255)
    private String cancellationReason;
}
