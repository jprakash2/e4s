package com.landisgyr.e4s.webthing;

import org.json.JSONArray;
//import org.mozilla.iot.webthing.Thing;
import org.mozilla.iot.webthing.Event;
import org.mozilla.iot.webthing.WebThingServer;

import java.util.Arrays;


/**
 * This class includes methods to support TD (Thing Description) objects.   Examples of Things include
 * OM, Topo, DCU and LVS.  Each Thing has a number of alarms, events, reports, etc.   The TD includes
 * meta-data about each thing, and how it can be used.
 *
 * WOT context and things directory is available via a WS-REST request to https:/directory/tds.
 * The methods in this package support REST-based http(s) requests.
 *
 * When an E4S application is deployed, a registration request must be POSTed to the WOT directory
 * service at "https://E4S-directory/tds/" including a JSON TD.    The response contains the
 * registration ticket, an idService in urn: format, unique for the application.
 *
 * When an E4S application is withdrawn from E4S, a request to unsubscribe must be sent to
 * "https://E4S-directry/tds/:idService" via a DELETE message.
 *
 * Whenever there is a change to the directory, the current TD will be published on the (public)
 * topic "E4S-directory/tds"
 */

public class Thing {
    // E4S MQTT topics are named according to web things schema

    /**
     * Make a web thing object
     */
    public static org.mozilla.iot.webthing.Thing makeThing()  {
        org.mozilla.iot.webthing.Thing thing = new org.mozilla.iot.webthing.Thing("urn:dev:ops:my-thing",
                "OM app",
                new JSONArray(Arrays.asList("E4SApp", "OMapp")),
                "E4S OM application");

        return thing;
    }

    // TODO: We will need additional methods here to:
    //  1. Publish our TD at startup, and ...
    //  2. Query the E4S-directory for the TDs of Topo, LVS and DCU

    /**
     * Starts a WebThings server on this node
     */
    public static void startWebThingsServer() {
        org.mozilla.iot.webthing.Thing thing = makeThing();
        WebThingServer server;

        try {
            // If adding more than one thing, use MultipleThings() with a name.
            // In the SingleThing() case, the thing's name will be broadcast.
            server = new WebThingServer(new WebThingServer.SingleThing(thing),
                    8888);

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    server.stop();
                }
            });

            server.start(false);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    /**
     * Signal an outage event (alarm?)
     */
    public static class OutageEvent extends Event {
        public OutageEvent(org.mozilla.iot.webthing.Thing thing, int data) {
            super(thing, "outage", data);
        }
    }

    /**
     * Signal a restore event
     */
    public static class RestoreEvent extends Event {
        public RestoreEvent(org.mozilla.iot.webthing.Thing thing, int data) { super(thing, "restore", data); }
    }

}
