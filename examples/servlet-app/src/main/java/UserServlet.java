import io.github.anamitraupadhyay.Quarklets.essentials.JsonUtils;
import io.github.anamitraupadhyay.Quarklets.experimetal.servlet.ServletJsonProcessor;
import main.java.InputPOJO;

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
        System.out.println("Received request with values: " + data);
        System.out.println("Name: " + data.getName());
        System.out.println("Age: " + data.getAge());

        JsonObject responseJson = Json.createObjectBuilder()
            .add("status", "success")
            .add("name", data.getName())
            .add("age", data.getAge())
            .build();

        JsonUtils.write(res, responseJson);
    }
}
/*
curl -X POST http://localhost:8080/user \
  -H "Content-Type: application/json" \
  -d '{"Name": "John", "age": 25}'
 */