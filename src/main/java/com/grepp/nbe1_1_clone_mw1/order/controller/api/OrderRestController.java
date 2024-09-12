package com.grepp.nbe1_1_clone_mw1.order.controller.api;


import com.grepp.nbe1_1_clone_mw1.auth.model.CustomUserDetail;
import com.grepp.nbe1_1_clone_mw1.global.annotation.LoginUser;
import com.grepp.nbe1_1_clone_mw1.order.controller.dto.CreateAnonymousOrderRequest;
import com.grepp.nbe1_1_clone_mw1.order.controller.dto.CreateOrderRequest;
import com.grepp.nbe1_1_clone_mw1.order.controller.dto.OrderResponse;
import com.grepp.nbe1_1_clone_mw1.order.controller.dto.UpdateOrderRequest;
import com.grepp.nbe1_1_clone_mw1.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/orders")
@RestController
public class OrderRestController {

  private final OrderService orderService;

  public OrderRestController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public OrderResponse createOrder(@LoginUser CustomUserDetail user, @Valid @RequestBody CreateOrderRequest request) {
    return OrderResponse.from(orderService.createOrder(
            user.getEmail(),
            request.orderItems()
    ));

  }

  @PostMapping("/anonymous")
  public OrderResponse createAnonymousOrder(@Valid @RequestBody CreateAnonymousOrderRequest orderRequest) {
    return OrderResponse.from(orderService.createAnonymousOrder(
            orderRequest.toContent(),
            orderRequest.orderItems()
    ));
  }

  @GetMapping("/{orderId}")
  public OrderResponse getOrder(@PathVariable String orderId) {
    return OrderResponse.from(orderService.getOrder(orderId));
  }

  @PutMapping("/{orderId}")
  public void updateOrder(@PathVariable String orderId,
          @RequestParam String email,
          @Valid @RequestBody UpdateOrderRequest request) {
      orderService.updateOrder(orderId, email, request.toContent());
  }

  @DeleteMapping("/{orderId}")
  public void deleteOrder(@PathVariable String orderId, @RequestParam String email) {
      orderService.deleteOrder(orderId, email);
  }


}
