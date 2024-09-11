package com.grepp.nbe1_1_clone_mw1.product.service;


import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;
import com.grepp.nbe1_1_clone_mw1.order.repository.OrderItemRepository;
import com.grepp.nbe1_1_clone_mw1.product.controller.dto.ProductResponse;
import com.grepp.nbe1_1_clone_mw1.product.controller.dto.UpdateProductRequest;
import com.grepp.nbe1_1_clone_mw1.product.model.Category;
import com.grepp.nbe1_1_clone_mw1.product.model.Product;
import com.grepp.nbe1_1_clone_mw1.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

  private final ProductRepository productRepository;
  private final OrderItemRepository orderItemRepository;

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

  @Transactional
  @Override
  public String deleteProduct(String productId) {
    orderItemRepository.deleteByProducts_ProductId(UUIDUtil.hexStringToByteArray(productId));
    productRepository.deleteByProductId(UUIDUtil.hexStringToByteArray(productId));
    return "delete success";
  }

  @Transactional
  @Override
  public Product updateProduct(String productId, UpdateProductRequest updateProductRequest) {
    Product oldProduct = productRepository.findById(UUIDUtil.hexStringToByteArray(productId)).orElseThrow(()->new RuntimeException("Product not found"));
    Product newProduct = Product.builder()
            .productId(oldProduct.getProductId())
            .productName(updateProductRequest.productName()==null ? oldProduct.getProductName() : updateProductRequest.productName())
            .price(updateProductRequest.price()==null ? oldProduct.getPrice() : updateProductRequest.price())
            .description(updateProductRequest.description()==null ? oldProduct.getDescription() : updateProductRequest.description())
            .category(updateProductRequest.category()==null ? oldProduct.getCategory() : updateProductRequest.category())
            .createdAt(oldProduct.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();

    return productRepository.save(newProduct);
  }

}
