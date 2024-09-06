package com.grepp.nbe1_1_team04_mw_1.product.domain.repository;

import com.grepp.nbe1_1_team04_mw_1.product.domain.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
}
