package com.grepp.nbe1_1_clone_mw1.product.repository;


import com.grepp.nbe1_1_clone_mw1.product.model.Category;
import com.grepp.nbe1_1_clone_mw1.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, byte[]> {

  Optional<Product> findByProductName(String productName);

  List<Product> findByCategory(Category category);
}
