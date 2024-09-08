package com.grepp.nbe1_1_team04_mw_1.product.domain.dto.response;

import com.grepp.nbe1_1_team04_mw_1.order_item.domain.entity.OrderItems;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Base64;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductUpdateResponseDTO {
    private String productId;

    private String productName;

    private String category;

    private Long price;

    private String description;

    private Integer quantity;

    public ProductUpdateResponseDTO(OrderItems orderItems) {
        this.productId = Base64.getEncoder().encodeToString(orderItems.getProducts().getProductId());
        this.productName = orderItems.getProducts().getProductName();
        this.category = orderItems.getProducts().getCategory();
        this.price = orderItems.getProducts().getPrice();
        this.description = orderItems.getProducts().getDescription();
        this.quantity = orderItems.getQuantity();
    }
}
