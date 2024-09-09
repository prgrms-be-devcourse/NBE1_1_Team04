package org.example.coffee.order.sevice;

import org.example.coffee.exception.EmailMismatchForOrderException;
import org.example.coffee.exception.OrderNotFoundException;
import org.example.coffee.order.dto.OrderRequest;
import org.example.coffee.order.dto.OrderResponse;
import org.example.coffee.product.entity.Product;
import org.example.coffee.product.reposiotry.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.example.coffee.util.UUIDUtil.*;
import static org.example.coffee.util.UUIDUtil.createUUID;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 클래스당 하나의 인스턴스 생성: @BeforeAll에 static을 안붙여도 됨
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductRepository productRepository;

    List<Product> testProducts = new ArrayList<>();

    @BeforeAll
    void 테스트용_상품추가() {
        Product product1 = new Product(createUUID(), "productA", "IT", 30000L, "상품입니다.", LocalDateTime.now(), LocalDateTime.now());
        Product product2 = new Product(createUUID(), "productB", "IT", 30000L, "상품입니다.", LocalDateTime.now(), LocalDateTime.now());
        Product product3 = new Product(createUUID(), "productC", "IT", 30000L, "상품입니다.", LocalDateTime.now(), LocalDateTime.now());

        testProducts.add(productRepository.save(product1));
        testProducts.add(productRepository.save(product2));
        testProducts.add(productRepository.save(product3));
    }

    @Test
    void 주문추가하기() {

        // given
        HashMap<String, Integer> shoppingMap = new HashMap<>();
        shoppingMap.put(bytesToHex(testProducts.get(0).getProductId()), 4);
        shoppingMap.put(bytesToHex(testProducts.get(1).getProductId()), 2);
        shoppingMap.put(bytesToHex(testProducts.get(2).getProductId()), 3);

        OrderRequest orderRequest = new OrderRequest("a@email.com", "강남대로162길", "06028", shoppingMap);

        // when
        OrderResponse orderResponse = orderService.addOrder(orderRequest);

        // then
        System.out.println(orderResponse);
        assertThat(orderResponse.getEmail()).isEqualTo(orderRequest.getEmail());
        assertThat(orderResponse.getAddress()).isEqualTo(orderRequest.getAddress());
        assertThat(orderResponse.getPostcode()).isEqualTo(orderRequest.getPostcode());
        orderResponse.getProducts().forEach((productResponse, quantity) ->
                assertThat(shoppingMap.get(productResponse.getId())).isEqualTo(quantity)
        );


    }

    @Test
    void 특정_주문찾기_성공() {
        // given
        HashMap<String, Integer> shoppingMap = new HashMap<>();
        shoppingMap.put(bytesToHex(testProducts.get(0).getProductId()), 4);
        shoppingMap.put(bytesToHex(testProducts.get(1).getProductId()), 2);
        shoppingMap.put(bytesToHex(testProducts.get(2).getProductId()), 3);

        OrderRequest orderRequest = new OrderRequest("a@email.com", "강남대로162길", "06028", shoppingMap);
        OrderResponse orderResponse = orderService.addOrder(orderRequest);

        // when
        OrderResponse findOrder = orderService.findOrder(orderResponse.getOrderId(), orderResponse.getEmail());

        // then
        assertThat(findOrder).isEqualTo(orderResponse);
    }

    @Test
    void 특정_주문찾기_실패1_없는주문() {
        // given
        HashMap<String, Integer> shoppingMap = new HashMap<>();
        shoppingMap.put(bytesToHex(testProducts.get(0).getProductId()), 4);
        shoppingMap.put(bytesToHex(testProducts.get(1).getProductId()), 2);
        shoppingMap.put(bytesToHex(testProducts.get(2).getProductId()), 3);

        OrderRequest orderRequest = new OrderRequest("a@email.com", "강남대로162길", "06028", shoppingMap);
        OrderResponse orderResponse = orderService.addOrder(orderRequest);

        // when, then
        assertThatThrownBy(() -> orderService.findOrder(bytesToHex(createUUID()), orderResponse.getEmail()))
                .isInstanceOf(OrderNotFoundException.class);

    }

    @Test
    void 특정_주문찾기_실패2_이메일불일치() {
        // given
        HashMap<String, Integer> shoppingMap = new HashMap<>();
        shoppingMap.put(bytesToHex(testProducts.get(0).getProductId()), 4);
        shoppingMap.put(bytesToHex(testProducts.get(1).getProductId()), 2);
        shoppingMap.put(bytesToHex(testProducts.get(2).getProductId()), 3);

        OrderRequest orderRequest = new OrderRequest("a@email.com", "강남대로162길", "06028", shoppingMap);
        OrderResponse orderResponse = orderService.addOrder(orderRequest);

        // when, then
        assertThatThrownBy(() -> orderService.findOrder(orderResponse.getOrderId(), "b@email.com"))
                .isInstanceOf(EmailMismatchForOrderException.class);

    }

    @Test
    void 주문수정하기() {
        // given
        HashMap<String, Integer> shoppingMap = new HashMap<>();
        shoppingMap.put(bytesToHex(testProducts.get(0).getProductId()), 4);
        shoppingMap.put(bytesToHex(testProducts.get(1).getProductId()), 2);
        shoppingMap.put(bytesToHex(testProducts.get(2).getProductId()), 3);

        OrderRequest orderRequest = new OrderRequest("a@email.com", "강남대로162길", "06028", shoppingMap);
        OrderResponse orderResponse = orderService.addOrder(orderRequest);

        // 비즈니스 요구사항상 주소만 수정 가능함
        OrderRequest editRequest = new OrderRequest("a@email.com", "아차산로 438", "05047", shoppingMap);

        // when
        OrderResponse editResponse = orderService.editOrder(orderResponse.getOrderId(), editRequest);

        // then
        assertThat(editResponse.getAddress()).isEqualTo("아차산로 438");
        assertThat(editResponse.getPostcode()).isEqualTo("05047");

    }

    @Test
    void 주문_삭제하기() {
        // given
        HashMap<String, Integer> shoppingMap = new HashMap<>();
        shoppingMap.put(bytesToHex(testProducts.get(0).getProductId()), 4);
        shoppingMap.put(bytesToHex(testProducts.get(1).getProductId()), 2);
        shoppingMap.put(bytesToHex(testProducts.get(2).getProductId()), 3);

        OrderRequest orderRequest = new OrderRequest("a@email.com", "강남대로162길", "06028", shoppingMap);
        OrderResponse orderResponse = orderService.addOrder(orderRequest);

        // when
        orderService.deleteOrder(orderResponse.getOrderId());

        // then
        assertThatThrownBy(() -> orderService.findOrder(orderResponse.getOrderId(), orderResponse.getEmail()))
                .isInstanceOf(OrderNotFoundException.class);
    }
}