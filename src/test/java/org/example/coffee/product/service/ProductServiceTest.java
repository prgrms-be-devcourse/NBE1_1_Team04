package org.example.coffee.product.service;

import org.example.coffee.exception.ProductNotFoundException;
import org.example.coffee.product.ProductMapper;
import org.example.coffee.product.dto.ProductRequest;
import org.example.coffee.product.dto.ProductResponse;
import org.example.coffee.product.entity.Product;
import org.example.coffee.product.reposiotry.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.example.coffee.util.UUIDUtil.*;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;


    @Test
    void 모든_상품_찾기() {
        //given
        Product product1 = new Product(createUUID(), "productA", "IT", 30000L, "상품입니다.", LocalDateTime.now(), LocalDateTime.now());
        Product product2 = new Product(createUUID(), "productB", "IT", 30000L, "상품입니다.", LocalDateTime.now(), LocalDateTime.now());
        Product product3 = new Product(createUUID(), "productC", "IT", 30000L, "상품입니다.", LocalDateTime.now(), LocalDateTime.now());
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        // when
        List<ProductResponse> allProducts = productService.findAllProducts();

        // then
        assertThat(allProducts).hasSize(3);
        assertThat(allProducts.get(0).getProductName()).isEqualTo("productA");
        assertThat(allProducts.get(1).getProductName()).isEqualTo("productB");
        assertThat(allProducts.get(2).getProductName()).isEqualTo("productC");

    }

    @Test
    void 단일상품찾기_성공() {
        //given
        Product product = new Product(createUUID(), "productA", "IT", 30000L, "상품입니다.", LocalDateTime.now(), LocalDateTime.now());
        Product saved = productRepository.save(product);

        // when
        ProductResponse findProduct = productService.findProduct(bytesToHex(saved.getProductId()));

        // then
        assertThat(saved.getProductName()).isEqualTo(findProduct.getProductName());

    }

    @Test
    void 단일상품찾기_실패() {
        // given, when, then
        assertThatThrownBy(() -> productService.findProduct(bytesToHex(createUUID())))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    void 상품_추가() {
        //given
        ProductRequest productRequest = new ProductRequest("productA", "IT", 30000L, "상품입니다.");

        // when
        ProductResponse productResponse = productService.addProduct(productRequest);

        // then
        assertThat(productRequest.getProductName()).isEqualTo(productResponse.getProductName());
    }

    @Test
    void 상품_수정() {
        // given
        ProductRequest productRequest = new ProductRequest("productA", "IT", 30000L, "상품입니다.");
        ProductResponse productResponse = productService.addProduct(productRequest);
        ProductRequest editRequest = new ProductRequest("productB", "FOOD", 20000L, "다른 상품입니다.");

        // when
        ProductResponse editProduct = productService.editProduct(productResponse.getId(), editRequest);

        // then
        assertThat(editProduct.getId()).isEqualTo(productResponse.getId()); // 같은 Product를 수정했는지
        assertThat(editProduct.getProductName()).isEqualTo("productB");
        assertThat(editProduct.getCategory()).isEqualTo("FOOD");
        assertThat(editProduct.getPrice()).isEqualTo(20000L);
        assertThat(editProduct.getDescription()).isEqualTo("다른 상품입니다.");

    }

    @Test
    void 상품_삭제() {
        // given
        ProductRequest productRequest = new ProductRequest("productA", "IT", 30000L, "상품입니다.");

        // when1
        ProductResponse productResponse = productService.addProduct(productRequest);

        // then1: 저장은 잘된건지 확인
        assertThat(productResponse.getProductName()).isEqualTo("productA");

        // when2
        productService.deleteProduct(productResponse.getId());

        // then2: 삭제가 잘됐는지 확인
        assertThatThrownBy(() -> productService.findProduct(productResponse.getId()))
                .isInstanceOf(ProductNotFoundException.class);


    }
}