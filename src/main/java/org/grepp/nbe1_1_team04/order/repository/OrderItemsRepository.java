package org.grepp.nbe1_1_team04.order.repository;

import org.grepp.nbe1_1_team04.order.entity.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, Long>  {
}
