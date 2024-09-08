package org.example.coffee.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String productName;
    private String category;
    private Long price;
    private String description;
}
