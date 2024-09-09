package org.grepp.nbe1_1_team04.product.controller;

import org.grepp.nbe1_1_team04.product.dto.ProductRequest;
import org.grepp.nbe1_1_team04.product.dto.ProductResponse;
import org.grepp.nbe1_1_team04.product.service.ProductService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createProduct(@RequestBody ProductRequest productRequest) {
        productService.registerProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable byte[] productId) {
        return ResponseEntity.ok().body(productService.getProduct(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<HttpStatus> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable byte[] productId) {
        productService.updateProduct(productRequest, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable byte[] productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}
