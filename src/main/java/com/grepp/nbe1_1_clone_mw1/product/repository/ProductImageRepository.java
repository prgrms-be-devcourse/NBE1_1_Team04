package com.grepp.nbe1_1_clone_mw1.product.repository;

import com.grepp.nbe1_1_clone_mw1.product.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    Optional<ProductImage> findFirstByProducts_ProductId(byte[] productId);

    void deleteByProducts_ProductId(byte[] productId);
}
