package com.grepp.nbe1_1_clone_mw1.order.controller.dto;

import com.grepp.nbe1_1_clone_mw1.order.model.Order;
import com.grepp.nbe1_1_clone_mw1.order.model.OrderItemInfo;
import com.grepp.nbe1_1_clone_mw1.order.model.OrderStatus;
import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        String orderId,
        String email,
        String address,
        String postcode,
        List<OrderItemInfo> orderItems,
        OrderStatus orderStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public OrderResponse(Order order, List<OrderItemInfo> orderItems) {
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

    public static OrderResponse from(Order order) {
        return new OrderResponse(
                UUIDUtil.bytesToHex(order.getOrderId()),
                order.getEmail(),
                order.getAddress(),
                order.getPostcode(),
                order.getOrderItems().stream()
                        .map(OrderItemInfo::new)
                        .toList(),
                order.getOrderStatus(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }


}
