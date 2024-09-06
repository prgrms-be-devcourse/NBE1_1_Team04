package com.grepp.nbe1_1_team04_mw_1.order.domain.dto.request;

public record OrderItemInfo(
        // String 형식으로 주기 위해 Base64 인코딩과 디코딩 과정을 거쳐야함
        String productId,
        int quantity
) {
}
