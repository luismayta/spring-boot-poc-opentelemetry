import org.gradle.kotlin.dsl.*
import org.gradle.api.tasks.JavaExec
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
  id("java-library")
	id("org.springframework.boot") version "3.3.1"
  id("io.spring.dependency-management") version "1.1.3"
  id("co.uzzu.dotenv.gradle") version "4.0.0"
}

group = "io.github.hadenlabs"
version = "0.0.0"
description = "poc-opentelemetry"

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
  archiveFileName.set("poc-opentelemetry.jar")
}

repositories {
  google()
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")
  implementation("org.liquibase:liquibase-core")
  runtimeOnly("org.postgresql:postgresql")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
  options.encoding = "UTF-8"
}

tasks.withType<Test> {
  useJUnitPlatform()
}

fun determineActiveProfile(): String {
  return System.getenv("SPRING_PROFILES_ACTIVE") ?: "local"
}

tasks.register("printEnvVariables") {
  doLast {
    println("SPRING_PROFILES_ACTIVE for system: ${System.getenv("SPRING_PROFILES_ACTIVE")}")
    println("SPRING_PROFILES_ACTIVE: ${project.findProperty("env.SPRING_PROFILES_ACTIVE") ?: "not set"}")
  }
}

gradle.taskGraph.whenReady {
  allTasks.forEach { task ->
    task.doFirst {
      println("Profile active for task '${task.name}': ${System.getProperty("spring.profiles.active", "default")}")
    }
  }
}
tasks.getByName<BootRun>("bootRun") {
  doFirst {
    systemProperty("spring.profiles.active", determineActiveProfile())
  }
}

tasks.named<Copy>("processResources") {
  filesMatching("application.yml") {
    expand(project.properties)
  }
}