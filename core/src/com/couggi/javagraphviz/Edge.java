package com.couggi.javagraphviz;

public class Edge implements Component {

    Graph graph;
    private Node nodeFrom;
    private Node nodeTo;
    private String name;
    private Attrs attrs;

    /**
     * Creates an edge between two nodes in the given graph.
     * 
     * @param nodeFrom
     * @param nodeTo
     * @param graph
     */
    public Edge(Node nodeFrom, Node nodeTo, Graph graph) {
	this.name = nodeFrom.name() + " -> " + nodeTo.name();
	this.graph = graph;
	this.nodeFrom = nodeFrom;
	this.nodeTo = nodeTo;
    }

    /**
     * node from
     */
    public Node from() {
	return nodeFrom;
    }

    /**
     * node to
     */
    public Node to() {
	return nodeTo;
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

	String xNodeNameOne = this.nodeFrom.name();
	String xNodeNameTwo = this.nodeTo.name();

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

}
