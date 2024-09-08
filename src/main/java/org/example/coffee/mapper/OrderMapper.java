package org.example.coffee.mapper;

import org.aspectj.weaver.ast.Or;
import org.example.coffee.dto.OrderRequest;
import org.example.coffee.dto.OrderResponse;
import org.example.coffee.dto.ProductResponse;
import org.example.coffee.entity.Order;
import org.example.coffee.entity.OrderItem;
import org.example.coffee.entity.Product;
import org.example.coffee.util.UUIDUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static Order toOrder(OrderRequest orderRequest) {
        return new Order(
                UUIDUtil.createUUID(),
                orderRequest.getEmail(),
                orderRequest.getAddress(),
                orderRequest.getPostcode(),
                "결제완료",
                LocalDateTime.now(),
                LocalDateTime.now(),
                new ArrayList<>()
        );
    }

    public static OrderResponse toResponse(Order order) {

        return new OrderResponse(
                UUIDUtil.bytesToHex(order.getOrderId()),
                order.getEmail(),
                order.getAddress(),
                order.getPostcode(),
                order.getOrderItemList().stream().map(orderItem -> ProductMapper.toResponse(orderItem.getProduct())).toList(),
                order.getOrderItemList().stream()
                        .mapToLong(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                        .sum(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}
