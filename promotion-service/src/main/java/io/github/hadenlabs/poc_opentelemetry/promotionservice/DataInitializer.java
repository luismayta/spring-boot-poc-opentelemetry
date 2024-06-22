package io.github.hadenlabs.poc_opentelemetry.promotionservice;

import io.github.hadenlabs.poc_opentelemetry.promotionservice.domain.Promotion;
import io.github.hadenlabs.poc_opentelemetry.promotionservice.domain.PromotionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {
    private final PromotionRepository repository;

    public DataInitializer(PromotionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        Promotion p1 = new Promotion(1L, 1L, BigDecimal.TEN);
        Promotion p2 = new Promotion(2L, 2L, BigDecimal.ONE);
        repository.save(p1);
        repository.save(p2);
    }
}
