package com.shiva.ecommerce_v2.product.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Integer id;
    
    private String name;

    private BigDecimal price;

    private String brand;

    private String description;

    private String categoryName;
    
}
