package com.grepp.nbe1_1_clone_mw1.order.model;

import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;
import jakarta.persistence.*;
import java.util.ArrayList;
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

  @Builder.Default
  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<OrderItem> orderItems = new ArrayList<>();

  @Column(name = "order_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  public static Order create(OrderContent content, List<OrderItem> orderItems){
    Order order = Order.builder()
            .orderId(UUIDUtil.createUUID())
            .email(content.email())
            .address(content.address())
            .postcode(content.postcode())
            .orderStatus(OrderStatus.ACCEPTED)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();

    return order.addOrderItems(orderItems);
  }

  public void updateStatus(OrderStatus status){
    this.orderStatus = status;
    this.updatedAt = LocalDateTime.now();
  }

  public void updateOrderItems(List<OrderItem> orderItems){
    this.orderItems = orderItems;
  }

  // 연관관계 편의
  public Order addOrderItems(List<OrderItem> orderItems){
    this.orderItems.addAll(orderItems);

    for (OrderItem orderItem : orderItems) {
      orderItem.setOrder(this);
    }
    return this;
  }

  public boolean isOrderer(String email){
    return this.email.equals(email);
  }

  public void update(OrderContent updatedContent){
      this.email = updatedContent.email();
      this.address = updatedContent.address();
      this.postcode = updatedContent.postcode();
      this.updatedAt = LocalDateTime.now();
  }

  public void startDelivery() {
    this.orderStatus = OrderStatus.SHIPPED;
    this.updatedAt = LocalDateTime.now();
  }
}
