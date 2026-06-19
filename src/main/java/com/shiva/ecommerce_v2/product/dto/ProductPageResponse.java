package com.shiva.ecommerce_v2.product.dto;

import java.util.List;

public record ProductPageResponse(
        List<ProductResponse> products,
        long totalElements,
        int totalPages,
        int size,
        int number
) {}