package com.grepp.nbe1_1_clone_mw1.product.service;


import com.grepp.nbe1_1_clone_mw1.product.controller.dto.ProductResponse;
import com.grepp.nbe1_1_clone_mw1.product.model.Category;
import com.grepp.nbe1_1_clone_mw1.product.model.Product;

import java.util.List;

public interface ProductService {

  List<ProductResponse> getProductsByCategory(Category category);

  List<ProductResponse> getAllProducts();

  Product createProduct(String productName, Category category, long price);

  Product createProduct(String productName, Category category, long price, String description);

}
