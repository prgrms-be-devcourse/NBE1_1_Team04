package com.grepp.nbe1_1_clone_mw1.product.controller.dto;

import com.grepp.nbe1_1_clone_mw1.product.model.Category;
import org.springframework.web.multipart.MultipartFile;

public record UpdateProductRequest(String productName, Category category, Long price, String description, MultipartFile[] uploadImage) {
}
