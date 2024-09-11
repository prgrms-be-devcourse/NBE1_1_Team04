package com.grepp.nbe1_1_clone_mw1.payment.toss.controller.dto;

import com.grepp.nbe1_1_clone_mw1.payment.toss.model.TossPaymentMethod;
import com.grepp.nbe1_1_clone_mw1.payment.toss.model.TossPaymentStatus;
import lombok.Builder;

@Builder
public record ConfirmPaymentResponse(
        String backendOrderId,
        TossPaymentMethod tossPaymentMethod,
        TossPaymentStatus tossPaymentStatus,
        long totalAmount
) {



    public static ConfirmPaymentResponse create(String backendOrderId, TossPaymentMethod tossPaymentMethod, TossPaymentStatus tossPaymentStatus, long totalAmount) {
        return ConfirmPaymentResponse.builder()
                .backendOrderId(backendOrderId)
                .tossPaymentMethod(tossPaymentMethod)
                .tossPaymentStatus(tossPaymentStatus)
                .totalAmount(totalAmount)
                .build();
    }
}
