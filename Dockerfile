# Use an official Java runtime as a parent image
FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/Bluebottle-1.0-SNAPSHOT.jar /app/backend.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/backend.jar"]