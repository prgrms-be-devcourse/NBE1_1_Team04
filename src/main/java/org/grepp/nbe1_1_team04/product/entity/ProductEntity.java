package org.grepp.nbe1_1_team04.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.grepp.nbe1_1_team04.global.entity.BaseTimeEntity;
import org.grepp.nbe1_1_team04.order.entity.OrderStatus;
import org.grepp.nbe1_1_team04.product.controller.ProductController;
import org.grepp.nbe1_1_team04.product.dto.ProductResponse;

@Entity(name = "product")
public class ProductEntity extends BaseTimeEntity {
    @Id
    private byte[] productId;
    private String productName;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private Long price;

    public ProductEntity(byte[] productId, String productName, String description, ProductCategory category, Long price) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public ProductEntity(String productName, String description, ProductCategory category, Long price) {
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public ProductEntity() {

    }

    public void updateProductId(byte[] productId) {
        this.productId = productId;
    }

    public ProductResponse toResponse(ProductEntity productEntity) {
        return new ProductResponse(
                this.productId,
                this.productName,
                this.category.name(),
                this.price,
                this.description
        );
    }

    public byte[] getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public Long getPrice() {
        return price;
    }
}
