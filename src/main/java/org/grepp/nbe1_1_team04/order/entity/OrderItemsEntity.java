package org.grepp.nbe1_1_team04.order.entity;

import jakarta.persistence.*;
import org.grepp.nbe1_1_team04.global.entity.BaseTimeEntity;
import org.grepp.nbe1_1_team04.product.entity.ProductCategory;
import org.grepp.nbe1_1_team04.product.entity.ProductEntity;

@Entity(name = "order_items")
public class OrderItemsEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long price;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public OrderItemsEntity(long price, int quantity, ProductCategory category, OrderEntity order, ProductEntity product) {
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.order = order;
        this.product = product;
    }

    public OrderItemsEntity() {

    }

    public long getId() {
        return id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public long getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
