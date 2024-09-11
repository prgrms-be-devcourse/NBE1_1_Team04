package com.devcoruse.nbe1_1_team04.product.domain;

import com.devcoruse.nbe1_1_team04.product.repository.Product;
import com.devcoruse.nbe1_1_team04.product.repository.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductAppender productAppender;
    private final ProductReader productReader;
    private final ProductUpdater productUpdater;
    private final ProductRemover productRemover;

    @Transactional
    public ProductId createProduct(ProductContent content) {
        return productAppender.append(content);
    }

    public Product getProduct(ProductId productId) {
        return productReader.read(productId);
    }

    @Transactional
    public ProductId updateProduct(ProductId productId, ProductContent content) {
        productUpdater.update(productId, content);
        return productId;
    }

    public void removeProduct(ProductId productId) {
        productRemover.remove(productId);
    }
}
