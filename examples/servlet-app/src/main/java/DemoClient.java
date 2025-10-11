import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DemoClient {
    public static void main(String[] args) {
        System.out.println("=== Servlet App Demo Client ===");
        System.out.println("This demo will start the server and send a JSON request to the servlet.");
        System.out.println();
        
        // Wait for server to be ready (if running in dev mode)
        System.out.println("Make sure the server is running on http://localhost:8080");
        System.out.println("Run './gradlew quarkusDev' in another terminal if not already running.");
        System.out.println();
        
        try {
            // Give some time for server to start if needed
            Thread.sleep(2000);
            
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
            System.out.println("Check the server logs above to see the printed values from InputPOJO");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println();
            System.err.println("Make sure the Quarkus server is running:");
            System.err.println("  cd examples/servlet-app");
            System.err.println("  ./gradlew quarkusDev");
            e.printStackTrace();
        }
    }
}
