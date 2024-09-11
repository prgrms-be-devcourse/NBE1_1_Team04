package com.grepp.nbe1_1_clone_mw1.order.controller.dto;

import com.grepp.nbe1_1_clone_mw1.order.model.Order;
import com.grepp.nbe1_1_clone_mw1.order.model.OrderStatus;
import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;

import java.time.LocalDateTime;
import java.util.List;

public record CreateOrderResponse(
        String orderId,
        String email,
        String address,
        String postcode,
        List<OrderItemInfo> orderItems,
        OrderStatus orderStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public CreateOrderResponse(Order order, List<OrderItemInfo> orderItems) {
        this(
                UUIDUtil.bytesToHex(order.getOrderId()),
                order.getEmail(),
                order.getAddress(),
                order.getPostcode(),
                orderItems,
                order.getOrderStatus(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}
