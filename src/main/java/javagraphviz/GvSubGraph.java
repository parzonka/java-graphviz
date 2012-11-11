package javagraphviz;

public class GvSubGraph extends GvGraph {

    public GvSubGraph(String name) {
	super("subgraph", "cluster_" + name);
    }

}
