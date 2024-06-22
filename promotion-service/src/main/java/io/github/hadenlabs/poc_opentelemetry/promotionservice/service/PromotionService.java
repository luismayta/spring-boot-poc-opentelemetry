package io.github.hadenlabs.poc_opentelemetry.promotionservice.service;

import io.github.hadenlabs.poc_opentelemetry.promotionservice.dto.PromotionDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    public PromotionService() {
    }

    public List<PromotionDTO> getAllPromotions() {
        List<PromotionDTO> promotions = new ArrayList<>();
        promotions.add(new PromotionDTO(1L, 1L, new BigDecimal("10000")));
        promotions.add(new PromotionDTO(2L, 1L, new BigDecimal("10000")));
        return promotions.stream()
            .map(promotion -> new PromotionDTO(promotion.id(), promotion.productId(), promotion.discount()))
            .collect(Collectors.toList());
    }
}
