package com.grepp.nbe1_1_clone_mw1.product.model;

import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {
  @Id
  @Column(columnDefinition = "BINARY(16)")
  private byte[] productId;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "category", nullable = false)
  @Enumerated(EnumType.STRING)
  private Category category;

  @Column(name = "price", nullable = false)
  private long price;

  @Column(name = "description")
  private String description;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  public static Product create(String productName, Category category, long price){
    return Product.builder()
            .productName(productName)
            .category(category)
            .price(price)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
  }

  public static Product create(String productName, Category category, long price, String description){
    return Product.builder()
            .productId(UUIDUtil.createUUID())
            .productName(productName)
            .category(category)
            .price(price)
            .description(description)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
  }
}


