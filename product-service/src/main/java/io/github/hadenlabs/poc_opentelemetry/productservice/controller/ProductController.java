package io.github.hadenlabs.poc_opentelemetry.productservice.controller;

import io.github.hadenlabs.poc_opentelemetry.productservice.dto.ProductResult;
import io.github.hadenlabs.poc_opentelemetry.productservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public List<ProductResult> getProducts() {
        return productService.getProducts();
    }
}
