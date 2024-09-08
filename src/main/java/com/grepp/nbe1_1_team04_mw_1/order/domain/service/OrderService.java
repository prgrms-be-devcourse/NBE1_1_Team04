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
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Base64;
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
    public String placeOrder(OrderRequestDTO orderRequestDTO) {
        Optional<Orders> order;
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
                    .orderStatus("new")
                    .email(orderRequestDTO.email())
                    .address(orderRequestDTO.address())
                    .postcode(orderRequestDTO.postcode())
                    .build();
            orderRepository.save(newOrder);
        }
        else {
            order.get().updateStatus("updated");
            newOrder = orderRepository.save(order.get());
        }
        createOrderItem(orderRequestDTO, newOrder);
        return "create order success";
    }

    public void createOrderItem(OrderRequestDTO orderRequestDTO, Orders order) {
        for(OrderItemInfo itemInfo : orderRequestDTO.orderItems()){
            Optional<OrderItems> orderItems = orderItemRepository.findByOrders_OrderIdAndProducts_ProductId(order.getOrderId(), Base64.getDecoder().decode(itemInfo.productId()));
            Products products = productRepository.findById(Base64.getDecoder().decode(itemInfo.productId())).orElseThrow(()-> new RuntimeException("해당 상품은 존재하지 않습니다"));
            if(orderItems.isEmpty()) {
                orderItemRepository.save(OrderItems.builder()
                        .products(products)
                        .orders(order)
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
    }

    public boolean checkAfter2pm(){
        return LocalDateTime.now().isAfter(LocalDateTime.now().with(LocalTime.of(14, 0)));
    }

    @Transactional
    public String cancelOrder(String orderId) {
        orderItemRepository.deleteByOrders_OrderId(Base64.getDecoder().decode(orderId));
        orderRepository.deleteByOrderId(Base64.getDecoder().decode(orderId));
        return "Order Cancelled";
    }

    @Transactional
    public Orders getOrder(String orderId) {
        Optional<Orders> order = orderRepository.findById(Base64.getDecoder().decode(orderId));
        if(order.isEmpty()) {
            throw new RuntimeException("해당 주문은 존재하지 않습니다");
        }
        return order.get();
    }

    @Transactional
    public String updateOrder(String orderId, OrderRequestDTO orderRequestDTO) {
        Orders order = orderRequestDTO.toEntity();
        Orders oldOrder = orderRepository.findById(Base64.getDecoder().decode(orderId)).orElseThrow(()-> new RuntimeException("해당 주문은 존재하지 않습니다"));
        Orders newOrder = Orders.builder()
                .orderId(oldOrder.getOrderId())
                .email(order.getEmail()==null ? oldOrder.getEmail() : order.getEmail())
                .postcode(order.getPostcode()==null ? oldOrder.getPostcode() : order.getPostcode())
                .address(order.getAddress()==null ? oldOrder.getAddress() : order.getAddress())
                .orderStatus("updated")
                .build();
        orderRepository.save(newOrder);
        orderItemRepository.deleteByOrders_OrderId(Base64.getDecoder().decode(orderId));
        createOrderItem(orderRequestDTO, newOrder);
        return "Order Updated";
    }

    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }
}
