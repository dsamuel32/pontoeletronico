#!/bin/sh

set -e

# Perform all actions as $POSTGRES_USER
export PGUSER="postgres"

# Create the 'template_postgis' template db
psql <<- 'EOSQL'
CREATE DATABASE pontoeletronico TEMPLATE=template1;
EOSQL

psql --dbname="pontoeletronico" <<- 'EOSQL'
CREATE USER sistema WITH PASSWORD 'sistema';
CREATE SCHEMA IF NOT EXISTS pontoeletronico AUTHORIZATION sistema;
EOSQL
