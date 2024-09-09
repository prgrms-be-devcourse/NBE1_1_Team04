package org.grepp.nbe1_1_team04.order.service;

import org.grepp.nbe1_1_team04.order.dto.OrderItemInfo;
import org.grepp.nbe1_1_team04.order.dto.OrderRequest;
import org.grepp.nbe1_1_team04.order.dto.OrderResponse;
import org.grepp.nbe1_1_team04.order.entity.OrderEntity;
import org.grepp.nbe1_1_team04.order.entity.OrderItemsEntity;
import org.grepp.nbe1_1_team04.order.repository.OrderItemsRepository;
import org.grepp.nbe1_1_team04.order.repository.OrderRepository;
import org.grepp.nbe1_1_team04.product.entity.ProductEntity;
import org.grepp.nbe1_1_team04.product.repository.ProductRepository;
import org.grepp.nbe1_1_team04.util.UUIDUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderItemsRepository orderItemsRepository;
    private ProductRepository productRepository;
    private UUIDUtil uuidUtil;

    public OrderService(OrderRepository orderRepository, OrderItemsRepository orderItemsRepository, ProductRepository productRepository, UUIDUtil uuidUtil) {
        this.orderRepository = orderRepository;
        this.orderItemsRepository = orderItemsRepository;
        this.productRepository = productRepository;
        this.uuidUtil = uuidUtil;
    }

    public void createOrder(OrderRequest orderRequest) {
        byte[] orderId = uuidUtil.createRandomUUID();

        OrderEntity orderEntity = orderRequest.toOrderEntity();
        orderEntity.updateOrderId(orderId);

        OrderEntity savedOrder = orderRepository.save(orderEntity);
        List<OrderItemInfo> orderItems = orderRequest.getOrderItems();
        saveOrderItems(orderItems, savedOrder);
    }

    private void saveOrderItems(List<OrderItemInfo> orderItems, OrderEntity savedOrder) {
        for (OrderItemInfo orderItem : orderItems) {
            ProductEntity productEntity = productRepository.findById(orderItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItemsEntity orderItemsEntity = new OrderItemsEntity(
                    productEntity.getPrice(),
                    orderItem.getQuantity(),
                    productEntity.getCategory(),
                    savedOrder,
                    productEntity
            );

            orderItemsRepository.save(orderItemsEntity);
        }
    }

    public List<OrderResponse> getOrders(String email) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findByEmail(email);

        for (OrderEntity orderEntity : orderEntities) {
            List<OrderItemInfo> orderItemInfos = getOrderItemInfos(orderEntity);

            orderResponses.add(new OrderResponse(
                    orderEntity.getOrderId(),
                    orderEntity.getEmail(),
                    orderEntity.getAddress(),
                    orderEntity.getPostcode(),
                    orderItemInfos
            ));
        }

        return orderResponses;
    }

    public OrderResponse getOrder(byte[] orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<OrderItemInfo> orderItemInfos = getOrderItemInfos(orderEntity);

        return new OrderResponse(
                orderEntity.getOrderId(),
                orderEntity.getEmail(),
                orderEntity.getAddress(),
                orderEntity.getPostcode(),
                orderItemInfos
        );
    }

    private List<OrderItemInfo> getOrderItemInfos(OrderEntity orderEntity) {
        return orderItemsRepository.findByOrder(orderEntity)
                .stream().map(m -> new OrderItemInfo(m.getProduct().getProductId(), m.getQuantity()))
                .toList();
    }
    //품목 삭제일 경우,
    //품목 추가일 경우,
    //TODO : 구현하기
    public void updateOrder(OrderRequest orderRequest, byte[] orderId) {
        OrderEntity orderEntity = orderRequest.toOrderEntity();
        orderEntity.updateOrderId(orderId);
        OrderEntity savedOrder = orderRepository.save(orderEntity);
    }

    public void deleteOrder(byte[] orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        orderItemsRepository.deleteByOrder(orderEntity);
        orderRepository.deleteById(orderId);
    }
}
