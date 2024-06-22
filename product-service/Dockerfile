#
# Dockerfile for poc-opentelemetry
#
FROM registry.infosis.tech/architecture/openjdk:21

ARG VERSION=0.0.0
ARG ARTIFACT_NAME=poc-opentelemetry.jar

LABEL maintainer="Team DevOps <haden@hadenlabs.com>" \
      org.label-schema.vcs-url="https://github.com/hadenlabs/spring-boot-poc-opentelemetry" \
      org.label-schema.version=$VERSION \
      org.label-schema.schema-version="1.0"

WORKDIR /usr/src/app

ENV TZ=America/Argentina/Buenos_Aires
ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8
ENV JAVA_OPTS=""

ENV BASE_DEPS \
    bash \
    curl \
    vim \
    tzdata \
    rsync \
    ca-certificates

# Install dependencies
RUN apt-get update -y \
    && apt-get install -y --no-install-recommends \
    ${BASE_DEPS} \
    # Cleanup
    && apt-get clean \
    && apt-get purge -y \
    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

ENV JAVA_OPTS="-javaagent:${JAVA_HOME}/bin/opentelemetry-javaagent.jar"


COPY build/libs/product-service.jar product-service.jar

EXPOSE 8090

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "${JAVA_OPTS}", "-jar","product-service.jar"]