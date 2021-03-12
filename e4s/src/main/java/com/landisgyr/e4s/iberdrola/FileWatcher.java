package com.landisgyr.e4s.iberdrola;

import com.landisgyr.e4s.context.Context;
import com.landisgyr.e4s.pubSub.PubSubEventQueue;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class FileWatcher {
    public  WatchService watchService;
    public  Path watchedPath;
    private static final Logger logger= LogManager.getLogger(FileWatcher.class);
    /**
     * Watches a particular directory on the file system for any changes/updates/new/deleted files.
     * We need to detect when the settings file is modified, and re-import it automatically if it changes.
     *
     * The "report" folder listed here is only for temporary testing purposes -- reports will arrive
     * (eventually) via MQTT.   But here, a "report" folder is watched for unit testing purposes --
     * if we drop a JSON report file into this watched folder, it can be automatically imported,
     * which is an easy way to test the report parsing code.
     *
     * As soon as OM can receive reports via MQTT, the need for watching a "report" folder will
     * go away.
     */
    public FileWatcher(String filePath) {
       try {
            watchService = FileSystems.getDefault().newWatchService();
            watchedPath = Paths.get(filePath);
            if (watchedPath != null) {
                //watchedPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);
                watchedPath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Returns a list of events (files created, deleted, modified) and associated file names */
    public List<WatchEvent<?>> getEvents() {
        WatchKey key;
        List<WatchEvent<?>> events = null;

        if ((key = watchService.poll()) != null) {
            events = key.pollEvents();
            key.reset();
        }

        if (events == null) logger.log(Level.INFO,"No changes detected in " + watchedPath);

        return events;
     }
}
