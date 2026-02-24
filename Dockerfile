FROM payara/server-full:6.2024.1-jdk17

# Copiar WAR
COPY target/contactos-crud-jakartaee.war $DEPLOY_DIR

# Copiar comandos
COPY postboot-commands.asadmin $POSTBOOT_COMMANDS