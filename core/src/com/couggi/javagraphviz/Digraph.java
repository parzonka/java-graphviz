package com.couggi.javagraphviz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * the Digraph Component of graphviz tools.
 * 
 * @author Everton Cardoso
 * 
 */
public class Digraph extends Component implements Graph {

    private int idCount;

    /**
     * representation of general node attributes
     */
    private Node defaultNode;

    /**
     * representation of general node attributes
     */
    private Edge edgeDefault;

    /**
     * nodes of the graph
     */
    private Map<String, Node> nodes;

    /**
     * edges of the graph
     */
    private List<Edge> edges;

    private List<SubGraph> subGraphs;

    private boolean prefIdEqualsLabel;

    /**
     * create a Digraph with name.
     */
    public Digraph(String name) {
	super(name);
	this.defaultNode = new Node("___defaultNode___", this);
	this.edgeDefault = new Edge(defaultNode, defaultNode, this);
	this.nodes = new HashMap<String, Node>();
	this.edges = new ArrayList<Edge>();
	this.subGraphs = new ArrayList<SubGraph>();
    }

    /*
     * @see net.javagraphviz.Graph#getDefaultNode()
     */
    public Node getDefaultNode() {
	return this.defaultNode;
    }

    /*
     * @see net.javagraphviz.Graph#addNode(java.lang.String)
     */
    public Node addNode(String name) {
	String id = nodes.containsKey(name) ? name + idCount++ : name;
	Node node = new Node(name, id, this);
	nodes.put(id, node);
	return node;
    }

    /**
     * Retrieves a node with given id, or creates a node if the id is not known to this graph.
     * 
     * @param id
     * @return a node
     */
    public Node getNode(String id) {
	Node node = nodes.get(id);
	if (node == null) {
	    node = new Node(id, this);
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
    public Node getNode() {
	String id;
	do {
	    id = "node" + idCount++;
	} while (nodes.containsKey(id));
	return getNode(id);
    }

    /*
     * @see net.javagraphviz.Graph#addEdge(net.javagraphviz.Node, net.javagraphviz.Node)
     */
    public Edge addEdge(Node nodeFrom, Node nodeTo) {
	if (!containsNode(nodeFrom) || !containsNode(nodeFrom))
	    throw new IllegalArgumentException("nodes not found");
	Edge edge = new Edge(nodeFrom, nodeTo, this);
	edges.add(edge);
	return edge;
    }

    public boolean containsNode(Node node) {
	boolean contains = this.nodes().contains(node);
	if (!contains)
	    for (Graph graph : subGraphs) {
		contains = graph.containsNode(node);
		if (contains)
		    break;
	    }
	return contains;
    }

    /*
     * @see net.javagraphviz.Graph#edge()
     */
    public Edge getDefaultEdge() {
	return this.edgeDefault;
    }

    @Override
    public List<Edge> edges() {
	return new ArrayList<Edge>(this.edges);
    }

    @Override
    public List<Node> nodes() {
	return new ArrayList<Node>(this.nodes.values());
    }

    public List<SubGraph> subGraphs() {
	return this.subGraphs;
    }

    @Override
    public String getType() {
	return "digraph";
    }

    @Override
    public String output() {

	StringBuffer xDOTScript = new StringBuffer("");
	String xSeparator = "";
	StringBuffer xData = new StringBuffer("");

	// mount the graph attributes
	if (!getAttributes().isEmpty()) {
	    for (Entry<String, String> attr : getAttributes().entrySet()) {
		xData.append(xSeparator + attr.getKey() + " = " + attr.getValue());
		xSeparator = ", ";
	    }
	    xDOTScript.append(" graph [" + xData + "];");
	}

	// reset variables
	xSeparator = "";
	xData = new StringBuffer("");

	// mount the node attributes
	if (!this.getDefaultNode().getAttributes().isEmpty()) {
	    for (Entry<String, String> attribute : this.getDefaultNode().getAttributes().entrySet()) {
		xData.append(xSeparator + attribute.getKey() + " = " + attribute.getValue());
		xSeparator = ", ";
	    }
	    xDOTScript.append(" node [" + xData + "];");
	}

	// reset variables
	xSeparator = "";
	xData = new StringBuffer("");

	// mount the edge attributes
	if (!this.getDefaultNode().getAttributes().isEmpty()) {
	    for (Entry<String, String> attribute : this.getDefaultNode().getAttributes().entrySet()) {
		xData.append(xSeparator + attribute.getKey() + " = " + attribute.getValue());
		xSeparator = ", ";
	    }
	    xDOTScript.append(" edge [" + xData + "];");
	}

	// reset variables
	xSeparator = "";
	xData = new StringBuffer("");

	// mount the subgraph
	for (SubGraph subGraph : subGraphs) {
	    xDOTScript.append(xSeparator + subGraph.output());
	}

	// mount components output
	// nodes
	for (Component component : this.nodes()) {
	    xDOTScript.append(" " + component.output() + "");
	}
	// edges
	for (Component component : this.edges()) {
	    xDOTScript.append(" " + component.output() + "");
	}

	// structure final
	xDOTScript = new StringBuffer(this.getType()).append(" ").append(this.name()).append(" {").append(xDOTScript)
		.append("}");

	return (xDOTScript.toString());

    }

    public void addSubGraph(SubGraph graph) {
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
