package com.landisgyr.e4s.context;

/** Class to represent a typical "EU-style" secondary substation */
public class Substation {
    public String id;                   // unique substation ID
    public String location;             // geo coordinates for this substation
    public LVSupervisor lvSupervisor;   // the LVS/RTU at this secondary substation
    public DCU dcu;                     // the DCU (PLC data concentrator) at this secondary substation
    public Topology topo;               // the topology between feeders and meters

    /** Constructor (no id) */
    public Substation() {
        id = "unknown";
        location = "unknown";
        lvSupervisor = new LVSupervisor();
        dcu = new DCU();
        topo = new Topology();
    }

    /** Constructor (id is known) */
    public Substation(String id) {
        this();
        this.id = id;
    }


    // a substation is healthy if the RTU & DCU are both healthy
    public boolean isHealthy() {
        return lvSupervisor.isHealthy() && dcu.isHealthy();
    }

}
