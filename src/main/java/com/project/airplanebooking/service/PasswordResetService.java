package com.project.airplanebooking.service;

public interface PasswordResetService {

    /**
     * Tạo và gửi OTP qua email
     * 
     * @param email Email cần gửi OTP
     * @return true nếu gửi thành công
     */
    boolean sendOTP(String email);

    /**
     * Xác minh OTP
     * 
     * @param email Email đã nhập OTP
     * @param otp   Mã OTP đã nhập
     * @return Reset token nếu OTP hợp lệ, null nếu không hợp lệ
     */
    String verifyOTP(String email, String otp);

    /**
     * Đặt lại mật khẩu
     * 
     * @param resetToken  Token dùng để reset mật khẩu
     * @param newPassword Mật khẩu mới
     * @return true nếu đặt lại mật khẩu thành công
     */
    boolean resetPassword(String resetToken, String newPassword);
}