package com.grepp.nbe1_1_clone_mw1.payment.toss.controller.dto;

public record SaveAmountRequest(
        String orderId,
        String amount
) {
}
