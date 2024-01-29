# Stage 1: Build the frontend
FROM node:14 AS frontend-builder

WORKDIR /app/reactjs-comprathor

COPY ./reactjs-comprathor/package.json .
COPY ./reactjs-comprathor/package-lock.json .

RUN npm install

COPY ./reactjs-comprathor .

EXPOSE 3000

CMD ["npm", "start"]

# Stage 2: Build the backend
FROM maven:3.8.4-openjdk-17 AS backend-builder

COPY . /app/source

WORKDIR /app/source

RUN mvn clean package -DskipTests

# Stage 3: Create the runtime image
FROM openjdk:17 AS runtime

WORKDIR /app

COPY --from=backend-builder /app/source/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
