package org.example.coffee.product.api;

import org.example.coffee.product.dto.ProductRequest;
import org.example.coffee.product.dto.ProductResponse;
import org.example.coffee.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductApi {

    private final ProductService productService;

    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 상품 전체 조회
     */
    @GetMapping
    public List<ProductResponse> getProductList() {
        return productService.findAllProducts();
    }

    /**
     * 특정 상품 조회
     */
    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable("id") String productId) {
        return productService.findProduct(productId);
    }

    /**
     * 상품 등록
     */
    @PostMapping
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    /**
     * 상품 수정
     */
    @PutMapping("/{id}")
    public ProductResponse editProduct(@PathVariable("id") String productID, @RequestBody ProductRequest productRequest) {
        return productService.editProduct(productID, productRequest);
    }


    /**
     * 상품 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.ok("PRODUCT_DELETED");
    }

}
