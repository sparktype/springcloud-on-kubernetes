#!/bin/zsh
export VAULT_ADDR=http://localhost:30082
vault kv put secret/users database_username=users
vault kv put secret/users database_password=users1234