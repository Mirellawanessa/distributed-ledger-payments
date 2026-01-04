FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/audit-service-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
