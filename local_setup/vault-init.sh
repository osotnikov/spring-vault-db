#!/bin/bash
set -e
export VAULT_ADDR=http://vault:8201
# give some time for Vault to start and be ready since it doesn't support a healthcheck and docker-compose can't rely on it
sleep 6

vault login 0000

# Add both key-value pairs
vault kv put secret/myapp static-props.prop-a=alpha static-props.prop-b=beta

# Add database secrets engine
vault secrets enable database

# Configure database connection
vault write database/config/userdb \
    plugin_name=postgresql-database-plugin \
    allowed_roles="dynamic-role" \
    connection_url="postgresql://{{username}}:{{password}}@db:5432/userdb?sslmode=disable" \
    username="test" \
    password="test"

# Add dynamic role
vault write database/roles/dynamic-role \
    db_name=userdb \
    creation_statements="CREATE ROLE \"{{name}}\" WITH LOGIN PASSWORD '{{password}}' VALID UNTIL '{{expiration}}'; GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO \"{{name}}\";" \
    default_ttl="1h" \
    max_ttl="24h"

exit 0

#curl \
#    -H "X-Vault-Token: 0000" \
#    -H "Content-Type: application/json" \
#    -X POST \
#    -d '{"db_name":"myapp","creation_statements":"CREATE ROLE \"{{name}}\" WITH LOGIN PASSWORD \"{{password}}\" VALID UNTIL \"{{expiration}}\";  GRANT SELECT ON ALL TABLES IN SCHEMA public TO \"{{name}}\";"}' \
#    http://localhost:8201/v1/database/creds/dynamic-role