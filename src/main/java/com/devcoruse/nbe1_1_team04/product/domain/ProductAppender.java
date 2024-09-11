package com.devcoruse.nbe1_1_team04.product.domain;

import com.devcoruse.nbe1_1_team04.product.repository.Product;
import com.devcoruse.nbe1_1_team04.product.repository.ProductId;
import com.devcoruse.nbe1_1_team04.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductAppender {

    private final ProductRepository productRepository;


    public ProductId append(ProductContent content) {
        Product product = Product.from(content);
        return productRepository.save(product).getProductId();
    }
}
