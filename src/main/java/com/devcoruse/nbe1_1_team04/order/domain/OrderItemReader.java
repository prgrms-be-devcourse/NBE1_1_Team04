package com.devcoruse.nbe1_1_team04.order.domain;

import com.devcoruse.nbe1_1_team04.order.repository.OrderId;
import com.devcoruse.nbe1_1_team04.order.repository.OrderItem;
import com.devcoruse.nbe1_1_team04.order.repository.OrderItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemReader {

    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> read(OrderId orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

}
