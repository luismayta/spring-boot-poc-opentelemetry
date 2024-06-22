package io.github.hadenlabs.poc_opentelemetry.promotionservice.dto;

import java.math.BigDecimal;

public record PromotionDTO(Long id, Long productId, BigDecimal discount) {
}
