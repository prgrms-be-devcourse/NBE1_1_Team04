package com.grepp.nbe1_1_team04_mw_1.product.domain.dto.request;

import com.grepp.nbe1_1_team04_mw_1.product.domain.entity.Products;

import java.util.Base64;

public class ProductRequestDTO {
    private String productId;

    private String productName;

    private String category;

    private Long price;

    private String description;

    public Products toEntity() {
        return Products.builder()
                .productId(Base64.getDecoder().decode(productId))
                .productName(productName)
                .category(category)
                .price(price)
                .description(description)
                .build();
    }
}
