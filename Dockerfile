# Etapa 1: construir el WAR con Maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: desplegar en Payara con Java 21
FROM payara/server-full:6.2024.6-jdk21

COPY --from=build /app/target/*.war /opt/payara/deployments/

ENV PORT=8080
EXPOSE 8080