package org.grepp.nbe1_1_team04.order.repository;

import org.grepp.nbe1_1_team04.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
