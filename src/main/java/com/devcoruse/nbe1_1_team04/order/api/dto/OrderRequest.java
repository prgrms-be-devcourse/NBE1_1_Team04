package com.devcoruse.nbe1_1_team04.order.api.dto;

import com.devcoruse.nbe1_1_team04.order.domain.OrderContent;
import com.devcoruse.nbe1_1_team04.order.domain.OrderItemInfo;
import java.util.List;

public record OrderRequest(
        String email,
        String address,
        Integer zipCode,
        Integer price,
        List<OrderItemInfo> orderItems
) {
    public OrderContent toContent() {
        return new OrderContent(email, address, zipCode, price);
    }
}
