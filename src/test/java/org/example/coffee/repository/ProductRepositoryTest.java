package org.example.coffee.repository;

import org.assertj.core.api.Assertions;
import org.example.coffee.product.entity.Product;
import org.example.coffee.product.reposiotry.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.example.coffee.util.UUIDUtil.*;


@SpringBootTest
//@Transactional
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    /**
     * TimeStamp를 기준으로 생성된 UUID 순으로 데이터를 받아오는지 테스트
     */
    @Test
    void save() {
        Product product1 = new Product(createUUID(), "productA", "IT", 30000L, "상품입니다.", LocalDateTime.now(), LocalDateTime.now());
        Product product2 = new Product(createUUID(), "productB", "IT", 30000L, "상품입니다.", LocalDateTime.now(), LocalDateTime.now());
        Product product3 = new Product(createUUID(), "productC", "IT", 30000L, "상품입니다.", LocalDateTime.now(), LocalDateTime.now());
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> all = productRepository.findAll();

        Assertions.assertThat(all.get(0).getProductName()).isEqualTo(product1.getProductName());
        Assertions.assertThat(all.get(1).getProductName()).isEqualTo(product2.getProductName());
        Assertions.assertThat(all.get(2).getProductName()).isEqualTo(product3.getProductName());


    }
}