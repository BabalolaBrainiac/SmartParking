#!/bin/bash

# Set environment variables
export SPRING_APPLICATION_NAME=SmartParkingApplication
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/smart_parking_db
export SPRING_DATASOURCE_USERNAME=your_username
export SPRING_DATASOURCE_PASSWORD=your_password

# Build the project
./mvnw clean package

# Run the application
java -jar target/smart-parking-application.jar
