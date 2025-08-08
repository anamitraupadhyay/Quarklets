package Quarklets.essentials;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import jakarta.json.Json;

import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

public class BaseServlet extends HttpServlet {
    public JsonObject jsonparse(HttpServletRequest Request) /*throws IOException*/ {
        
        StringBuffer jsonPayload = new StringBuffer();

        try (BufferedReader reader = Request.getReader()) {

            String line;

            while ((line = reader.readLine()) != null) {
                jsonPayload.append(line);
            }

        }
        catch (IOException io) {
            
            throw new RuntimeException("error reading request body",io);
        }
        try (JsonReader jsonreadobj = Json.createReader(new StringReader(jsonPayload.toString()))) {
            
            return jsonreadobj.readObject();
            
        } catch (Exception e) {

            throw new RuntimeException("error parsing json body though recieved in whole");
        }
    }
}
