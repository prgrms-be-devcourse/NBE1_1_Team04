package com.grepp.nbe1_1_team04_mw_1.order.domain.repository;

import com.grepp.nbe1_1_team04_mw_1.order.domain.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, byte[]> {
    Optional<Orders> findTopByEmailAndCreatedAtAfter(String email, LocalDateTime createdDate);

    Optional<Orders> findByOrderId(byte[] orderId);

    void deleteByOrderId(byte[] orderId);
}
