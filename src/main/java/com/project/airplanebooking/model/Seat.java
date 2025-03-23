package com.project.airplanebooking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "seats")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Seat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_id", nullable = false, foreignKey = @ForeignKey(name = "fk_seat_airplane"))
    private Airplane airplane;

    @Column(name = "seat_number", nullable = false, length = 50)
    private String seatNumber;

    @Column(name = "seat_type", nullable = false, length = 50)
    private String seatType;

    @Column(name = "seat_position", nullable = false, length = 50)
    private String seatPosition;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

}