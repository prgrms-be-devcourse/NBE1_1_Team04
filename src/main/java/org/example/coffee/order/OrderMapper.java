package org.example.coffee.order;

import org.example.coffee.order.dto.OrderRequest;
import org.example.coffee.order.dto.OrderResponse;
import org.example.coffee.order.entity.Order;
import org.example.coffee.order.entity.OrderItem;
import org.example.coffee.product.ProductMapper;
import org.example.coffee.product.dto.ProductResponse;
import org.example.coffee.util.UUIDUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                order.getOrderItemList().stream()
                        .collect(Collectors.toMap(
                                orderItem -> ProductMapper.toResponse(orderItem.getProduct()),
                                OrderItem::getQuantity,
                                Integer::sum // 한 주문에서 같은 상품을 여러번 나누어서 주문하는 경우가 있기 때문에 상품 중복 키가 발생할 수 있음.
                                            // toMap()메서드는 중복키가 발생하면 예외를 발생시키는데
                                            // 그 때, 수량을 합치도록 했음
                        )),
                order.getOrderItemList().stream()
                        .mapToLong(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                        .sum(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}
