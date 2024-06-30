##!/bin/bash
#
## Clean and package the application using Maven
#echo "Building the application..."
#mvn clean package
#
## Check if Maven build was successful
#if [ $? -ne 0 ]; then
#  echo "Maven build failed. Exiting."
#  exit 1
#fi
#
## Run the application
#echo "Starting the application..."
#java -jar target/smart-parking-application.jar
#
## Check if application started successfully
#if [ $? -ne 0 ]; then
#  echo "Failed to start the application."
#  exit 1
#fi
#
#echo "Application is running."
