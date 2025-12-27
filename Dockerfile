# Stage 1: Build
FROM eclipse-temurin:21-jdk-alpine-3.23 AS build
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B
COPY src src
RUN ./mvnw clean install -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre-alpine-3.23
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
