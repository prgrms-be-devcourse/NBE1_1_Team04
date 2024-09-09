package org.grepp.nbe1_1_team04.order.dto;

import java.util.List;

public class OrderResponse {
    private String email;
    private String address;
    private String postCode;
    private List<OrderItemInfo> orderItems;

    public OrderResponse(String email, String address, String postCode, List<OrderItemInfo> orderItems) {
        this.email = email;
        this.address = address;
        this.postCode = postCode;
        this.orderItems = orderItems;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }

    public List<OrderItemInfo> getOrderItems() {
        return orderItems;
    }
}
