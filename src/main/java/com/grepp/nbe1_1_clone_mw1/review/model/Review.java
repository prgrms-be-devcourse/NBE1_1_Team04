package com.grepp.nbe1_1_clone_mw1.review.model;

import com.grepp.nbe1_1_clone_mw1.product.model.Product;
import com.grepp.nbe1_1_clone_mw1.review.controller.dto.ReviewRequest;
import com.grepp.nbe1_1_clone_mw1.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "review_name", nullable = false)
    private String reviewName;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "rate", nullable = false)
    private double rate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Review create(ReviewRequest reviewRequest){
        return Review.builder()
                .reviewName(reviewRequest.reviewName())
                .content(reviewRequest.content())
                .rate(reviewRequest.rate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public void updateProductAndUser(Product product, User user){
        this.products = product;
        this.user = user;
    }
}
