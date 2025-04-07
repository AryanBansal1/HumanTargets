# Use OpenJDK base image
FROM openjdk:21-jdk-slim

# Copy the JAR file
COPY target/HumanTargets-0.0.1-SNAPSHOT.jar app.jar

# Copy SSL
COPY ssl/keystore.p12 keystore.p12

# Expose the port your app runs on
EXPOSE 9898

# Run the app
ENTRYPOINT ["java", "-jar", "/app.jar"]
