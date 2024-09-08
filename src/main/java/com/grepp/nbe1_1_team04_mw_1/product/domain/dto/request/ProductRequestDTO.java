package com.grepp.nbe1_1_team04_mw_1.product.domain.dto.request;

import com.grepp.nbe1_1_team04_mw_1.product.domain.entity.Products;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductRequestDTO {

    private String productName;

    private String category;

    private Long price;

    private String description;

    public Products toEntity() {
        return Products.builder()
                .productName(productName)
                .category(category)
                .price(price)
                .description(description)
                .build();
    }
}
