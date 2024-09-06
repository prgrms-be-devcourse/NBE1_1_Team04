package org.grepp.nbe1_1_team04.product.service;

import org.grepp.nbe1_1_team04.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
