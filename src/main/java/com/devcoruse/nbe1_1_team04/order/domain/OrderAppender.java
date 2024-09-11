package com.devcoruse.nbe1_1_team04.order.domain;


import com.devcoruse.nbe1_1_team04.order.repository.Order;
import com.devcoruse.nbe1_1_team04.order.repository.OrderId;
import com.devcoruse.nbe1_1_team04.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderAppender {

    private final OrderRepository orderRepository;

    public OrderId append(OrderContent content) {
        Order order = Order.create(content);
        orderRepository.save(order);
        return order.getOrderId();
    }



}
