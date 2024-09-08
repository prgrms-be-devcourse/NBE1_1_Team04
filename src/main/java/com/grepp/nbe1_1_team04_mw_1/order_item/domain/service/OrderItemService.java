package com.grepp.nbe1_1_team04_mw_1.order_item.domain.service;

import com.grepp.nbe1_1_team04_mw_1.order.domain.dto.request.OrderItemInfo;
import com.grepp.nbe1_1_team04_mw_1.order.domain.dto.request.OrderRequestDTO;
import com.grepp.nbe1_1_team04_mw_1.order.domain.entity.Orders;
import com.grepp.nbe1_1_team04_mw_1.order_item.domain.entity.OrderItems;
import com.grepp.nbe1_1_team04_mw_1.order_item.domain.repository.OrderItemRepository;
import com.grepp.nbe1_1_team04_mw_1.product.domain.entity.Products;
import com.grepp.nbe1_1_team04_mw_1.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public List<OrderItems> getOrderItems(String orderId) {
        return orderItemRepository.findByOrders_OrderId(Base64.getDecoder().decode(orderId));
    }
}
