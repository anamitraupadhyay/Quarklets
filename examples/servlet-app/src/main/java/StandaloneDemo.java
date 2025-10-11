import main.java.InputPOJO;
import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.Dino;
import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.ObjectDino;
import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.ValueDino;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Standalone demo that demonstrates the InputPOJO and UserServlet functionality.
 * Can run in two modes:
 * 1. Standalone mode (default): Just demonstrates the POJO without a server
 * 2. Server mode: Makes actual HTTP call to running server
 */
public class StandaloneDemo {
    public static void main(String[] args) {
        System.out.println("=== Servlet App Standalone Demo ===");
        System.out.println();
        
        // Check if server mode is requested
        boolean serverMode = args.length > 0 && args[0].equals("--server");
        
        if (serverMode) {
            runWithServer();
        } else {
            runStandalone();
        }
    }
    
    private static void runStandalone() {
        System.out.println("Running in STANDALONE mode (no server required)");
        System.out.println();
        
        // Simulate what happens when the servlet receives a request
        System.out.println("Simulating JSON request: {\"Name\": \"John\", \"age\": 25}");
        System.out.println();
        
        // Demonstrate the ServletJsonProcessor approach with manual Dino tree
        System.out.println("Using ServletJsonProcessor approach:");
        System.out.println("- Creating Dino tree from JSON");
        
        // Create a Dino tree manually (simulating what ServletJsonProcessor does)
        ObjectDino root = new ObjectDino(null);
        root.addchild(new ValueDino("John", "Name"));
        root.addchild(new ValueDino("25", "age"));
        
        // Create InputPOJO instance and bind
        InputPOJO data = new InputPOJO();
        data.bind(root);
        
        // Print the values (same as what UserServlet does)
        System.out.println("Received request with values: " + data);
        System.out.println("Name: " + data.getName());
        System.out.println("Age: " + data.getAge());
        System.out.println();
        
        // Simulate response
        System.out.println("Response would be: {\"status\":\"success\",\"name\":\"John\",\"age\":25}");
        System.out.println();
        System.out.println("=== Demo Complete ===");
        System.out.println();
        System.out.println("To test with actual server:");
        System.out.println("1. Run: cd examples/servlet-app && ./gradlew quarkusDev");
        System.out.println("2. Run: java -cp build/classes/java/main StandaloneDemo --server");
    }
    
    private static void runWithServer() {
        System.out.println("Running in SERVER mode (requires server at http://localhost:8080)");
        System.out.println();
        
        try {
            // Create JSON payload
            String jsonPayload = "{\"Name\": \"John\", \"age\": 25}";
            
            System.out.println("Sending JSON request to servlet:");
            System.out.println("POST http://localhost:8080/user");
            System.out.println("Payload: " + jsonPayload);
            System.out.println();
            
            // Create connection
            URL url = new URL("http://localhost:8080/user");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            
            // Send request
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            
            // Read response
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            
            if (responseCode == 200) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Response: " + response.toString());
                }
            } else {
                System.out.println("Error: Received non-200 response code");
            }
            
            System.out.println();
            System.out.println("=== Demo Complete ===");
            System.out.println("Check the server logs to see the printed values from InputPOJO");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println();
            System.err.println("Make sure the Quarkus server is running:");
            System.err.println("  cd examples/servlet-app");
            System.err.println("  ./gradlew quarkusDev");
        }
    }
}
