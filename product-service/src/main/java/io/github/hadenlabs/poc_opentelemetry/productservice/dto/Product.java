package io.github.hadenlabs.poc_opentelemetry.productservice.dto;

import java.math.BigDecimal;

public record Product (
    Long id,
    String name,
    BigDecimal price
){}
