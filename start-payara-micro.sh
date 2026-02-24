#!/bin/sh
set -eu

cat > /opt/payara/config/post-boot-generated.asadmin <<EOF
create-jdbc-connection-pool --datasourceclassname=org.postgresql.ds.PGSimpleDataSource --restype=javax.sql.DataSource PostgresPool
set resources.jdbc-connection-pool.PostgresPool.property.user=${DB_USER}
set resources.jdbc-connection-pool.PostgresPool.property.password=${DB_PASSWORD}
set resources.jdbc-connection-pool.PostgresPool.property.serverName=${DB_HOST}
set resources.jdbc-connection-pool.PostgresPool.property.portNumber=${DB_PORT}
set resources.jdbc-connection-pool.PostgresPool.property.databaseName=${DB_NAME}
create-jdbc-resource --connectionpoolid=PostgresPool java:app/jdbc/vuelosDS
EOF

exec java -Xms64m -Xmx320m -XX:MaxMetaspaceSize=160m \
  -jar /opt/payara/payara-micro.jar \
  --noCluster \
  --port 8080 \
  --addlibs /opt/payara/libs/postgresql.jar \
  --postbootcommandfile /opt/payara/config/post-boot-generated.asadmin \
  --deploy /opt/payara/deployments/ROOT.war \
  --contextroot /