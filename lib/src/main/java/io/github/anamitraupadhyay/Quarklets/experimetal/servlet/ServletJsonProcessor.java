package io.github.anamitraupadhyay.Quarklets.experimetal.servlet;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.Dino;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.DataHandlerFromServlet;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.SimpleParser;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.Token;
import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.AutobindInterface;
import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.Tokenizer;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * Single orchestrator class that implements the Thread-Runnable pattern for JSON binding.
 * 
 * This class encapsulates the complete 4-step pipeline:
 * 1. HttpServletRequest → StringBuilder 
 * 2. StringBuilder → Tokens
 * 3. Tokens → Dino objects
 * 4. Dino → Bound POJO
 * 
 * Usage (Thread-Runnable Pattern):
 * - HttpServletRequest = Thread (has work to do)
 * - POJO with AutobindInterface = Runnable (implements the work via bind method)
 * - ServletJsonProcessor = Orchestrator (like Thread.start() that triggers everything)
 * 
 * Example:
 * {@code
 * TransactionData result = new ServletJsonProcessor<>(request, TransactionData.class).process();
 * }
 */
public class ServletJsonProcessor<T extends AutobindInterface> {
    
    private final HttpServletRequest request;
    private final Class<T> targetClass;
    
    /**
     * Constructor - sets up the "Thread" (request) and "Runnable" (target class)
     * 
     * @param request HttpServletRequest containing JSON data
     * @param targetClass Class type that implements AutobindInterface
     */
    public ServletJsonProcessor(HttpServletRequest request, Class<T> targetClass) {
        this.request = request;
        this.targetClass = targetClass;
    }
    
    /**
     * Single method that orchestrates the complete 4-step pipeline and returns bound object.
     * This is like calling Thread.start() - it triggers the entire process.
     * 
     * @return Bound POJO instance with all fields populated from request JSON
     */
    public T process() {
        try {
            // Step 1: HttpServletRequest → StringBuilder
            StringBuilder jsonData = DataHandlerFromServlet.stringbuilderparse(request);
            
            // Step 2: Find JSON start position (handle BOM/cleanup)
            int jsonStart = DataHandlerFromServlet.findJsonStart(jsonData);
            
            // Step 3: StringBuilder → Tokens
            Tokenizer tokenizer = new Tokenizer();
            List<Token> tokens = tokenizer.JsonParse(jsonData, jsonStart);
            
            // Step 4: Tokens → Dino object tree
            SimpleParser parser = new SimpleParser();
            Dino jsonTree = parser.parse(tokens);
            
            // Step 5: Create POJO instance (like creating Runnable implementation)
            T instance = targetClass.getDeclaredConstructor().newInstance();
            
            // Step 6: Bind data (like Thread.run() calling Runnable.run())
            instance.bind(jsonTree);
            
            return instance;
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to process servlet request and bind to " + 
                                     targetClass.getSimpleName() + ": " + e.getMessage(), e);
        }
    }
    
    /**
     * Static convenience method for one-liner usage
     * 
     * @param request HttpServletRequest containing JSON data
     * @param targetClass Target POJO class implementing AutobindInterface
     * @return Bound POJO instance
     */
    public static <T extends AutobindInterface> T bind(HttpServletRequest request, Class<T> targetClass) {
        return new ServletJsonProcessor<>(request, targetClass).process();
    }
    
    /**
     * Alternative method with detailed logging for learning/debugging
     * 
     * @param request HttpServletRequest containing JSON data
     * @param targetClass Target POJO class implementing AutobindInterface
     * @param enableLogging Whether to print step-by-step progress
     * @return Bound POJO instance
     */
    public static <T extends AutobindInterface> T bindWithLogging(HttpServletRequest request, 
                                                                  Class<T> targetClass, 
                                                                  boolean enableLogging) {
        if (enableLogging) {
            System.out.println("=== ServletJsonProcessor: Thread-Runnable Pattern Demo ===");
            System.out.println("Thread (HttpServletRequest) has work to do...");
            System.out.println("Runnable (" + targetClass.getSimpleName() + ") will implement the work...");
            System.out.println();
        }
        
        try {
            ServletJsonProcessor<T> processor = new ServletJsonProcessor<>(request, targetClass);
            
            if (enableLogging) {
                System.out.println("Step 1: Extracting StringBuilder from HttpServletRequest...");
            }
            StringBuilder jsonData = DataHandlerFromServlet.stringbuilderparse(request);
            
            if (enableLogging) {
                System.out.println("Step 2: Finding JSON start position...");
            }
            int jsonStart = DataHandlerFromServlet.findJsonStart(jsonData);
            
            if (enableLogging) {
                System.out.println("Step 3: Tokenizing JSON data...");
            }
            Tokenizer tokenizer = new Tokenizer();
            List<Token> tokens = tokenizer.JsonParse(jsonData, jsonStart);
            
            if (enableLogging) {
                System.out.println("Step 4: Parsing tokens into Dino tree...");
            }
            SimpleParser parser = new SimpleParser();
            Dino jsonTree = parser.parse(tokens);
            
            if (enableLogging) {
                System.out.println("Step 5: Creating " + targetClass.getSimpleName() + " instance (Runnable)...");
            }
            T instance = targetClass.getDeclaredConstructor().newInstance();
            
            if (enableLogging) {
                System.out.println("Step 6: Calling bind() method (like Thread.run() calling Runnable.run())...");
            }
            instance.bind(jsonTree);
            
            if (enableLogging) {
                System.out.println("=== Complete! Bound object ready for use ===");
                System.out.println("Result: " + instance.toString());
            }
            
            return instance;
            
        } catch (Exception e) {
            if (enableLogging) {
                System.err.println("Error during processing: " + e.getMessage());
            }
            throw new RuntimeException("Failed to process servlet request: " + e.getMessage(), e);
        }
    }
}