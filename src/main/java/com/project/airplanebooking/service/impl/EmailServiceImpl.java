package com.project.airplanebooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.project.airplanebooking.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendOtpEmail(String to, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("Mã xác thực VeYu");

            String htmlContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto;'>"
                    + "<h2 style='color: #2E8B57;'>VeYu - Xác thực</h2>"
                    + "<p>Xin chào,</p>"
                    + "<p>Chúng tôi đã nhận được yêu cầu xác thực cho tài khoản của bạn. "
                    + "Vui lòng sử dụng mã OTP sau để xác nhận:</p>"
                    + "<div style='background-color: #f0f0f0; padding: 15px; text-align: center; "
                    + "font-size: 24px; font-weight: bold; letter-spacing: 5px; margin: 20px 0;'>"
                    + otp
                    + "</div>"
                    + "<p>Mã OTP này sẽ hết hạn sau 10 phút.</p>"
                    + "<p>Nếu bạn không yêu cầu xác thực, vui lòng bỏ qua email này.</p>"
                    + "<p>Trân trọng,<br>Đội ngũ VeYu</p>"
                    + "</div>";

            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.info("Đã gửi OTP {} đến email {}", otp, to);
        } catch (MessagingException e) {
            log.error("Lỗi khi gửi email: {}", e.getMessage());
        }
    }

}
