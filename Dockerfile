# Dockerfile per Spring Boot + OpenAPI
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia il jar buildato
COPY target/cinema-scheduler*.jar app.jar

# Espone la porta dell'app
EXPOSE 8080

# Avvia l'app
ENTRYPOINT ["java", "-jar", "app.jar"]
