package com.grepp.nbe1_1_clone_mw1.order.controller.dto;

import com.grepp.nbe1_1_clone_mw1.auth.model.CustomUserDetail;
import com.grepp.nbe1_1_clone_mw1.global.annotation.LoginUser;

import java.util.List;

public record CreateOrderRequest(@LoginUser CustomUserDetail userDetail, List<OrderItemInfo> orderItems) {
}
