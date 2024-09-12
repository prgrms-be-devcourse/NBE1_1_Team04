package com.grepp.nbe1_1_clone_mw1.order.controller.dto;

import com.grepp.nbe1_1_clone_mw1.order.model.OrderContent;

public record UpdateOrderRequest(
        String email,
        String address,
        String postcode
) {
    public OrderContent toContent() {
        return new OrderContent(
                email,
                address,
                postcode
        );
    }
}
