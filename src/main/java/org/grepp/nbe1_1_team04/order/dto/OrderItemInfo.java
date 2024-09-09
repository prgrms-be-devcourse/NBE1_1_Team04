package org.grepp.nbe1_1_team04.order.dto;

public class OrderItemInfo {
    byte[] productId;
    int quantity;

    public OrderItemInfo(byte[] productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public byte[] getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
