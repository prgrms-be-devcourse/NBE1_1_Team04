package com.grepp.nbe1_1_clone_mw1.order.repository;

import com.grepp.nbe1_1_clone_mw1.order.model.Order;
import com.grepp.nbe1_1_clone_mw1.order.model.OrderItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    void deleteByProduct_ProductId(byte[] productId);

    List<OrderItem> findByOrder(Order order);
}
