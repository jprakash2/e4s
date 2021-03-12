package com.landisgyr.e4s.pubSub;

import com.landisgyr.e4s.context.ContextInterface;
import com.landisgyr.e4s.omcore.Logger;

/** Provides a method to update the context as a result of incoming MQTT messages received */
public class PubSubUpdater implements ContextInterface {

    @Override
    public void updateContext() {
        // update context here, as appropriate
        // logger.log(Level.INFO,"PubSubUpdater.updateContext()");
        // TODO: Use specific DCU and LVS alarm messages to change context and request on-demand reports *after* appropriate timeouts
    }
}