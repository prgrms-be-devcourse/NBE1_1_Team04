package com.grepp.nbe1_1_team04_mw_1.product.domain.service;

import com.grepp.nbe1_1_team04_mw_1.product.domain.entity.Products;
import com.grepp.nbe1_1_team04_mw_1.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Products getProducts(String productId) {
        return productRepository.findById(Base64.getDecoder().decode(productId)).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

}
