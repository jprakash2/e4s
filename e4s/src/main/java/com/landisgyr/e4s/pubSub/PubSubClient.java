package com.landisgyr.e4s.pubSub;

import com.landisgyr.e4s.iberdrola.XMLParser;
// this is the MQTT Pub/Sub interface
import com.landisgyr.e4s.omcore.Settings;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


import java.util.ArrayList;

/** This class uses an MQTT client (Eclipse Paho MQTT v3-1.2.1) to
 * connect with a (local) MQTT broker.  It supports subscribing to MQTT
 * topics, receiving messages on a topic, and publishing messages to a topic.
 *
 * Notes from E4S Specification:
 * The E4S MQTT broker will be available on "mqtt://localMqttService:8883"
 * All clients must use user/password credentials in their connections. All
 * publications should comply with QoS = 2 parameter, for preventing duplicate
 * message delivery.
 */

public class PubSubClient implements MqttCallback {
    public static ArrayList <String> subscriptions;
    public String mqttBroker;
    public String clientID;
    public static MqttClient pahoClient;
    public static MemoryPersistence persistence;
    public static MqttConnectOptions connectOptions;
    public static PubSubEventQueue pubSubEventQueue;
    private static final Logger logger= LogManager.getLogger(PubSubClient.class);

    /** MQTT-based client to support publish-subscribe */
    public PubSubClient() {
        logger.log(Level.INFO,"Creating PubSub instance (Paho MQTT client)");
        subscriptions = new ArrayList<>();
        pubSubEventQueue = new PubSubEventQueue();
        if (Settings.getMqttBrokerURL() == null) logger.log(Level.INFO,"NULL mqttBrokerURL!");
        if (Settings.getMqttClientID() == null) logger.log(Level.INFO,"NULL mqttClientID");
        mqttBroker = Settings.getMqttBrokerURL();

        clientID = Settings.getMqttClientID();

        persistence = new MemoryPersistence();
        try {
            // create a Paho MQTT client
            pahoClient = new MqttClient(mqttBroker, clientID, persistence);
            connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);
            pahoClient.setCallback(this);
            logger.log(Level.INFO,"Connecting to MQTT broker");

            // TODO: assumes there is an MQTT broker running so we can connect ...
            // TODO: we will eventually need to reconnect if we disconnected,
            //       retry if connect fails, etc.
            pahoClient.connect(connectOptions);
            logger.log(Level.INFO,"Connected to broker");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /** Callback (from Paho thread) when the client has lost connection to this broker */
    public void connectionLost(Throwable arg0) {
        // TODO: need to implement this: try to reconnect to the broker
    }


    /** Callback (from Paho thread) when an outgoing publish operation is complete */
    public void deliveryComplete(IMqttDeliveryToken arg0) {
        // may not require any code here, but the interface demands this callback
    }


    /** Callback (from Paho thread) when a message arrives @ MQTT broker for a subscribed topic */
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // a new message arrived for a subscribed topic
        logger.log(Level.INFO,"MQTT message arrived");
        pubSubEventQueue.addEvent(new PubSubEvent(topic, message));
    }


    /** Subscribe to a topic */
    public void subscribe(String topic) {
        try {
            pahoClient.subscribe(topic);
            subscriptions.add(topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /** Unsubscribe from a topic */
    public void unsubscribe(String topic) {
        try {
            pahoClient.unsubscribe(topic);
            subscriptions.remove(topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /** Get a list of subscribed topics */
    public ArrayList<String> getSubscriptions() {

        return subscriptions;
    }


    /** Publish a message on a topic at specified quality of service (qos) */
    public void publish(String topic, String publication, int qos) {
        try {
            MqttMessage message = new MqttMessage(publication.getBytes());
            message.setQos(qos);
            pahoClient.publish(topic, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
