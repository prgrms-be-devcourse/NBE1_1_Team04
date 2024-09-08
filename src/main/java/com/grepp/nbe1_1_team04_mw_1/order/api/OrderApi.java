package com.grepp.nbe1_1_team04_mw_1.order.api;

import com.grepp.nbe1_1_team04_mw_1.order.domain.dto.request.OrderRequestDTO;
import com.grepp.nbe1_1_team04_mw_1.order.domain.dto.response.OrderResponseDTO;
import com.grepp.nbe1_1_team04_mw_1.order.domain.entity.Orders;
import com.grepp.nbe1_1_team04_mw_1.order.domain.service.OrderService;
import com.grepp.nbe1_1_team04_mw_1.order_item.domain.entity.OrderItems;
import com.grepp.nbe1_1_team04_mw_1.order_item.domain.service.OrderItemService;
import com.grepp.nbe1_1_team04_mw_1.product.domain.dto.response.ProductUpdateResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderApi {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        //DTO를 서비스 단 까지 넘겨도 되는지??
        return ResponseEntity.ok(orderService.placeOrder(orderRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<Orders> orders = orderService.getAllOrders();
        List<OrderResponseDTO> orderResponseDTOs = orders.stream().map(OrderResponseDTO::new).toList();
        return ResponseEntity.ok(orderResponseDTOs);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(new OrderResponseDTO(orderService.getOrder(orderId)));
    }

    @GetMapping("/update/{orderId}")
    public ResponseEntity<List<ProductUpdateResponseDTO>> updateOrder(@PathVariable String orderId) {
        List<OrderItems> orderItems = orderItemService.getOrderItems(orderId);
        List<ProductUpdateResponseDTO> productUpdateResponseDTOS = orderItems.stream().map(ProductUpdateResponseDTO::new).toList();
        return ResponseEntity.ok(productUpdateResponseDTOS);
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable String orderId, @RequestBody OrderRequestDTO orderRequest) {
        return ResponseEntity.ok(orderService.updateOrder(orderId, orderRequest));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }
}
