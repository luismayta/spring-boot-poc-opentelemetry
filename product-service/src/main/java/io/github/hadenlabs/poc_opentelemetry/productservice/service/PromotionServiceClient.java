package io.github.hadenlabs.poc_opentelemetry.productservice.service;

import io.github.hadenlabs.poc_opentelemetry.productservice.ApplicationProperties;
import io.github.hadenlabs.poc_opentelemetry.productservice.dto.Promotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
    import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PromotionServiceClient {

    private static final Logger log = LoggerFactory.getLogger(PromotionServiceClient.class);

    private final RestTemplate restTemplate;
    private final ApplicationProperties properties;

    public PromotionServiceClient(RestTemplate restTemplate, ApplicationProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    public List<Promotion> getProductPromotions() {
        try {
            log.info("Fetching promotions from {}", properties.getPromotionServiceUrl() + "/api/promotions");
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<?> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<List<Promotion>> response = restTemplate.exchange(
                properties.getPromotionServiceUrl() + "/api/promotions", HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<List<Promotion>>() {});
            return response.getBody();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
