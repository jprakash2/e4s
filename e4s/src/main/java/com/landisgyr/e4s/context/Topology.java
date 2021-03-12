package com.landisgyr.e4s.context;

/** Information about the topological mapping of equipment */
public class Topology {

    // TODO: understand the topology S75 report, create appropriate data structure here
    // (tree? map?) to reflect topo data.   Expected that this will include asset nodes (with geo-coordiantes,
    // assetIDs, maybe asset types?) and a set of connections between these objects.

    // This information will be used in the assessment phase of an outage, in order to help determine
    // the exact scope of the outage (geographic area affected).
    public Topology() {
        // TODO: Study the S75 report, update structure here accordingly to capture relevant data
    }

}
