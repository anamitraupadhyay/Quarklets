package io.github.anamitraupadhyay.Quarklets.experimetal.servlet;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.ObjectDino;
import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.ValueDino;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.DataHandlerFromServlet;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.SimpleParser;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.Token;
import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.Tokenizer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Complete servlet example demonstrating the Thread-Runnable pattern with Quarklets JSON binding.
 * This servlet shows THREE different approaches to data binding:
 * 
 * 1. Direct parameter binding (simplest)
 * 2. JSON body parsing with Dino binding  
 * 3. Hybrid approach using both
 */
@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            // Determine the content type and process accordingly
            String contentType = request.getContentType();
            
            if (contentType != null && contentType.contains("application/json")) {
                // Handle JSON body requests
                processJsonBodyRequest(request, out);
            } else {
                // Handle form parameters or query parameters
                processParameterRequest(request, out);
            }
            
        } catch (Exception e) {
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            // Handle GET requests with query parameters
            processParameterRequest(request, out);
        } catch (Exception e) {
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    /**
     * APPROACH 1: Direct parameter binding (Thread-Runnable pattern)
     * This is the simplest approach mentioned in the comment.
     */
    private void processParameterRequest(HttpServletRequest request, PrintWriter out) throws Exception {
        // Step 1: HttpServletRequest comes in (like creating a Thread with work to do)
        // Step 2: Create the "Runnable" object that will process the data
        TransactionServletData transaction = new TransactionServletData();
        
        // Step 3: Bind the request data (like Thread.run() calling Runnable.run())  
        transaction.bindFromServlet(request);
        
        // Step 4: Object is now bound and can be passed around as a reference
        processTransactionData(transaction, out, "Parameter Binding");
    }
    
    /**
     * APPROACH 2: JSON body parsing with Dino binding
     * This uses the existing Dino JSON parsing system.
     */
    private void processJsonBodyRequest(HttpServletRequest request, PrintWriter out) throws Exception {
        // Step 1: Extract JSON from request body
        StringBuilder jsonData = DataHandlerFromServlet.stringbuilderparse(request);
        int jsonStart = DataHandlerFromServlet.findJsonStart(jsonData);
        
        // Step 2: Parse JSON using the existing Dino system
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.JsonParse(jsonData, jsonStart);
        
        SimpleParser parser = new SimpleParser();
        ObjectDino jsonTree = (ObjectDino) parser.parse(tokens);
        
        // Step 3: Create the "Runnable" object and bind (Thread-Runnable pattern)
        TransactionServletData transaction = new TransactionServletData();
        transaction.bind(jsonTree); // Using the Dino binding method
        
        // Step 4: Process the bound object
        processTransactionData(transaction, out, "JSON Body Binding");
    }
    
    /**
     * Process the bound transaction data and generate response.
     * This demonstrates how the bound object can be passed around and used.
     */
    private void processTransactionData(TransactionServletData transaction, PrintWriter out, String method) {
        if (!transaction.isValid()) {
            out.println("{\"error\": \"Invalid transaction data\", \"method\": \"" + method + "\"}");
            return;
        }
        
        // Simulate processing the transaction (this is where business logic would go)
        String result = processTransaction(transaction);
        
        // Generate response
        out.println("{");
        out.println("  \"status\": \"success\",");
        out.println("  \"method\": \"" + method + "\",");
        out.println("  \"result\": \"" + result + "\",");
        out.println("  \"transaction\": {");
        out.println("    \"cc_num\": \"" + maskCreditCard(transaction.getCcNum()) + "\",");
        out.println("    \"amt\": " + transaction.getAmt() + ",");
        out.println("    \"zip\": \"" + transaction.getZip() + "\",");
        out.println("    \"lat\": " + transaction.getLat() + ",");
        out.println("    \"long\": " + transaction.getLongVal() + ",");
        out.println("    \"city_pop\": " + transaction.getCityPop() + ",");
        out.println("    \"unix_time\": " + transaction.getUnixTime() + ",");
        out.println("    \"merch_lat\": " + transaction.getMerchLat() + ",");
        out.println("    \"merch_long\": " + transaction.getMerchLong());
        out.println("  }");
        out.println("}");
    }
    
    /**
     * Simulate business logic processing.
     * This shows how the bound object reference is passed around and used.
     */
    private String processTransaction(TransactionServletData transaction) {
        // Example business logic using the bound data
        if (transaction.getAmt() > 1000) {
            return "High-value transaction flagged for review";
        } else if (transaction.getAmt() < 0) {
            return "Invalid transaction amount";
        } else {
            return "Transaction processed successfully";
        }
    }
    
    /**
     * Utility method to mask credit card numbers for security
     */
    private String maskCreditCard(String ccNum) {
        if (ccNum == null || ccNum.length() < 4) {
            return "****";
        }
        return "****-****-****-" + ccNum.substring(ccNum.length() - 4);
    }
}