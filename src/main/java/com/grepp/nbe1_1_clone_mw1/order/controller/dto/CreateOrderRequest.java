package com.grepp.nbe1_1_clone_mw1.order.controller.dto;

import com.grepp.nbe1_1_clone_mw1.auth.model.CustomUserDetail;
import com.grepp.nbe1_1_clone_mw1.global.annotation.LoginUser;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(@LoginUser @NotNull CustomUserDetail userDetail, List<OrderItemInfo> orderItems) {
}
