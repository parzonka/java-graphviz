package com.couggi.javagraphviz;

public class SubGraph extends Graph {

    public SubGraph(String name) {
	super("subgraph", "cluster_" + name);
    }

}
