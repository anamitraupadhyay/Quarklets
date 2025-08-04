import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserServlet extends BaseServlet {

    @JsonBody
    public static class UserRequest {
        public String name;
        public int age;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        UserRequest user = parseBody(req, UserRequest.class);
        writeJson(res, Map.of("message", "User added: " + user.name));
    }
}
