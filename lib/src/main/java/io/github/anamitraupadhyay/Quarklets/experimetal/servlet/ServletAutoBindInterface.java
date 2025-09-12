package io.github.anamitraupadhyay.Quarklets.experimetal.servlet;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.Dino;
import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.AutobindInterface;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Extended interface for servlet-based JSON binding.
 * This interface demonstrates how the existing AutobindInterface can be extended 
 * to work directly with HttpServletRequest objects, following the Thread-Runnable pattern.
 * 
 * Usage pattern (similar to Thread-Runnable):
 * 1. HttpServletRequest request = ... (like creating a Thread)
 * 2. TransactionServletData data = new TransactionServletData(); (like creating a Runnable) 
 * 3. data.bindFromServlet(request); (like Thread.run() calls Runnable.run())
 */
public interface ServletAutoBindInterface extends AutobindInterface {
    
    /**
     * Direct binding from HttpServletRequest - this is the "run()" equivalent method
     * that gets called with the servlet request, just like how Thread.run() calls Runnable.run()
     */
    void bindFromServlet(HttpServletRequest request);
    
    /**
     * Helper methods for getting typed values from servlet request parameters
     */
    default int getIntFromRequest(String paramName, HttpServletRequest request) {
        String value = request.getParameter(paramName);
        return value != null ? Integer.parseInt(value) : 0;
    }
    
    default double getDoubleFromRequest(String paramName, HttpServletRequest request) {
        String value = request.getParameter(paramName);
        return value != null ? Double.parseDouble(value) : 0.0;
    }
    
    default String getStringFromRequest(String paramName, HttpServletRequest request) {
        return request.getParameter(paramName);
    }
    
    default long getLongFromRequest(String paramName, HttpServletRequest request) {
        String value = request.getParameter(paramName);
        return value != null ? Long.parseLong(value) : 0L;
    }
}