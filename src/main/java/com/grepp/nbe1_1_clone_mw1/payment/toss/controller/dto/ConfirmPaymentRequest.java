package com.grepp.nbe1_1_clone_mw1.payment.toss.controller.dto;

public record ConfirmPaymentRequest(
        String backendOrderId,
        String orderId,
        String amount,
        String paymentKey
) {
}
