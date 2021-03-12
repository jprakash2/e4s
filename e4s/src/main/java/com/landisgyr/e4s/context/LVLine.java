package com.landisgyr.e4s.context;

/** Class to represent a LowVoltage Line (LVSLine) in the substation */
public class LVLine {
    public String id;
    public String lastUpdate;       // aka the "Fh" dateTime attribute
    public LVPhase[] LVPhases = new LVPhase[3];    // 3 phases

    /** Constructor */
    public LVLine() {
        id = "unknown";
        for (int i = 0; i < LVPhases.length; i++) {
            LVPhases[i] = new LVPhase();
        }
    }

    /** Constructor (id known) */
    public LVLine(String id) {
        this();
        this.id = id;
    }

    /** Returns whether the LVLine is healthy (no alarms and V, I within limits. */
    // an LVLine is healthy if there are no alarms, and if the voltages
    // and currents on all phases are within prescribed limits
    public boolean isHealthy() {
        boolean isHealthy = true;

        // check instV, I
        for (int i = 0; i < LVPhases.length; i++) {
            if (LVPhases[i].alarm) isHealthy = false;
            if (!LVPhases[i].withinLimits()) isHealthy = false;
        }
        return isHealthy;
    }

}
