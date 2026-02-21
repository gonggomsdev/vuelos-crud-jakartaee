FROM payara/micro:6.2023.10-jdk17
COPY mysql-connector-j-8.x.x.jar ${PAYARA_DIR}/libs/
COPY target/contactos-crud-jakartaee.war ${DEPLOY_DIR}
ENTRYPOINT ["java", "-jar", "payara-micro.jar", \
    "--deploy", "contactos-crud-jakartaee.war", \
    "--addlibs", "libs/mysql-connector-j-8.x.x.jar", \
    "--postdeploycommand", \
    "create-jdbc-connection-pool --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --restype javax.sql.DataSource --property user=${DB_USER}:password=${DB_PASSWORD}:url='jdbc\\:mysql\\://${DB_HOST}\\:${DB_PORT}/${DB_NAME}' contactosPool", \
    "--postdeploycommand", \
    "create-jdbc-resource --connectionpoolid contactosPool jdbc/bd1DS"]