package io.github.anamitraupadhyay.Quarklets.experimetal.servlet;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.Dino;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.DataHandlerFromServlet;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.SimpleParser;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.Token;
import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.Tokenizer;
import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.TransactionData;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * Simple demonstration of HttpServletRequest → StringBuilder → Parsing → Tokenization → Object binding
 * This shows how all the existing components work together in a simple flow.
 * 
 * Flow:
 * 1. HttpServletRequest (input)
 * 2. DataHandlerFromServlet.stringbuilderparse() → StringBuilder  
 * 3. Tokenizer.JsonParse() → List<Token>
 * 4. SimpleParser.parse() → Dino object tree
 * 5. TransactionData.bind() → Bound POJO object
 * 
 * This is like Thread-Runnable pattern:
 * - HttpServletRequest is like the Thread (has work to do)
 * - TransactionData is like Runnable (implements the work via bind method)  
 * - The parsing pipeline connects them together
 */
public class SimpleServletBindingDemo {
    
    /**
     * Main demonstration method showing complete flow from HttpServletRequest to bound object
     */
    public static TransactionData processServletRequest(HttpServletRequest request) {
        try {
            // Step 1: Extract data from HttpServletRequest to StringBuilder
            StringBuilder jsonData = DataHandlerFromServlet.stringbuilderparse(request);
            
            // Step 2: Find where JSON starts (clean up any BOM or extra characters)
            int jsonStart = DataHandlerFromServlet.findJsonStart(jsonData);
            
            // Step 3: Tokenize the StringBuilder into Token list
            Tokenizer tokenizer = new Tokenizer();
            List<Token> tokens = tokenizer.JsonParse(jsonData, jsonStart);
            
            // Step 4: Parse tokens into Dino object tree
            SimpleParser parser = new SimpleParser();
            Dino jsonTree = parser.parse(tokens);
            
            // Step 5: Create POJO and bind data (Thread-Runnable pattern)
            // Like creating a Runnable implementation
            TransactionData transaction = new TransactionData();
            
            // Like calling Thread.run() which calls Runnable.run()
            transaction.bind(jsonTree);
            
            return transaction;
            
        } catch (Exception e) {
            System.err.println("Error processing servlet request: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Alternative method that shows the complete flow step by step for learning
     */
    public static void demonstrateStepByStepFlow(HttpServletRequest request) {
        System.out.println("=== Step-by-Step Servlet JSON Binding Demo ===\n");
        
        try {
            // Step 1: HttpServletRequest → StringBuilder
            System.out.println("Step 1: Converting HttpServletRequest to StringBuilder...");
            StringBuilder jsonData = DataHandlerFromServlet.stringbuilderparse(request);
            System.out.println("StringBuilder content: " + jsonData.toString());
            System.out.println();
            
            // Step 2: Find JSON start position
            System.out.println("Step 2: Finding JSON start position...");
            int jsonStart = DataHandlerFromServlet.findJsonStart(jsonData);
            System.out.println("JSON starts at position: " + jsonStart);
            System.out.println();
            
            // Step 3: StringBuilder → Tokens
            System.out.println("Step 3: Tokenizing JSON data...");
            Tokenizer tokenizer = new Tokenizer();
            List<Token> tokens = tokenizer.JsonParse(jsonData, jsonStart);
            System.out.println("Generated " + tokens.size() + " tokens");
            for (Token token : tokens) {
                System.out.println("  " + token);
            }
            System.out.println();
            
            // Step 4: Tokens → Dino object tree
            System.out.println("Step 4: Parsing tokens into Dino tree...");
            SimpleParser parser = new SimpleParser();
            Dino jsonTree = parser.parse(tokens);
            System.out.println("Created Dino tree with " + jsonTree.getChildCount() + " children");
            System.out.println();
            
            // Step 5: Dino → Bound POJO (Thread-Runnable pattern)
            System.out.println("Step 5: Binding to POJO (Thread-Runnable pattern)...");
            System.out.println("Creating TransactionData (like creating Runnable implementation)");
            TransactionData transaction = new TransactionData();
            
            System.out.println("Calling bind() method (like Thread.run() calling Runnable.run())");
            transaction.bind(jsonTree);
            
            System.out.println("Final bound object: " + transaction);
            System.out.println();
            
            System.out.println("=== Complete! Object ready for use ===");
            
        } catch (Exception e) {
            System.err.println("Error in demonstration: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Simple utility method that combines everything into one call
     * This is what you'd use in production - clean and simple
     */
    public static TransactionData bindFromServlet(HttpServletRequest request) {
        return processServletRequest(request);
    }
}