package com.grepp.nbe1_1_team04_mw_1.product.api;

import com.grepp.nbe1_1_team04_mw_1.product.domain.dto.response.ProductResponseDTO;
import com.grepp.nbe1_1_team04_mw_1.product.domain.entity.Products;
import com.grepp.nbe1_1_team04_mw_1.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class ProductApi {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProducts(@PathVariable String productId) {
        return ResponseEntity.ok(new ProductResponseDTO(productService.getProducts(productId)));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<Products> products = productService.getAllProducts();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for(Products product: products) {
            productResponseDTOS.add(new ProductResponseDTO(product));
        }
        return ResponseEntity.ok(productResponseDTOS);
    }
}
