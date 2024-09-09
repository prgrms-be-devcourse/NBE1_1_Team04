package org.example.coffee.order.dto;

import lombok.Data;
import org.example.coffee.product.dto.ProductResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class OrderResponse {
    private final String orderId;
    private final String email;
    private final String address;
    private final String postcode;
    private final Map<ProductResponse, Integer> products;
    private final Long totalPrice;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
