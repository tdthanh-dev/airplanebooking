package com.project.airplanebooking.service.impl;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.project.airplanebooking.model.PasswordResetToken;
import com.project.airplanebooking.model.User;
import com.project.airplanebooking.repository.PasswordResetTokenRepository;
import com.project.airplanebooking.repository.UserRepository;
import com.project.airplanebooking.service.EmailService;
import com.project.airplanebooking.security.JwtTokenProvider;
import com.project.airplanebooking.service.PasswordResetService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    private static final Random RANDOM = new SecureRandom();
    private static final int OTP_LENGTH = 6;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${app.password-reset.otp.expiration:600000}")
    private long otpExpirationMs;

    @Value("${app.password-reset.token.expiration:3600000}")
    private long tokenExpirationMs;

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Override
    public boolean sendOTP(String email) {
        // Kiểm tra email có tồn tại trong hệ thống không
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return false;
        }

        // Tạo mã OTP ngẫu nhiên 6 chữ số
        String otp = generateOTP();

        // Lưu thông tin OTP vào database
        PasswordResetToken token = PasswordResetToken.builder()
                .email(email)
                .otp(otp)
                .createdAt(LocalDateTime.now())
                .expiryDate(LocalDateTime.now().plusSeconds(otpExpirationMs / 1000))
                .used(false)
                .build();

        passwordResetTokenRepository.save(token);

        // Gửi OTP qua email
        emailService.sendOtpEmail(email, otp);

        return true;
    }

    @Override
    public String verifyOTP(String email, String otp) {
        // Tìm OTP hợp lệ của email
        Optional<PasswordResetToken> tokenOptional = passwordResetTokenRepository
                .findByEmailAndOtpAndUsedFalseAndVerifiedAtIsNull(email, otp);

        if (tokenOptional.isEmpty()) {
            return null;
        }

        PasswordResetToken token = tokenOptional.get();

        // Kiểm tra OTP có hết hạn không
        if (token.isExpired()) {
            return null;
        }

        // Cập nhật thời gian xác minh
        token.setVerifiedAt(LocalDateTime.now());
        passwordResetTokenRepository.save(token);

        // Tạo reset token dưới dạng JWT (gắn thêm token_id để đảm bảo 1 lần sử dụng)
        Map<String, Object> claims = new HashMap<>();
        claims.put("purpose", "password_reset");
        claims.put("token_id", token.getId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new java.util.Date())
                .setExpiration(new java.util.Date(System.currentTimeMillis() + tokenExpirationMs))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean resetPassword(String resetToken, String newPassword) {
        // Trích xuất email từ token và kiểm tra tính hợp lệ
        String email;
        try {
            email = jwtTokenProvider.getUsernameFromJWT(resetToken);
            if (email == null) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        // Kiểm tra token có hợp lệ không
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();

        if (!jwtTokenProvider.validateToken(resetToken)) {
            return false;
        }

        // Lấy thông tin token trong DB
        Long tokenId;
        try {
            tokenId = ((Number) Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                    .build()
                    .parseClaimsJws(resetToken)
                    .getBody()
                    .get("token_id"))
                    .longValue();
        } catch (Exception e) {
            return false;
        }

        Optional<PasswordResetToken> prtOptional = passwordResetTokenRepository.findById(tokenId);
        if (prtOptional.isEmpty()) {
            return false;
        }

        PasswordResetToken prt = prtOptional.get();
        if (prt.isExpired() || prt.isUsed()) {
            return false;
        }

        // Đánh dấu token đã sử dụng
        prt.setUsed(true);
        passwordResetTokenRepository.save(prt);

        // Cập nhật mật khẩu đã hash
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return true;
    }

    /**
     * Tạo mã OTP ngẫu nhiên
     * 
     * @return mã OTP có 6 chữ số
     */
    private String generateOTP() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(RANDOM.nextInt(10));
        }
        return otp.toString();
    }
}