package org.grepp.nbe1_1_team04.product.controller;

import org.grepp.nbe1_1_team04.product.dto.ProductRequest;
import org.grepp.nbe1_1_team04.product.dto.ProductResponse;
import org.grepp.nbe1_1_team04.product.service.ProductService;
import org.springframework.http.HttpMethod;
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
    public ResponseEntity<HttpMethod> createProduct(@RequestBody ProductRequest productRequest) {
        productService.registerProduct(productRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable long productId) {
        return ResponseEntity.ok().body(productService.getProduct());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<HttpMethod> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable long productId) {
        productService.updateProduct(productRequest, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<HttpMethod> deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }






}
