# Use the official OpenJDK 11 image as a base image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY ./target/to-watch-list-0.0.1-SNAPSHOT.jar /app/

# Expose the port that your application is running on
EXPOSE 8055

# Specify the command to run your application
CMD ["java", "-jar", "to-watch-list-0.0.1-SNAPSHOT.jar"]
