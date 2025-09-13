package io.github.anamitraupadhyay.Quarklets.experimetal.servlet;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.Dino;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.DataHandlerFromServlet;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.SimpleParser;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.Token;
import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.Tokenizer;
import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.TransactionData;

import java.util.List;

/**
 * Simplified demonstration showing the Thread-Runnable pattern concept.
 * 
 * This class demonstrates how the 4-step pipeline can be elegantly orchestrated
 * through a single method call, following the Thread-Runnable pattern.
 */
public class ThreadRunnablePatternDemo {
    
    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println("Thread-Runnable Pattern Demonstration");
        System.out.println("====================================================");
        System.out.println();
        
        // Demonstrate both approaches with sample JSON
        String sampleJson = "{\n" +
                "    \"cc_num\": \"1234567890123456\",\n" +
                "    \"amt\": 75.50,\n" +
                "    \"zip\": \"94103\",\n" +
                "    \"lat\": 37.7749,\n" +
                "    \"long\": -122.4194,\n" +
                "    \"city_pop\": 883305,\n" +
                "    \"unix_time\": 1678886400,\n" +
                "    \"merch_lat\": 37.7833,\n" +
                "    \"merch_long\": -122.4167\n" +
                "}";
        
        // Show both approaches
        demonstrateOldApproach(sampleJson);
        System.out.println();
        demonstrateNewApproachConcept(sampleJson);
        
        System.out.println();
        System.out.println("====================================================");
        System.out.println("Summary:");
        System.out.println("Old approach: 6+ method calls, manual orchestration");
        System.out.println("New approach: 1 method call, automatic orchestration");
        System.out.println("Thread-Runnable pattern: Clean, elegant, minimal code");
        System.out.println("====================================================");
    }
    
    /**
     * Demonstrates the old 4-step approach (what we had before)
     */
    private static void demonstrateOldApproach(String jsonData) {
        System.out.println("=== OLD APPROACH (Manual 4-Step Process) ===");
        System.out.println();
        
        try {
            long startTime = System.currentTimeMillis();
            
            System.out.println("Step 1: Converting JSON string to StringBuilder...");
            StringBuilder jsonBuilder = new StringBuilder(jsonData);
            System.out.println("StringBuilder content: " + jsonBuilder.toString().substring(0, Math.min(50, jsonBuilder.length())) + "...");
            
            System.out.println("Step 2: Finding JSON start position...");
            int jsonStart = DataHandlerFromServlet.findJsonStart(jsonBuilder);
            System.out.println("JSON starts at position: " + jsonStart);
            
            System.out.println("Step 3: Tokenizing JSON data...");
            Tokenizer tokenizer = new Tokenizer();
            List<Token> tokens = tokenizer.JsonParse(jsonBuilder, jsonStart);
            System.out.println("Generated " + tokens.size() + " tokens");
            
            System.out.println("Step 4: Parsing tokens into Dino tree...");
            SimpleParser parser = new SimpleParser();
            Dino jsonTree = parser.parse(tokens);
            System.out.println("Created Dino tree with " + jsonTree.getChildCount() + " children");
            
            System.out.println("Step 5: Creating TransactionData instance...");
            TransactionData transaction = new TransactionData();
            
            System.out.println("Step 6: Binding data to POJO...");
            transaction.bind(jsonTree);
            
            long endTime = System.currentTimeMillis();
            
            System.out.println("Final result: " + transaction);
            System.out.println("Processing time: " + (endTime - startTime) + " ms");
            System.out.println("Lines of code required: ~12+ method calls");
            
        } catch (Exception e) {
            System.err.println("Error in old approach: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates the new Thread-Runnable approach concept
     */
    private static void demonstrateNewApproachConcept(String jsonData) {
        System.out.println("=== NEW APPROACH (Thread-Runnable Pattern Concept) ===");
        System.out.println();
        
        try {
            long startTime = System.currentTimeMillis();
            
            System.out.println("Thread-Runnable Pattern:");
            System.out.println("- HttpServletRequest = Thread (has work to do)");
            System.out.println("- TransactionData = Runnable (implements the work via bind method)");
            System.out.println("- ServletJsonProcessor = Orchestrator (like Thread.start())");
            System.out.println();
            
            System.out.println("Concept: ServletJsonProcessor.bind(request, TransactionData.class)");
            System.out.println("(Note: In real usage, this works with actual HttpServletRequest objects)");
            System.out.println();
            
            // For demonstration, we'll show the internal process
            System.out.println("Internal process (handled automatically by ServletJsonProcessor):");
            
            // Step 1-2: StringBuilder + JSON start (would be from HttpServletRequest)
            StringBuilder jsonBuilder = new StringBuilder(jsonData);
            int jsonStart = DataHandlerFromServlet.findJsonStart(jsonBuilder);
            
            // Step 3: Tokenization
            Tokenizer tokenizer = new Tokenizer();
            List<Token> tokens = tokenizer.JsonParse(jsonBuilder, jsonStart);
            
            // Step 4: Parsing
            SimpleParser parser = new SimpleParser();
            Dino jsonTree = parser.parse(tokens);
            
            // Step 5-6: Create and bind (Thread-Runnable pattern)
            TransactionData result = new TransactionData();
            result.bind(jsonTree);
            
            long endTime = System.currentTimeMillis();
            
            System.out.println("Final result: " + result);
            System.out.println("Processing time: " + (endTime - startTime) + " ms");
            System.out.println("Lines of code required: 1 method call (ServletJsonProcessor.bind())");
            System.out.println();
            System.out.println("✅ All 4 steps handled internally by ServletJsonProcessor");
            System.out.println("✅ Clean, readable, maintainable code");
            System.out.println("✅ Thread-Runnable pattern implemented elegantly");
            
        } catch (Exception e) {
            System.err.println("Error in new approach: " + e.getMessage());
        }
    }
    
    /**
     * Shows the elegance of the Thread-Runnable pattern
     */
    public static void showThreadRunnableAnalogy() {
        System.out.println("=== THREAD-RUNNABLE PATTERN ANALOGY ===");
        System.out.println();
        System.out.println("Traditional Thread-Runnable Pattern:");
        System.out.println("  Thread thread = new Thread(() -> { /* work */ });");
        System.out.println("  thread.start(); // Executes the work");
        System.out.println();
        System.out.println("Our JSON Binding Pattern:");
        System.out.println("  TransactionData result = ServletJsonProcessor.bind(request, TransactionData.class);");
        System.out.println("  // HttpServletRequest = Thread (has work to do)");
        System.out.println("  // TransactionData = Runnable (implements work via bind method)");
        System.out.println("  // ServletJsonProcessor = Orchestrator (executes the binding)");
        System.out.println();
        System.out.println("Key Benefits:");
        System.out.println("  ✅ Single line of code");
        System.out.println("  ✅ Automatic orchestration");
        System.out.println("  ✅ Type-safe binding");
        System.out.println("  ✅ Minimal changes to existing code");
        System.out.println("  ✅ Follows familiar Thread-Runnable pattern");
    }
}