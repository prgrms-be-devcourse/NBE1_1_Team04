package com.devcoruse.nbe1_1_team04.product.domain;

import com.devcoruse.nbe1_1_team04.product.repository.Product;
import com.devcoruse.nbe1_1_team04.product.repository.ProductId;
import com.devcoruse.nbe1_1_team04.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductReader {

    private final ProductRepository productRepository;

    public Product read(ProductId productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found - " + productId));
    }

}
