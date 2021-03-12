package com.landisgyr.e4s.pubSub;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.time.LocalDateTime;
import java.util.Date;

/** Information about an incoming MQTT publication (a message received at OM) */
public class PubSubEvent {
    public String topic;                    // MQTT topic
    public MqttMessage message;             // MQTT message, including content of publication
    public LocalDateTime timestamp;         // when the message was received

    /** Constructor */
    public PubSubEvent(String topic, MqttMessage message) {
        this.topic = topic;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}
