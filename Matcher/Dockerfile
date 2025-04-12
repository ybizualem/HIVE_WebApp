# Use official Maven image as the base image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy necessary files
COPY target/invites-0.0.1-SNAPSHOT.jar .

# Expose the port that the application will run on
EXPOSE 8080

# Specify the command to run the application
CMD ["java", "-jar", "/app/invites-0.0.1-SNAPSHOT.jar"]