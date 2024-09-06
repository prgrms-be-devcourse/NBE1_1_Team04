package org.grepp.nbe1_1_team04.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.grepp.nbe1_1_team04.global.entity.BaseTimeEntity;

@Entity(name = "order")
public class OrderEntity extends BaseTimeEntity {
    @Id
    private Byte[] orderId;
    private String email;
    private String address;
    private String postcode;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Byte[] getOrderId() {
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
