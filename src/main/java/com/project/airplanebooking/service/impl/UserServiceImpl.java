package com.project.airplanebooking.service.impl;

import com.project.airplanebooking.dto.request.RegisterRequest;
import com.project.airplanebooking.model.User;
import com.project.airplanebooking.repository.UserRepository;
import com.project.airplanebooking.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setPhone(registerRequest.getPhone());
        user.setRole("USER");
        user.setStatus("ACTIVE");
        user.setIsActive(true);
        user.setFacebookAccountId(registerRequest.getFacebookAccountId());
        user.setGoogleAccountId(registerRequest.getGoogleAccountId());
        user.setLoyaltyPoints(0);
        user.setLastSearchedRoute(null);
        user.setPreferredAirportId(null);
        user.setPreferredSeatClass(null);
        user.setPreferredAirlineId(null);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (!user.getUsername().equals(updatedUser.getUsername()) &&
                userRepository.findByUsername(updatedUser.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        if (!user.getEmail().equals(updatedUser.getEmail()) &&
                userRepository.findByEmail(updatedUser.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setUsername(updatedUser.getUsername());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        user.setEmail(updatedUser.getEmail());
        user.setPhone(updatedUser.getPhone());

        if (updatedUser.getRole() != null) {
            user.setRole(updatedUser.getRole());
        }

        if (updatedUser.getLoyaltyPoints() != null) {
            user.setLoyaltyPoints(updatedUser.getLoyaltyPoints());
        }

        if (updatedUser.getLastSearchedRoute() != null) {
            user.setLastSearchedRoute(updatedUser.getLastSearchedRoute());
        }

        if (updatedUser.getPreferredAirportId() != null) {
            user.setPreferredAirportId(updatedUser.getPreferredAirportId());
        }

        if (updatedUser.getPreferredSeatClass() != null) {
            user.setPreferredSeatClass(updatedUser.getPreferredSeatClass());
        }

        if (updatedUser.getPreferredAirlineId() != null) {
            user.setPreferredAirlineId(updatedUser.getPreferredAirlineId());
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setStatus("INACTIVE");
        user.setIsActive(false);
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUserStatus(Long id, String status) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setStatus(status);
        user.setIsActive("ACTIVE".equals(status));
        userRepository.save(user);
    }

    @Override
    public void activateUser(Long id) {
        updateUserStatus(id, "ACTIVE");
    }

    @Override
    public void deactivateUser(Long id) {
        updateUserStatus(id, "INACTIVE");
    }

    @Override
    public void updateLoyaltyPoints(Long id, Integer points) {
        User user = getUserById(id);
        Integer currentPoints = user.getLoyaltyPoints() != null ? user.getLoyaltyPoints() : 0;
        user.setLoyaltyPoints(currentPoints + points);
        userRepository.save(user);
    }

    @Override
    public void updateLastSearchedRoute(Long id, String route) {
        User user = getUserById(id);
        user.setLastSearchedRoute(route);
        userRepository.save(user);
    }

    @Override
    public void updatePreferences(Long id, Integer airportId, String seatClass, Integer airlineId) {
        User user = getUserById(id);
        if (airportId != null) {
            user.setPreferredAirportId(airportId);
        }
        if (seatClass != null) {
            user.setPreferredSeatClass(seatClass);
        }
        if (airlineId != null) {
            user.setPreferredAirlineId(airlineId);
        }
        userRepository.save(user);
    }
}