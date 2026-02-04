# Stage 1: Build the application
FROM maven:3.10.1-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=target/*-SNAPSHOT.jar
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
