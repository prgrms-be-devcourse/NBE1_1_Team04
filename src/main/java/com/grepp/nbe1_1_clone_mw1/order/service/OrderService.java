package com.grepp.nbe1_1_clone_mw1.order.service;


import com.grepp.nbe1_1_clone_mw1.order.model.Order;
import com.grepp.nbe1_1_clone_mw1.order.model.OrderItemInfo;

import com.grepp.nbe1_1_clone_mw1.order.model.OrderContent;
import java.util.List;

public interface OrderService {
  Order createOrder(OrderContent orderContent, List<OrderItemInfo> orderItems);

  Order getOrder(String orderId);

  void updateOrder(String orderId, String email, OrderContent orderContent);

  void deleteOrder(String orderId, String email);
}
