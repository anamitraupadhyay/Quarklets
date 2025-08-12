# Quarkus Servlet with Quarklets Library

This is a sample Quarkus-based servlet application that demonstrates how to use the `Quarklets` library for efficient JSON handling within HTTP servlets.

---
## Prerequisites

* **Java 21+**
* **Gradle 8.7+**
* **Git**

---
## Getting Started

1.  **Clone the repository:**
    ```bash
    git clone <your-repo-url>
    cd servlet-app
    ```

2.  **Run in development mode:**
    ```bash
    ./gradlew quarkusDev
    ```
    The application will be available at `http://localhost:8080`. Development mode enables live coding and provides a Dev UI at `/q/dev`.

3.  **Test the endpoint:**
    ```bash
    curl -X POST http://localhost:8080/user \
      -H "Content-Type: application/json" \
      -d '{"Name": "John", "age": 25}'
    ```
    **Expected Response:**
    ```json
    {"status":"success","name":"John","age":25}
    ```

---
## API Reference

### `POST /user`

Accepts a JSON object containing user data and returns a success response.

**Request Body:**
```json
{
  "Name": "Alice",
  "age": 30
}

**Success Response:**

```json
{
  "status": "success",
  "name": "Alice",
  "age": 30
}
```

**Default Values:**

  * If `Name` is not provided, it defaults to `"Unknown"`.
  * If `age` is not provided, it defaults to `0`.

-----

## Build Commands

  * **Run in Development Mode:**
    ```bash
    ./gradlew quarkusDev
    ```
  * **Build the Application:**
    ```bash
    ./gradlew build
    ```
  * **Run the Production Build:**
    ```bash
    java -jar build/quarkus-app/quarkus-run.jar
    ```
  * **Clean the Build Directory:**
    ```bash
    ./gradlew clean
    ```

-----

## Configuration

### Dependencies

  * **Quarkus 3.12.0**: The core framework.
  * **Quarklets Library** (`io.github.anamitraupadhyay:lib:1.0.0-SNAPSHOT`): For JSON utilities.
  * **Jakarta Servlet API**: For servlet implementation.
  * **Lombok**: To reduce boilerplate code.

### `gradle.properties`

```properties
quarkusPlatformGroupId=io.quarkus.platform
quarkusPlatformArtifactId=quarkus-bom
quarkusPlatformVersion=3.12.0
```

### `settings.gradle`

```gradle
rootProject.name='servlet-app'
```

-----

## Troubleshooting

  * **Port Conflict:** To run on a different port, use the `-Dquarkus.http.port` flag.
    ```bash
    ./gradlew quarkusDev -Dquarkus.http.port=8081
    ```
  * **Dependency Issues:** Force a dependency refresh during the build.
    ```bash
    ./gradlew clean build --refresh-dependencies
    ```

-----

## Contributing

Contributions are welcome. Please fork the repository, create a feature branch, and open a pull request with your changes.

-----

## License

This project is licensed under the Apache License 2.0.

```
```