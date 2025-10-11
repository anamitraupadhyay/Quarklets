# Servlet App Demo

This directory contains a minimal demonstration of the Quarklets library with a servlet-based application using the experimental `ServletJsonProcessor`.

## Files Modified

1. **InputPOJO.java** - A POJO class that implements `AutobindInterface`
   - Has `name` (String) and `age` (int) fields
   - Implements the `bind()` method to automatically bind JSON data
   
2. **UserServlet.java** - A servlet that demonstrates the ServletJsonProcessor pattern
   - Uses `ServletJsonProcessor.bind(request, InputPOJO.class)` for one-line JSON binding
   - Prints the bound values
   - Returns a JSON response

## The ServletJsonProcessor Pattern

The servlet uses the experimental `ServletJsonProcessor` which follows the "Thread-Runnable" pattern:

```java
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Single line - HttpServletRequest becomes bound POJO
        InputPOJO data = ServletJsonProcessor.bind(request, InputPOJO.class);
        
        // Print out the values proving all are working fine
        System.out.println("Received request with values: " + data);
        System.out.println("Name: " + data.getName());
        System.out.println("Age: " + data.getAge());
    }
}
```

The `InputPOJO` class implements `AutobindInterface` and defines how to bind JSON fields:

```java
public class InputPOJO implements AutobindInterface {
    private String name;
    private int age;
    
    @Override
    public void bind(Dino jsonTree) {
        this.name = getString("Name", jsonTree);
        this.age = getInt("age", jsonTree);
    }
}
```

## Running the Demo

### Option 1: Standalone Mode (No Server Required)

Simply run the StandaloneDemo to see how the InputPOJO and servlet logic work:

```bash
cd examples/servlet-app
./gradlew compileJava
# Create a simple run script
cat > run-demo.sh << 'EOF'
CLASSPATH="build/classes/java/main"
for jar in ~/.m2/repository/io/github/anamitraupadhyay/lib/1.0.0-SNAPSHOT/*.jar; do
    CLASSPATH="$CLASSPATH:$jar"
done
java -cp "$CLASSPATH" StandaloneDemo "$@"
EOF
chmod +x run-demo.sh
./run-demo.sh
```

This will demonstrate the binding approach without requiring a running server.

### Option 2: With Quarkus Server

To test with an actual HTTP request to the servlet:

**Terminal 1 - Start the server:**
```bash
cd examples/servlet-app
./gradlew quarkusDev
```

**Terminal 2 - Test with curl:**
```bash
curl -X POST http://localhost:8080/user \
  -H "Content-Type: application/json" \
  -d '{"Name": "John", "age": 25}'
```

## What the Demo Shows

1. **InputPOJO** - Implements `AutobindInterface`:
   - Fields: `name` (String) and `age` (int)
   - `bind()` method that uses helper methods from the interface to extract values from the Dino tree
   - Getters, setters, and toString method

2. **UserServlet** - Demonstrates the ServletJsonProcessor pattern:
   - Single line to bind request to POJO: `ServletJsonProcessor.bind(request, InputPOJO.class)`
   - Prints the bound POJO values to console
   - Returns a JSON response with status and the values

## Expected Output

When you run the standalone demo, you should see:

```
=== Servlet App Standalone Demo ===

Running in STANDALONE mode (no server required)

Simulating JSON request: {"Name": "John", "age": 25}

Using ServletJsonProcessor approach:
- Creating Dino tree from JSON
Received request with values: InputPOJO{name='John', age=25}
Name: John
Age: 25

Response would be: {"status":"success","name":"John","age":25}

=== Demo Complete ===
```

When running with the server, the server console will show the same printed values from the servlet.
