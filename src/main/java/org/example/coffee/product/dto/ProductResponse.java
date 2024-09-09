package org.example.coffee.product.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponse {
    private final String id; // 프론트에는 uuid가 문자열 형태로 넘어가야 함
    private final String productName;
    private final String category;
    private final long price;
    private final String description;
    private final LocalDateTime created_at;
    private final LocalDateTime updated_at;
}
