package com.devcoruse.nbe1_1_team04.order.repository;

import com.devcoruse.nbe1_1_team04.global.entity.BaseTimeEntity;
import com.devcoruse.nbe1_1_team04.order.domain.OrderItemInfo;
import com.devcoruse.nbe1_1_team04.product.domain.Category;
import com.devcoruse.nbe1_1_team04.product.repository.Product;
import com.devcoruse.nbe1_1_team04.product.repository.ProductId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Getter
@Table(name = "order_items")
public class OrderItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Embedded
    private OrderId orderId;

    @Embedded
    private ProductId productId;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer price;

    private Integer quantity;

    public static OrderItem create(OrderId orderId, Product product, OrderItemInfo orderItemInfo) {
        return OrderItem.builder()
                .orderId(orderId)
                .productId(product.getProductId())
                .category(product.getCategory())
                .price(product.getPrice())
                .quantity(orderItemInfo.quantity())
                .build();
    }
}
