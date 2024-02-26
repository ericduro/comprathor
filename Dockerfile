# Stage 1: Build the backend
FROM maven:3.8.4-openjdk-17 AS backend-builder

COPY . /app/source

WORKDIR /app/source

RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17 AS runtime

WORKDIR /app

COPY --from=backend-builder /app/source/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]