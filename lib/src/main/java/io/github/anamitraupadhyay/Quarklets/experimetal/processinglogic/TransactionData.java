package io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.Dino;

/**
 * POJO class representing transaction data that implements AutobindInterface.
 * This class demonstrates the binding mechanism similar to how Thread works with Runnable.
 */
public class TransactionData implements AutobindInterface {
    private String cc_num;
    private double amt;
    private String zip;
    private double lat;
    private double long_val; // 'long' is a reserved keyword, so using long_val
    private int city_pop;
    private long unix_time;
    private double merch_lat;
    private double merch_long;

    // Default constructor
    public TransactionData() {}

    @Override
    public void bind(Dino jsonTree) {
        if (jsonTree == null) return;
        
        // Use the helper methods from AutobindInterface to extract values
        this.cc_num = getString("cc_num", jsonTree);
        this.amt = getDouble("amt", jsonTree);
        this.zip = getString("zip", jsonTree);
        this.lat = getDouble("lat", jsonTree);
        this.long_val = getDouble("long", jsonTree);
        this.city_pop = getInt("city_pop", jsonTree);
        this.unix_time = (long) getDouble("unix_time", jsonTree); // Convert to long
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
            "TransactionData{cc_num='%s', amt=%.2f, zip='%s', lat=%.4f, long=%.4f, " +
            "city_pop=%d, unix_time=%d, merch_lat=%.4f, merch_long=%.4f}",
            cc_num, amt, zip, lat, long_val, city_pop, unix_time, merch_lat, merch_long
        );
    }
}