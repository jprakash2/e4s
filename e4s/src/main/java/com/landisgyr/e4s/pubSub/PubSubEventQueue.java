package com.landisgyr.e4s.pubSub;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/** This class provides methods to support an incoming Event queue
    for receiving incoming MQTT messages on subscribed topics */
public class PubSubEventQueue {
    private Queue<PubSubEvent> pubSubEventQ = new LinkedList<>();
    private static final Logger logger= LogManager.getLogger(PubSubEventQueue.class);

    /** Adds an event to the event queue */
    public void addEvent(PubSubEvent e) {

        pubSubEventQ.add(e);
    }

    /** Returns the number of events on the event queue */
    public int getEventCount() {

        return pubSubEventQ.size();
    }

    /** Pulls the next event off the queue */
    public PubSubEvent getNextEvent() {

        return pubSubEventQ.poll();
    }

    /** Peeks at the next event on the queue without removing it */
    public PubSubEvent peekNextEvent() {

        return pubSubEventQ.peek();
    }

    /** Returns whether the event queue is empty */
    public boolean isEmpty() {
        return pubSubEventQ.isEmpty();
    }


    /** Prints the event queue contents */
    public void dumpEventQueue() {
        if (pubSubEventQ.isEmpty()) {
            logger.log(Level.INFO,"EVENT QUEUE is empty");
        } else {
            logger.log(Level.INFO,"================================================== EVENT QUEUE ==================================================");
            for (PubSubEvent e : pubSubEventQ) {
                logger.log(Level.INFO,e.timestamp +  " topic: " + e.topic + "; message: " + new String(e.message.getPayload(), StandardCharsets.UTF_8));
            }
            logger.log(Level.INFO,"=================================================================================================================");
        }
    }
}
