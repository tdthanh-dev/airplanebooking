package com.project.airplanebooking.service;

public interface EmailService {
    void sendOtpEmail(String to, String otp);
}