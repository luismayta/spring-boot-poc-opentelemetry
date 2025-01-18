import org.gradle.kotlin.dsl.*
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
  id("java-library")
  id("org.springframework.boot") version "3.3.5"
  id("io.spring.dependency-management") version "1.1.7"
  id("co.uzzu.dotenv.gradle") version "4.0.0"
}

group = "io.github.hadenlabs"
version = "0.0.0"
description = "poc-opentelemetry"

tasks.named("bootJar") {
  enabled = false
}

allprojects {
  repositories {
    mavenCentral()

    maven {
      url = uri("https://repo1.maven.org/maven2/")
    }

    maven {
      url = uri("https://packages.io/opentelemetry/java")
    }
  }
}

subprojects {
  apply(plugin = "java-library")
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")

  dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")

  }

  java {
    toolchain {
      languageVersion = JavaLanguageVersion.of(21)
    }
  }

  tasks.named("bootJar") {
    enabled = false
  }

  tasks.named("jar") {
    enabled = true
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

}

fun determineActiveProfile(): String {
  return System.getenv("SPRING_PROFILES_ACTIVE") ?: "local"
}

tasks.getByName<BootRun>("bootRun") {
  doFirst {
    systemProperty("spring.profiles.active", determineActiveProfile())
  }
}
