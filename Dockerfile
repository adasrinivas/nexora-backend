# Build stage
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN ./mvnw dependency:go-offline -B || true
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
RUN mkdir -p /app/data
COPY --from=builder /app/target/backend-0.0.1-SNAPSHOT.jar app.jar
ENV PORT=8080
ENV SPRING_DATASOURCE_URL=jdbc:h2:file:/app/data/nexoradb;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=""
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]
