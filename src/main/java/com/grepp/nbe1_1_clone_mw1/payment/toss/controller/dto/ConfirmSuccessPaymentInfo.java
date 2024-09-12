package com.grepp.nbe1_1_clone_mw1.payment.toss.controller.dto;

import com.grepp.nbe1_1_clone_mw1.order.model.Order;
import com.grepp.nbe1_1_clone_mw1.payment.toss.model.TossPayment;
import com.grepp.nbe1_1_clone_mw1.payment.toss.model.TossPaymentMethod;
import com.grepp.nbe1_1_clone_mw1.payment.toss.model.TossPaymentStatus;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record ConfirmSuccessPaymentInfo(
    String backendOrderId,
    String tossOrderId,
    long totalAmount,
    String tossPaymentKey,
    TossPaymentMethod tossPaymentMethod,
    TossPaymentStatus tossPaymentStatus,
    LocalDateTime requestedAt,
    LocalDateTime approvedAt
) {
    public static ConfirmSuccessPaymentInfo create(String backendOrderId, String tossOrderId, String tossPaymentKey, TossPaymentMethod tossPaymentMethod, TossPaymentStatus tossPaymentStatus, long totalAmount, LocalDateTime requestedAt, LocalDateTime approvedAt) {
        return ConfirmSuccessPaymentInfo.builder()
                .backendOrderId(backendOrderId)
                .tossOrderId(tossOrderId)
                .tossPaymentKey(tossPaymentKey)
                .tossPaymentMethod(tossPaymentMethod)
                .tossPaymentStatus(tossPaymentStatus)
                .totalAmount(totalAmount)
                .requestedAt(requestedAt)
                .approvedAt(approvedAt)
                .build();
    }

    public TossPayment toTossPayment(Order order) {
        return TossPayment.create(
                tossPaymentKey,
                tossOrderId,
                order,
                totalAmount,
                tossPaymentMethod,
                tossPaymentStatus,
                requestedAt,
                approvedAt
        );
    }
}
