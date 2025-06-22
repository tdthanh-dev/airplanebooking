package com.project.airplanebooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.airplanebooking.model.OtpEntity;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, Long> {

    Optional<OtpEntity> findByEmailAndOtpAndUsedFalse(String email, String otp);

    Optional<OtpEntity> findTopByEmailOrderByCreatedAtDesc(String email);
}