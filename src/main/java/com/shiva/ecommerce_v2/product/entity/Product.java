package com.shiva.ecommerce_v2.product.entity;

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

    @Column(nullable = false, length = 250)
    private  String category;

    @Column(nullable = false, length = 500)
    private  String description;

}