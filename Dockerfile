# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy mvnw and pom.xml first (to leverage Docker cache)
COPY mvnw .
COPY pom.xml .

# Copy source code
COPY src ./src

# Make mvnw executable
RUN chmod +x mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# Expose port 8082
EXPOSE 8082

# Start the app
CMD ["java", "-jar", "target/internship-management-0.0.1-SNAPSHOT.jar"]