package com.grepp.nbe1_1_clone_mw1.product.service;


import com.grepp.nbe1_1_clone_mw1.product.controller.dto.ProductResponse;
import com.grepp.nbe1_1_clone_mw1.product.model.Category;
import com.grepp.nbe1_1_clone_mw1.product.model.Product;
import com.grepp.nbe1_1_clone_mw1.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService {

  private final ProductRepository productRepository;

  public DefaultProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<ProductResponse> getProductsByCategory(Category category) {

    return productRepository.findByCategory(category).stream().map(ProductResponse::new).toList();
  }

  @Override
  public List<ProductResponse> getAllProducts() {
    return productRepository.findAll().stream().map(ProductResponse::new).toList();
  }

  @Override
  public Product createProduct(String productName, Category category, long price) {
    var product = Product.create(productName, category, price);
    return productRepository.save(product);
  }

  @Override
  public Product createProduct(String productName, Category category, long price, String description) {
    var product = Product.create(productName, category, price, description);
    return productRepository.save(product);
  }

}
