package com.devcoruse.nbe1_1_team04.order.domain;

import com.devcoruse.nbe1_1_team04.order.repository.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderUpdater {

    public void update(Order order, OrderContent updateContent) {
        order.update(updateContent);
    }

}
