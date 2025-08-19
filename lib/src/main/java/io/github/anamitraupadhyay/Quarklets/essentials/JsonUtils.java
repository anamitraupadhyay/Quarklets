package io.github.anamitraupadhyay.Quarklets.essentials;

import jakarta.json.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;

public final class JsonUtils {
    private JsonUtils() {} // prevent instantiation

    public static JsonObject parse(HttpServletRequest request) {
        StringBuilder jsonPayload = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonPayload.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading request body", e);
        }
        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonPayload.toString()))) {
            return jsonReader.readObject();
        }
    }

    /*public static JsonObject parseBuffer(HttpServletRequest req) {
        //try (StringBuffer jsonpayloadBuffer = new StringBuffer()) { // The resource type StringBuffer does not implement
                                                                    // java.lang.AutoCloseableJava(16778087)
            //BufferSerializer
            //BufferPoolMXBean
            //BufferedInputStream bufferinput = new BufferedInputStream(null)
            //String line;
            //while (!(line = reader.readline(null))) {
                //jsonpayloadBuffer.append(jsonpayloadBuffer);
            //}

            return new JsonObject() {
                
            };
        }*/


    public static void write(HttpServletResponse response, JsonObject json) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(json.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error writing response", e);
        }
    }
}