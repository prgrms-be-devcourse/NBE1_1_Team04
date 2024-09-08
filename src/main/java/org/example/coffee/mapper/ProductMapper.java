package org.example.coffee.mapper;

import org.example.coffee.dto.ProductRequest;
import org.example.coffee.dto.ProductResponse;
import org.example.coffee.entity.Product;
import org.example.coffee.util.UUIDUtil;

import java.time.LocalDateTime;

public class ProductMapper {

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(
                UUIDUtil.bytesToHex(product.getProductId()),
                product.getProductName(),
                product.getCategory(),
                product.getPrice(),
                product.getDescription(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public static Product toProduct(ProductRequest request) {
        return new Product(
                UUIDUtil.createUUID(),
                request.getProductName(),
                request.getCategory(),
                request.getPrice(),
                request.getDescription(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

    }

    public static void setEditInfo(ProductRequest productRequest, Product product) {
        if(productRequest.getProductName() != null) product.setProductName(productRequest.getProductName());
        if(productRequest.getCategory() != null) product.setCategory(productRequest.getCategory());
        if(productRequest.getPrice() != null) product.setPrice(productRequest.getPrice());
        if(productRequest.getDescription() != null) product.setDescription(productRequest.getDescription());

        // 수정시간 업데이트
        product.setUpdatedAt(LocalDateTime.now());
    }
}
