package com.shiva.ecommerce_v2.product.entity;

import com.shiva.ecommerce_v2.category.Category;
import jakarta.persistence.*;
import java.math.BigDecimal;

import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    
    @Column(nullable = false, length = 250)
    private  String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private  BigDecimal price;

    
    @Column(nullable = false, length = 250)
    private  String brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false, length = 500)
    private  String description;

}