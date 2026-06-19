package com.shiva.ecommerce_v2.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shiva.ecommerce_v2.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
  
}
