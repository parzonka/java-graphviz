package com.couggi.javagraphviz;

public class Edge implements Component {

    private final Graph graph;
    private final Node startNode;
    private final Node endNode;
    private final String name;
    private Attrs attrs;

    /**
     * Creates an edge between two nodes in the given graph.
     * 
     * @param startNode
     * @param endNode
     * @param graph
     */
    public Edge(Node startNode, Node endNode, Graph graph) {
	this.name = startNode.name() + " -> " + endNode.name();
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

    /*
     * @see net.javagraphviz.Component#name()
     */
    public String name() {
	return this.name;
    }

    /*
     * @see net.javagraphviz.Component#attribute(java.lang.String)
     */
    @Override
    public Attr attr(String name) {
	return this.attrs.get(name);
    }

    /*
     * @see net.javagraphviz.Component#attributes()
     */
    @Override
    public Attrs attrs() {
	return this.attrs;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

    @Override
    public String output() {

	String xLink = " -> ";

	String xNodeNameOne = this.startNode.name();
	String xNodeNameTwo = this.endNode.name();

	StringBuffer xOut = new StringBuffer(xNodeNameOne + xLink + xNodeNameTwo);
	StringBuffer xAttr = new StringBuffer("");
	String xSeparator = "";

	for (Attr attrs : this.attrs.list()) {
	    xAttr.append(xSeparator + attrs.name() + " = " + attrs.value().toGv());
	    xSeparator = ", ";
	}
	if (xAttr.length() > 0) {
	    xOut.append(" [" + xAttr.toString() + "]");
	}
	xOut.append(";");

	return (xOut.toString());

    }

    public Graph getGraph() {
	return graph;
    }

}
