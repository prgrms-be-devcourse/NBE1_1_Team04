package com.grepp.nbe1_1_clone_mw1.review.repository;

import com.grepp.nbe1_1_clone_mw1.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProducts_ProductId(byte[] productId);
    List<Review> findByUser_UserId(byte[] userId);
    List<Review> findByUser_Email(String email);
}
