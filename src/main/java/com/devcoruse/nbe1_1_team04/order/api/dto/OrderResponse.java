package com.devcoruse.nbe1_1_team04.order.api.dto;

import com.devcoruse.nbe1_1_team04.order.repository.Order;

public record OrderResponse(
        String orderId,
        String email,
        String address,
        Integer zipCode,
        String orderStatus
) {

    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getOrderId().toString(),
                order.getEmail(),
                order.getAddress().getAddress(),
                order.getAddress().getZipCode(),
                order.getOrderStatus().name()
        );
    }
}
