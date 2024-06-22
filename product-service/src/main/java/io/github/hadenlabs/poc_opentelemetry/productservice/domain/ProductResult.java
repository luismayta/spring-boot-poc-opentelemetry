package io.github.hadenlabs.poc_opentelemetry.productservice.domain;

import java.math.BigDecimal;

public record ProductResult(
    Long id,
    String name,
    BigDecimal originalPrice,
    BigDecimal discount,
    BigDecimal price
) {
}
