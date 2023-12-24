# Use a base image with a Java runtime environment (JRE)
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Define environment variables for JAR file name and other settings
ENV JAR_FILE_NAME RottenApples-0.0.1-SNAPSHOT.jar
ENV SERVER_PORT 8080

# Copy the Spring Boot JAR file into the container
COPY target/${JAR_FILE_NAME} app.jar

# Expose the port that your Spring Boot application listens on
EXPOSE ${SERVER_PORT}

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "RottenApples-0.0.1-SNAPSHOT.jar"]
