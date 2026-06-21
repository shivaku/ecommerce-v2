package com.shiva.ecommerce_v2.product.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Description required")
    private String description;

    @NotNull(message = "Category is required")
    private Long categoryId;
}
