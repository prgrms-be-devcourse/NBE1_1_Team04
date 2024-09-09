package com.grepp.nbe1_1_team04_mw_1.product.domain.service;

import com.grepp.nbe1_1_team04_mw_1.global.exception.CustomErrorCode;
import com.grepp.nbe1_1_team04_mw_1.global.exception.CustomException;
import com.grepp.nbe1_1_team04_mw_1.global.util.UUIDUtil;
import com.grepp.nbe1_1_team04_mw_1.order_item.domain.repository.OrderItemRepository;
import com.grepp.nbe1_1_team04_mw_1.product.domain.dto.request.ProductRequestDTO;
import com.grepp.nbe1_1_team04_mw_1.product.domain.entity.Products;
import com.grepp.nbe1_1_team04_mw_1.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final UUIDUtil uuidUtil;

    public Products getProducts(String productId) {
        return productRepository.findById(Base64.getDecoder().decode(productId)).orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_NOT_FOUND));
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public String addProduct(Products product) {
        product.createUUID(uuidUtil.createUUID());
        productRepository.save(product);
        return "add product success";
    }

    @Transactional
    public String updateProduct(String productId, Products product) {
        Products oldProduct = productRepository.findById(Base64.getDecoder().decode(productId))
                .orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_NOT_FOUND));
        Products newProduct = Products.builder()
                .productId(oldProduct.getProductId())
                .productName(product.getProductName()==null?oldProduct.getProductName():product.getProductName())
                .price(product.getPrice()==null?oldProduct.getPrice():product.getPrice())
                .description(product.getDescription()==null?oldProduct.getDescription():product.getDescription())
                .category(product.getCategory()==null?oldProduct.getCategory():product.getCategory())
                .build();
        productRepository.save(newProduct);
        return "update product success";
    }

    @Transactional
    public String deleteProduct(String productId) {
        orderItemRepository.deleteByProducts_ProductId(Base64.getDecoder().decode(productId));
        productRepository.deleteById(Base64.getDecoder().decode(productId));
        return "delete product success";
    }
}
