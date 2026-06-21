package com.shiva.ecommerce_v2.product.service;


import java.util.List;

import com.shiva.ecommerce_v2.category.Category;
import com.shiva.ecommerce_v2.category.repository.CategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import com.shiva.ecommerce_v2.product.dto.ProductPageResponse;
import com.shiva.ecommerce_v2.product.dto.ProductRequest;
import com.shiva.ecommerce_v2.product.dto.ProductResponse;
import com.shiva.ecommerce_v2.product.entity.Product;
import com.shiva.ecommerce_v2.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductPageResponse getAllProducts(
      int page,
      int size,
      String sortByString, 
      String direction
    ){
        Sort sort = direction.equalsIgnoreCase("desc")
                    ? Sort.by(sortByString).descending()
                    : Sort.by(sortByString).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productPage  = productRepository.findAll(pageable);

        List<ProductResponse> products = productPage.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .toList();
        return new ProductPageResponse(
                products,
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.getSize(),
                productPage.getNumber()
        );
    }

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getBrand(),
            product.getDescription(),          
            product.getCategory().getName()
        );
    }

    public ProductResponse addProduct(ProductRequest productReq) {
        Long categoryId = productReq.getCategoryId();

        Category category = categoryRepository.getReferenceById(categoryId);
        Product product = new Product();

        product.setName(productReq.getName());
        product.setPrice(productReq.getPrice());
        product.setBrand(productReq.getBrand());
        product.setDescription(productReq.getDescription());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return mapToResponse(savedProduct);
    }

    @Component
    public class ProductMapper {

        public ProductResponse toResponse(Product product) {
            return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getBrand(),
                product.getDescription(),
                product.getCategory().getName()
            );
        }

        public Product toEntity(ProductRequest request) {
            Category category = categoryRepository
                    .findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            Product product = new Product();

            product.setName(request.getName());
            product.setPrice(request.getPrice());
            product.setBrand(request.getBrand());
            product.setDescription(request.getDescription());
            product.setCategory(category);

            return product;
        }
    }
}
