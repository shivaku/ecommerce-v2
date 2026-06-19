package com.shiva.ecommerce_v2.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.ecommerce_v2.product.dto.ProductPageResponse;
import com.shiva.ecommerce_v2.product.dto.ProductRequest;
import com.shiva.ecommerce_v2.product.dto.ProductResponse;
import com.shiva.ecommerce_v2.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ProductPageResponse getAllProduct(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "desc") String direction
    ){
        return productService.getAllProducts(page, size, sortBy, direction);
    }

    @PostMapping
    public ProductResponse addProduct(@Valid @RequestBody ProductRequest productReq) {
        return productService.addProduct(productReq);
    }
  
}