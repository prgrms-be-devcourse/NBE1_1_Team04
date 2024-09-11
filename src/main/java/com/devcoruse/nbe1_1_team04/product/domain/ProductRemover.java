package com.devcoruse.nbe1_1_team04.product.domain;

import com.devcoruse.nbe1_1_team04.product.repository.ProductId;
import com.devcoruse.nbe1_1_team04.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRemover {

    private final ProductRepository productRepository;

    public void remove(ProductId productId) {
        productRepository.deleteById(productId);
    }

}
