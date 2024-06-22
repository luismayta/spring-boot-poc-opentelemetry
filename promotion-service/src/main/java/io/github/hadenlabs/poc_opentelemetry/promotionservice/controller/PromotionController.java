package io.github.hadenlabs.poc_opentelemetry.promotionservice.controller;

import io.github.hadenlabs.poc_opentelemetry.promotionservice.dto.PromotionDTO;
import io.github.hadenlabs.poc_opentelemetry.promotionservice.service.PromotionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping("/api/promotions")
    public List<PromotionDTO> getPromotions() {
        return promotionService.getAllPromotions();
    }
}
