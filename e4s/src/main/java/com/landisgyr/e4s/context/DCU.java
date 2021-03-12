package com.landisgyr.e4s.context;

import java.util.ArrayList;

/** PLC data concentrator and all connected meters */
public class DCU {
    public String id;                   // id of this DCU, aka "Cnc" attribute
    public ArrayList<Meter> meters;     // meters connected to this DCU

    /** Constructor */
    public DCU() {
        meters = new ArrayList<Meter>();
        id = "unknown";
    }

    /** Constructor (id known) */
    public DCU(String id) {
        this();
        this.id = id;
    }

    /** Returns whether the DCU and associated meters are healthy */
    // the DCU is healthy if all meters are healthy?
    // TODO: confirm this -- perhaps "healthy" if > x% of meters are healthy?
    public boolean isHealthy() {
        boolean healthy = true;

        for (Meter m: meters) {
            if (!m.isHealthy()) healthy = false;
        }

        return healthy;
    }
}
