# ===============================
# STAGE 1 — Build da aplicação
# ===============================
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copia apenas o pom primeiro (cache eficiente)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Build do jar
RUN mvn clean package -DskipTests


# ===============================
# STAGE 2 — Runtime
# ===============================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Usuário não-root (boa prática de segurança)
RUN addgroup -S app && adduser -S app -G app
USER app

# Copia o jar gerado
COPY --from=builder /app/target/ledger-service-1.0.0.jar app.jar

# Porta padrão Spring Boot
EXPOSE 8080

# JVM flags seguras para containers
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "app.jar"]
