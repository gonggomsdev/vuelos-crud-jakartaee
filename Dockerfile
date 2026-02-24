FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

FROM payara/micro:6.2024.6-jdk21
USER root
RUN mkdir -p /opt/payara/config /opt/payara/libs /opt/payara/deployments
ADD https://jdbc.postgresql.org/download/postgresql-42.7.1.jar /opt/payara/libs/postgresql.jar
COPY --from=build /build/target/contactos-crud-jakartaee.war /opt/payara/deployments/ROOT.war
COPY start-payara-micro.sh /opt/payara/config/start-payara-micro.sh
RUN chmod +x /opt/payara/config/start-payara-micro.sh
RUN chown -R payara:payara /opt/payara
USER payara
EXPOSE 8080
ENTRYPOINT ["/opt/payara/config/start-payara-micro.sh"]