package io.github.hadenlabs.poc_opentelemetry.productservice.service;

import io.github.hadenlabs.poc_opentelemetry.productservice.dto.ProductResult;
import io.github.hadenlabs.poc_opentelemetry.productservice.dto.Promotion;
import io.github.hadenlabs.poc_opentelemetry.productservice.dto.Product;
import io.github.hadenlabs.poc_opentelemetry.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final PromotionServiceClient promotionServiceClient;

    public ProductService(ProductRepository productRepository, PromotionServiceClient promotionServiceClient) {
        this.promotionServiceClient = promotionServiceClient;
    }

    public List<ProductResult> getProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product(1L, "Lenovo Laptop", new BigDecimal("65000")));
        products.add(new Product(2L, "Apple MacBook", new BigDecimal("120000")));
        products.add(new Product(3L, "Dell Monitor", new BigDecimal("20000")));

        List<Promotion> promotions = promotionServiceClient.getProductPromotions();
        Map<Long, Promotion> promotionsMap = promotions.stream()
            .collect(Collectors.toMap(Promotion::productId, promotion -> promotion));

        return products.stream().map(product -> {
            BigDecimal originalPrice = product.price();
            BigDecimal discount = promotionsMap.getOrDefault(product.id(), new Promotion(null, product.id(), BigDecimal.ZERO)).discount();
            BigDecimal price = originalPrice.subtract(discount);

            return new ProductResult(
                product.id(),
                product.name(),
                originalPrice,
                discount,
                price
            );
        }).collect(Collectors.toList());
    }
}
