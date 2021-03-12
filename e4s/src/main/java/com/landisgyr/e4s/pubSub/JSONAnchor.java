package com.landisgyr.e4s.pubSub;

import com.landisgyr.e4s.iberdrola.XMLParser;
import com.landisgyr.e4s.omcore.SettingsUpdater;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * This class contains methods which parse and create JSON content.    This is useful for parsing
 * E4S MQTT messages, since these are in the WebThing format, which is in turn based on JSON.
 * E4S OM will be required to parse JSON-formatted SReports in POC phase 2.
 */
public class JSONAnchor {
    public JSONObject jo;
    private static final Logger logger= LogManager.getLogger(JSONAnchor.class);

    public JSONAnchor() {
        jo = null;
    }

    /**
     * Creates a JSON object from a .json file
     */
    public JSONObject jsonFileToObject(String path) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            jo = new JSONObject(content);
        } catch (Exception e) {
            logger.log(Level.INFO,"Cannot open file: " + path);
            e.printStackTrace();
        }
        return jo;
    }


    /**
     * Returns the specified String value from a JSONObject, or null if not found.
     * @param key - the JSON key to retrieve
     * @return JSON value as a String
     */
    public String getString(String key) {
        String result = null;

        if (this.jo != null) {
            result = this.jo.getString(key);
        }
        return result;
    }

    /**
     * Returns the specified Integer value from a JSONObject, or null if not found.
     * @param key - the JSON key to retrieve
     * @return JSON value as an Integer
     */
    public Integer getInt(String key) {
         Integer result = null;
         if (this.jo != null) {
             result = this.jo.getInt(key);
         }
         return result;
    }


    /**
     * Returns the specified Double value from a JSONObject, or null if not found.
     * @param key - the JSON key to retrieve
     * @return JSON value as a Double
     */
    public Double getDouble(String key) {
         Double result = null;
         if (this.jo != null) {
             result = this.jo.getDouble(key);
         }
        return result;
    }


    /**
     * Returns the specified Boolean value from a JSONObject, or null if not found.
     * @param key - the JSON key to retrieve
     * @return JSON value as a Boolean
     */
    public boolean getBoolean(String key) {
        Boolean result = null;
        if (this.jo != null) {
            result = this.jo.getBoolean(key);
        }
        return result;
    }


    /** Write JSON content */
    public void write() {

        // TODO: look into using the .escape() method to escape text containing special characters
        this.jo.put("name", "fred");
        this.jo.put("age", new Integer(20));
    }
}
