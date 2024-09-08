package com.grepp.nbe1_1_team04_mw_1.product.domain.dto.response;

import com.grepp.nbe1_1_team04_mw_1.product.domain.entity.Products;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Base64;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductResponseDTO {
    private String productId;

    private String productName;

    private String category;

    private Long price;

    private String description;

    public ProductResponseDTO(Products product) {
        this.productId = Base64.getEncoder().encodeToString(product.getProductId());
        this.productName = product.getProductName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }
}
