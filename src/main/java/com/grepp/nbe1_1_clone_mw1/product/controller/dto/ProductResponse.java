package com.grepp.nbe1_1_clone_mw1.product.controller.dto;

import com.grepp.nbe1_1_clone_mw1.product.model.Category;
import com.grepp.nbe1_1_clone_mw1.product.model.Product;
import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;

import java.time.LocalDateTime;

public record ProductResponse(String productId,
                              String productName,
                              Category category,
                              long price,
                              String description,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt
                              ) {
    public ProductResponse(Product product){
        this(
                UUIDUtil.bytesToHex(product.getProductId()),
                product.getProductName(),
                product.getCategory(),
                product.getPrice(),
                product.getDescription(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
