package com.landisgyr.e4s.omcore;

import com.landisgyr.e4s.context.ContextInterface;
import com.landisgyr.e4s.iberdrola.FileWatcher;

import java.nio.file.WatchEvent;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class SettingsUpdater implements ContextInterface {
    public static FileWatcher watcher;
    private static final Logger logger= LogManager.getLogger(SettingsUpdater.class);
    
    /** Registers a FileWatcher on the specified filePath */
    public static void registerFileWatcher (String filePath){
        watcher = new FileWatcher(filePath);
    }


    @Override
    public void updateContext() {
        // update context here, as appropriate
        //logger.log(Level.INFO,"SettingsUpdater.updateContext()");

        // check if settings file has been updated.  If so, open it, parse contents
        // and update context.settings
        List<WatchEvent<?>> events = watcher.getEvents();

        if ((events != null) && (events.size() > 0)) {
            for (WatchEvent<?> event : events) {
                logger.log(Level.INFO,"Settings event: " + event.kind() + "; File: " + event.context());
                // TODO: uncomment line below to test the auto-update feature for settings
                // updateSettings();
                modifySettings();
            }
        } else {
            // logger.log(Level.INFO,"No changes to settings.json");
        }

    }

    /** Update settings by reading/parsing Settings file */
    public static void updateSettings() {
        Settings.importSettings(Settings.settingsFilePath + Settings.settingsFileName);
        Settings.setLoggerProperty();
        Settings.dumpSettings();
    }
    public static void modifySettings() {
    	Settings.importSettings(Settings.settingsFilePath + Settings.settingsFileName);
    	Settings.updateLoggerManager();
    	Settings.dumpSettings();
    	}
}
