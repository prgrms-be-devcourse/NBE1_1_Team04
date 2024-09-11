package com.grepp.nbe1_1_clone_mw1.review.controller.dto;

import com.grepp.nbe1_1_clone_mw1.review.model.Review;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long reviewId,
        String reviewName,
        String content,
        double rate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
    public ReviewResponse(Review review){
        this(
                review.getReviewId(),
                review.getReviewName(),
                review.getContent(),
                review.getRate(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }
}
