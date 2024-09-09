package org.example.coffee.order;

import org.example.coffee.order.dto.OrderRequest;
import org.example.coffee.order.dto.OrderResponse;
import org.example.coffee.order.entity.Order;
import org.example.coffee.product.ProductMapper;
import org.example.coffee.util.UUIDUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
