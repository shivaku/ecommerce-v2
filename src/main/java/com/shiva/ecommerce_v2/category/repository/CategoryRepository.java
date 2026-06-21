package com.shiva.ecommerce_v2.category.repository;

import com.shiva.ecommerce_v2.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{
}
