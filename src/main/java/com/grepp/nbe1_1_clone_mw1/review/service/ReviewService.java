package com.grepp.nbe1_1_clone_mw1.review.service;

import com.grepp.nbe1_1_clone_mw1.auth.model.CustomUserDetail;
import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;
import com.grepp.nbe1_1_clone_mw1.order.repository.OrderItemRepository;
import com.grepp.nbe1_1_clone_mw1.product.model.Product;
import com.grepp.nbe1_1_clone_mw1.product.repository.ProductRepository;
import com.grepp.nbe1_1_clone_mw1.review.controller.dto.ReviewRequest;
import com.grepp.nbe1_1_clone_mw1.review.controller.dto.ReviewResponse;
import com.grepp.nbe1_1_clone_mw1.review.model.Review;
import com.grepp.nbe1_1_clone_mw1.review.repository.ReviewRepository;
import com.grepp.nbe1_1_clone_mw1.user.model.User;
import com.grepp.nbe1_1_clone_mw1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;

    public ResponseEntity<List<ReviewResponse>> getReviewsByUser(CustomUserDetail userDetail) {
        List<Review> reviews = reviewRepository.findByUser_Email(userDetail.getEmail());
        List<ReviewResponse> responses = new ArrayList<>();
        for (Review review : reviews) {
            responses.add(new ReviewResponse(review));
        }
        return ResponseEntity.ok(responses);
    }

    public ResponseEntity<List<ReviewResponse>> getReviewsByProduct(String productId) {
        List<Review> reviews = reviewRepository.findByProducts_ProductId(UUIDUtil.hexStringToByteArray(productId));
        List<ReviewResponse> responses = new ArrayList<>();
        for (Review review : reviews) {
            responses.add(new ReviewResponse(review));
        }
        return ResponseEntity.ok(responses);
    }

    public ResponseEntity<ReviewResponse> getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));
        return ResponseEntity.ok(new ReviewResponse(review));
    }

    @Transactional
    public ResponseEntity<String> createReview(String productId, CustomUserDetail userDetail, ReviewRequest reviewRequest) {
        // 권한 설정 넣어주기
        // userId 값을 받아와서 해당 userId의 주문과 productId의 orderItem이 존재하지 않으면 주문 exception 넣어주기 // 해당 유저의 주문내역에서 주문상품 찾기
        if (!orderItemRepository.existsByOrder_Email(userDetail.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("no order history for the product");
        }
        Product product = productRepository.findById(UUIDUtil.hexStringToByteArray(productId))
                .orElseThrow(() -> new RuntimeException("Product not found"));
        User user = userRepository.findByEmail(userDetail.getEmail())
                .orElseThrow(()->new RuntimeException("User not found")); // userRepository로 현재 접속한 user 찾기

        Review review = Review.create(reviewRequest);
        review.updateProductAndUser(product, user);
        reviewRepository.save(review);
        return ResponseEntity.ok("review created");
    }

    @Transactional
    public ResponseEntity<String> updateReview(Long reviewId, CustomUserDetail userDetail, ReviewRequest reviewRequest) {
        Review oldReview = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));
        User user = userRepository.findByEmail(userDetail.getEmail())
                .orElseThrow(()->new RuntimeException("User not found")); // userRepository로 현재 접속한 user 찾기
        // oldReview의 user와 현재 접속한 user가 다르면 리뷰 수정 권한 없음 로직 작성
        if (!checkUser(user, oldReview)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("review updated failure");
        }
        Review newReview = Review.builder()
                .reviewId(oldReview.getReviewId())
                .reviewName(reviewRequest.reviewName()==null?oldReview.getReviewName():reviewRequest.reviewName())
                .content(reviewRequest.content()==null?oldReview.getContent():reviewRequest.content())
                .rate(reviewRequest.rate()==null?oldReview.getRate():reviewRequest.rate())
                .createdAt(oldReview.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .products(oldReview.getProducts())
                .user(user)
                .build();
        reviewRepository.save(newReview);
        return ResponseEntity.ok("review updated");
    }

    @Transactional
    public ResponseEntity<String> deleteReview(Long reviewId, CustomUserDetail userDetail) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));
        User user = userRepository.findByEmail(userDetail.getEmail())
                .orElseThrow(()->new RuntimeException("User not found")); // userRepository로 현재 접속한 user 찾기
        // oldReview의 user와 현재 접속한 user가 다르면 리뷰 수정 권한 없음 로직 작성
        if (!checkUser(user, review)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("review deleted failure");
        }
        reviewRepository.delete(review);
        return ResponseEntity.ok("review deleted");
    }

    private boolean checkUser(User user, Review review) {
        return Arrays.equals(review.getUser().getUserId(), user.getUserId());
    }
}
