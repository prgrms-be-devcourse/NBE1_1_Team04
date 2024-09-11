package com.grepp.nbe1_1_clone_mw1.review.controller.api;

import com.grepp.nbe1_1_clone_mw1.review.controller.dto.ReviewRequest;
import com.grepp.nbe1_1_clone_mw1.review.controller.dto.ReviewResponse;
import com.grepp.nbe1_1_clone_mw1.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewRestController {
    private final ReviewService reviewService;

    @GetMapping("/review")
    public ResponseEntity<List<ReviewResponse>> getReviews(byte[] userId) {
        return reviewService.getReviewsByUser(userId);
    }

    @GetMapping("/review/{productId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByProductId(@PathVariable("productId") String productId) {
        return reviewService.getReviewsByProduct(productId);
    }

    @PostMapping("/review/{productId}")
    public ResponseEntity<String> addReview(@PathVariable("productId") String productId, @RequestBody ReviewRequest reviewRequest, byte[] userId) {
        return reviewService.createReview(productId, userId, reviewRequest);
    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable("reviewId") Long reviewId, @RequestBody ReviewRequest reviewRequest, byte[] userId) {
        return reviewService.updateReview(reviewId, userId, reviewRequest);
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") Long reviewId, byte[] userId) {
        return reviewService.deleteReview(reviewId, userId);
    }
}
