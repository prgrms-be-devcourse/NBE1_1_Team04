package org.example.coffee.api;

import org.example.coffee.dto.OrderRequest;
import org.example.coffee.dto.OrderResponse;
import org.example.coffee.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/orders")
public class OrderApi {

    private final OrderService orderService;

    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 전체 주문 조히
     * 테스트용
     */
    @GetMapping
    public List<OrderResponse> getProduct() {
        return orderService.findAllOrders();
    }


    /**
     * 특정 주문 조회
     * 이메일로 검증
     */
    @GetMapping("{id}")
    public OrderResponse getProduct(@PathVariable("id") String orderId, @RequestParam("email") String email) {
        return orderService.findOrder(orderId, email);
    }

    /**
     * 주문 추가
     */
    @PostMapping
    public OrderResponse addOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.addOrder(orderRequest);
    }

    /**
     * 주문 수정
     */
    @PutMapping("{id}")
    public OrderResponse editOrder(@PathVariable("id") String orderId, @RequestBody OrderRequest orderRequest) {
        return orderService.editOrder(orderId, orderRequest);
    }


    /**
     * 주문 삭제
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") String orderId) {
        orderService.deleteOrder(orderId);

        return ResponseEntity.ok("ORDER_DELETED");
    }
}
