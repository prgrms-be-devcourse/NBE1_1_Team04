package com.grepp.nbe1_1_team04_mw_1.order.domain.dto.request;

public record OrderItemInfo(
        byte[] productId,
        int quantity
) {
}
