package com.grepp.nbe1_1_clone_mw1.order.controller.dto;

import jakarta.validation.constraints.Email;

import java.util.List;

public record CreateAnonymousOrderRequest(
        @Email String email, String address, String postcode, List<OrderItemInfo> orderItems
) {
}
