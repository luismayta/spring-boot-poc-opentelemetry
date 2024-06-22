package io.github.hadenlabs.poc_opentelemetry.promotionservice.repository;

import io.github.hadenlabs.poc_opentelemetry.promotionservice.domain.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
