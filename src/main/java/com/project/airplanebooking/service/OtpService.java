package com.project.airplanebooking.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.airplanebooking.model.OtpEntity;
import com.project.airplanebooking.model.User;
import com.project.airplanebooking.repository.OtpRepository;
import com.project.airplanebooking.repository.UserRepository;
import com.project.airplanebooking.exception.BadRequestException;
import com.project.airplanebooking.exception.ResourceNotFoundException;

@Service
public class OtpService {

    private final OtpRepository otpRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private static final int OTP_EXPIRY_MINUTES = 5;

    @Autowired
    public OtpService(OtpRepository otpRepository, EmailService emailService, UserRepository userRepository) {
        this.otpRepository = otpRepository;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    public void sendOtp(String email) {
        // Kiểm tra email có tồn tại trong hệ thống không
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản với email: " + email));

        // Tạo OTP mới
        String otp = generateOtp();

        // Kiểm tra xem đã có OTP nào cho email này chưa
        Optional<OtpEntity> existingOtp = otpRepository.findTopByEmailOrderByCreatedAtDesc(email);

        // Nếu có, kiểm tra thời gian tạo, tránh spam
        if (existingOtp.isPresent() &&
                LocalDateTime.now().minusMinutes(1).isBefore(existingOtp.get().getCreatedAt())) {
            throw new BadRequestException("Vui lòng đợi 1 phút trước khi yêu cầu OTP mới");
        }

        // Lưu OTP vào database
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES));
        otpEntity.setUsed(false);
        otpRepository.save(otpEntity);

        // Gửi OTP qua email
        String subject = "Mã xác thực đăng nhập";
        String body = "Mã xác thực của bạn là: " + otp + "\nMã này có hiệu lực trong " + OTP_EXPIRY_MINUTES + " phút.";
        emailService.sendEmail(email, subject, body);
    }

    public boolean verifyOtp(String email, String otp) {
        OtpEntity otpEntity = otpRepository.findByEmailAndOtpAndUsedFalse(email, otp)
                .orElseThrow(() -> new BadRequestException("Mã OTP không hợp lệ hoặc đã hết hạn"));

        if (otpEntity.isExpired()) {
            throw new BadRequestException("Mã OTP đã hết hạn");
        }

        // Đánh dấu OTP đã được sử dụng
        otpEntity.setUsed(true);
        otpRepository.save(otpEntity);

        return true;
    }

    private String generateOtp() {
        // Tạo OTP 6 chữ số
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }
}