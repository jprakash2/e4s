package com.landisgyr.e4s.omcore;

import com.landisgyr.e4s.pubSub.JSONAnchor;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.jdbc.JDBCAppender;
import org.json.JSONObject;

/**
 * Methods to import and access all OM settings (JSON format)
 */
public class Settings {
    // the settings.json file & pathname are (obviously) not in the settings.json file
    public static final String settingsFilePath = "C:/E4STest/OMSettings/";
    public static final String settingsFileName = "settings.json";

    // holds JSONObject used to parse settings
    private static JSONAnchor jsonSettingsAnchor;
    private static final Logger logger= LogManager.getLogger(Settings.class);
    /**
     * Imports settings from settings.json file (JSON format)
     * @param settingsPathAndFile - the path/file to import settings from
     */
    public static void importSettings(String settingsPathAndFile) {
        jsonSettingsAnchor = new JSONAnchor();
        jsonSettingsAnchor.jsonFileToObject(settingsFilePath + settingsFileName);
    }


    /**
     * Retrieves the value of a key from the named section of settings.json
     * @param section - the section of settings.json to be searched
     * @param key - the key to search for in the named section
     * @return the value of the property as a String, or null if not found
     */
    public static String getSettingAsString(String section, String key) {
        String retValue = null;

        if ((jsonSettingsAnchor != null) && (jsonSettingsAnchor.jo != null)) {
            if (jsonSettingsAnchor.jo.has(section)) {
                JSONObject sectionObj = jsonSettingsAnchor.jo.getJSONObject(section);
                if (sectionObj.has(key)) {
                    retValue = sectionObj.getString(key);
                } else {
                    logger.log(Level.INFO,"getSettingsAsString(): key " + key + " not found in section " + section);
                }

            } else {
                logger.log(Level.INFO,"getSettingAsString: section " + section + " not found");
            }
        } else {
            logger.log(Level.INFO,"jsonSettingsAnchor or jsonSettingsAnchor.jo is null");
        }

        return retValue;
    }


    /**
     * Retrieves the value of a key from the named section of settings.json
     * @param section - the section of settings.json to be searched
     * @param key - the key to search for in the named section
     * @return the value of the property as a Boolean, or null if not found
     */
    public static Boolean getSettingAsBoolean(String section, String key) {
        Boolean retValue = null;

        if ((jsonSettingsAnchor != null) && (jsonSettingsAnchor.jo != null)) {
            if (jsonSettingsAnchor.jo.has(section)) {
                JSONObject sectionObj = jsonSettingsAnchor.jo.getJSONObject(section);
                if (sectionObj.has(key)) {
                    retValue = sectionObj.getBoolean(key);
                } else {
                    logger.log(Level.INFO,"getSettingsAsBoolean(): key " + key + " not found in section " + section);
                }

            } else {
                logger.log(Level.INFO,"getSettingsAsBoolean: section " + section + " not found");
            }
        } else {
            logger.log(Level.INFO,"jsonSettingsAnchor or jsonSettingsAnchor.jo is null");
        }

        return retValue;
    }


    /**
     * Retrieves the value of a key from the named section of settings.json
     * @param section - the section of settings.json to be searched
     * @param key - the key to search for in the named section
     * @return the value of the property as an Integer, or null if not found
     */
    public static Integer getSettingAsInteger(String section, String key) {
        Integer retValue = null;

        if ((jsonSettingsAnchor != null) && (jsonSettingsAnchor.jo != null)) {
            if (jsonSettingsAnchor.jo.has(section)) {
                JSONObject sectionObj = jsonSettingsAnchor.jo.getJSONObject(section);
                if (sectionObj.has(key)) {
                    retValue = new Integer(sectionObj.getInt(key));
                } else {
                    logger.log(Level.INFO,"getSettingsAsInteger(): key " + key + " not found in section " + section);
                }

            } else {
                logger.log(Level.INFO,"getSettingsAsInteger: section " + section + " not found");
            }
        } else {
            logger.log(Level.INFO,"jsonSettingsAnchor or jsonSettingsAnchor.jo is null");
        }

        return retValue;
    }


    /**
     * Retrieves the value of a key from the named section of settings.json
     * @param section - the section of settings.json to be searched
     * @param key - the key to search for in the named section
     * @return the value of the property as a Double, or null if not found
     */
    public static Double getSettingAsDouble(String section, String key) {
        Double retValue = null;

        if ((jsonSettingsAnchor != null) && (jsonSettingsAnchor.jo != null)) {
            if (jsonSettingsAnchor.jo.has(section)) {
                JSONObject sectionObj = jsonSettingsAnchor.jo.getJSONObject(section);
                if (sectionObj.has(key)) {
                    retValue = sectionObj.getDouble(key);
                } else {
                    logger.log(Level.INFO,"getSettingsAsDouble(): key " + key + " not found in section " + section);
                }

            } else {
                logger.log(Level.INFO,"getSettingsAsDouble: section " + section + " not found");
            }
        } else {
            logger.log(Level.INFO,"jsonSettingsAnchor or jsonSettingsAnchor.jo is null");
        }

        return retValue;
    }


    public static String getSReportDir() {
        return getSettingAsString("filesystem", "sReportDir");
    }
    
    public static String getOMLogPropertyPath() {
    	return getSettingAsString("logging","OMLogPropertyPath");
    	}
    
	
	  public static String getOmlogDir() {
		  return getSettingAsString("logging","OMlogDir"); 
		  }
	 


    public static String getMqttBrokerURL() {
        return getSettingAsString("mqtt", "mqttBrokerURL");
    }


    public static String getMqttClientID() {
        return getSettingAsString("mqtt", "mqttClientID");
    }


    public static String getMqttTopicLVSAlarm() {
        return getSettingAsString("mqtt", "mqttTopicLVSAlarm");
    }


    public static String getMqttTopicDCAlarm() {
        return getSettingAsString("mqtt", "mqttTopicDCAlarm");
    }


    public static String getMqttTopicOutage() {
        return getSettingAsString("mqtt","mqttTopicOutage");
    }


    public static String getMqttTopicRestore() {
        return getSettingAsString("mqtt","mqttTopicRestore");
    }


    public static Boolean getStormMode() {
       return getSettingAsBoolean("outage","stormMode");
    }


    public static Boolean getAlgorithmOperation() {
        return getSettingAsBoolean("outage","algorithmOperation");
    }

    public static Boolean getDBPersistenceLoging() {
        return getSettingAsBoolean("logging","isPersistenceEnabled");
    }

    public static Boolean getAutoPingRequest() {
        return getSettingAsBoolean("outage", "autoPingRequest");
    }


    public static Integer getFsmDelaymS() {
        return getSettingAsInteger("outage", "fsmDelaymS");
    }


    /** Returns the T1 value appropriate for current mode */
    public static Integer getActiveT1() {
        return getSettingAsInteger("outage", (getStormMode() ? "T1Storm" : "T1NoStorm"));
    }

    /** Returns the T2 value appropriate for current mode */
    public static Integer getActiveT2() {
        return getSettingAsInteger("outage", (getStormMode() ? "T2Storm" : "T2NoStorm"));
    }

    /** Returns the P1 value appropriate for current mode */
    public static Boolean getActiveP1() {
        return getSettingAsBoolean("outage", (getStormMode() ? "P1Storm" : "P1NoStorm"));
    }

    /** Returns the M1 value appropriate for current mode */
    public static Integer getActiveM1() {
        return getSettingAsInteger("outage", (getStormMode() ? "M1Storm" : "M1NoStorm"));
    }

    public static Boolean getLogging() {
        return getSettingAsBoolean("logging", "enabled");
    }
    
    public static void updateLoggerManager() {
    	if (!getDBPersistenceLoging()) {
    	org.apache.log4j.Logger logger = org.apache.log4j.LogManager.getRootLogger();
    	JDBCAppender appender = (JDBCAppender) logger.getAppender("jdbc");
    	logger.removeAppender(appender);
    	} else {
    	setLoggerProperty();
    	}
    	}

    	public static Boolean getPersistenceEnabled() {
    	return getSettingAsBoolean("logging", "isPersistenceEnabled");
    	}


    /** Lists the current settings and their values */
    public static void dumpSettings() {
        logger.log(Level.INFO,"");
        logger.log(Level.INFO,"=================== OM SETTINGS ====================");
        logger.log(Level.INFO,"sReportDirectory  : " + getSReportDir());
        logger.log(Level.INFO,"sReportDirectory  : " + getOmlogDir());
        logger.log(Level.INFO,"fsmDelaymS        : " + getFsmDelaymS());
        logger.log(Level.INFO,"mqttBrokerURL     : " + getMqttBrokerURL());
        logger.log(Level.INFO,"mqttClientID      : " + getMqttClientID());
        logger.log(Level.INFO,"mqttTopicLVSAlarm : " + getMqttTopicLVSAlarm());
        logger.log(Level.INFO,"mqttTopicDCAlarm  : " + getMqttTopicDCAlarm());
        logger.log(Level.INFO,"mqttTopicOutage   : " + getMqttTopicOutage());
        logger.log(Level.INFO,"mqttTopicRestore  : " + getMqttTopicRestore());
        logger.log(Level.INFO,"algorithmOperation: " + getAlgorithmOperation());
        logger.log(Level.INFO,"autoPingRequest   : " + getAutoPingRequest());
        logger.log(Level.INFO,"stormMode         : " + getStormMode());
        logger.log(Level.INFO,"T1                : " + getActiveT1());
        logger.log(Level.INFO,"T2                : " + getActiveT2());
        logger.log(Level.INFO,"P1                : " + getActiveP1());
        logger.log(Level.INFO,"M1                : " + getActiveM1());
        logger.log(Level.INFO,"logging           : " + getLogging());
        logger.log(Level.INFO,"logging           : " + getDBPersistenceLoging());
        logger.log(Level.INFO,"====================================================");
    }
    
    /** setting logger properties  */
    public static void setLoggerProperty() {
    	Properties props = new Properties();
    	try {
    	props.load(new FileInputStream(getOMLogPropertyPath()));
    	PropertyConfigurator.configure(props);
    	} catch (Exception e) {
    	e.printStackTrace(System.out);
    	}
    
    	}

}
