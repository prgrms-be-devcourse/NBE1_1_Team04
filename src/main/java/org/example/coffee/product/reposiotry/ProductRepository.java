package org.example.coffee.product.reposiotry;

import org.example.coffee.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, byte[]> {
}
