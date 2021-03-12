package com.landisgyr.e4s.webthing;

/**
 * Class placeholder for web services on E4S (http server)
 */
public class WebService {
    // TODO: It is believed as of Feb 2021 that we will need to request LVS reports via
    // a web service request to the LVS app.   This is because they are not planning to
    // develop their MQTT-based pub-sub interfaces fully in POC phase 2.   This will mean
    // the certain requests for reports from LVS may need to made here.
    // Also, the "legacy" format of those requests is XML.  So we may need to format
    // the request as XML.  However, the response (the report itself) will come back to
    // us over MQTT, and in JSON format.
}
