package com.project.airplanebooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.airplanebooking.model.PasswordResetToken;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByEmailAndOtpAndUsedFalseAndVerifiedAtIsNull(String email, String otp);

    Optional<PasswordResetToken> findTopByEmailOrderByCreatedAtDesc(String email);
}