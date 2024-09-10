package org.grepp.nbe1_1_team04.order.repository;

import org.grepp.nbe1_1_team04.order.entity.OrderEntity;
import org.grepp.nbe1_1_team04.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, byte[]> {
    List<OrderEntity> findByEmail(String email);
    List<OrderEntity> findByEmailOrderByCreatedAtDesc(String email);
    List<OrderEntity> findByOrderStatus(OrderStatus orderStatus);
}
