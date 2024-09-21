# 1. Use the Gradle JDK 21 image as base for building the application
FROM gradle:jdk21-jammy AS builder

# 2. Set the working directory inside the container for the build phase
WORKDIR /app

# 3. Copy the entire project into the container
COPY . .

# 4. Run Gradle build to generate the JAR file
RUN gradle build --no-daemon

# 5. Use a lightweight JDK image for running the application
FROM openjdk:21-jdk-slim

# 6. Set the working directory for the runtime container
WORKDIR /app

# 7. Copy the JAR file from the build stage to the runtime stage
COPY --from=builder /app/build/libs/additionaltask-0.0.1-SNAPSHOT.jar ./additionaltask.jar

# 8. Expose the application's port (8080)
EXPOSE 8080

# 9. Run the Spring Boot application with the JAR file
CMD ["java", "-jar", "additionaltask.jar"]

