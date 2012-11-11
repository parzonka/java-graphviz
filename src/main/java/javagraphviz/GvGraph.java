package javagraphviz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GvGraph extends GvComponent {

    private final String graphType;
    private int idCount;
    private Map<String, String> globalNodeAttributes;
    private Map<String, String> edgeAttributes;
    private Map<String, GvNode> nodes;
    private List<GvEdge> edges;
    private List<GvSubGraph> subGraphs;
    private boolean prefIdEqualsLabel;

    protected GvGraph(String graphType, String name) {
	super(name);
	this.graphType = graphType;
	this.globalNodeAttributes = new HashMap<String, String>();
	this.edgeAttributes = new HashMap<String, String>();
	this.nodes = new HashMap<String, GvNode>();
	this.edges = new ArrayList<GvEdge>();
	this.subGraphs = new ArrayList<GvSubGraph>();
    }
    
    /**
     * Creates a new directed graph with the given name.
     * @param name
     * @return the digraph
     */
    public static GvGraph createDigraph(String name) {
	return new GvGraph("digraph", name);
    }

    public Map<String, String> getGlobalNodeAttributes() {
	return this.globalNodeAttributes;
    }

    public GvNode addNode(String name) {
	String id = nodes.containsKey(name) ? name + idCount++ : name;
	GvNode node = new GvNode(name, id, this);
	nodes.put(id, node);
	return node;
    }

    /**
     * Retrieves a node with given id, or creates a node if the id is not known to this graph.
     * 
     * @param id
     * @return a node
     */
    public GvNode getNode(String id) {
	GvNode node = nodes.get(id);
	if (node == null) {
	    node = new GvNode(id, this);
	    nodes.put(id, node);
	}
	if (prefIdEqualsLabel) {
	    node.getAttributes().put("label", id);
	}
	return node;
    }

    /**
     * Creates a node with auto generated id.
     * 
     * @return a node
     */
    public GvNode getNode() {
	String id;
	do {
	    id = "node" + idCount++;
	} while (nodes.containsKey(id));
	return getNode(id);
    }

    public GvEdge addEdge(GvNode nodeFrom, GvNode nodeTo) {
	if (!containsNode(nodeFrom) || !containsNode(nodeFrom))
	    throw new IllegalArgumentException("nodes not found");
	GvEdge edge = new GvEdge(nodeFrom, nodeTo, this);
	edges.add(edge);
	return edge;
    }

    public boolean containsNode(GvNode node) {
	boolean contains = this.getNodes().contains(node);
	if (!contains)
	    for (GvGraph graph : subGraphs) {
		contains = graph.containsNode(node);
		if (contains)
		    break;
	    }
	return contains;
    }

    public Map<String, String> getGlobalEdgeAttributes() {
	return this.edgeAttributes;
    }

    public void setGlobalEdgeAttribute(String attribute, String value) {
	this.edgeAttributes.put(attribute, value);
    }

    public void getGlobalEdgeAttributeValue(String attribute) {
	this.edgeAttributes.get(attribute);
    }

    public void getGlobalNodeAttributeValue(String attribute) {
	this.globalNodeAttributes.get(attribute);
    }

    public void setGlobalNodeAttribute(String attribute, String value) {
	this.globalNodeAttributes.put(attribute, value);
    }

    public List<GvEdge> getEdges() {
	return Collections.unmodifiableList(this.edges);
    }

    public List<GvNode> getNodes() {
	return Collections.unmodifiableList(new ArrayList<GvNode>(this.nodes.values()));
    }

    public List<GvSubGraph> subGraphs() {
	return this.subGraphs;
    }

    public String getType() {
	return graphType;
    }

    public String getDescription() {

	StringBuilder sb = new StringBuilder();
	
	sb.append(this.getType()).append(" ").append(this.getId()).append(" {");
	if (!getAttributes().isEmpty()) {
	    sb.append(" graph");
	    appendAttributes(sb, getAttributes());
	    sb.append(";");
	}
	if (!getGlobalNodeAttributes().isEmpty()) {
	    sb.append(" node");
	    appendAttributes(sb, getGlobalNodeAttributes());
	    sb.append(";");
	}
	if (!getGlobalEdgeAttributes().isEmpty()) {
	    sb.append(" edge");
	    appendAttributes(sb, getGlobalEdgeAttributes());
	    sb.append(";");
	}
	for (GvSubGraph subGraph : subGraphs) {
	    sb.append(" ").append(subGraph.getDescription());
	}
	for (GvComponent component : this.getNodes()) {
	    sb.append(" ").append(component.getDescription());
	}
	for (GvComponent component : this.getEdges()) {
	    sb.append(" ").append(component.getDescription());
	}
	sb.append("}");

	return (sb.toString());

    }

    public void addSubGraph(GvSubGraph graph) {
	this.subGraphs.add(graph);
    }

    /**
     * If set to <code>true</code>, the graphviz label of a node is set identical to the node id. Otherwise, the label
     * is set to the empty string, and can be set via attributes.
     * 
     * @param deriveNodeLabelFromId
     */
    public boolean isIdEqualsLabel() {
	return prefIdEqualsLabel;
    }

    /**
     * If set to <code>true</code>, the graphviz label of a node is set identical to the node id. Otherwise, the label
     * is set to the empty string, and can be set via attributes.
     * 
     * @param idEqualsLabel
     */
    public void setIdEqualsLabel(boolean idEqualsLabel) {
	this.prefIdEqualsLabel = idEqualsLabel;
    }

}
