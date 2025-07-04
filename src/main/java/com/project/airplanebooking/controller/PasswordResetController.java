package com.project.airplanebooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.airplanebooking.dto.request.OtpRequest;
import com.project.airplanebooking.dto.request.OtpVerificationRequest;
import com.project.airplanebooking.dto.request.ResetPasswordRequest;
import com.project.airplanebooking.service.PasswordResetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/password-reset")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @Autowired
    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    /**
     * Gửi OTP tới email của người dùng
     */
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@Valid @RequestBody OtpRequest otpRequest) {
        boolean sent = passwordResetService.sendOTP(otpRequest.getEmail());
        if (sent) {
            return ResponseEntity.ok("OTP đã được gửi thành công");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email không tồn tại trong hệ thống");
    }

    /**
     * Xác minh OTP và trả về reset token (JWT) nếu hợp lệ
     */
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@Valid @RequestBody OtpVerificationRequest request) {
        String resetToken = passwordResetService.verifyOTP(request.getEmail(), request.getOtp());
        if (resetToken == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP không hợp lệ hoặc đã hết hạn");
        }
        return ResponseEntity.ok(resetToken);
    }

    /**
     * Đặt lại mật khẩu mới bằng reset token
     */
    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        boolean success = passwordResetService.resetPassword(request.getResetToken(), request.getNewPassword());
        if (success) {
            return ResponseEntity.ok("Đặt lại mật khẩu thành công");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token không hợp lệ hoặc đã hết hạn");
    }
}