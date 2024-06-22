package io.github.hadenlabs.poc_opentelemetry.productservice.controller;

import io.github.hadenlabs.poc_opentelemetry.productservice.domain.Product;
import io.github.hadenlabs.poc_opentelemetry.productservice.domain.ProductResult;
import io.github.hadenlabs.poc_opentelemetry.productservice.domain.Promotion;
import io.github.hadenlabs.poc_opentelemetry.productservice.domain.ProductRepository;
import io.github.hadenlabs.poc_opentelemetry.productservice.domain.PromotionServiceClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    private final ProductRepository productRepository;
    private final PromotionServiceClient promotionServiceClient;

    public ProductController(ProductRepository productRepository, PromotionServiceClient promotionServiceClient) {
        this.productRepository = productRepository;
        this.promotionServiceClient = promotionServiceClient;
    }

    @GetMapping("/api/products")
    public List<ProductResult> getProducts() {
        List<Product> products = productRepository.findAll();

        List<Promotion> promotions = promotionServiceClient.getProductPromotions();
        Map<Long, Promotion> promotionsMap = promotions
            .stream().collect(Collectors.toMap(Promotion::productId, promotion -> promotion));

        List<ProductResult> productResults = new ArrayList<>(products.size());
        for (Product product : products) {
            BigDecimal originalPrice = product.getPrice();
            BigDecimal discount = promotionsMap.containsKey(product.getId()) ?
                promotionsMap.get(product.getId()).discount() :
                BigDecimal.ZERO;
            BigDecimal price = originalPrice.subtract(discount);

            ProductResult productResult = new ProductResult(
                product.getId(),
                product.getName(),
                originalPrice,
                discount,
                price
            );
            productResults.add(productResult);
        }
        return productResults;
    }
}
