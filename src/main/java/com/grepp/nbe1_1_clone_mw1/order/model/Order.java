package com.grepp.nbe1_1_clone_mw1.order.model;

import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {
  @Id
  @Column(columnDefinition = "BINARY(16)")
  private byte[] orderId;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "postcode", nullable = false)
  private String postcode;

  @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
  private List<OrderItem> orderItems;

  @Column(name = "order_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  public static Order create(String email, String address, String postcode){
    return Order.builder()
            .orderId(UUIDUtil.createUUID())
            .email(email)
            .address(address)
            .postcode(postcode)
            .orderStatus(OrderStatus.ACCEPTED)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
  }

  public void updateStatus(OrderStatus status){
    this.orderStatus = status;
    this.updatedAt = LocalDateTime.now();
  }

  public void updateOrderItems(List<OrderItem> orderItems){
    this.orderItems = orderItems;
  }
}
