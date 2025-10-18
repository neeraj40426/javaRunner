# Use official OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy Maven wrapper and project files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Make mvnw executable
RUN chmod +x mvnw

# Build the project and skip tests (optional: remove -DskipTests for full build)
RUN ./mvnw clean package -DskipTests

# Expose port the Spring Boot app will run on
EXPOSE 8080

# Run the built JAR file
CMD ["java", "-jar", "target/javaquiz-0.0.1-SNAPSHOT.jar"]

