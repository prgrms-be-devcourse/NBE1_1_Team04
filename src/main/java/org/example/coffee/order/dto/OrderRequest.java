package org.example.coffee.order.dto;

import lombok.Data;

import java.util.Map;

@Data
public class OrderRequest {
    private String email;
    private String address;
    private String postcode;
    // 1. Map으로 product_id - quantity 이렇게 받음
    private Map<String, Integer> orderItems;

}
