package com.grepp.nbe1_1_clone_mw1.payment.toss.model;

import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;
import com.grepp.nbe1_1_clone_mw1.order.model.Order;
import com.grepp.nbe1_1_clone_mw1.payment.toss.controller.dto.ConfirmPaymentResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TossPayment {
    @Id
    byte[] paymentId;

    @Column(nullable = false, unique = true)
    String tossPaymentKey;

    // 토스내부에서 관리하는 별도의 orderId가 존재함
    @Column(nullable = false)
    String tossOrderId;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    Order order;

    long totalAmount;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    TossPaymentMethod tossPaymentMethod;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    TossPaymentStatus tossPaymentStatus;

    @Column(nullable = false)
    LocalDateTime requestedAt;

    LocalDateTime approvedAt;

    public static TossPayment create(
            String tossPaymentKey,
            String tossOrderId,
            Order order,
            long totalAmount,
            TossPaymentMethod tossPaymentMethod,
            TossPaymentStatus tossPaymentStatus,
            LocalDateTime requestedAt,
            LocalDateTime approvedAt
    ) {
        return TossPayment.builder()
                .paymentId(UUIDUtil.createUUID())
                .tossPaymentKey(tossPaymentKey)
                .order(order)
                .tossOrderId(tossOrderId)
                .totalAmount(totalAmount)
                .tossPaymentMethod(tossPaymentMethod)
                .tossPaymentStatus(tossPaymentStatus)
                .requestedAt(requestedAt)
                .approvedAt(approvedAt)
                .build();
    }

    public ConfirmPaymentResponse toResponse() {
        return ConfirmPaymentResponse.create(UUIDUtil.bytesToHex(order.getOrderId()), tossPaymentMethod, tossPaymentStatus, totalAmount);
    }

    public void changePaymentStatus(TossPaymentStatus newStatus) {
        this.tossPaymentStatus = newStatus;
    }
}
