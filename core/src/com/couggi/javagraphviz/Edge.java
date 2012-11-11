package com.couggi.javagraphviz;

import java.util.Map.Entry;

public class Edge extends Component {

    private final Graph graph;
    private final Node startNode;
    private final Node endNode;

    /**
     * Creates an edge between two nodes in the given graph.
     * 
     * @param startNode
     * @param endNode
     * @param graph
     */
    public Edge(Node startNode, Node endNode, Graph graph) {
	super(startNode.name() + " -> " + endNode.name());
	this.graph = graph;
	this.startNode = startNode;
	this.endNode = endNode;
    }

    /**
     * Creates an edge between two nodes.
     * 
     * @param startNode
     * @param endNode
     * @return the created edge
     */
    public static Edge connect(Node startNode, Node endNode) {
	final Graph graph = startNode.graph();
	if (graph != endNode.graph()) {
	    throw new IllegalArgumentException("Nodes must be part of the same graph!");
	}
	return new Edge(startNode, endNode, graph);
    }

    public Node getStartNode() {
	return startNode;
    }

    public Node getEndNode() {
	return endNode;
    }

    @Override
    public String output() {

	String xLink = " -> ";

	String xNodeNameOne = this.startNode.name();
	String xNodeNameTwo = this.endNode.name();

	StringBuffer xOut = new StringBuffer(xNodeNameOne + xLink + xNodeNameTwo);
	StringBuffer xAttr = new StringBuffer("");
	String xSeparator = "";

	for (Entry<String, String> attribute : getAttributes().entrySet()) {
	    xAttr.append(xSeparator + attribute.getKey() + " = \"" + attribute.getValue() + "\"");
	    xSeparator = ", ";
	}
	if (xAttr.length() > 0) {
	    xOut.append(" [" + xAttr.toString() + "]");
	}
	xOut.append(";");

	return (xOut.toString());

    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((endNode == null) ? 0 : endNode.hashCode());
	result = prime * result + ((graph == null) ? 0 : graph.hashCode());
	result = prime * result + ((startNode == null) ? 0 : startNode.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Edge other = (Edge) obj;
	if (endNode == null) {
	    if (other.endNode != null)
		return false;
	} else if (!endNode.equals(other.endNode))
	    return false;
	if (graph == null) {
	    if (other.graph != null)
		return false;
	} else if (!graph.equals(other.graph))
	    return false;
	if (startNode == null) {
	    if (other.startNode != null)
		return false;
	} else if (!startNode.equals(other.startNode))
	    return false;
	return true;
    }

    public Graph getGraph() {
	return graph;
    }

}
