# Use a base image with a Java runtime environment (JRE)
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy all JAR files from the target directory into the container
COPY target/*.jar app.jar

# Define environment variable for the server port
ENV SERVER_PORT 8080

# Expose the port that your Spring Boot application listens on
EXPOSE ${SERVER_PORT}

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
