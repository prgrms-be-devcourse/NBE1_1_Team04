package org.example.coffee.product.service;

import org.example.coffee.product.dto.ProductRequest;
import org.example.coffee.product.dto.ProductResponse;
import org.example.coffee.product.entity.Product;
import org.example.coffee.exception.ProductNotFoundException;
import org.example.coffee.product.ProductMapper;
import org.example.coffee.product.reposiotry.ProductRepository;
import org.example.coffee.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse findProduct(String productId) {
        Product product = productRepository.findById(UUIDUtil.hexToBytes(productId)).orElseThrow(() -> new ProductNotFoundException("없는 상품입니다."));
        return ProductMapper.toResponse(product);
    }

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product saved = productRepository.save(ProductMapper.toProduct(productRequest));
        return ProductMapper.toResponse(saved);
    }

    @Transactional
    public ProductResponse editProduct(String productId, ProductRequest productRequest) {
        Product product = productRepository.findById(UUIDUtil.hexToBytes(productId))
                .orElseThrow(() -> new ProductNotFoundException("없는 상품입니다."));

        if(productRequest.getProductName() != null) product.setProductName(productRequest.getProductName());
        if(productRequest.getCategory() != null) product.setCategory(productRequest.getCategory());
        if(productRequest.getPrice() != null) product.setPrice(productRequest.getPrice());
        if(productRequest.getDescription() != null) product.setDescription(productRequest.getDescription());

        // 수정시간 업데이트
        product.setUpdatedAt(LocalDateTime.now());

        return ProductMapper.toResponse(product);
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(UUIDUtil.hexToBytes(productId));
    }

}

