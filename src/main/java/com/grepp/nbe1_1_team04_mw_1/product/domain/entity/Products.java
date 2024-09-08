package com.grepp.nbe1_1_team04_mw_1.product.domain.entity;

import com.grepp.nbe1_1_team04_mw_1.global.entity.BaseTimeEntity;
import com.grepp.nbe1_1_team04_mw_1.order_item.domain.entity.OrderItems;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Products extends BaseTimeEntity {

    @Id
    @Column(length = 16)
    private byte[] productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
    @Builder.Default
    private List<OrderItems> orderItems = new ArrayList<>();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "productId = " + getProductId() + ", " +
                "productName = " + getProductName() + ", " +
                "category = " + getCategory() + ", " +
                "price = " + getPrice() + ", " +
                "description = " + getDescription() + ", " +
                "createdAt = " + getCreatedAt() + ", " +
                "updatedAt = " + getUpdatedAt() + ")";
    }

    public void createUUID(byte[] uuid){
        this.productId = uuid;
    }
}
