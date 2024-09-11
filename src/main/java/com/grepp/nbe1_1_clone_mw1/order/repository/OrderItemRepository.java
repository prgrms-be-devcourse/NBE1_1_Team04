package com.grepp.nbe1_1_clone_mw1.order.repository;

import com.grepp.nbe1_1_clone_mw1.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    void deleteByProducts_ProductId(byte[] productId);
}
