package org.grepp.nbe1_1_team04.order.dto;

import org.grepp.nbe1_1_team04.order.entity.OrderEntity;
import org.grepp.nbe1_1_team04.order.entity.OrderStatus;

import java.util.List;

public class OrderRequest {
    private String email;
    private String address;
    private String postCode;
    private List<OrderItemInfo> orderItems;

    public OrderRequest(String email, String address, String postCode, List<OrderItemInfo> orderItems) {
        this.email = email;
        this.address = address;
        this.postCode = postCode;
        this.orderItems = orderItems;
    }

    public OrderEntity toOrderEntity() {
        return new OrderEntity(
                this.email,
                this.address,
                this.postCode,
                OrderStatus.valueOf("ORDERED")
        );
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
