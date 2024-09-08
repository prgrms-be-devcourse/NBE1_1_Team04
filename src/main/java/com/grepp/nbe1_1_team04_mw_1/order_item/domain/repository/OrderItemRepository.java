package com.grepp.nbe1_1_team04_mw_1.order_item.domain.repository;

import com.grepp.nbe1_1_team04_mw_1.order_item.domain.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {
    Optional<OrderItems> findByOrders_OrderIdAndProducts_ProductId(byte[] orderId, byte[] productId);

    List<OrderItems> findByOrders_OrderId(byte[] orderId);

    void deleteByOrders_OrderIdAndProducts_ProductId(byte[] orderId, byte[] productId);

    void deleteByOrders_OrderId(byte[] orderId);

    void deleteByProducts_ProductId(byte[] productId);
}
