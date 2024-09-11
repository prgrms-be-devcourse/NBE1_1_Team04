package com.devcoruse.nbe1_1_team04.product.domain;

import com.devcoruse.nbe1_1_team04.product.repository.Product;
import com.devcoruse.nbe1_1_team04.product.repository.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductUpdater {

    private final ProductReader productReader;

    public void update(ProductId productId, ProductContent content) {
        Product product = productReader.read(productId);
        product.update(content);
    }

}
