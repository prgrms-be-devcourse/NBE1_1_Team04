package com.grepp.nbe1_1_clone_mw1.order.service;

import com.grepp.nbe1_1_clone_mw1.order.model.Order;
import com.grepp.nbe1_1_clone_mw1.order.repository.OrderRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderScheduler {

    private final OrderRepository orderRepository;

    @Scheduled(cron = "0 0 14 * * *")
    @SchedulerLock(name = "delivery", lockAtMostFor = "PT2S", lockAtLeastFor = "PT2S")
    @Transactional
    public void delivery() {
        orderRepository.findByCreatedAtBetween(getDeadlineTime().minusDays(1), getDeadlineTime())
                .forEach(Order::startDelivery);
    }

    private LocalDateTime getDeadlineTime() {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 14, 0);
    }

}
