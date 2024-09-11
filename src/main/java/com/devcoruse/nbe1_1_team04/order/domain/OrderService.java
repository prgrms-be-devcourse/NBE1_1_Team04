package com.devcoruse.nbe1_1_team04.order.domain;

import com.devcoruse.nbe1_1_team04.order.repository.Order;
import com.devcoruse.nbe1_1_team04.order.repository.OrderId;
import com.devcoruse.nbe1_1_team04.order.repository.OrderItem;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderAppender orderAppender;
    private final OrderReader orderReader;
    private final OrderUpdater orderUpdater;
    private final OrderRemover orderRemover;
    private final OrderItemReader orderItemReader;
    private final OrderItemAppender orderItemAppender;

    @Transactional
    public OrderId order(OrderContent content, List<OrderItemInfo> orderItemInfos) {
        OrderId orderId = orderAppender.append(content);
        orderItemAppender.append(orderId, orderItemInfos);
        return orderId;
    }

    public Order getOrder(OrderId orderId) {
        return orderReader.read(orderId);
    }

    @Transactional
    public void updateOrder(OrderId orderId, OrderContent updateContent) {
        Order order = orderReader.read(orderId);
        orderUpdater.update(order, updateContent);
    }

    public void deleteOrder(OrderId orderId) {
        Order order = orderReader.read(orderId);
        orderRemover.remove(order);
    }

    public List<OrderItem> getOrderItem(OrderId orderId) {
        return orderItemReader.read(orderId);
    }
}
