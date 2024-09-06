package com.grepp.nbe1_1_team04_mw_1.product.domain.entity;

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
public class Products extends BaseTimeEntity {

    @Id
    @Column(length = 16)
    private Byte[] product_id;

    @Column(name = "product_name", nullable = false)
    private String product_name;

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
                "product_id = " + getProduct_id() + ", " +
                "product_name = " + getProduct_name() + ", " +
                "category = " + getCategory() + ", " +
                "price = " + getPrice() + ", " +
                "description = " + getDescription() + ", " +
                "createdDate = " + getCreatedDate() + ", " +
                "modifiedDate = " + getModifiedDate() + ")";
    }
}
