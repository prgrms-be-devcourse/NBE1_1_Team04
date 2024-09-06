package org.grepp.nbe1_1_team04.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.grepp.nbe1_1_team04.global.entity.BaseTimeEntity;
import org.grepp.nbe1_1_team04.order.entity.OrderStatus;
import org.grepp.nbe1_1_team04.product.controller.ProductController;

@Entity(name = "product")
public class ProductEntity extends BaseTimeEntity {
    @Id
    private Byte[] productId;
    private String productName;
    private String description;
    private ProductCategory category;
    private Long price;
}
