package com.landisgyr.e4s.context;

import java.util.ArrayList;

/** The Low-voltage supervisor, aka "RTU" */
public class LVSupervisor {
    public String id;                   // aka "Rtu" or "RTU"
    public ArrayList<LVLine> lvLines;

    /** Constructor (id not known) */
    public LVSupervisor() {
        id = "unknown";
        lvLines = new ArrayList<LVLine>();
   }

   /** Constructor (id known) */
   public LVSupervisor(String id) {
        this();
        this.id = id;
   }


    /** Adds an LVLine to the lines monitored by this RTU */
    public void addLVLine(LVLine lvl) {
        lvLines.add(lvl);
    }


    /** An RTU is healthy if all LVLines are healthy */
    public boolean isHealthy() {
        boolean isHealthy = true;

        for (LVLine lvl: lvLines) {
            if (!lvl.isHealthy()) isHealthy = false;
        }

        return isHealthy;
    }
}
