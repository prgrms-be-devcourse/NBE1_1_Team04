package com.grepp.nbe1_1_team04_mw_1;

import com.grepp.nbe1_1_team04_mw_1.global.util.UUIDUtil;
import com.grepp.nbe1_1_team04_mw_1.order.domain.dto.request.OrderItemInfo;
import com.grepp.nbe1_1_team04_mw_1.order.domain.dto.request.OrderRequestDTO;
import com.grepp.nbe1_1_team04_mw_1.order.domain.repository.OrderRepository;
import com.grepp.nbe1_1_team04_mw_1.order.domain.service.OrderService;
import com.grepp.nbe1_1_team04_mw_1.order_item.domain.repository.OrderItemRepository;
import com.grepp.nbe1_1_team04_mw_1.product.domain.entity.Products;
import com.grepp.nbe1_1_team04_mw_1.product.domain.repository.ProductRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@SpringBootTest
class Nbe11Team04Mw1ApplicationTests {
	@Autowired
	OrderService orderService;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UUIDUtil uuidUtil;

	@Test
	@DisplayName("삭제")
	void test12(){
		orderItemRepository.deleteAll();
		orderRepository.deleteAll();
		productRepository.deleteAll();
	}

	@Test
	@DisplayName("새로 주문")
	void test1(){
		//given
		Products products1 = productRepository.save(Products.builder()
				.productId(uuidUtil.createUUID())
				.price(200L)
				.description("1234")
				.productName("coffee1")
				.category("coffee")
				.build()
		);


		//when
		OrderRequestDTO dto = new OrderRequestDTO("a", "add", "1234", List.of(new OrderItemInfo[]{new OrderItemInfo(Base64.getEncoder().encodeToString(products1.getProductId()), 1)}));
		orderService.placeOrder(dto);

		//then
		System.out.println(orderRepository.findAll());
		System.out.println(orderItemRepository.findAll());
	}


	@Test
	@DisplayName("새로운 상품 추가 주문")
	void test2(){
		Products products2 = productRepository.save(Products.builder()
				.productId(uuidUtil.createUUID())
				.price(2000L)
				.description("ㅁㅁㅁ")
				.productName("coffee2")
				.category("coffee")
				.build()
		);

		OrderRequestDTO dto = new OrderRequestDTO("a", "add", "1234", List.of(new OrderItemInfo[]{new OrderItemInfo(Base64.getEncoder().encodeToString(products2.getProductId()), 1)}));
		orderService.placeOrder(dto);

		System.out.println(orderRepository.findAll());
		System.out.println(orderItemRepository.findAll());
	}

	@Test
	@DisplayName("기존 상품 추가 주문")
	void test3(){
		Products products1 = productRepository.save(Products.builder()
				.productId(uuidUtil.createUUID())
				.price(200L)
				.description("1234")
				.productName("coffee1")
				.category("coffee")
				.build()
		);


		//when
		OrderRequestDTO dto = new OrderRequestDTO("a", "add", "1234", List.of(new OrderItemInfo[]{new OrderItemInfo(Base64.getEncoder().encodeToString(products1.getProductId()), 1)}));
		orderService.placeOrder(dto);

		OrderRequestDTO dto2 = new OrderRequestDTO("a", "add", "1234", List.of(new OrderItemInfo[]{new OrderItemInfo(Base64.getEncoder().encodeToString(products1.getProductId()), 2)}));
		orderService.placeOrder(dto2);

		//then
		System.out.println(orderRepository.findAll());
		System.out.println(orderItemRepository.findAll());
	}

	@Test
	void testd(){
//		orderRepository.deleteByOrderId();
		System.out.println(Arrays.toString(productRepository.findByProductName("coffee2").get().getProductId()));
		System.out.println(uuidUtil.bytesToHex(productRepository.findByProductName("coffee2").get().getProductId()));
		System.out.println(productRepository.findById(new byte[]{17, -17, 108, 58, 37, -81, -113, -123, -72, 34, -13, 117, -62, -100, -15, 117}));
	}


	@Test
	void contextLoads() {
	}

}
