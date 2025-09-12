package io.github.anamitraupadhyay.Quarklets.experimetal.servlet;

import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.TransactionData;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.StringReader;

/**
 * Simple test demonstration showing how the complete pipeline works
 * HttpServletRequest → StringBuilder → Tokens → Dino → Bound Object
 * 
 * This demonstrates the learning approach the user requested.
 */
public class SimpleFlowTest {
    
    public static void main(String[] args) {
        System.out.println("=== Simple Servlet JSON Binding Flow Test ===\n");
        
        // Create a mock HttpServletRequest with JSON data
        MockHttpServletRequest mockRequest = createMockRequestWithJsonData();
        
        // Test the simple flow
        testSimpleFlow(mockRequest);
        
        // Test step-by-step demonstration
        testStepByStepFlow(mockRequest);
    }
    
    private static void testSimpleFlow(HttpServletRequest request) {
        System.out.println("=== Testing Simple Flow ===");
        
        // This is the simple one-liner approach
        TransactionData result = SimpleServletBindingDemo.bindFromServlet(request);
        
        if (result != null) {
            System.out.println("SUCCESS: Bound object created:");
            System.out.println(result);
        } else {
            System.out.println("FAILED: Could not bind object");
        }
        System.out.println();
    }
    
    private static void testStepByStepFlow(HttpServletRequest request) {
        System.out.println("=== Testing Step-by-Step Flow ===");
        
        // This shows the detailed learning flow
        SimpleServletBindingDemo.demonstrateStepByStepFlow(request);
    }
    
    /**
     * Creates a mock HttpServletRequest with JSON transaction data
     */
    private static MockHttpServletRequest createMockRequestWithJsonData() {
        // Sample JSON transaction data
        String jsonData = """
            {
                "cc_num": "1234567890123456",
                "amt": 75.50,
                "zip": "94103", 
                "lat": 37.7749,
                "long": -122.4194,
                "city_pop": 883305,
                "unix_time": 1678886400,
                "merch_lat": 37.7833,
                "merch_long": -122.4167
            }
            """;
        
        return new MockHttpServletRequest(jsonData);
    }
    
    /**
     * Simple mock HttpServletRequest for testing
     */
    static class MockHttpServletRequest implements HttpServletRequest {
        private final String jsonData;
        
        public MockHttpServletRequest(String jsonData) {
            this.jsonData = jsonData;
        }
        
        @Override
        public BufferedReader getReader() {
            return new BufferedReader(new StringReader(jsonData));
        }
        
        // Minimal implementation - only what's needed for the demo
        // All other methods would throw UnsupportedOperationException in a real mock
        
        @Override public String getAuthType() { return null; }
        @Override public jakarta.servlet.http.Cookie[] getCookies() { return null; }
        @Override public long getDateHeader(String name) { return 0; }
        @Override public String getHeader(String name) { return null; }
        @Override public java.util.Enumeration<String> getHeaders(String name) { return null; }
        @Override public java.util.Enumeration<String> getHeaderNames() { return null; }
        @Override public int getIntHeader(String name) { return 0; }
        @Override public String getMethod() { return "POST"; }
        @Override public String getPathInfo() { return null; }
        @Override public String getPathTranslated() { return null; }
        @Override public String getContextPath() { return null; }
        @Override public String getQueryString() { return null; }
        @Override public String getRemoteUser() { return null; }
        @Override public boolean isUserInRole(String role) { return false; }
        @Override public java.security.Principal getUserPrincipal() { return null; }
        @Override public String getRequestedSessionId() { return null; }
        @Override public String getRequestURI() { return null; }
        @Override public StringBuffer getRequestURL() { return null; }
        @Override public String getServletPath() { return null; }
        @Override public jakarta.servlet.http.HttpSession getSession(boolean create) { return null; }
        @Override public jakarta.servlet.http.HttpSession getSession() { return null; }
        @Override public String changeSessionId() { return null; }
        @Override public boolean isRequestedSessionIdValid() { return false; }
        @Override public boolean isRequestedSessionIdFromCookie() { return false; }
        @Override public boolean isRequestedSessionIdFromURL() { return false; }
        @Override public boolean authenticate(jakarta.servlet.http.HttpServletResponse response) { return false; }
        @Override public void login(String username, String password) {}
        @Override public void logout() {}
        @Override public java.util.Collection<jakarta.servlet.http.Part> getParts() { return null; }
        @Override public jakarta.servlet.http.Part getPart(String name) { return null; }
        @Override public <T extends jakarta.servlet.http.HttpUpgradeHandler> T upgrade(Class<T> httpUpgradeHandlerClass) { return null; }
        @Override public Object getAttribute(String name) { return null; }
        @Override public java.util.Enumeration<String> getAttributeNames() { return null; }
        @Override public String getCharacterEncoding() { return "UTF-8"; }
        @Override public void setCharacterEncoding(String env) {}
        @Override public int getContentLength() { return jsonData.length(); }
        @Override public long getContentLengthLong() { return jsonData.length(); }
        @Override public String getContentType() { return "application/json"; }
        @Override public jakarta.servlet.ServletInputStream getInputStream() { return null; }
        @Override public String getParameter(String name) { return null; }
        @Override public java.util.Enumeration<String> getParameterNames() { return null; }
        @Override public String[] getParameterValues(String name) { return null; }
        @Override public java.util.Map<String, String[]> getParameterMap() { return null; }
        @Override public String getProtocol() { return "HTTP/1.1"; }
        @Override public String getScheme() { return "http"; }
        @Override public String getServerName() { return "localhost"; }
        @Override public int getServerPort() { return 8080; }
        @Override public String getRemoteAddr() { return "127.0.0.1"; }
        @Override public String getRemoteHost() { return "localhost"; }
        @Override public void setAttribute(String name, Object o) {}
        @Override public void removeAttribute(String name) {}
        @Override public java.util.Locale getLocale() { return null; }
        @Override public java.util.Enumeration<java.util.Locale> getLocales() { return null; }
        @Override public boolean isSecure() { return false; }
        @Override public jakarta.servlet.RequestDispatcher getRequestDispatcher(String path) { return null; }
        @Override public int getRemotePort() { return 0; }
        @Override public String getLocalName() { return "localhost"; }
        @Override public String getLocalAddr() { return "127.0.0.1"; }
        @Override public int getLocalPort() { return 8080; }
        @Override public jakarta.servlet.ServletContext getServletContext() { return null; }
        @Override public jakarta.servlet.AsyncContext startAsync() { return null; }
        @Override public jakarta.servlet.AsyncContext startAsync(jakarta.servlet.ServletRequest servletRequest, jakarta.servlet.ServletResponse servletResponse) { return null; }
        @Override public boolean isAsyncStarted() { return false; }
        @Override public boolean isAsyncSupported() { return false; }
        @Override public jakarta.servlet.AsyncContext getAsyncContext() { return null; }
        @Override public jakarta.servlet.DispatcherType getDispatcherType() { return null; }
        @Override public String getRequestId() { return null; }
        @Override public String getProtocolRequestId() { return null; }
        @Override public jakarta.servlet.ServletConnection getServletConnection() { return null; }
    }
}