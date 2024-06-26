# Build Artefact
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn -f pom.xml clean package


# Run Application
FROM eclipse-temurin:21-jre-alpine
WORKDIR /usr/local/lib
COPY --from=builder /home/app/target/*.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
