package com.shiva.ecommerce_v2.category;

import com.shiva.ecommerce_v2.product.entity.Product;
import jakarta.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length=250)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
