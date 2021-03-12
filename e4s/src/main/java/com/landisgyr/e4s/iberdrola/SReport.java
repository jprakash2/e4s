package com.landisgyr.e4s.iberdrola;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.landisgyr.e4s.omcore.SettingsUpdater;

/** This class is used to hold (base) information about Iberdrola SReports */
public class SReport {

    // instance variables
    public String reportPath;       // TODO: This will go away once reports arrive via MQTT
    public String reportType;

    // included in each Report
    public String idRpt;            // id of report
    public String idPet;            // unique request identifier - used to match up request & response
    public String version;          // version # of the report format
    private static final Logger logger= LogManager.getLogger(SReport.class);

    public SReport(String reportPath) {
        this.reportPath = reportPath;
        this.reportType = "unknown";
        this.idRpt = "unknown";
        this.idPet = "unknown";
        this.version = "unknown";
    }

    public void dumpReport() {
        logger.log(Level.INFO,"====== SREPORT HEADER =======");
        logger.log(Level.INFO,"reportPath: " + reportPath);
        logger.log(Level.INFO,"reportType: " + reportType);
        logger.log(Level.INFO,"idRpt:    : " + idRpt);
        logger.log(Level.INFO,"idPet     : " + idPet);
        logger.log(Level.INFO,"version   : " + version);
        logger.log(Level.INFO,"=============================");
    }
}
