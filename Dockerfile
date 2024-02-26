# Use a base image with OpenJDK 21 JRE
FROM openjdk:21

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/spaceship-0.0.1-SNAPSHOT.jar /app/

# Specify the command to run on container start
CMD ["java", "-jar", "spaceship-0.0.1-SNAPSHOT.jar"]
