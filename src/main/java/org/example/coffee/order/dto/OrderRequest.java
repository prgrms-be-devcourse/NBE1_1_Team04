package org.example.coffee.order.dto;

import lombok.Data;

import java.util.Map;

@Data
public class OrderRequest {
    private final String email;
    private final String address;
    private final String postcode;
    // 1. Map으로 product_id - quantity 이렇게 받음
    private final Map<String, Integer> orderItems;

}
