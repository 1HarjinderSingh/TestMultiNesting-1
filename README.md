# Spring Boot 2 Hello World Service

This workspace contains a small Spring Boot 2 application in the `app` subproject that exposes a single endpoint:

- GET /hello -> returns "Hello, World!"

Files created:

- `app/build.gradle` - Gradle build for the Spring Boot 2 application (uses Spring Boot 2.7.14)
- `app/src/main/java/org/example/Application.java` - Spring Boot application entry point
- `app/src/main/java/org/example/HelloController.java` - REST controller exposing `/hello`

How to build and run

From the repository root (Windows PowerShell):

```powershell
# Build a runnable jar
./gradlew :app:bootJar

# Run the app (starts on port 8080)
java -jar app\build\libs\app-0.0.1-SNAPSHOT.jar

# Alternatively run with Gradle (development):
./gradlew :app:bootRun
```

Smoke test

Once the app is running, call the endpoint:

```powershell
Invoke-RestMethod -Uri http://localhost:8080/hello
# should print: Hello, World!
```

Notes

- This project uses Java 11 toolchain as defined in the `lib` module; ensure you have a JDK 11 available.
- If you prefer a newer Spring Boot version, update `app/build.gradle` plugin versions accordingly.
