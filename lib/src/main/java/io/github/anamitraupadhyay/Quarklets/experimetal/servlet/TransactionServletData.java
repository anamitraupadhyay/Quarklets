package io.github.anamitraupadhyay.Quarklets.experimetal.servlet;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.Dino;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Servlet-enabled TransactionData class that implements ServletAutoBindInterface.
 * This demonstrates the Thread-Runnable pattern for servlet request binding.
 * 
 * Usage pattern:
 * 1. HttpServletRequest comes in (like a Thread with work to do)
 * 2. Create TransactionServletData object (like creating a Runnable implementation)  
 * 3. Call bindFromServlet(request) (like Thread.run() calling Runnable.run())
 * 4. Object is now populated and can be passed around as reference
 */
public class TransactionServletData implements ServletAutoBindInterface {
    private String cc_num;
    private double amt;
    private String zip;
    private double lat;
    private double long_val; // 'long' is a reserved keyword
    private int city_pop;
    private long unix_time;
    private double merch_lat;
    private double merch_long;

    // Default constructor
    public TransactionServletData() {}

    @Override
    public void bindFromServlet(HttpServletRequest request) {
        if (request == null) return;
        
        // Use the helper methods to extract values from request parameters
        // This is where the actual binding happens - similar to Runnable.run()
        this.cc_num = getStringFromRequest("cc_num", request);
        this.amt = getDoubleFromRequest("amt", request);
        this.zip = getStringFromRequest("zip", request);
        this.lat = getDoubleFromRequest("lat", request);
        this.long_val = getDoubleFromRequest("long", request);
        this.city_pop = getIntFromRequest("city_pop", request);
        this.unix_time = getLongFromRequest("unix_time", request);
        this.merch_lat = getDoubleFromRequest("merch_lat", request);
        this.merch_long = getDoubleFromRequest("merch_long", request);
    }

    @Override
    public void bind(Dino jsonTree) {
        // Keep the original Dino binding for compatibility
        if (jsonTree == null) return;
        
        this.cc_num = getString("cc_num", jsonTree);
        this.amt = getDouble("amt", jsonTree);
        this.zip = getString("zip", jsonTree);
        this.lat = getDouble("lat", jsonTree);
        this.long_val = getDouble("long", jsonTree);
        this.city_pop = getInt("city_pop", jsonTree);
        this.unix_time = (long) getDouble("unix_time", jsonTree);
        this.merch_lat = getDouble("merch_lat", jsonTree);
        this.merch_long = getDouble("merch_long", jsonTree);
    }

    // Getters
    public String getCcNum() { return cc_num; }
    public double getAmt() { return amt; }
    public String getZip() { return zip; }
    public double getLat() { return lat; }
    public double getLongVal() { return long_val; }
    public int getCityPop() { return city_pop; }
    public long getUnixTime() { return unix_time; }
    public double getMerchLat() { return merch_lat; }
    public double getMerchLong() { return merch_long; }

    // Setters  
    public void setCcNum(String cc_num) { this.cc_num = cc_num; }
    public void setAmt(double amt) { this.amt = amt; }
    public void setZip(String zip) { this.zip = zip; }
    public void setLat(double lat) { this.lat = lat; }
    public void setLongVal(double long_val) { this.long_val = long_val; }
    public void setCityPop(int city_pop) { this.city_pop = city_pop; }
    public void setUnixTime(long unix_time) { this.unix_time = unix_time; }
    public void setMerchLat(double merch_lat) { this.merch_lat = merch_lat; }
    public void setMerchLong(double merch_long) { this.merch_long = merch_long; }

    @Override
    public String toString() {
        return String.format(
            "TransactionServletData{cc_num='%s', amt=%.2f, zip='%s', lat=%.4f, long=%.4f, " +
            "city_pop=%d, unix_time=%d, merch_lat=%.4f, merch_long=%.4f}",
            cc_num, amt, zip, lat, long_val, city_pop, unix_time, merch_lat, merch_long
        );
    }
    
    /**
     * Convenience method to check if the object has been properly bound
     * @return true if essential fields are populated
     */
    public boolean isValid() {
        return cc_num != null && !cc_num.isEmpty() && 
               zip != null && !zip.isEmpty() &&
               amt > 0;
    }
}