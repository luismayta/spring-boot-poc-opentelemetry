package io.github.hadenlabs.poc_opentelemetry.promotionservice.controller;

import io.github.hadenlabs.poc_opentelemetry.promotionservice.domain.Promotion;
import io.github.hadenlabs.poc_opentelemetry.promotionservice.repository.PromotionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PromotionController {

    private static final Logger log = LoggerFactory.getLogger(PromotionController.class);

    private final PromotionRepository promotionRepository;

    public PromotionController(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @GetMapping("/api/promotions")
    public List<Promotion> getPromotions() {
        //randomWait();
        return promotionRepository.findAll();
    }

    private void randomWait() {
        int waitSeconds = getRandomNumber(0, 3);
        log.info("Sleeping for {} seconds", waitSeconds);
        try {
            Thread.sleep(waitSeconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
