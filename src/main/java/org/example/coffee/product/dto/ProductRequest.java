package org.example.coffee.product.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private final String productName;
    private final String category;
    private final Long price;
    private final String description;
}
