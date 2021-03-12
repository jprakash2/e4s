package com.landisgyr.e4s.omcore;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.landisgyr.e4s.context.Context;
import com.landisgyr.e4s.iberdrola.ReportUpdater;
import com.landisgyr.e4s.pubSub.PubSubClient;
import static java.lang.System.*;
import java.io.FileInputStream;

/** The OM application state machine (FSM), timers and main() */
public class OMNode extends TimerTask {
    public enum OMState {IDLE, COLLECT, ANALYZE_VERIFY};

    public static Context context;
    public static PubSubClient pubSub;

    public static OMState omState;
    public static OMTimer t1Timer;
    public static OMTimer t2Timer;
    private static final Logger logger= LogManager.getLogger(OMNode.class);
    
	/*
	 * static { Properties props = new Properties(); try { props.load(new
	 * FileInputStream("C:\\E4STest\\OMSettings\\log4j.properties"));
	 * PropertyConfigurator.configure(props); } catch (Exception e) {
	 * e.printStackTrace(System.out); } }
	 */

    /** Outage Management main */
    public static void main(String[] args) {
        initOMApp();

        // sample run for a fixed number of iterations
        // TODO: should be while(true) after unit testing complete
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(Settings.getFsmDelaymS());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // announce start of processing
            logger.log(Level.INFO,"Pass " + (i+1) + "  " + LocalDateTime.now().toLocalTime());
            PubSubClient.pubSubEventQueue.dumpEventQueue();

            // run one cycle of the FSM
            runFSM();
        }
        out.println("Done");
        exit(0);

    }

    @Override
   	public void run() {
   		logger.info("======");
   	}
    /** Initializes the OM application */
    public static void initOMApp() {
        // import the latest settings
        SettingsUpdater.updateSettings();

        // create a context object to hold all state information
        context = new Context();

        // setup PubSub object, connect to local MQTT broker (Mosquitto)
        pubSub = new PubSubClient();

        // TODO: There are some steps missing here, which will eventually need to be added.
        //  1. Need to register our Thing Description (TD) with the WebThings directory agent
        //  2. Need to "discover" the other 3 apps as "Things" in the WebThings directory
        //     The MQTT topics, alarms, events will come from the TDs of Topo, LVS and DCU "Things".
        //     But as of Feb 2021, it is looking like the other 3 vendors will not implement this
        //     by end of POC phase 2.   For now, hard-code the MQTT topics (they could also be in
        //     settings file)

        // subscribe to topics of interest, which are "alarms" (and possibly "events") from LVS and DCU
        // We will not get any alarms from Topo - it merely produces a scheduled report every night
        // We may get an "event" from Topo when the report is ready.
        pubSub.subscribe(Settings.getMqttTopicLVSAlarm());
        pubSub.subscribe(Settings.getMqttTopicDCAlarm());
        // TODO: may need to subscribe to an event from Topo which says the new nightly report is ready

        // initial FSM state
        omState = OMState.IDLE;

        // timer callback for T1 timer
        TimerTask timerTaskT1 = new TimerTask() {
            public void run() {
                logger.log(Level.INFO,"====> T1 timer has expired!");
                t1Timer.timerState = OMTimer.TimerState.EXPIRED;
            }
        };

        // timer callback for T2 timer
        TimerTask timerTaskT2 = new TimerTask() {
            public void run() {
                logger.log(Level.INFO,"====> T2 timer expired!");
                t2Timer.timerState = OMTimer.TimerState.EXPIRED;
            }
        };

        // setup the timers with their default values
        // the getActiveTx() method returns either the regular or storm value, depending
        // on whether stormMode is active.
        t1Timer = new OMTimer("T1", Settings.getActiveT1(), timerTaskT1);
        t2Timer = new OMTimer("T2", Settings.getActiveT2(), timerTaskT2);


        // watch for any changes in the SReports directory
        ReportUpdater.registerFileWatcher(Settings.getSReportDir());

        // watch for any changes in the OMSettings directory
        SettingsUpdater.registerFileWatcher("C:/E4STest/OMSettings");

        logger.log(Level.INFO,"OM initialization is complete!\r\n");
    }



    /** Single-step the OM FSM (each method invocation = a single, discrete FSM step) */
    public static void runFSM() {
        // logger.log(Level.INFO,"Updating context; epochSecond: " + Instant.now().getEpochSecond());
        context.update();

        if (Settings.getAlgorithmOperation()) {
            switch (omState) {
                case IDLE:
                    logger.log(Level.INFO,"FSM is in IDLE state");
                    // TODO: implement this logic
                    // Any outage event takes us to the COLLECT state; start the T1 timer
                    break;

                case COLLECT:
                    logger.log(Level.INFO,"FSM is in COLLECT state");
                    // TODO: implement this logic
                    // if additional outage events happen, just queue them up
                    // if restore events happen, they "cancel" outage events on the queue -- queue these up, too
                    // when T1 expires {
                    //    if there are "outstanding" outage events which have not been canceled by a "restore", then return to IDLE
                    //    else go to ANALYZE_VERIFY and start the T2 timer
                    // }
                    break;

                case ANALYZE_VERIFY:
                    logger.log(Level.INFO,"FSM is in ANALYZE_VERIFY state");
                    // TODO: implement this logic
                    // if the P1 option in Settings is true (analyze scope of outage) then verity V, I in all circuits
                    // if autoPingRequest in Settings is true then ping up to M1 meters
                    // when T2 expires {
                    //     if there are anomolies (outstanding events, abnormal V, I in feeder(s), meters not responding to pings) then ReportGenerator.generateOutageReport();
                    // }
                    // while (anomolies exist) stay in ANALYZE_VERIFY state;
                    // if (no anomolies exist) {
                    //      ReportGenerator.generateRestoreReport();
                    //      go to IDLE state;
                    // }
                    break;

                default:
                    logger.log(Level.INFO,"Invalid omState " + omState);
            }
        } else {
            // algorithm is disabled from settings
            logger.log(Level.INFO,"OM is disabled");
        }

        logger.log(Level.INFO," ");
    }


    /** Analyzes the scope of a potential outage */
    public static void analyzeOutageScope() {
        // analyze scope of outage, then issue an outage report
        // There are no specific Iberdrola requirements for the POC regarding the content of the report.
        // It is reasonable to assume the report should contain the time/date, the number of feeders affected
        // in the substation, the number of meters affected.  Additionally, if we can use the topology information
        // to help determine a geographic boundary, that would be desirable.
        // TODO: Implement this
   }


    /** Confirms restoration of power after an outage */
    public static void confirmRestoration() {
        // analyze restoration, then issue restoration report
        // There are no specific Iberdrola requirements for the POC regarding the content of this report.
        // It is reasonable to assume the report should include the time/date, the duration of the outage,
        // the equipment affected, and possibly estimated number of customers affected.
        // TODO: Implement this
    }
}
