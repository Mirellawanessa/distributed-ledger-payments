FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/reconciliation-service-*.jar app.jar

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "app.jar"]
