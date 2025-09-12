package io.github.anamitraupadhyay.Quarklets.experimetal;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.*;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.*;
import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.*;

import java.util.List;

/**
 * Complete demonstration of JSON structure generation and binding functionality.
 * This class demonstrates the "Thread-Runnable" style binding pattern.
 */
public class JsonBindingDemo {
    
    public static void main(String[] args) {
        System.out.println("=== JSON Structure Generation and Binding Demo ===\n");
        
        // Test 1: Manual JSON structure creation
        testManualJsonCreation();
        
        // Test 2: JSON string parsing 
        testJsonStringParsing();
        
        // Test 3: Complete binding demo (Thread-Runnable pattern)
        testCompleteBinding();
    }
    
    /**
     * Test 1: Manually create the JSON structure using Dino classes
     */
    public static void testManualJsonCreation() {
        System.out.println("=== Test 1: Manual JSON Structure Creation ===");
        
        // Create root object
        ObjectDino root = new ObjectDino(null);
        
        // Add key-value pairs as ValueDino children
        try {
            root.addchild(new ValueDino("1234567890123456", "cc_num"));
            root.addchild(new ValueDino("75.50", "amt"));
            root.addchild(new ValueDino("94103", "zip"));
            root.addchild(new ValueDino("37.7749", "lat"));
            root.addchild(new ValueDino("-122.4194", "long"));
            root.addchild(new ValueDino("883305", "city_pop"));
            root.addchild(new ValueDino("1678886400", "unix_time"));
            root.addchild(new ValueDino("37.7833", "merch_lat"));
            root.addchild(new ValueDino("-122.4167", "merch_long"));
            
            System.out.println("✓ Successfully created JSON structure with " + root.getChildCount() + " fields");
            
            // Test findChildByKey functionality
            testFindChildByKey(root);
            
        } catch (Exception e) {
            System.out.println("✗ Error creating JSON structure: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }
    
    /**
     * Test the findChildByKey functionality
     */
    private static void testFindChildByKey(ObjectDino root) {
        System.out.println("Testing findChildByKey functionality:");
        
        // Test finding existing keys
        Dino ccNumNode = root.findChildByKey("cc_num");
        if (ccNumNode != null && "1234567890123456".equals(ccNumNode.value)) {
            System.out.println("✓ Found cc_num: " + ccNumNode.value);
        } else {
            System.out.println("✗ Failed to find cc_num");
        }
        
        Dino amtNode = root.findChildByKey("amt");
        if (amtNode != null && "75.50".equals(amtNode.value)) {
            System.out.println("✓ Found amt: " + amtNode.value);
        } else {
            System.out.println("✗ Failed to find amt");
        }
        
        // Test non-existing key
        Dino nonExistent = root.findChildByKey("non_existent");
        if (nonExistent == null) {
            System.out.println("✓ Correctly returned null for non-existent key");
        } else {
            System.out.println("✗ Should have returned null for non-existent key");
        }
    }
    
    /**
     * Test 2: Parse a JSON string into Dino objects
     */
    public static void testJsonStringParsing() {
        System.out.println("=== Test 2: JSON String Parsing ===");
        
        // Simple JSON string for testing
        String jsonString = "{\"cc_num\":\"1234567890123456\",\"amt\":\"75.50\",\"zip\":\"94103\"}";
        System.out.println("Input JSON: " + jsonString);
        
        try {
            // Tokenize the JSON string
            Tokenizer tokenizer = new Tokenizer();
            List<Token> tokens = tokenizer.JsonParse(new StringBuilder(jsonString), 0);
            System.out.println("✓ Tokenized JSON into " + tokens.size() + " tokens");
            
            // Parse tokens into Dino structure
            SimpleParser parser = new SimpleParser();
            Dino parsedRoot = parser.parse(tokens);
            
            if (parsedRoot != null && parsedRoot instanceof ObjectDino) {
                System.out.println("✓ Successfully parsed JSON into Dino structure");
                ObjectDino objRoot = (ObjectDino) parsedRoot;
                System.out.println("✓ Root has " + objRoot.getChildCount() + " children");
                
                // Test finding values
                Dino ccNum = objRoot.findChildByKey("cc_num");
                if (ccNum != null) {
                    System.out.println("✓ Found cc_num in parsed structure: " + ccNum.value);
                }
            } else {
                System.out.println("✗ Failed to parse JSON structure");
            }
            
        } catch (Exception e) {
            System.out.println("✗ Error parsing JSON: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }
    
    /**
     * Test 3: Complete binding demo - the "Thread-Runnable" pattern
     * This demonstrates how the Dino structure binds to a TransactionData object
     */
    public static void testCompleteBinding() {
        System.out.println("=== Test 3: Complete Binding Demo (Thread-Runnable Pattern) ===");
        
        try {
            // Step 1: Create a JSON structure (like creating a Thread)
            ObjectDino jsonTree = createCompleteTransactionJson();
            System.out.println("✓ Created complete transaction JSON structure");
            
            // Step 2: Create a TransactionData object (like creating a Runnable)
            TransactionData transaction = new TransactionData();
            System.out.println("✓ Created TransactionData object (implements AutobindInterface)");
            
            // Step 3: Bind the JSON to the object (like Thread.run() calls Runnable.run())
            transaction.bind(jsonTree);
            System.out.println("✓ Successfully bound JSON data to TransactionData object");
            
            // Step 4: Verify the binding worked
            System.out.println("✓ Bound data: " + transaction.toString());
            
            // Step 5: Verify individual fields
            verifyBoundData(transaction);
            
        } catch (Exception e) {
            System.out.println("✗ Error in complete binding demo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Create the complete transaction JSON structure as specified in requirements
     */
    private static ObjectDino createCompleteTransactionJson() throws Exception {
        ObjectDino root = new ObjectDino(null);
        
        // Add all required fields with exact values from requirements
        root.addchild(new ValueDino("1234567890123456", "cc_num"));
        root.addchild(new ValueDino("75.50", "amt"));
        root.addchild(new ValueDino("94103", "zip"));
        root.addchild(new ValueDino("37.7749", "lat"));
        root.addchild(new ValueDino("-122.4194", "long"));
        root.addchild(new ValueDino("883305", "city_pop"));
        root.addchild(new ValueDino("1678886400", "unix_time"));
        root.addchild(new ValueDino("37.7833", "merch_lat"));
        root.addchild(new ValueDino("-122.4167", "merch_long"));
        
        return root;
    }
    
    /**
     * Verify that the bound data matches expected values
     */
    private static void verifyBoundData(TransactionData transaction) {
        System.out.println("Verifying bound data:");
        
        // Verify string fields
        if ("1234567890123456".equals(transaction.getCcNum())) {
            System.out.println("✓ cc_num correctly bound: " + transaction.getCcNum());
        } else {
            System.out.println("✗ cc_num binding failed");
        }
        
        if ("94103".equals(transaction.getZip())) {
            System.out.println("✓ zip correctly bound: " + transaction.getZip());
        } else {
            System.out.println("✗ zip binding failed");
        }
        
        // Verify numeric fields
        if (Math.abs(transaction.getAmt() - 75.50) < 0.001) {
            System.out.println("✓ amt correctly bound: " + transaction.getAmt());
        } else {
            System.out.println("✗ amt binding failed");
        }
        
        if (Math.abs(transaction.getLat() - 37.7749) < 0.001) {
            System.out.println("✓ lat correctly bound: " + transaction.getLat());
        } else {
            System.out.println("✗ lat binding failed");
        }
        
        if (transaction.getCityPop() == 883305) {
            System.out.println("✓ city_pop correctly bound: " + transaction.getCityPop());
        } else {
            System.out.println("✗ city_pop binding failed");
        }
        
        if (transaction.getUnixTime() == 1678886400L) {
            System.out.println("✓ unix_time correctly bound: " + transaction.getUnixTime());
        } else {
            System.out.println("✗ unix_time binding failed");
        }
    }
}