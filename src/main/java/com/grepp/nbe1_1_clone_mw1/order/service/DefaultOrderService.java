package com.grepp.nbe1_1_clone_mw1.order.service;


import com.grepp.nbe1_1_clone_mw1.order.controller.dto.CreateOrderRequest;
import com.grepp.nbe1_1_clone_mw1.order.controller.dto.CreateOrderResponse;
import com.grepp.nbe1_1_clone_mw1.order.controller.dto.OrderItemInfo;
import com.grepp.nbe1_1_clone_mw1.order.model.Order;
import com.grepp.nbe1_1_clone_mw1.order.model.OrderItem;
import com.grepp.nbe1_1_clone_mw1.product.model.Product;
import com.grepp.nbe1_1_clone_mw1.order.repository.OrderItemRepository;
import com.grepp.nbe1_1_clone_mw1.order.repository.OrderRepository;
import com.grepp.nbe1_1_clone_mw1.product.repository.ProductRepository;
import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;
import com.grepp.nbe1_1_clone_mw1.user.model.User;
import com.grepp.nbe1_1_clone_mw1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  @Transactional
  @Override
  public CreateOrderResponse createOrder(String email, List<OrderItemInfo> orderItems) {
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    return saveOrderInformation(email, user.getAddress(), user.getPostCode(), orderItems);
  }

  @Transactional
  @Override
  public CreateOrderResponse createAnonymousOrder(String email, String address, String postcode, List<OrderItemInfo> orderItems) {
    return saveOrderInformation(email, address, postcode, orderItems);

//    Order newOrder = Order.create(email, address, postcode);
//    List<OrderItem> orderItemList = new ArrayList<>();
//
//    orderItems.stream().forEach(orderItem -> {
//      Product product = productRepository.findById(UUIDUtil.hexStringToByteArray(orderItem.productId()))
//              .orElseThrow(() -> new RuntimeException("Product not found"));
//      orderItemList.add(OrderItem.create(orderItem.category(), orderItem.price(), orderItem.quantity(), product, newOrder));
//    });
//    newOrder.updateOrderItems(orderItemList);
//    orderRepository.save(newOrder);
//    return new CreateOrderResponse(newOrder, orderItemList.stream().map(OrderItemInfo::new).toList());
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
