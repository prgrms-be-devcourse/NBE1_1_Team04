package com.grepp.nbe1_1_clone_mw1.product.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_images")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private Long productImageId;

    @Column(name = "image_name", nullable = false)
    private String imageName;

    @Column(name = "saved_path", nullable = false)
    private String savedPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product products;

    public static ProductImage create(String imageName, String savedPath) {
        return ProductImage.builder()
                .imageName(imageName)
                .savedPath(savedPath)
                .build();
    }

    public void updateProduct(Product product){
        this.products = product;
    }
}
