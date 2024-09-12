package com.grepp.nbe1_1_clone_mw1.review.controller.api;

import com.grepp.nbe1_1_clone_mw1.auth.model.CustomUserDetail;
import com.grepp.nbe1_1_clone_mw1.global.annotation.LoginUser;
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
    public ResponseEntity<List<ReviewResponse>> getReviews(@LoginUser CustomUserDetail userDetail) {
        return reviewService.getReviewsByUser(userDetail);
    }

    @GetMapping("/review/product/{productId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByProductId(@PathVariable("productId") String productId) {
        return reviewService.getReviewsByProduct(productId);
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable("reviewId") Long reviewId) {
        return reviewService.getReview(reviewId);
    }

    @PostMapping("/review/{productId}")
    public ResponseEntity<String> addReview(@PathVariable("productId") String productId, @RequestBody ReviewRequest reviewRequest, @LoginUser CustomUserDetail userDetail) {
        return reviewService.createReview(productId, userDetail, reviewRequest);
    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable("reviewId") Long reviewId, @RequestBody ReviewRequest reviewRequest, @LoginUser CustomUserDetail userDetail) {
        return reviewService.updateReview(reviewId, userDetail, reviewRequest);
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") Long reviewId, @LoginUser CustomUserDetail userDetail) {
        return reviewService.deleteReview(reviewId, userDetail);
    }
}
