package com.grepp.nbe1_1_team04_mw_1.order.domain.service;

import com.grepp.nbe1_1_team04_mw_1.global.util.UUIDUtil;
import com.grepp.nbe1_1_team04_mw_1.order.domain.dto.request.OrderItemInfo;
import com.grepp.nbe1_1_team04_mw_1.order.domain.dto.request.OrderRequestDTO;
import com.grepp.nbe1_1_team04_mw_1.order.domain.entity.Orders;
import com.grepp.nbe1_1_team04_mw_1.order.domain.repository.OrderRepository;
import com.grepp.nbe1_1_team04_mw_1.order_item.domain.entity.OrderItems;
import com.grepp.nbe1_1_team04_mw_1.order_item.domain.repository.OrderItemRepository;
import com.grepp.nbe1_1_team04_mw_1.product.domain.entity.Products;
import com.grepp.nbe1_1_team04_mw_1.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UUIDUtil uuidUtil;

    @Transactional
    public ResponseEntity<String> placeOrder(OrderRequestDTO orderRequestDTO) {
        Optional<Orders> order = Optional.empty();
        Orders newOrder;
        if(!checkAfter2pm()){
            //어제 오후 2시 이후 주문 찾기
            order = orderRepository.findTopByEmailAndCreatedAtAfter(orderRequestDTO.email(), LocalDateTime.now().minusDays(1).with(LocalTime.of(14, 0)));
        }
        else {
            order = orderRepository.findTopByEmailAndCreatedAtAfter(orderRequestDTO.email(), LocalDateTime.now().with(LocalTime.of(14, 0)));
        }
        //현재 오후 2시 이후거나 // 현재 오후 2시 이전인데 어제 오후 2시 이후로 주문이 없으면 // 주문 생성
        if(order.isEmpty()) {
            newOrder = Orders.builder()
                    .orderId(uuidUtil.createUUID())
                    .orderStatus("ordered")
                    .email(orderRequestDTO.email())
                    .address(orderRequestDTO.address())
                    .postcode(orderRequestDTO.postcode())
                    .build();
            orderRepository.save(newOrder);
        }
        else {
            newOrder = orderRepository.save(order.get()); // updated_date 를 업데이트 시키기 위함  // 테스트 필요
        }
        for(OrderItemInfo itemInfo : orderRequestDTO.orderItems()){
            Optional<OrderItems> orderItems = orderItemRepository.findByOrders_OrderIdAndProducts_ProductId(newOrder.getOrderId(), itemInfo.productId());
            Products products = productRepository.findById(itemInfo.productId()).orElseThrow(()-> new RuntimeException("해당 상품은 존재하지 않습니다."));
            if(orderItems.isEmpty()) {
                orderItemRepository.save(OrderItems.builder()
                        .products(products)
                        .orders(newOrder)
                        .quantity(itemInfo.quantity())
                        .category("coffee")
                        .price(products.getPrice()*itemInfo.quantity())
                        .build()
                );
            }else {
                orderItems.get().updateQuantity(orderItems.get().getQuantity() + itemInfo.quantity(),
                        (orderItems.get().getQuantity() + itemInfo.quantity())*products.getPrice());
            }
        }
        return ResponseEntity.ok().body("Order Complete");
    }

    public boolean checkAfter2pm(){
        return LocalDateTime.now().isAfter(LocalDateTime.now().with(LocalTime.of(14, 0)));
    }
}
