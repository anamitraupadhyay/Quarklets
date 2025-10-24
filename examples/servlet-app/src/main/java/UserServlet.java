import io.github.anamitraupadhyay.Quarklets.essentials.JsonUtils;
import io.github.anamitraupadhyay.Quarklets.experimetal.servlet.ServletJsonProcessor;
import io.github.anamitraupadhyay.Quarklets.experimetal.servlet.ServletJsonProcessorReflectionless;

import main.java.InputPOJO;
import main.java.InputPOJOReflectionless;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Single line - HttpServletRequest becomes bound POJO
        InputPOJO data = ServletJsonProcessor.bind(req, InputPOJO.class);
        
        // Print out the values proving all are working fine
        System.out.println("Received request with values using reflection binding: " + data);
        System.out.println("Name: " + data.getName());
        System.out.println("Age: " + data.getAge());

        /*JsonObject responseJson = Json.createObjectBuilder()
            .add("status", "success")
            .add("name", data.getName())
            .add("age", data.getAge())
            .build();*/

        // Single line reflectionless JsonBinder
        ServletJsonProcessorReflectionless processor = new ServletJsonProcessorReflectionless(req);
        InputPOJOReflectionless user = processor.binder(new InputPOJOReflectionless());

        // Print out the values proving all are working fine
        System.out.println("Received request with values using reflectionless binding: " + user);
        System.out.println("Name: " + user.getName());
        System.out.println("Age: " + user.getAge());

        /*JsonObject responseJsonreflectionless = Json.createObjectBuilder()
            .add("status", "success")
            .add("name", user.getName())
            .add("age", user.getAge())
            .build();*/

        // --- BUILD JSON OBJECTS FOR EACH RESULT ---

        // Response for reflection result
        JsonObject responseJsonReflection = Json.createObjectBuilder()
            .add("status", "success")
            .add("source", "ReflectionBinder")
            .add("name", data.getName())
            .add("age", data.getAge())
            .build();

        // Response for reflectionless result
        JsonObject responseJsonReflectionless = Json.createObjectBuilder()
            .add("status", "success")
            .add("source", "ReflectionlessBinder")
            .add("name", user.getName())
            .add("age", user.getAge())
            .build();

        // --- THE CRITICAL STEP: COMBINE BOTH INTO A SINGLE OBJECT ---

        JsonObject finalResponse = Json.createObjectBuilder()
            .add("overallStatus", "OK")
            .add("message", "Both binding results are included in this single response.")
            .add("reflectionResult", responseJsonReflection) // Embed the first object
            .add("reflectionlessResult", responseJsonReflectionless) // Embed the second object
            .build();

        // --- CALL YOUR UTILITY EXACTLY ONCE ---
        
        // the correct way to use  utility to send all data at once.
        JsonUtils.write(res, finalResponse); 
    }
}
/*
curl -X POST http://localhost:8080/user \
  -H "Content-Type: application/json" \
  -d '{"Name": "John", "age": 25}'
 */