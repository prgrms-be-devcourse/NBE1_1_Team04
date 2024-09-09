package org.example.coffee.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "products")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Product {

    @Id
    private byte[] productId;

    @Column(length = 20, nullable = false)
    private String productName;

    @Column(length = 50, nullable = false)
    private String category;

    @Column(nullable = false)
    private Long price;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
