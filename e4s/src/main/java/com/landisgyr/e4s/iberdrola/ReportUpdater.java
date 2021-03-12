package com.landisgyr.e4s.iberdrola;

import com.landisgyr.e4s.context.ContextInterface;
import com.landisgyr.e4s.omcore.Settings;
import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/** Monitor for new SReports and update the context as a result of parsing them. */
public class ReportUpdater implements ContextInterface {
    public static FileWatcher watcher;
    public static ArrayList<String> watchedReportIds = buildWatchedReportIds();
    private static final Logger logger= LogManager.getLogger(ReportUpdater.class);
    public static void registerFileWatcher(String filePath){
        watcher = new FileWatcher(filePath);
    }

    // these are the reports we are interested in monitoring
    //   PLC Data Concentrator Reports
    //     Power fail:               S01,S13     Group 1, Subgroup 10, Events 4,5,6 (last gasp on phases 1,2,3)
    //     First breath:             S01,S13     Group 1, Subgroup 10, Events 21,22,23 (first breath on phases 1,2,3)
    //     PRIME topology:           S20         List of managed meters (PRIME topology. mapping from DC to meters)
    //
    //   LVS reports
    //     Historic V, I profiles:   S64         (Average V, I at 5 minute intervals) in each Feeder
    //     V, I for all LV circuits: S71         Voltage and current in each Feeder
    //
    //   Topology reports
    //     Topology report:          S75         (local report produced by Topo app)

    /** Builds a watchedReports list (a specific set of reports to watch for) */
    public static ArrayList<String> buildWatchedReportIds() {
        ArrayList<String> wrl = new ArrayList<>();

        wrl.add("S01");
        wrl.add("S13");
        wrl.add("S20");
        wrl.add("S64");
        wrl.add("S71");
        wrl.add("S75");

        return wrl;
    }


    /** Checks to see whether a report name is on the watchedReports list */
    public boolean isWatchedReportID(String reportID) {
        boolean isWatched = false;

        for (String idRpt: watchedReportIds) {
            if (idRpt.toUpperCase().equals(reportID.toUpperCase())) isWatched = true;
        }

        return isWatched;
    }


    /** Updates the context as a result of parsing an SReport file */
    @Override
    public void updateContext() {
        // update context here, as appropriate
        // logger.log(Level.INFO,"ReportUpdater.updateContext()");
        
        // Check if any new reports are available.  If so, we need to parse them and import data needed by OM.
        List<WatchEvent<?>> events = watcher.getEvents();

        // parse the reports here
        if ((events != null) && (events.size() > 0)) {
            for (WatchEvent<?> event : events) {
                String idRpt = null;

                logger.log(Level.INFO,"Event kind: " + event.kind() + "; File: " + event.context());

                // Open this report and get the "Report" header (includes IdRpt, IdPet, Version)
                String reportPath = Settings.getSReportDir() + event.context();

                // TODO:  Note this example code parses an XML report.   Reports will be in JSON format.
                XMLParser xmlParser = new XMLParser(reportPath);
                SReport reportHeader = xmlParser.getReportHeader(reportPath, xmlParser);
                reportHeader.dumpReport();

                // is this report being watched?
                if (isWatchedReportID(reportHeader.idRpt)) {
                    logger.log(Level.INFO,"This report is on the watched list, and it changed");

                    // TODO: parse the remainder of the report, import relevant data to Context
                    // Since there are several report types, call a separate method to parse each type
                    switch(reportHeader.idRpt) {
                        case "S01":
                            // TODO: this example uses an XML report, but reports will be in JSON format
                            parseReportS01(xmlParser, reportHeader);
                            break;
                        case "S13":
                            break;
                        case "S20":
                            break;
                        case "S64":
                            break;
                        case "S71":
                            break;
                        case "S75":
                            break;
                        default:
                            logger.log(Level.INFO,"Unknown report type: " + reportHeader.idRpt);
                    }

                } else {
                    logger.log(Level.INFO,"This report is not being watched");
                }
            }
        } else {
            //logger.log(Level.INFO,"No changes to any SReports");
        }

    }

    // TODO: All report updaters below should parse JSON reports (not XML)

    /** Parses an Iberdrola "S01" report (basic instant values in meter), generated in
     *  response to "pinging" a meter.   This report is prepared by the DCU.
     * @param xmlParser
     * @param reportHeader
     */
    public void parseReportS01(XMLParser xmlParser, SReport reportHeader) {
        // TODO: implement S01 report parsing here
        // it is expected this will update the isHealthy attribute of the Meter
        // objects listed in the report
    }

    /** Parses an Iberdrola "S13" report (spontaneous meter events), spontaneously
     * generated when a meter loses power, for example.  This report is prepared by
     * the DCU.
     * @param xmlParser
     * @param reportHeader
     */
    public void parseReportS13(XMLParser xmlParser, SReport reportHeader) {
        // TODO: implement S13 report parsing here
        // it is expected this will update the isHealthy attribute of the Meter
        // objects listed in the report.
    }

    /** Parses an Iberdrola "S20" report (list of managed meters), generated in
     *  response to a request/demand for this report.  In this report, the DCU
     *  lists which meters are under it's management, ie. which meters are
     *  connected to the DCU in this secondary substation.  This report is prepared
     *  by the DCU.
     * @param xmlParser
     * @param reportHeader
     */
    public void parseReportS20(XMLParser xmlParser, SReport reportHeader) {
        // TODO: implement S20 report parsing here
        // it is expected that this report will be used to build / update the
        // ArrayList<Meter> of meters in the DCU object.
    }

    /** Parses an Iberdrola "S64" report (average/historical V, I profiles), generated
     * periodically (5 mins?) by the LVSupervisor.
     * @param xmlParser
     * @param reportHeader
     */
    public void parseReportS64(XMLParser xmlParser, SReport reportHeader) {
        // TODO: implement S64 report parsing here
        // it is expected that this report will be used to build/update the list of
        // LVLine and associated LVPhase objects owned by the LVSupervisor, and
        // will affect the avgV and avgI fields in the various LVPhase objects.
    }

    /** Parses an Iberdrola "S71" report (instantaneous V, I in each feeder), generated
     * in response to a request for the report (part of the analysis phase of the OM
     * state machine).
     * @param xmlParser
     * @param reportHeader
     */
    public void parseReportS71(XMLParser xmlParser, SReport reportHeader) {
        // TODO: implement S71 report parsing here
        // it is expected that this report will be used to update the LVPhase
        // objects (owned by LVLine objects within the LVSupervisor object).
        // Specifically, the instV, instI in each LVPhase should be updated.
    }

    /** Parses an Iberdrola "S75" report (topology), generated peridically
     * (once per day?) and updates the Topology object within the Context.
     * @param xmlParser
     * @param reportHeader
     */
    public void parseReportS75(XMLParser xmlParser, SReport reportHeader) {
        // TODO: implement S75 report parsing here
        // it is expected this report will be used to update the Topology
        // object
    }
}
