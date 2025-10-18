# Start from Maven + JDK 17 image
FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the jar without running tests
RUN mvn clean package -DskipTests

# Second stage: slim image for runtime
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port Render assigns via environment variable
ENV PORT=10000
EXPOSE $PORT

# Spring Boot picks up $PORT
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=$PORT -jar app.jar"]


