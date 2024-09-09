package org.example.coffee.order.repository;

import org.example.coffee.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, byte[]> {
    List<Order> findByEmail(String email);
}
