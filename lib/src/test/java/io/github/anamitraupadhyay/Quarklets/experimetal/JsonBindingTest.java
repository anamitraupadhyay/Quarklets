package io.github.anamitraupadhyay.Quarklets.experimetal;

import io.github.anamitraupadhyay.Quarklets.experimetal.exampleofrunnablelikeandsimpleflowlike.TransactionData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.*;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.*;
import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for JSON structure generation and binding functionality.
 * Tests the complete flow from JSON creation to object binding.
 */
class JsonBindingTest {

    @Test
    @DisplayName("Should create JSON structure manually using Dino classes")
    void testManualJsonStructureCreation() {
        // Create root object
        ObjectDino root = new ObjectDino(null);
        
        // Add required fields
        assertDoesNotThrow(() -> {
            root.addchild(new ValueDino("1234567890123456", "cc_num"));
            root.addchild(new ValueDino("75.50", "amt"));
            root.addchild(new ValueDino("94103", "zip"));
        });
        
        // Verify structure was created
        assertEquals(3, root.getChildCount(), "Should have 3 children");
        
        // Test findChildByKey functionality
        Dino ccNumNode = root.findChildByKey("cc_num");
        assertNotNull(ccNumNode, "Should find cc_num node");
        assertEquals("1234567890123456", ccNumNode.value, "cc_num value should match");
        
        Dino amtNode = root.findChildByKey("amt");
        assertNotNull(amtNode, "Should find amt node");
        assertEquals("75.50", amtNode.value, "amt value should match");
        
        // Test non-existent key
        Dino nonExistent = root.findChildByKey("non_existent");
        assertNull(nonExistent, "Should return null for non-existent key");
    }

    @Test
    @DisplayName("Should parse JSON string into Dino objects")
    void testJsonStringParsing() {
        String jsonString = "{\"cc_num\":\"1234567890123456\",\"amt\":\"75.50\"}";
        
        // Tokenize
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.JsonParse(new StringBuilder(jsonString), 0);
        assertFalse(tokens.isEmpty(), "Should produce tokens");
        
        // Parse
        SimpleParser parser = new SimpleParser();
        Dino parsedRoot = parser.parse(tokens);
        
        assertNotNull(parsedRoot, "Should parse successfully");
        assertInstanceOf(ObjectDino.class, parsedRoot, "Root should be ObjectDino");
        
        ObjectDino objRoot = (ObjectDino) parsedRoot;
        assertEquals(2, objRoot.getChildCount(), "Should have 2 children");
        
        // Verify parsed values
        Dino ccNum = objRoot.findChildByKey("cc_num");
        assertNotNull(ccNum, "Should find cc_num in parsed structure");
        assertEquals("1234567890123456", ccNum.value, "Parsed cc_num should match");
    }

    @Test
    @DisplayName("Should bind JSON data to TransactionData object (Thread-Runnable pattern)")
    void testCompleteBinding() {
        // Create complete JSON structure
        ObjectDino jsonTree = createCompleteTransactionJson();
        assertEquals(9, jsonTree.getChildCount(), "Should have all 9 required fields");
        
        // Create TransactionData object
        TransactionData transaction = new TransactionData();
        
        // Bind JSON to object
        assertDoesNotThrow(() -> transaction.bind(jsonTree), "Binding should not throw exception");
        
        // Verify bound data
        assertEquals("1234567890123456", transaction.getCcNum(), "cc_num should be bound correctly");
        assertEquals(75.50, transaction.getAmt(), 0.001, "amt should be bound correctly");
        assertEquals("94103", transaction.getZip(), "zip should be bound correctly");
        assertEquals(37.7749, transaction.getLat(), 0.001, "lat should be bound correctly");
        assertEquals(-122.4194, transaction.getLongVal(), 0.001, "long should be bound correctly");
        assertEquals(883305, transaction.getCityPop(), "city_pop should be bound correctly");
        assertEquals(1678886400L, transaction.getUnixTime(), "unix_time should be bound correctly");
        assertEquals(37.7833, transaction.getMerchLat(), 0.001, "merch_lat should be bound correctly");
        assertEquals(-122.4167, transaction.getMerchLong(), 0.001, "merch_long should be bound correctly");
    }

    @Test
    @DisplayName("Should handle null values gracefully")
    void testNullValueHandling() {
        ObjectDino root = new ObjectDino(null);
        
        // Test findChildByKey with null key
        Dino result = root.findChildByKey(null);
        assertNull(result, "Should return null for null key");
        
        // Test binding with null tree
        TransactionData transaction = new TransactionData();
        assertDoesNotThrow(() -> transaction.bind(null), "Should handle null tree gracefully");
    }

    @Test  
    @DisplayName("Should validate AutobindInterface type conversion methods")
    void testTypeConversionMethods() {
        ObjectDino root = new ObjectDino(null);
        
        // Add test values
        assertDoesNotThrow(() -> {
            root.addchild(new ValueDino("123", "int_val"));
            root.addchild(new ValueDino("123.45", "double_val"));
            root.addchild(new ValueDino("test", "string_val"));
        });
        
        TransactionData transaction = new TransactionData();
        
        // Test type conversion methods
        int intVal = transaction.getInt("int_val", root);
        assertEquals(123, intVal, "Should convert string to int");
        
        double doubleVal = transaction.getDouble("double_val", root);
        assertEquals(123.45, doubleVal, 0.001, "Should convert string to double");
        
        String stringVal = transaction.getString("string_val", root);
        assertEquals("test", stringVal, "Should return string value");
        
        // Test non-existent key
        int nonExistent = transaction.getInt("non_existent", root);
        assertEquals(0, nonExistent, "Should return 0 for non-existent int");
    }

    /**
     * Helper method to create the complete transaction JSON structure
     */
    private ObjectDino createCompleteTransactionJson() {
        ObjectDino root = new ObjectDino(null);
        
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
        } catch (Exception e) {
            fail("Should not throw exception when creating JSON structure");
        }
        
        return root;
    }
}