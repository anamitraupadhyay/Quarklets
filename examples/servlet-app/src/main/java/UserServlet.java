import io.github.anamitraupadhyay.Quarklets.essentials.JsonUtils;

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
        JsonObject json = JsonUtils.parse(req);
        String name = json.getString("Name", "Unknown");
        int age = json.getInt("age", 0);



        //customjsonparser obj = new customjsonparser(new inputpojo()); or since a class that implements an interface is a interface 
        //Runnable r = new myclass();
        //Pojo obj = new Pojo(new inputpojo()); but what happens if i want the binded input pojo object


        JsonObject responseJson = Json.createObjectBuilder()
            .add("status", "success")
            .add("name", name)
            .add("age", age)
            .build();

        JsonUtils.write(res, responseJson);
    }
}
/*
curl -X POST http://localhost:8080/user \
  -H "Content-Type: application/json" \
  -d '{"Name": "John", "age": 25}'
 */