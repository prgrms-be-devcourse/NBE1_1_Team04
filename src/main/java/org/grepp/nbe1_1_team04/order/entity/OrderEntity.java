package org.grepp.nbe1_1_team04.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.grepp.nbe1_1_team04.global.entity.BaseTimeEntity;
import org.grepp.nbe1_1_team04.order.dto.OrderResponse;

@Entity(name = "order")
public class OrderEntity extends BaseTimeEntity {
    @Id
    private byte[] orderId;
    private String email;
    private String address;
    private String postcode;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public OrderEntity(byte[] orderId, String email, String address, String postcode, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = orderStatus;
    }

    public OrderEntity(String email, String address, String postcode, OrderStatus orderStatus) {
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = orderStatus;
    }

    public OrderEntity() {

    }

    public void updateOrderId(byte[] orderId) {
        this.orderId = orderId;
    }

    public OrderResponse toResponse() {
        return new OrderResponse(
                this.orderId,
                this.email,
                this.address,
                this.postcode
        );
    }

    public byte[] getOrderId() {
        return orderId;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
