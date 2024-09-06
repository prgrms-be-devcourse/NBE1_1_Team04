package org.grepp.nbe1_1_team04.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.grepp.nbe1_1_team04.global.entity.BaseTimeEntity;

@Entity(name = "order")
public class OrderEntity extends BaseTimeEntity {
    @Id
    private Byte[] orderId;
    private String email;
    private String address;
    private String postcode;
    private OrderStatus orderStatus;
}
