package com.grepp.nbe1_1_clone_mw1.order.controller.api;


import com.grepp.nbe1_1_clone_mw1.order.controller.dto.CreateOrderRequest;
import com.grepp.nbe1_1_clone_mw1.order.controller.dto.OrderResponse;
import com.grepp.nbe1_1_clone_mw1.order.controller.dto.UpdateOrderRequest;
import com.grepp.nbe1_1_clone_mw1.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/orders")
@RestController
public class OrderRestController {

  private final OrderService orderService;

  public OrderRestController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public OrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
    return OrderResponse.from(orderService.createOrder(
            request.toContent(),
            request.orderItems()
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
