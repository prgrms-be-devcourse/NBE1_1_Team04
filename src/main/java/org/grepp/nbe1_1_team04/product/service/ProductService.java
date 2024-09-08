package org.grepp.nbe1_1_team04.product.service;

import org.grepp.nbe1_1_team04.product.dto.ProductRequest;
import org.grepp.nbe1_1_team04.product.dto.ProductResponse;
import org.grepp.nbe1_1_team04.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void registerProduct(ProductRequest productRequest) {

    }

    public List<ProductResponse> getProducts() {
        return  null;
    }

    public ProductResponse getProduct() {
        return null;
    }

    public void updateProduct(ProductRequest productRequest, long productId) {

    }

    public void deleteProduct(long productId) {

    }
}
