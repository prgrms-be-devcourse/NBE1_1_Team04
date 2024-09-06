package com.grepp.nbe1_1_team04_mw_1.order.domain.entity;

import com.grepp.nbe1_1_team04_mw_1.global.entity.BaseTimeEntity;
import com.grepp.nbe1_1_team04_mw_1.order_item.domain.entity.OrderItems;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Orders extends BaseTimeEntity {

    @Id
    @Column(length = 16)
    private Byte[] order_id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "postcode", nullable = false)
    private String postcode;

    @Column(name = "order_status", nullable = false)
    private String order_status;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    @Builder.Default
    private List<OrderItems> orderItems = new ArrayList<>();


    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "order_id = " + getOrder_id() + ", " +
                "email = " + getEmail() + ", " +
                "address = " + getAddress() + ", " +
                "postcode = " + getPostcode() + ", " +
                "order_status = " + getOrder_status() + ", " +
                "createdDate = " + getCreatedDate() + ", " +
                "modifiedDate = " + getModifiedDate() + ")";
    }
}
