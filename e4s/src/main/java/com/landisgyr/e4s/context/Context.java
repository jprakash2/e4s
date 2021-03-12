package com.landisgyr.e4s.context;

import com.landisgyr.e4s.iberdrola.ReportUpdater;
import com.landisgyr.e4s.pubSub.PubSubUpdater;
import com.landisgyr.e4s.omcore.SettingsUpdater;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Context {
    /** This object maintains the latest known state of all external "real world"
        variables associated with the substation and its connected equipment,
        including DCU, LVSupervisor, feeders, meters and topology.
        The context is updated through the use of registered updateContext() "callback",
        methods, ensuring synchronized, well-controlled updates of the context.   The context should
        only be changed (i.e. written) by registered updateContext() methods.

        All the "real-world" data needed for the OM app should be contained within the
        substation object. This object summarizes the entire state of the external world
        from the viewpoint of the Outage Management app.
     */
    public Substation substation;
    private static final Logger logger= LogManager.getLogger(Substation.class);
    // This is the list of registered updaters for the context.  The updaters are called
    // sequentially, from first to last.  Each updater has an opportunity to write to the
    // context during it's turn.
    public ArrayList<ContextInterface> contextUpdaters = new ArrayList<>();

    /** Constructor */
    public Context() {
        logger.log(Level.INFO,"Creating OM context");
        substation = new Substation();

        // these are the context updaters for OM
        registerContextUpdaterInterface(new SettingsUpdater());
        registerContextUpdaterInterface(new PubSubUpdater());
        registerContextUpdaterInterface(new ReportUpdater());
    }


    /**  Registers a context updater */
    public void registerContextUpdaterInterface(ContextInterface contextInterface) {
        contextUpdaters.add(contextInterface);
    }


    /** Asks all registered context updaters to make any changes to the context
        based on information they may have received, such as alarms or reports.
     */
    public void update() {
        // updates the context by calling a list of updaters
        for (ContextInterface cui: contextUpdaters) {
            cui.updateContext();
        }
    }
}
