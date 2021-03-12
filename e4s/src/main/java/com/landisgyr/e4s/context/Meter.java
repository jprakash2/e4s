package com.landisgyr.e4s.context;

/** Class to represent a Meter (endpoint) */
public class Meter  {
    public String id;
    public String lastUpdate;       // aka the "Fh" dateTime attribute
    public String location;
    public boolean isHealthy;

    /** Constructor (id not known) */
    public Meter() {
        id = "unknown";
        lastUpdate = "unknown";
        location = "unknown";
        isHealthy = true;
    }

    /** Constructor (id known) */
    public Meter(String id) {
        this();
        this.id = id;
    }

    /** Returns true if this meter is not in outage */
    public boolean isHealthy() {
        return isHealthy;
    }
}
