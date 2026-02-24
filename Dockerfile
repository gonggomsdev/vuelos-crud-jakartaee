# Etapa 1: construir el WAR con Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: desplegar en Payara
FROM payara/server-full:6.2024.6

# Copiar el WAR al directorio de autodeploy
COPY --from=build /app/target/*.war /opt/payara/deployments/
EXPOSE 8080