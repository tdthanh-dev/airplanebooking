package com.project.airplanebooking.service;

import com.project.airplanebooking.dto.request.RegisterRequest;
import com.project.airplanebooking.model.User;

import java.util.List;

public interface UserService {
    User createUser(RegisterRequest registerRequest);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    void updateUserStatus(Long id, String status);

    void activateUser(Long id);

    void deactivateUser(Long id);
}