package com.grepp.nbe1_1_clone_mw1.order.repository;


import com.grepp.nbe1_1_clone_mw1.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, byte[]> {

}
