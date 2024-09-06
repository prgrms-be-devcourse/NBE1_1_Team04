package com.grepp.nbe1_1_team04_mw_1.order.api;

import com.grepp.nbe1_1_team04_mw_1.order.domain.dto.request.OrderRequestDTO;
import com.grepp.nbe1_1_team04_mw_1.order.domain.dto.response.OrderResponseDTO;
import com.grepp.nbe1_1_team04_mw_1.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderApi {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO request) {
        return ResponseEntity.ok(orderService.placeOrder(request));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(new OrderResponseDTO(orderService.getOrder(orderId)));
    }

//    @PutMapping("/{orderId}")
//    public ResponseEntity<String> updateOrder(@PathVariable String orderId, @RequestBody OrderRequestDTO orderRequest) {
//
//    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }


}
