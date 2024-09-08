package com.grepp.nbe1_1_team04_mw_1.product.api;

import com.grepp.nbe1_1_team04_mw_1.product.domain.dto.request.ProductRequestDTO;
import com.grepp.nbe1_1_team04_mw_1.product.domain.dto.response.ProductResponseDTO;
import com.grepp.nbe1_1_team04_mw_1.product.domain.entity.Products;
import com.grepp.nbe1_1_team04_mw_1.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.addProduct(productRequestDTO.toEntity()));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable String productId, @RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.updateProduct(productId, productRequestDTO.toEntity()));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }
}
