package com.devcoruse.nbe1_1_team04.product.api;

import com.devcoruse.nbe1_1_team04.product.domain.Category;
import com.devcoruse.nbe1_1_team04.product.domain.ProductContent;

public record ProductRequest(
        String productName,
        String description,
        String price,
        Category category
) {
    public ProductContent toContent() {
        return new ProductContent(productName, description, price, category);
    }
}
