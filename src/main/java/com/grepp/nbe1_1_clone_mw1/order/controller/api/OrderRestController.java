package com.grepp.nbe1_1_clone_mw1.order.controller.api;


import com.grepp.nbe1_1_clone_mw1.order.controller.dto.CreateAnonymousOrderRequest;
import com.grepp.nbe1_1_clone_mw1.order.controller.dto.CreateOrderRequest;
import com.grepp.nbe1_1_clone_mw1.order.controller.dto.CreateOrderResponse;
import com.grepp.nbe1_1_clone_mw1.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

  private final OrderService orderService;

  public OrderRestController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("/api/v1/orders")
  public CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest orderRequest) {
    return orderService.createOrder(
            orderRequest.userDetail().getEmail(),
            orderRequest.orderItems()
    );
  }

  @PostMapping("/api/v1/orders/anonymous")
  public CreateOrderResponse createAnonymousOrder(@Valid @RequestBody CreateAnonymousOrderRequest orderRequest) {
    return orderService.createAnonymousOrder(
            orderRequest.email(),
            orderRequest.address(),
            orderRequest.postcode(),
            orderRequest.orderItems()
    );
  }
}
