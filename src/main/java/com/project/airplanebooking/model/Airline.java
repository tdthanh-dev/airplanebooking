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

@Entity
@Table(name = "airlines")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Airline extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "iata_code", nullable = false, length = 3)
    private String iataCode;

    @Column(name = "icao_code", nullable = false, length = 4)
    private String icaoCode;

    @Column(name = "call_sign", nullable = false, length = 20)
    private String callSign;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "website", nullable = false, length = 100)
    private String website;

    @Column(name = "hotline", nullable = false, length = 15)
    private String hotline;
}
