package org.grepp.nbe1_1_team04.product.dto;

import org.grepp.nbe1_1_team04.product.entity.ProductCategory;
import org.grepp.nbe1_1_team04.product.entity.ProductEntity;

public class ProductRequest {
    private String productName;
    private String description;
    private String category;
    private long price;

    public ProductRequest(String productName, String description, String category, long price) {
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public ProductEntity toEntity(ProductRequest productRequest) {
        return new ProductEntity(
                this.productName,
                this.description,
                ProductCategory.valueOf(this.category),
                this.price
        );
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public long getPrice() {
        return price;
    }
}
