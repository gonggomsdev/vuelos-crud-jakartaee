#!/bin/sh
set -eu

echo "=== Generating post-boot commands ==="

cat > /opt/payara/config/post-boot-generated.asadmin <<EOF
create-jdbc-connection-pool --datasourceclassname=org.postgresql.ds.PGSimpleDataSource --restype=javax.sql.DataSource --property user=${DB_USER}:password=${DB_PASSWORD}:serverName=${DB_HOST}:portNumber=${DB_PORT}:databaseName=${DB_NAME}:sslmode=${DB_SSL_MODE} PostgresPool
create-jdbc-resource --connectionpoolid=PostgresPool jdbc/vuelosDS
EOF

echo "=== Post-boot file contents ==="
cat /opt/payara/config/post-boot-generated.asadmin

exec java \
  -jar /opt/payara/payara-micro.jar \
  --noCluster \
  --port 8080 \
  --addlibs /opt/payara/libs/postgresql.jar \
  --postbootcommandfile /opt/payara/config/post-boot-generated.asadmin \
  --deploy /opt/payara/deployments/ROOT.war \
  --contextroot /