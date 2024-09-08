package org.example.coffee.repository;

import org.example.coffee.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, byte[]> {
    List<Order> findByEmail(String email);
}
