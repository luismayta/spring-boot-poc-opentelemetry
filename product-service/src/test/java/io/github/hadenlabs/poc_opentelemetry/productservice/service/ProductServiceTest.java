package io.github.hadenlabs.poc_opentelemetry.productservice.service;

import io.github.hadenlabs.poc_opentelemetry.productservice.dto.Product;
import io.github.hadenlabs.poc_opentelemetry.productservice.dto.ProductResult;
import io.github.hadenlabs.poc_opentelemetry.productservice.dto.Promotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @Mock
    private PromotionServiceClient promotionServiceClient;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProducts() {
        List<Promotion> promotions = Arrays.asList(
            new Promotion(null, 1L, new BigDecimal("3000")),
            new Promotion(null, 2L, new BigDecimal("5000"))
        );

        when(promotionServiceClient.getProductPromotions()).thenReturn(promotions);

        List<ProductResult> productResults = productService.getProducts();

        assertEquals(3, productResults.size());

        ProductResult product1 = productResults.get(0);
        assertEquals(1L, product1.id());
        assertEquals("Lenovo Laptop", product1.name());
        assertEquals(new BigDecimal("65000"), product1.originalPrice());
        assertEquals(new BigDecimal("3000"), product1.discount());
        assertEquals(new BigDecimal("62000"), product1.price());

        ProductResult product2 = productResults.get(1);
        assertEquals(2L, product2.id());
        assertEquals("Apple MacBook", product2.name());
        assertEquals(new BigDecimal("120000"), product2.originalPrice());
        assertEquals(new BigDecimal("5000"), product2.discount());
        assertEquals(new BigDecimal("115000"), product2.price());

        ProductResult product3 = productResults.get(2);
        assertEquals(3L, product3.id());
        assertEquals("Dell Monitor", product3.name());
        assertEquals(new BigDecimal("20000"), product3.originalPrice());
        assertEquals(BigDecimal.ZERO, product3.discount());
        assertEquals(new BigDecimal("20000"), product3.price());
    }
}
