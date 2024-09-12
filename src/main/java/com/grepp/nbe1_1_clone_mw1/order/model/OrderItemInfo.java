package com.grepp.nbe1_1_clone_mw1.order.model;

import com.grepp.nbe1_1_clone_mw1.product.model.Category;
import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;

public record OrderItemInfo(
        String productId,
        Category category,
        long price,
        int quantity) {

    public OrderItemInfo(OrderItem orderItem) {
        this(UUIDUtil.bytesToHex(orderItem.getProduct().getProductId()), orderItem.getCategory(), orderItem.getPrice(), orderItem.getQuantity());
    }
}
