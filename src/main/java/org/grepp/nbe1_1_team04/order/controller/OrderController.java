package org.grepp.nbe1_1_team04.order.controller;

import org.grepp.nbe1_1_team04.order.dto.OrderRequest;
import org.grepp.nbe1_1_team04.order.dto.OrderResponse;
import org.grepp.nbe1_1_team04.order.service.OrderService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(@RequestParam String email) {
        List<OrderResponse> orderList = orderService.getOrders(email);
        return ResponseEntity.ok().body(orderList);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable byte[] orderId) {
        OrderResponse orderResponse = orderService.getOrder(orderId);
        return ResponseEntity.ok().body(orderResponse);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<HttpStatus> updateOrder(@RequestBody OrderRequest orderRequest, @PathVariable byte[] orderId) {
        orderService.updateOrder(orderRequest, orderId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable byte[] orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
