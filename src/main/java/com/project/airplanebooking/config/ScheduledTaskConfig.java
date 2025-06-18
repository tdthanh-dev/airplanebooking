package com.project.airplanebooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.project.airplanebooking.service.SeatService;

@Configuration
@EnableScheduling
public class ScheduledTaskConfig {

    @Autowired
    private SeatService seatService;

    /**
     * Kiểm tra và cập nhật trạng thái ghế từ HOLD sang AVAILABLE nếu đã quá 15 phút
     * Chạy mỗi 5 phút
     */
    @Scheduled(fixedRate = 300000) // 5 phút = 300,000 ms
    public void checkExpiredHoldSeats() {
        seatService.checkAndUpdateExpiredHoldSeats();
    }
}