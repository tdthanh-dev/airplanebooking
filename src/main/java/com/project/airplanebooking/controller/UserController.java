package com.project.airplanebooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.airplanebooking.dto.request.RegisterRequest;
import com.project.airplanebooking.dto.response.RegisterResponse;
import com.project.airplanebooking.dto.response.UserResponse;
import com.project.airplanebooking.model.User;
import com.project.airplanebooking.service.impl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            User user = userServiceImpl.createUser(registerRequest);
            return ResponseEntity.ok(new RegisterResponse(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userServiceImpl.getAllUsers();
            List<RegisterResponse> responseList = users.stream()
                    .map(RegisterResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            User user = userServiceImpl.getUserById(id);
            return ResponseEntity.ok(new RegisterResponse(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            User user = userServiceImpl.getUserByEmail(email);
            return ResponseEntity.ok(new RegisterResponse(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<RegisterResponse> getUserByUsername(@PathVariable String username) {
        try {
            User user = userServiceImpl.getUserByUsername(username);
            return ResponseEntity.ok(new RegisterResponse(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RegisterResponse());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        try {
            User updatedUser = userServiceImpl.updateUser(id, user);
            return ResponseEntity.ok(new RegisterResponse(updatedUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponse());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userServiceImpl.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponse());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateUserStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            userServiceImpl.updateUserStatus(id, status);
            return ResponseEntity.ok("User status updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateUser(@PathVariable Long id) {
        try {
            userServiceImpl.activateUser(id);
            return ResponseEntity.ok("User activated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateUser(@PathVariable Long id) {
        try {
            userServiceImpl.deactivateUser(id);
            return ResponseEntity.ok("User deactivated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/loyalty-points")
    public ResponseEntity<?> updateLoyaltyPoints(@PathVariable Long id, @RequestParam Integer points) {
        try {
            userServiceImpl.updateLoyaltyPoints(id, points);
            User user = userServiceImpl.getUserById(id);
            return ResponseEntity.ok(new RegisterResponse(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/last-searched-route")
    public ResponseEntity<?> updateLastSearchedRoute(@PathVariable Long id, @RequestParam String route) {
        try {
            userServiceImpl.updateLastSearchedRoute(id, route);
            User user = userServiceImpl.getUserById(id);
            return ResponseEntity.ok(new RegisterResponse(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/preferences")
    public ResponseEntity<?> updatePreferences(
            @PathVariable Long id,
            @RequestParam(required = false) Integer airportId,
            @RequestParam(required = false) String seatClass,
            @RequestParam(required = false) Integer airlineId) {
        try {
            userServiceImpl.updatePreferences(id, airportId, seatClass, airlineId);
            User user = userServiceImpl.getUserById(id);
            return ResponseEntity.ok(new RegisterResponse(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}