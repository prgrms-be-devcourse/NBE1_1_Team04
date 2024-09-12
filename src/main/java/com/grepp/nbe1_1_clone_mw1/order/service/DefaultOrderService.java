package com.grepp.nbe1_1_clone_mw1.order.service;


import com.grepp.nbe1_1_clone_mw1.order.controller.dto.OrderResponse;
import com.grepp.nbe1_1_clone_mw1.order.model.OrderItemInfo;
import com.grepp.nbe1_1_clone_mw1.order.model.Order;
import com.grepp.nbe1_1_clone_mw1.order.model.OrderContent;
import com.grepp.nbe1_1_clone_mw1.order.model.OrderItem;
import com.grepp.nbe1_1_clone_mw1.product.model.Product;
import com.grepp.nbe1_1_clone_mw1.order.repository.OrderItemRepository;
import com.grepp.nbe1_1_clone_mw1.order.repository.OrderRepository;
import com.grepp.nbe1_1_clone_mw1.product.repository.ProductRepository;
import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  @Transactional
  @Override
  public Order createOrder(OrderContent orderContent, List<OrderItemInfo> orderItemInfos) {

    List<OrderItem> orderItems = orderItemInfos.stream().map(orderItemInfo -> {
      Product product = getProductById(orderItemInfo.productId());
      return OrderItem.create(orderItemInfo, product);
    }).toList();

    return orderRepository.save(Order.create(orderContent, orderItems));
  }

  @Override
  public Order getOrder(String orderId) {
    return getOrderById(orderId);
  }

  @Override
  @Transactional
  public void updateOrder(String orderId, String email, @Valid OrderContent content) {
    Order order = getOrderById(orderId);
    if (!order.isOrderer(email)) {
      throw new RuntimeException("Not allowed to update order");
    }
    order.update(content);
  }

  @Override
  public void deleteOrder(String orderId, String email) {
    Order order = getOrderById(orderId);
    if (!order.isOrderer(email)) {
      throw new RuntimeException("Not allowed to delete order");
    }
    orderRepository.delete(order);
  }

  private Order getOrderById(String orderId) {
    return orderRepository.findById(UUIDUtil.hexStringToByteArray(orderId)).orElseThrow(() -> new EntityNotFoundException("Order not found"));
  }

  private Product getProductById(String productId) {
    return productRepository.findById(UUIDUtil.hexStringToByteArray(productId))
            .orElseThrow(() -> new RuntimeException("Product not found"));
  }
}
