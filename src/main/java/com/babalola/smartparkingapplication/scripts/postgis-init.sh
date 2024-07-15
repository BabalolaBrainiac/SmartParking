#!/bin/bash
set -e

# Ensure the service is running
pg_isready -U "$POSTGRES_USER" -d "$POSTGRES_DB"

# Install PostGIS
apt-get update
apt-get install -y postgis postgresql-$PG_MAJOR-postgis-$POSTGIS_VERSION postgresql-$PG_MAJOR-postgis-$POSTGIS_VERSION-scripts

# Enable PostGIS extension on the database
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE EXTENSION IF NOT EXISTS postgis;
EOSQL
