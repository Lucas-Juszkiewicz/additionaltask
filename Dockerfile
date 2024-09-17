# 1. Use the Gradle JDK 21 image as base
FROM gradle:jdk21-jammy

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy the built JAR file into the container
COPY build/libs/additionaltask-0.0.1-SNAPSHOT.jar ./additionaltask.jar

# 4. Expose the application's port (8080)
EXPOSE 8080

# 5. Run the Spring Boot application with the copied JAR file
CMD ["java", "-jar", "additionaltask.jar"]
