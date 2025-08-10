import Quarklets.essentials.JsonUtils;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        JsonObject json = JsonUtils.parse(req);
        String name = json.getString("Name", "Unknown");
        int age = json.getInt("age", 0);

        JsonObject responseJson = Json.createObjectBuilder()
            .add("status", "success")
            .add("name", name)
            .add("age", age)
            .build();

        JsonUtils.write(res, responseJson);
    }
}
