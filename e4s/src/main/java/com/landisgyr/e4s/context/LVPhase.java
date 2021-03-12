package com.landisgyr.e4s.context;

/** Information about one of the (generally 3) phases making up an LVLine */
public class LVPhase {
    public String lastUpdated;
    public double avgV;     // average (historical) values
    public double avgI;
    public double instV;    // latest known values
    public double instI;
    public boolean alarm;   // alarm

    public final double UNKNOWN = -999999;
    public final double INST_V_LOW_LIMIT = 184.0;        // EU harmonized 230V * 0.8
    public final double INST_V_HIGH_LIMIT = 276.0;       // EU harmonized 230V * 1.2
    public final double INST_I_LOW_LIMIT = 10.0;         // TODO: determine reasonable limit
    public final double INST_I_HIGH_LIMIT = 10000.0;     // TODO: determine reasonable limit

    /** Constructor */
    public LVPhase() {
        avgV = UNKNOWN;
        avgI = UNKNOWN;
        instV = UNKNOWN;
        instI = UNKNOWN;
        alarm = false;
    }


    /** Determines whether Voltage, current are within "reasonable" operating limits */
    public boolean withinLimits() {
        boolean inLimits = true;

        if (instV != UNKNOWN) {
            if ((instV < INST_V_LOW_LIMIT) || (instV > INST_V_HIGH_LIMIT)) inLimits = false;

            if (avgV != UNKNOWN) {
                if ((instV < avgV * 0.8) || (instV > avgV * 1.2)) inLimits = false;
            }
        }

        if (instI != UNKNOWN) {
            if ((instI < INST_I_LOW_LIMIT) || (instI > INST_I_HIGH_LIMIT)) inLimits = false;
        }

        if (avgI != UNKNOWN) {
            if ((instI < avgI * 0.8) || (instI > avgI * 1.2)) inLimits = false;
        }

        return inLimits;
    }
}
