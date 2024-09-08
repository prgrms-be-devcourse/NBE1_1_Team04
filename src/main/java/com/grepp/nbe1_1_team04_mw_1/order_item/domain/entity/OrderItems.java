package com.grepp.nbe1_1_team04_mw_1.order_item.domain.entity;

import com.grepp.nbe1_1_team04_mw_1.global.entity.BaseTimeEntity;
import com.grepp.nbe1_1_team04_mw_1.order.domain.entity.Orders;
import com.grepp.nbe1_1_team04_mw_1.product.domain.entity.Products;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderItems extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Products products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "seq = " + getSeq() + ", " +
                "category = " + getCategory() + ", " +
                "price = " + getPrice() + ", " +
                "quantity = " + getQuantity() + ", " +
                "createdAt = " + getCreatedAt() + ", " +
                "updatedAt = " + getUpdatedAt() + ")";
    }

    public void updateQuantity(Integer quantity, Long price) {
        this.quantity = quantity;
        this.price = price;
    }
}
