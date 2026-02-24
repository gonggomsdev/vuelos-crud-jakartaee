#!/bin/sh
set -eu

echo "Starting Payara Micro..."

required_vars="DB_HOST DB_PORT DB_NAME DB_USER DB_PASSWORD DB_SSL_MODE"

for var in $required_vars; do
  eval "value=\${$var:-}"
  if [ -z "$value" ]; then
    echo "Missing required environment variable: $var" >&2
    exit 1
  fi
done

echo "Generating datasource configuration..."

cat > /opt/payara/config/post-boot-generated.asadmin <<EOF
set-log-levels jakarta.enterprise.system.core=INFO

create-jdbc-connection-pool \
  --datasourceclassname=org.postgresql.ds.PGSimpleDataSource \
  --restype=javax.sql.DataSource \
  PostgresPool

set resources.jdbc-connection-pool.PostgresPool.property.user=${DB_USER}
set resources.jdbc-connection-pool.PostgresPool.property.password=${DB_PASSWORD}
set resources.jdbc-connection-pool.PostgresPool.property.serverName=${DB_HOST}
set resources.jdbc-connection-pool.PostgresPool.property.portNumber=${DB_PORT}
set resources.jdbc-connection-pool.PostgresPool.property.databaseName=${DB_NAME}
set resources.jdbc-connection-pool.PostgresPool.property.sslmode=${DB_SSL_MODE}

create-jdbc-resource \
  --connectionpoolid=PostgresPool \
  jdbc/bd1DS
EOF


echo "Launching Payara Micro..."

exec java -jar /opt/payara/payara-micro.jar \
  --noCluster \
  --port ${PORT:-8080} \
  --addlibs /opt/payara/libs/postgresql.jar \
  --postbootcommandfile /opt/payara/config/post-boot-generated.asadmin \
  --postdeploycommandfile /opt/payara/config/post-deploy-commands.asadmin \
  --deploy /opt/payara/deployments/ROOT.war \
  --contextroot /