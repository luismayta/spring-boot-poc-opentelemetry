package io.github.hadenlabs.poc_opentelemetry.productservice;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private String promotionServiceUrl;
    private Jaeger jaeger = new Jaeger();

    public String getPromotionServiceUrl() {
        return promotionServiceUrl;
    }

    public void setPromotionServiceUrl(String promotionServiceUrl) {
        this.promotionServiceUrl = promotionServiceUrl;
    }

    public Jaeger getJaeger() {
        return jaeger;
    }

    public void setJaeger(Jaeger jaeger) {
        this.jaeger = jaeger;
    }

    public static class Jaeger {
        private boolean enabled = true;
        private Long timeout;
        private String endpoint;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public Long getTimeout() {
            return timeout;
        }

        public void setTimeout(Long timeout) {
            this.timeout = timeout;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }
    }
}
