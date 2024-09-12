package com.grepp.nbe1_1_clone_mw1.order.service;

import com.grepp.nbe1_1_clone_mw1.order.controller.dto.CreateOrderRequest;
import com.grepp.nbe1_1_clone_mw1.order.controller.dto.CreateOrderResponse;
import com.grepp.nbe1_1_clone_mw1.order.controller.dto.OrderItemInfo;
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
import com.grepp.nbe1_1_clone_mw1.user.model.User;
import com.grepp.nbe1_1_clone_mw1.user.repository.UserRepository;
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
  private final UserRepository userRepository;



  @Transactional
  @Override
  public CreateOrderResponse createAnonymousOrder(String email, String address, String postcode, List<OrderItemInfo> orderItems) {
    return saveOrderInformation(email, address, postcode, orderItems);

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

  private CreateOrderResponse saveOrderInformation(String email, String address, String postcode, List<OrderItemInfo> orderItems) {
    Order newOrder = orderRepository.save(Order.create(email, address, postcode));
    List<OrderItem> orderItemList = new ArrayList<>();
    orderItems.stream().forEach(orderItem -> {
      Product product = productRepository.findById(UUIDUtil.hexStringToByteArray(orderItem.productId()))
              .orElseThrow(() -> new RuntimeException("Product not found"));
      orderItemList.add(orderItemRepository.save(OrderItem.create(orderItem.category(), orderItem.price(), orderItem.quantity(), product, newOrder)));
    });
    return new CreateOrderResponse(newOrder, orderItemList.stream().map(OrderItemInfo::new).toList());
  }
}
