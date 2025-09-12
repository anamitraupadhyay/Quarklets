package io.github.anamitraupadhyay.Quarklets.experimetal.servlet;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple demo showing the servlet binding concept without requiring servlet dependencies.
 * This demonstrates how the Thread-Runnable pattern works with request data.
 */
public class ServletConceptDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Servlet Binding Concept Demo ===\n");
        
        // Test the concept using a simple Map to simulate request parameters
        testParameterBindingConcept();
        
        // Test object passing pattern
        testObjectPassingPattern();
    }
    
    /**
     * Demonstrates the Thread-Runnable pattern concept with simulated request data
     */
    public static void testParameterBindingConcept() {
        System.out.println("=== Parameter Binding Concept (Thread-Runnable Pattern) ===");
        
        // Step 1: Simulate request data (like HttpServletRequest parameters)
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("cc_num", "1234567890123456");
        requestParams.put("amt", "75.50");
        requestParams.put("zip", "94103");
        requestParams.put("lat", "37.7749");
        requestParams.put("long", "-122.4194");
        requestParams.put("city_pop", "883305");
        requestParams.put("unix_time", "1678886400");
        requestParams.put("merch_lat", "37.7833");
        requestParams.put("merch_long", "-122.4167");
        
        System.out.println("✓ Created simulated request parameters (like HttpServletRequest)");
        
        // Step 2: Create the "Runnable" equivalent object
        TransactionConceptData transaction = new TransactionConceptData();
        System.out.println("✓ Created TransactionConceptData (like creating a Runnable)");
        
        // Step 3: Bind the request data (like Thread.run() calling Runnable.run())
        transaction.bindFromRequestParams(requestParams);
        System.out.println("✓ Bound request data to object (like Runnable.run())");
        
        // Step 4: Object is now populated and can be passed around
        System.out.println("✓ Bound data: " + transaction.toString());
        System.out.println("✓ Object validation: " + (transaction.isValid() ? "Valid" : "Invalid"));
        
        System.out.println();
    }
    
    /**
     * Test how bound objects can be passed around and reused
     */
    public static void testObjectPassingPattern() {
        System.out.println("=== Object Passing Pattern ===");
        
        // Create and bind an object
        Map<String, String> params = new HashMap<>();
        params.put("cc_num", "9876543210987654");
        params.put("amt", "150.75");
        params.put("zip", "90210");
        
        TransactionConceptData transaction = new TransactionConceptData();
        transaction.bindFromRequestParams(params);
        System.out.println("✓ Created and bound transaction object");
        
        // Pass the object to different methods (simulating real servlet use)
        String validationResult = validateTransaction(transaction);
        System.out.println("✓ Validation result: " + validationResult);
        
        String processingResult = processTransactionBusiness(transaction);
        System.out.println("✓ Processing result: " + processingResult);
        
        // Show how getters/setters work under the bind method concept
        System.out.println("✓ Demonstrating getter/setter access:");
        System.out.println("  - CC Number (masked): " + maskCreditCard(transaction.getCcNum()));
        System.out.println("  - Amount: $" + transaction.getAmt());
        System.out.println("  - ZIP: " + transaction.getZip());
        
        // Modify and pass around (shows reference object concept)
        transaction.setAmt(200.00);
        System.out.println("✓ Modified amount via setter: $" + transaction.getAmt());
        
        System.out.println("✓ Object reference passing demonstrated successfully");
        System.out.println();
    }
    
    // Business methods that receive the bound object as reference
    private static String validateTransaction(TransactionConceptData transaction) {
        if (!transaction.isValid()) {
            return "FAILED - Invalid transaction data";
        }
        if (transaction.getAmt() < 0) {
            return "FAILED - Negative amount not allowed";
        }
        return "PASSED - Transaction is valid";
    }
    
    private static String processTransactionBusiness(TransactionConceptData transaction) {
        double amount = transaction.getAmt();
        if (amount > 1000) {
            return "HIGH_VALUE - Requires additional approval";
        } else if (amount > 100) {
            return "NORMAL - Standard processing";
        } else {
            return "LOW_VALUE - Fast track processing";
        }
    }
    
    private static String maskCreditCard(String ccNum) {
        if (ccNum == null || ccNum.length() < 4) {
            return "****";
        }
        return "****-****-****-" + ccNum.substring(ccNum.length() - 4);
    }
    
    /**
     * Simplified transaction data class that demonstrates the concept
     * without requiring servlet dependencies
     */
    static class TransactionConceptData {
        private String cc_num;
        private double amt;
        private String zip;
        private double lat;
        private double long_val;
        private int city_pop;
        private long unix_time;
        private double merch_lat;
        private double merch_long;

        public TransactionConceptData() {}

        /**
         * The "run()" equivalent method - this is where binding happens
         * Similar to how Runnable.run() contains the actual work
         */
        public void bindFromRequestParams(Map<String, String> params) {
            if (params == null) return;
            
            // This is where getters/setters are under the bind method
            // The object gets created and bound in one step
            this.cc_num = params.get("cc_num");
            this.amt = parseDouble(params.get("amt"));
            this.zip = params.get("zip");
            this.lat = parseDouble(params.get("lat"));
            this.long_val = parseDouble(params.get("long"));
            this.city_pop = parseInt(params.get("city_pop"));
            this.unix_time = parseLong(params.get("unix_time"));
            this.merch_lat = parseDouble(params.get("merch_lat"));
            this.merch_long = parseDouble(params.get("merch_long"));
        }
        
        // Helper methods for safe parsing
        private double parseDouble(String value) {
            return value != null ? Double.parseDouble(value) : 0.0;
        }
        
        private int parseInt(String value) {
            return value != null ? Integer.parseInt(value) : 0;
        }
        
        private long parseLong(String value) {
            return value != null ? Long.parseLong(value) : 0L;
        }

        // Getters - these are available after binding
        public String getCcNum() { return cc_num; }
        public double getAmt() { return amt; }
        public String getZip() { return zip; }
        public double getLat() { return lat; }
        public double getLongVal() { return long_val; }
        public int getCityPop() { return city_pop; }
        public long getUnixTime() { return unix_time; }
        public double getMerchLat() { return merch_lat; }
        public double getMerchLong() { return merch_long; }

        // Setters - allow modification after binding
        public void setCcNum(String cc_num) { this.cc_num = cc_num; }
        public void setAmt(double amt) { this.amt = amt; }
        public void setZip(String zip) { this.zip = zip; }
        public void setLat(double lat) { this.lat = lat; }
        public void setLongVal(double long_val) { this.long_val = long_val; }
        public void setCityPop(int city_pop) { this.city_pop = city_pop; }
        public void setUnixTime(long unix_time) { this.unix_time = unix_time; }
        public void setMerchLat(double merch_lat) { this.merch_lat = merch_lat; }
        public void setMerchLong(double merch_long) { this.merch_long = merch_long; }

        public boolean isValid() {
            return cc_num != null && !cc_num.isEmpty() && 
                   zip != null && !zip.isEmpty() &&
                   amt > 0;
        }

        @Override
        public String toString() {
            return String.format(
                "TransactionConceptData{cc_num='%s', amt=%.2f, zip='%s', lat=%.4f, long=%.4f, " +
                "city_pop=%d, unix_time=%d, merch_lat=%.4f, merch_long=%.4f}",
                cc_num, amt, zip, lat, long_val, city_pop, unix_time, merch_lat, merch_long
            );
        }
    }
}