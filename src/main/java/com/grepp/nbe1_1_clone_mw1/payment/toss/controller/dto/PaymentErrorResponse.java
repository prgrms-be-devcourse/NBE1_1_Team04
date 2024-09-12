package com.grepp.nbe1_1_clone_mw1.payment.toss.controller.dto;

import lombok.Builder;

@Builder
public record PaymentErrorResponse(
        int code,
        String message
) {
}
