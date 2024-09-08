package org.example.coffee.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private final String orderId;
    private final String email;
    private final String address;
    private final String postcode;
    private final List<ProductResponse> products;
    private final Long totalPrice;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
