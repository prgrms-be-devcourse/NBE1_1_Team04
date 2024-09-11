package com.grepp.nbe1_1_clone_mw1.order.model;

import java.time.LocalDateTime;


import com.grepp.nbe1_1_clone_mw1.product.model.Category;
import com.grepp.nbe1_1_clone_mw1.product.model.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order orders;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "seq = " + getSeq() + ", " +
                "category = " + getCategory() + ", " +
                "price = " + getPrice() + ", " +
                "quantity = " + getQuantity() + ", " +
                "createdAt = " + getCreatedAt() + ", " +
                "updatedAt = " + getUpdatedAt() + ")";
    }

    public void updateQuantity(Integer quantity, Long price) {
        this.quantity = quantity;
        this.price = price;
    }

    public static OrderItem create(Category category, long price, int quantity, Product products, Order orders) {
        return OrderItem.builder()
                .category(category)
                .price(price)
                .quantity(quantity)
                .products(products)
                .orders(orders)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}