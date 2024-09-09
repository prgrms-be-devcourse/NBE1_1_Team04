package org.example.coffee.order.sevice;

import org.example.coffee.order.dto.OrderRequest;
import org.example.coffee.order.dto.OrderResponse;
import org.example.coffee.order.entity.Order;
import org.example.coffee.order.entity.OrderItem;
import org.example.coffee.product.entity.Product;
import org.example.coffee.exception.EmailMismatchForOrderException;
import org.example.coffee.exception.OrderNotFoundException;
import org.example.coffee.exception.ProductNotFoundException;
import org.example.coffee.order.OrderMapper;
import org.example.coffee.order.repository.OrderRepository;
import org.example.coffee.product.reposiotry.ProductRepository;
import org.example.coffee.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toResponse)
                .toList();
    }

    public OrderResponse findOrder(String orderId, String email) {
        Order order = orderRepository.findById(UUIDUtil.hexToBytes(orderId)).orElseThrow(() -> new OrderNotFoundException("해당 주문을 찾을 수 없습니다."));
        if(!order.getEmail().equals(email)) throw new EmailMismatchForOrderException("해당 주문에 대한 이메일이 일치하지 않습니다.");


        return OrderMapper.toResponse(order);
    }


    @Transactional
    public OrderResponse addOrder(OrderRequest orderRequest) {
        List<Order> orders = orderRepository.findByEmail(orderRequest.getEmail());


        // 같은 주문이 있는지 체크하고 없다면 새로운 주문 생성
        Order order = getSameOrder(orderRequest, orders).orElse(OrderMapper.toOrder(orderRequest));


        // 상품들 추가하기
        for (String productId : orderRequest.getOrderItems().keySet()) {
            // 먼저 해당 productId로 Product 가져오기
            Product product = productRepository.findById(UUIDUtil.hexToBytes(productId))
                    .orElseThrow(() -> new ProductNotFoundException("없는 상품입니다."));

            // OrderItem객체 만들기
            OrderItem orderItem = new OrderItem(
                    product,
                    order,
                    product.getCategory(),
                    product.getPrice(),
                    orderRequest.getOrderItems().get(productId),
                    order.getCreatedAt(),
                    order.getUpdatedAt()
            );

            // itemList에 추가하기
            order.getOrderItemList().add(orderItem);
        }

        // 저장 혹은 갱신
        Order saved = orderRepository.save(order);

        return OrderMapper.toResponse(saved);
    }

    @Transactional
    public OrderResponse editOrder(String orderId, OrderRequest orderRequest) {
        Order order = orderRepository.findById(UUIDUtil.hexToBytes(orderId))
                .orElseThrow(() -> new OrderNotFoundException("해당 주문을 찾을 수 없습니다."));

        if(orderRequest.getAddress() != null) order.setAddress(orderRequest.getAddress());
        if(orderRequest.getPostcode() != null) order.setPostcode(orderRequest.getPostcode());

        return OrderMapper.toResponse(order);
    }

    /**
     * {
     *     "email": "test@example.com",
     *     "address": "8888 Test Street2",
     *     "postcode": "8888",
     *     "orderItems": {
     *         "11ef6ce5497e4ffe84ecc556f3dda148": 1
     *     }
     * }
     */

    public void deleteOrder(String orderId) {
        orderRepository.deleteById(UUIDUtil.hexToBytes(orderId));
    }

    private static Optional<Order> getSameOrder(OrderRequest orderRequest, List<Order> orders) {
        // 해당 이메일로 된 주문 중에서 우편번호와 시간범위가 같은 주문이 있는지 확인 (다르면 무조건 다른 주문)
        for (Order order : orders) {
            // 우편번호 확인
            if(!order.getPostcode().equals(orderRequest.getPostcode()))
                continue;

            // 시간범위 체크
            LocalDateTime updatedAt = order.getUpdatedAt();
            LocalDateTime now = LocalDateTime.now();

            // 현재 날짜의 14시
            LocalDateTime startOfPeriod = now.with(LocalTime.of(14, 0));
            // 다음날 13시 59분 59초
            LocalDateTime endOfPeriod = startOfPeriod.plusDays(1).with(LocalTime.of(13, 59, 59));

            // updatedAt이 오늘 14시부터 다음날 13시 59분까지의 범위에 있는지 확인
            if (!updatedAt.isBefore(startOfPeriod) && !updatedAt.isAfter(endOfPeriod)) {
                // 같은 주문으로 처리
                return Optional.of(order);
            }

        }
        return Optional.empty();
    }
}
