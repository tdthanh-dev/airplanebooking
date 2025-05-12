package com.project.airplanebooking.model;

import java.time.LocalDateTime;

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
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "phone", unique = true, nullable = false, length = 15)
    private String phone;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "facebook_account_id")
    private Integer facebookAccountId;

    @Column(name = "google_account_id")
    private Integer googleAccountId;

    @Column(name = "loyalty_points")
    private Integer loyaltyPoints;

    @Column(name = "last_searched_route")
    private String lastSearchedRoute;

    @Column(name = "preferred_airport_id")
    private Integer preferredAirportId;

    @Column(name = "preferred_seat_class")
    private String preferredSeatClass;

    @Column(name = "preferred_airline_id")
    private Integer preferredAirlineId;
    /*
     * first name
     * last name
     * username
     * password
     * email
     * phone
     * role
     * status
     * is_active
     * last_login
     * facebook_account_id
     * google_account_id
     * created_at
     * updated_at
     */

}
