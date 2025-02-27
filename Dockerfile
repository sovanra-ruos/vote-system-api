# Builder stage
FROM gradle:8.4-jdk17-alpine AS builder
WORKDIR /app
COPY . .
#Note: Skipping tests is generally not recommended except for diagnostic purposes.
RUN gradle build --no-daemon -x test

# Build the application
#RUN gradle build --no-daemon

# Final stage
FROM openjdk:17-alpine
WORKDIR /app
# Copy the built JAR from the builder stage and rename it for simplicity
COPY --from=builder /app/build/libs/*0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080


# Now you can reference a fixed name in the ENTRYPOINT
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]