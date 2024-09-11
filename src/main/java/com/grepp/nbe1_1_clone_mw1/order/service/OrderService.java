package com.grepp.nbe1_1_clone_mw1.order.service;


import com.grepp.nbe1_1_clone_mw1.order.controller.dto.CreateOrderResponse;
import com.grepp.nbe1_1_clone_mw1.order.controller.dto.OrderItemInfo;

import java.util.List;

public interface OrderService {
  CreateOrderResponse createOrder(String email, String address, String postcode, List<OrderItemInfo> orderItems);
}
