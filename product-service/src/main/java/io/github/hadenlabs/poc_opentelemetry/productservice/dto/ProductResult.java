package io.github.hadenlabs.poc_opentelemetry.productservice.dto;

import java.math.BigDecimal;

public record ProductResult(
    Long id,
    String name,
    BigDecimal originalPrice,
    BigDecimal discount,
    BigDecimal price
) {
}
