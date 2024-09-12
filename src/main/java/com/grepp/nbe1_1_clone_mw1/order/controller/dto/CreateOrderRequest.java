package com.grepp.nbe1_1_clone_mw1.order.controller.dto;

import com.grepp.nbe1_1_clone_mw1.order.model.OrderItemInfo;

import java.util.List;

public record CreateOrderRequest(
        List<OrderItemInfo> orderItems
) {

}
