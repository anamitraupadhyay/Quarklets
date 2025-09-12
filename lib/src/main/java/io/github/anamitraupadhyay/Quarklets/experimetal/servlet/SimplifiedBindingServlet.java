package io.github.anamitraupadhyay.Quarklets.experimetal.servlet;

import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.TransactionData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Demonstration of the simplified Thread-Runnable pattern for servlet JSON binding.
 * 
 * This shows how the ServletJsonProcessor eliminates the need for manual field extraction
 * and reduces the entire pipeline to a single elegant method call.
 * 
 * Before (Manual approach):
 * - Manual request.getLong("cc_num"), request.getDouble("amt"), etc.
 * - Multiple steps for parsing and binding
 * 
 * After (Thread-Runnable approach):
 * - Single line: ServletJsonProcessor.bind(request, TransactionData.class)
 * - Automatic binding via POJO's bind method
 */
@WebServlet("/simplified-binding")
public class SimplifiedBindingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // ===== OLD APPROACH (what you wanted to replace) =====
            // BufferedReader reader = request.getReader();
            // StringBuilder jsonPayload = new StringBuilder();
            // // ... read lines ...
            // JSONObject requestJson = new JSONObject(jsonPayload.toString());
            // TransactionData transactionData = new TransactionData(
            //     requestJson.getLong("cc_num"),
            //     requestJson.getDouble("amt"),
            //     requestJson.getString("zip"),
            //     // ... etc for all 9 fields
            // );
            
            // ===== NEW APPROACH (Thread-Runnable Pattern) =====
            // Single elegant line - HttpServletRequest becomes bound POJO
            TransactionData transactionData = ServletJsonProcessor.bind(request, TransactionData.class);
            
            // Object is now fully bound and ready for business logic
            processTransactionData(transactionData);
            
            // Send response
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\",\"message\":\"Data bound successfully\"}");
            
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    /**
     * Business logic method that works with the bound POJO
     */
    private void processTransactionData(TransactionData data) {
        System.out.println("Processing transaction: " + data);
        // Your business logic here - fraud detection, validation, etc.
    }
    
    /**
     * Alternative method showing the learning/debugging version
     */
    @SuppressWarnings("unused")
    private TransactionData processWithLogging(HttpServletRequest request) {
        // This version shows step-by-step progress for learning
        return ServletJsonProcessor.bindWithLogging(request, TransactionData.class, true);
    }
}