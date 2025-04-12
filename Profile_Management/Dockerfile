# Create a new image with JRE
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY  target/SpringBootRegistrationLogin-1.0.jar .

# Expose the port the application runs on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "SpringBootRegistrationLogin-1.0.jar"]