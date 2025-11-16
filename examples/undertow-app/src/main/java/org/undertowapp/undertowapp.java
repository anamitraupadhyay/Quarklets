import io.github.anamitraupadhyay.Quarklets.essentials.JsonUtils;
import io.github.anamitraupadhyay.Quarklets.experimetal.servlet.ServletJsonProcessorReflectionless;
import io.github.anamitraupadhyay.Quarklets.experimental.servlet.ServletJsonProcessor;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import io.undertow.util.Headers;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import main.java.InputPOJOReflectionless;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ReadListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class UndertowQuarkletsServer {

    public static void main(final String[] args) {
        RoutingHandler handler = Handlers.routing()
                .post("/user", UndertowQuarkletsServer::handlePostRequest)
                .get("/*", exchange -> {
                    exchange.setStatusCode(404);
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    exchange.getResponseSender().send("Page Not Found");
                });

        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(handler)
                .build();
        server.start();
        System.out.println("Server started on http://localhost:8080");
    }

    private static void handlePostRequest(HttpServerExchange exchange) {
        // Undertow requires reading the request body asynchronously/blocking managed
        exchange.getRequestReceiver().receiveFullString(
                (HttpServerExchange exc, String body) -> {
            try {
                // Quarklets utilities depend on the jakarta.servlet API interfaces (HttpServletRequest)
                // We need to create an adapter or mock object that wraps the Undertow exchange
                // to fulfill this dependency.

                // This is a minimal mock/adapter implementation for HttpServletRequest
                jakarta.servlet.http.HttpServletRequest mockRequest = createMockHttpServletRequest(body);

                // Use the Quarklets processor with the mock request object
                ServletJsonProcessorReflectionless processor = new ServletJsonProcessorReflectionless(mockRequest);
                InputPOJOReflectionless user = processor.binder(new InputPOJOReflectionless());

                // Log the results (for demonstration)
                System.out.println("Received request with values using reflectionless binding:");
                System.out.println("Name: " + user.getName());
                System.out.println("Age: " + user.getAge());

                // Build the response JSON
                JsonObject responseJson = Json.createObjectBuilder()
                        .add("status", "success")
                        .add("source", "Undertow with ReflectionlessBinder")
                        .add("name", user.getName())
                        .add("age", user.getAge())
                        .build();

                // Set response headers and send the response
                exc.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                // The Quarklets JsonUtils.write utility requires an HttpServletResponse object.
                // We would need to mock HttpServletResponse as well, or use Undertow's sender directly.
                exc.getResponseSender().send(responseJson.toString());

            } catch (Exception e) {
                e.printStackTrace();
                exc.setStatusCode(500);
                exc.getResponseSender().send("Error processing request: " + e.getMessage());
            }
        }, StandardCharsets.UTF_8);
    }

    /**
     * Helper method to create a minimal mock HttpServletRequest.
     *
     * NOTE: This requires having the jakarta.servlet-api dependency in your project.
     */
    private static jakarta.servlet.http.HttpServletRequest createMockHttpServletRequest(String body) throws IOException {
        return new jakarta.servlet.http.HttpServletRequest() {
            @Override
            public ServletInputStream getInputStream() throws IOException {
                final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
                return new ServletInputStream() {
                    @Override
                    public boolean isFinished() { return bais.available() == 0; }
                    @Override
                    public boolean isReady() { return true; }
                    @Override
                    public void setReadListener(ReadListener readListener) {}
                    @Override
                    public int read() throws IOException { return bais.read(); }
                };
            }
            // Implement other required methods minimally (e.g., getContentType)
            @Override
            public String getContentType() {
                return "application/json";
            }
            // Other methods would throw UnsupportedOperationException if called
        };
    }
}




/*
import io.github.anamitraupadhyay.Quarklets.essentials.JsonUtils;
import io.github.anamitraupadhyay.Quarklets.experimetal.servlet.ServletJsonProcessorReflectionless;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.Methods;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import main.java.InputPOJOReflectionless;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomHandlerServer {

    public static void main(final String[] args) {
        // We set our custom handler directly
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(new CustomHttpHandler()) // <--- No Handlers.routing() used here
                .build();
        server.start();
        System.out.println("Server started with custom handler on http://localhost:8080");
    }

    // Our custom handler class that implements the core HttpHandler interface
    static class CustomHttpHandler implements HttpHandler {
        @Override
        public void handleRequest(HttpServerExchange exchange) throws Exception {
            String path = exchange.getRelativePath();
            String method = exchange.getRequestMethod().toString();

            if ("/user".equals(path) && Methods.POST.equals(exchange.getRequestMethod())) {
                handlePostRequest(exchange);
            } else if ("/greet".equals(path) && Methods.GET.equals(exchange.getRequestMethod())) {
                 handleGetRequest(exchange);
            } else {
                exchange.setStatusCode(404);
                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                exchange.getResponseSender().send("Page Not Found");
            }
        }

        private void handleGetRequest(HttpServerExchange exchange) {
            String name = "World";
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
            exchange.getResponseSender().send("Hello " + name);
        }

        private void handlePostRequest(HttpServerExchange exchange) {
            // The same body processing logic is used here
            exchange.getRequestReceiver().receiveFullString((exc, body) -> {
                try {
                    jakarta.servlet.http.HttpServletRequest mockRequest = createMockHttpServletRequest(body);
                    ServletJsonProcessorReflectionless processor = new ServletJsonProcessorReflectionless(mockRequest);
                    InputPOJOReflectionless user = processor.binder(new InputPOJOReflectionless());

                    JsonObject responseJson = Json.createObjectBuilder()
                        .add("status", "success")
                        .add("source", "CustomHandler with ReflectionlessBinder")
                        .add("name", user.getName())
                        .add("age", user.getAge())
                        .build();

                    exc.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                    exc.getResponseSender().send(responseJson.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                    exc.setStatusCode(500);
                    exc.getResponseSender().send("Error processing request");
                }
            }, StandardCharsets.UTF_8);
        }

        // The same mock request method
        private jakarta.servlet.http.HttpServletRequest createMockHttpServletRequest(String body) throws IOException {
             // ... (implementation is identical to the previous answer) ...
             return new jakarta.servlet.http.HttpServletRequest() {
                @Override public ServletInputStream getInputStream() throws IOException {
                    final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
                    return new ServletInputStream() {
                        @Override public boolean isFinished() { return bais.available() == 0; }
                        @Override public boolean isReady() { return true; }
                        @Override public void setReadListener(ReadListener readListener) {}
                        @Override public int read() throws IOException { return bais.read(); }
                    };
                }
                @Override public String getContentType() { return "application/json"; }
                // ... other methods omitted for brevity ...
            };
        }
    }
}
 */