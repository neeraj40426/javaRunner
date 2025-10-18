# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first to leverage caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy the source code
COPY src ./src

# Give executable permission to mvnw
RUN chmod +x mvnw

# Build the project with Maven
RUN ./mvnw clean package -DskipTests

# Expose the port your app runs on
EXPOSE 8080

# Set environment variable to activate docker profile
ENV SPRING_PROFILES_ACTIVE=docker

# Command to run the jar
CMD ["java", "-jar", "target/javaquiz-0.0.1-SNAPSHOT.jar"]
