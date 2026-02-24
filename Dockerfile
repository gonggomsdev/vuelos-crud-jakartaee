FROM payara/server-full:6.2024.1-jdk17

# Descargar driver PostgreSQL
ADD https://jdbc.postgresql.org/download/postgresql-42.7.3.jar \
    $PAYARA_DIR/glassfish/domains/domain1/lib/

# Copiar comandos postboot
COPY postboot-commands.asadmin $POSTBOOT_COMMANDS

# Copiar WAR
COPY target/ROOT.war $DEPLOY_DIR