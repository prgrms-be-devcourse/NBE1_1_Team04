package com.grepp.nbe1_1_team04_mw_1.order.domain.entity;

import com.grepp.nbe1_1_team04_mw_1.global.entity.BaseTimeEntity;
import com.grepp.nbe1_1_team04_mw_1.order_item.domain.entity.OrderItems;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Orders extends BaseTimeEntity {

    @Id
    @Column(length = 16)
    private byte[] orderId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "postcode", nullable = false)
    private String postcode;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    @Builder.Default
    private List<OrderItems> orderItems = new ArrayList<>();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "orderId = " + Arrays.toString(getOrderId()) + ", " +
                "email = " + getEmail() + ", " +
                "address = " + getAddress() + ", " +
                "postcode = " + getPostcode() + ", " +
                "orderStatus = " + getOrderStatus() + ", " +
                "createdAt = " + getCreatedAt() + ", " +
                "updatedAt = " + getUpdatedAt() + ")";
    }
}
