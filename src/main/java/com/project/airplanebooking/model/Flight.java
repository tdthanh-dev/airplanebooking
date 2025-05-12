package com.project.airplanebooking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "flights")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Flight extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_no", nullable = false, length = 10)
    private String flightNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_id", nullable = false)
    private Airplane airplane;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_airport_id", nullable = false)
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    private Airport arrivalAirport;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @Column(name = "base_fare", nullable = false)
    private Double baseFare;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "current_price", nullable = false)
    private Double currentPrice;

    @Column(name = "is_full", nullable = false)
    private Boolean isFull;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @Column(name = "delay_minutes", nullable = false)
    private Integer delayMinutes;

    @Column(name = "flight_type", nullable = false, length = 50)
    private String flightType;

    
}
