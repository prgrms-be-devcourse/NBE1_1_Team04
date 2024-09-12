package com.grepp.nbe1_1_clone_mw1.payment.toss.reposiotry;

import com.grepp.nbe1_1_clone_mw1.payment.toss.model.TossPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TossPaymentRepository extends JpaRepository<TossPayment, byte[]> {

    Optional<TossPayment> findByOrder_OrderId(byte[] orderId);
    Optional<TossPayment> findByTossPaymentKey(String tossPaymentKey);
}
