import org.gradle.kotlin.dsl.*
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
  id("java-library")
	id("org.springframework.boot") version "3.3.1"
  id("io.spring.dependency-management") version "1.1.3"
}

group = "io.github.hadenlabs"
version = "0.0.0"
description = "poc-opentelemetry"

/*springBoot {
  mainClass = "io.github.hadenlabs.poc_opentelemetry.promotionservice.PromotionServiceApplication"
}*/

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
  mainClass.set("io.github.hadenlabs.poc_opentelemetry.promotionservice.PromotionServiceApplication")
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")

  // Spring Boot Web
  implementation("org.springframework.boot:spring-boot-starter-web")

  //  swagger
  implementation("io.springfox:springfox-swagger2:2.9.2")
  implementation("io.springfox:springfox-swagger-ui:2.9.2")

  // Spring Boot Actuator
  implementation("org.springframework.boot:spring-boot-starter-actuator")

  // Spring Boot Data JPA
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")

  // H2 Database
  implementation("com.h2database:h2")

  // PostgreSQL
  implementation("org.postgresql:postgresql")

  //  observability
  // Micrometer Tracing Bridge for OpenTelemetry
  implementation("io.micrometer:micrometer-tracing-bridge-otel")
  implementation("io.opentelemetry:opentelemetry-sdk:1.5.0")
  implementation("io.opentelemetry:opentelemetry-exporter-jaeger:1.5.0")
  implementation("io.opentelemetry:opentelemetry-exporter-logging:1.5.0")

  // Spring Boot DevTools
  runtimeOnly("org.springframework.boot:spring-boot-devtools")

  // Spring Boot Starter Test
  testImplementation("org.springframework.boot:spring-boot-starter-test")

  // Testcontainers JUnit Jupiter
  testImplementation("org.testcontainers:junit-jupiter")

  // Testcontainers PostgreSQL
  testImplementation("org.testcontainers:postgresql")

  //  liquibase
  implementation("org.liquibase:liquibase-core")

  runtimeOnly("org.postgresql:postgresql")

  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}