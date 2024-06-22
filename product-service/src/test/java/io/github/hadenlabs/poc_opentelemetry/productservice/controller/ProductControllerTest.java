package io.github.hadenlabs.poc_opentelemetry.productservice.controller;

import io.github.hadenlabs.poc_opentelemetry.productservice.domain.Product;
import io.github.hadenlabs.poc_opentelemetry.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
        Product product1 = new Product(1L, "Product 1", new BigDecimal("100.00"));
        Product product2 = new Product(2L, "Product 2", new BigDecimal("150.00"));
        productRepository.saveAll(Arrays.asList(product1, product2));
    }

    @Test
    public void shouldReturnAllProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Product 1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value("100.00"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Product 2"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value("150.00"));
    }
}
