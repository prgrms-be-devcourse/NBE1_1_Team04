package com.devcoruse.nbe1_1_team04.order.api.dto;

import com.devcoruse.nbe1_1_team04.order.repository.OrderItem;
import com.devcoruse.nbe1_1_team04.product.domain.Category;

public record OrderItemResponse(
        String productId,
        Category category,
        Integer price,
        int quantity
) {

    public static OrderItemResponse from(OrderItem orderItem) {
        return new OrderItemResponse(
                orderItem.getProductId().toString(),
                orderItem.getCategory(),
                orderItem.getPrice(),
                orderItem.getQuantity()
        );
    }
}
