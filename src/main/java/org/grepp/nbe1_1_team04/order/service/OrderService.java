package org.grepp.nbe1_1_team04.order.service;

import org.grepp.nbe1_1_team04.order.dto.OrderRequest;
import org.grepp.nbe1_1_team04.order.dto.OrderResponse;
import org.grepp.nbe1_1_team04.order.repository.OrderRepository;
import org.grepp.nbe1_1_team04.util.UUIDUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private UUIDUtil uuidUtil;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(OrderRequest orderRequest) {

    }

    public List<OrderResponse> getOrders() {
        return null;
    }

    public OrderResponse getOrder(byte[] orderId) {
        return null;
    }

    public void updateOrder(OrderRequest orderRequest, byte[] orderId) {

    }

    public void deleteOrder(byte[] orderId) {

    }
}
