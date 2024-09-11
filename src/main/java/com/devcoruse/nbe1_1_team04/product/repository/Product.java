package com.devcoruse.nbe1_1_team04.product.repository;

import com.devcoruse.nbe1_1_team04.global.entity.BaseTimeEntity;
import com.devcoruse.nbe1_1_team04.product.domain.Category;
import com.devcoruse.nbe1_1_team04.product.domain.ProductContent;
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
@Table(name = "products")
@Getter
public class Product extends BaseTimeEntity {

    @EmbeddedId
    private ProductId productId;

    private String productName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer price;

    private String description;

    public static Product from(ProductContent content) {
        return Product.builder()
                .productId(ProductId.generate())
                .productName(content.productName())
                .category(content.category())
                .price(Integer.parseInt(content.price()))
                .description(content.description())
                .build();
    }

    public void update(ProductContent content) {
        this.productName = content.productName();
        this.category = content.category();
        this.price = Integer.parseInt(content.price());
        this.description = content.description();
    }
}
