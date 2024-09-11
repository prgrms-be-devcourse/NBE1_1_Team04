package com.devcoruse.nbe1_1_team04.product.api.dto;

import com.devcoruse.nbe1_1_team04.product.domain.Category;
import com.devcoruse.nbe1_1_team04.product.repository.Product;

public record ProductResponse(
        String productName,
        String description,
        Integer price,
        Category category
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory()
        );
    }
}
