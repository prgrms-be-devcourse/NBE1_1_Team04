package org.grepp.nbe1_1_team04.order.repository;

import org.grepp.nbe1_1_team04.order.entity.OrderEntity;
import org.grepp.nbe1_1_team04.order.entity.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, Long>  {
    List<OrderItemsEntity> findByOrder(OrderEntity order);
    void deleteByOrder(OrderEntity order);
}
