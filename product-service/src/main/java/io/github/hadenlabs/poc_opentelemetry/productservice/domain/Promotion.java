package io.github.hadenlabs.poc_opentelemetry.productservice.domain;

import java.math.BigDecimal;

public record Promotion(
    Long id,
    Long productId,
    BigDecimal discount
) {
}
