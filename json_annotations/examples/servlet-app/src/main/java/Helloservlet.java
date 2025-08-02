import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/helloservlet")
class helloservlet extends HttpServlet{
    public void doget(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        response.setContentType("application/json");
        
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String jsonResponse = "{\"message\": \"helloservlet\"}";
        out.print(jsonResponse);

        out.flush();
    }
}