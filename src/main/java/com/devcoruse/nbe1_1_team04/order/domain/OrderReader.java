package com.devcoruse.nbe1_1_team04.order.domain;

import com.devcoruse.nbe1_1_team04.order.repository.Order;
import com.devcoruse.nbe1_1_team04.order.repository.OrderId;
import com.devcoruse.nbe1_1_team04.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderReader {

    private final OrderRepository orderRepository;

    public Order read(OrderId orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }
}
