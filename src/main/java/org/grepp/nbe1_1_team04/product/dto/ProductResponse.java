package org.grepp.nbe1_1_team04.product.dto;

public class ProductResponse {
    private byte[] productId;
    private String productName;
    private String category;
    private long price;
    private String description;

    public ProductResponse(byte[] productId, String productName, String category, long price, String description) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public byte[] getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
