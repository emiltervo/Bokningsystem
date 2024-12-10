# Use the official PostgreSQL image as the base
FROM postgres:latest

# Set environment variables for PostgreSQL
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=postgres

# Create a directory in the container for the initialization script
WORKDIR /docker-entrypoint-initdb.d

# Copy your SQL script to the container's initialization directory
COPY src/main/java/models/database.sql /docker-entrypoint-initdb.d/

# Expose the PostgreSQL default port
EXPOSE 5432

# The base image automatically executes all scripts in /docker-entrypoint-initdb.d/
# during initialization, so no additional commands are needed.
