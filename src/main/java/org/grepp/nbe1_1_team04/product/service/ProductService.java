package org.grepp.nbe1_1_team04.product.service;

import com.fasterxml.uuid.Generators;
import org.grepp.nbe1_1_team04.product.dto.ProductRequest;
import org.grepp.nbe1_1_team04.product.dto.ProductResponse;
import org.grepp.nbe1_1_team04.product.entity.ProductEntity;
import org.grepp.nbe1_1_team04.product.repository.ProductRepository;
import org.grepp.nbe1_1_team04.util.UUIDUtil;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private UUIDUtil uuidUtil;

    public ProductService(ProductRepository productRepository, UUIDUtil uuidUtil) {
        this.productRepository = productRepository;
        this.uuidUtil = uuidUtil;
    }

    public void registerProduct(ProductRequest productRequest) {
        ProductEntity productEntity = productRequest.toEntity(productRequest);
        byte[] productId = uuidUtil.createRandomUUID();

        productEntity.updateProductId(productId);
        productRepository.save(productEntity);
    }

    public List<ProductResponse> getProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(m -> m.toResponse(m)).collect(Collectors.toList());
    }

    public ProductResponse getProduct(byte[] productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productEntity.toResponse(productEntity);
    }

    public void updateProduct(ProductRequest productRequest, byte[] productId) {
        ProductEntity productEntity = productRequest.toEntity(productRequest);
        productEntity.updateProductId(productId);
        productRepository.save(productEntity);
    }

    public void deleteProduct(byte[] productId) {
        productRepository.deleteById(productId);
    }
}
