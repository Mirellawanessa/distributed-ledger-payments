FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/reconciliation-service-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
