package com.grepp.nbe1_1_clone_mw1.review.controller.dto;

public record ReviewRequest(
        String reviewName,
        String content,
        Double rate
) {
}
