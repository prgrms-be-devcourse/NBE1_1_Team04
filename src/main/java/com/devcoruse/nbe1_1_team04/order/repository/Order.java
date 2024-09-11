package com.devcoruse.nbe1_1_team04.order.repository;

import com.devcoruse.nbe1_1_team04.global.entity.BaseTimeEntity;
import com.devcoruse.nbe1_1_team04.global.util.IdUtils;
import com.devcoruse.nbe1_1_team04.order.domain.OrderContent;
import com.devcoruse.nbe1_1_team04.order.domain.OrderStatus;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Getter
public class Order extends BaseTimeEntity {

    @EmbeddedId
    private OrderId orderId;

    private String email;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public static Order create(OrderContent content) {
        return Order.builder()
                .orderId(OrderId.generate())
                .email(content.email())
                .address(Address.of(content.address(), content.zipCode()))
                .orderStatus(OrderStatus.ORDERED)
                .build();
    }

    public void update(OrderContent updateContent) {
        this.email = updateContent.email();
        this.address = Address.of(updateContent.address(), updateContent.zipCode());
    }
}
