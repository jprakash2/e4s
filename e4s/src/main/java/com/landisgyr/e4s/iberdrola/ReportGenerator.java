package com.landisgyr.e4s.iberdrola;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.landisgyr.e4s.pubSub.PubSubEventQueue;

/** Generates outage and restore reports in JSON format */
public class ReportGenerator {
    // This class includes methods to generate Iberdrola-specific Outage and Restore reports.
    //
    // ****************************************************************************************************************
    // These reports are traditionally XML-based - this is the format the Iberdrola headend system (STG) is expecting.
    // However, the reports exchanged between E4S applications are in JSON format, per Web Things standard.
    // ****************************************************************************************************************
    private static final Logger logger= LogManager.getLogger(ReportGenerator.class);

    /** Generates an outage report */
    public void generateOutageReport() {
        // TODO: implement JSON report generation here (need a new report IDs "Sxx" for outage)
        // TODO: Use JSON primitives to produce the report as a JSON object
        // it is expected that the outage report will contain, at a minimum:
        //   1. An Iberdrola report header (see the other SReports for examples)
        //   2. Time/date the outage started
        //   3. The values of T1, T2, M1 and P1 at the time of outage (from settings)
        //   4. A hierarchical list of equipment affected (the whole substation?  One feeder?  All phases?  Which meters?)
        //   5. The equipment should include a "type" (feeder, phase, meter, etc.) and the (asset) id.
        //   6. The geographic region affected (TODO: can we draw a geo-border around the outage area?  This is a
        //       nice-to-have feature but not essential)

        logger.log(Level.INFO,"Outage report gets built here");
    }

    /** Generates a restoration report */
    public void generateRestorationReport() {
        // TODO: implement JSON report generation here (need new report ID "Syy" for restore)
        // The restoration report should contain:
        //   1. An Iberdrola report header (see the other SReports for examples)
        //   2. Time outage started/ended and duration
        //   3. Estimate of the number of customers affected by the outage

        logger.log(Level.INFO,"Restoration report goes here");
    }
}
