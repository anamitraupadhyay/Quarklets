# Servlet App Demo

This directory contains a minimal demonstration of the Quarklets library with a servlet-based application.

## Files Modified

1. **InputPOJO.java** - A simple POJO class with `name` and `age` fields
2. **UserServlet.java** - A servlet that receives JSON, creates an InputPOJO instance, prints the values, and returns a response

## Running the Demo

### Option 1: Standalone Mode (No Server Required)

Simply run the StandaloneDemo to see how the InputPOJO and servlet logic work:

```bash
cd examples/servlet-app
./gradlew compileJava
java -cp build/classes/java/main StandaloneDemo
```

This will demonstrate the parsing and printing of values without requiring a running server.

### Option 2: With Quarkus Server

To test with an actual HTTP request to the servlet:

**Terminal 1 - Start the server:**
```bash
cd examples/servlet-app
./gradlew quarkusDev
```

**Terminal 2 - Run the demo client:**
```bash
cd examples/servlet-app
java -cp build/classes/java/main StandaloneDemo --server
```

Or use the DemoClient:
```bash
java -cp build/classes/java/main DemoClient
```

Or use curl:
```bash
curl -X POST http://localhost:8080/user \
  -H "Content-Type: application/json" \
  -d '{"Name": "John", "age": 25}'
```

## What the Demo Shows

1. **InputPOJO** - A simple Java class with:
   - Fields: `name` (String) and `age` (int)
   - Getters and setters
   - Constructor and toString method

2. **UserServlet** - A servlet that:
   - Parses JSON from the request using `JsonUtils.parse()`
   - Creates an InputPOJO instance with the parsed values
   - Prints the POJO values to console
   - Returns a JSON response with status and the values

## Expected Output

When you run the standalone demo, you should see:

```
=== Servlet App Standalone Demo ===

Running in STANDALONE mode (no server required)

Simulating JSON request: {"Name": "John", "age": 25}

Received request with values: InputPOJO{name='John', age=25}
Name: John
Age: 25

Response would be: {"status":"success","name":"John","age":25}

=== Demo Complete ===
```

When running with the server, the server console will show the same printed values from the servlet.
