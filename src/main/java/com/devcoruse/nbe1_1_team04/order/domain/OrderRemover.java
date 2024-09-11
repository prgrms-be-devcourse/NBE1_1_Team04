package com.devcoruse.nbe1_1_team04.order.domain;

import com.devcoruse.nbe1_1_team04.order.repository.Order;
import com.devcoruse.nbe1_1_team04.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderRemover {

    private final OrderRepository orderRepository;

    public void remove(Order order) {
        orderRepository.delete(order);
    }


}
