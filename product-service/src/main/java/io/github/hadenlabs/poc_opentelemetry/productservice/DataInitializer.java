package io.github.hadenlabs.poc_opentelemetry.productservice;

import io.github.hadenlabs.poc_opentelemetry.productservice.domain.Product;
import io.github.hadenlabs.poc_opentelemetry.productservice.domain.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ProductRepository repository;

    public DataInitializer(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        Product p1 = new Product(1L, "Samsung TV", new BigDecimal(45000));
        Product p2 = new Product(2L, "LG Fritz", new BigDecimal(25000));
        Product p3 = new Product(3L, "Lenovo Laptop", new BigDecimal(65000));
        repository.save(p1);
        repository.save(p2);
        repository.save(p3);
    }
}
