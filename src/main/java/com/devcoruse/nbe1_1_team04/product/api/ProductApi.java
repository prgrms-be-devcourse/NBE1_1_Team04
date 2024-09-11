package com.devcoruse.nbe1_1_team04.product.api;

import com.devcoruse.nbe1_1_team04.product.api.dto.ProductResponse;
import com.devcoruse.nbe1_1_team04.product.domain.ProductService;
import com.devcoruse.nbe1_1_team04.product.repository.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductApi {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request.toContent()).toString());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String productId) {
        return ResponseEntity.ok(ProductResponse.from(productService.getProduct(ProductId.from(productId))));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable String productId, @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(ProductId.from(productId), request.toContent()).toString());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable String productId) {
        productService.removeProduct(ProductId.from(productId));
        return ResponseEntity.ok("Product removed");
    }

}
