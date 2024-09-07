package org.example.coffee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity(name = "products")
@Getter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Product {

    @Id
    private byte[] productId;

    @Column(length = 20, nullable = false)
    private String productName;

    @Column(length = 50, nullable = false)
    private String category;

    @Column(nullable = false)
    private long price;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
