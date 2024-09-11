package com.devcoruse.nbe1_1_team04.order.api;

import com.devcoruse.nbe1_1_team04.order.api.dto.OrderItemResponse;
import com.devcoruse.nbe1_1_team04.order.api.dto.OrderRequest;
import com.devcoruse.nbe1_1_team04.order.api.dto.OrderResponse;
import com.devcoruse.nbe1_1_team04.order.domain.OrderService;
import com.devcoruse.nbe1_1_team04.order.repository.Order;
import com.devcoruse.nbe1_1_team04.order.repository.OrderId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderApi {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.order(request.toContent(), request.orderItems()).toString());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(OrderId.from(orderId));
        return ResponseEntity.ok(OrderResponse.from(order));
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<OrderItemResponse>> getOrderItems(@PathVariable String orderId) {
        List<OrderItemResponse> responses = orderService.getOrderItem(OrderId.from(orderId)).stream()
                .map(OrderItemResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable String orderId, @RequestBody OrderRequest orderRequest) {
        orderService.updateOrder(OrderId.from(orderId), orderRequest.toContent());
        return ResponseEntity.ok("Order updated");
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(OrderId.from(orderId));
        return ResponseEntity.ok("Order deleted");
    }


}
