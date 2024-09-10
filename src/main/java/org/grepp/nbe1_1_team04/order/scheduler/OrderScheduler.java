package org.grepp.nbe1_1_team04.order.scheduler;

import org.grepp.nbe1_1_team04.order.entity.OrderEntity;
import org.grepp.nbe1_1_team04.order.entity.OrderStatus;
import org.grepp.nbe1_1_team04.order.repository.OrderRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderScheduler {
    private OrderRepository orderRepository;

    public OrderScheduler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Scheduled(cron = "0 0 14 0 0 ?")
    public void startDelivery() {
        List<OrderEntity> orderEntities = orderRepository.findByOrderStatus(OrderStatus.ORDERED);
        orderEntities.forEach(orderEntity -> {
            orderEntity.updateOrderStatus(OrderStatus.DELIVERING);
        });
    }
}
