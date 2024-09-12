package com.grepp.nbe1_1_clone_mw1.order.model;

public record OrderContent(
        String email,
        String address,
        String postcode
) {
    public static OrderContent create(String email, String address, String postcode) {
        return new OrderContent(email, address, postcode);
    }
}
