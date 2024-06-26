package io.github.hadenlabs.poc_opentelemetry.productservice.repository;

import io.github.hadenlabs.poc_opentelemetry.productservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
