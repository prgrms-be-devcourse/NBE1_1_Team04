package com.grepp.nbe1_1_team04_mw_1.product.domain.repository;

import com.grepp.nbe1_1_team04_mw_1.product.domain.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, byte[]> {

    Optional<Products> findByProductName(String productName);
}
