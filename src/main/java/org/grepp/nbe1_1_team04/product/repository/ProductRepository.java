package org.grepp.nbe1_1_team04.product.repository;

import org.grepp.nbe1_1_team04.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Byte[]> {
}
