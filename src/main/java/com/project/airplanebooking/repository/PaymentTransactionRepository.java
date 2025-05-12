package com.project.airplanebooking.repository;

import com.project.airplanebooking.model.PaymentTransaction;
import com.project.airplanebooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
    List<PaymentTransaction> findByBooking(Booking booking);

    List<PaymentTransaction> findByStatus(String status);

}
