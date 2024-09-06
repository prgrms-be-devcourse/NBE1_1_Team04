package com.grepp.nbe1_1_team04_mw_1.order_item.domain.entity;

import com.grepp.nbe1_1_team04_mw_1.global.entity.BaseTimeEntity;
import com.grepp.nbe1_1_team04_mw_1.order.domain.entity.Orders;
import com.grepp.nbe1_1_team04_mw_1.product.domain.entity.Products;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderItems extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private int seq;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

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
                "createdDate = " + getCreatedDate() + ", " +
                "modifiedDate = " + getModifiedDate() + ")";
    }
}
