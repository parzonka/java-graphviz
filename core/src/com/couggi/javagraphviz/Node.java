package com.couggi.javagraphviz;

import java.util.Map.Entry;

public class Node extends Component {

    private Graph graph;

    /**
     * create a node with name
     */
    public Node(String name, Graph graph) {
	this(name, name, graph);
    }

    public Node(String label, String id, Graph graph) {
	super(id);
	this.graph = graph;
	setAttribute("label", label);
    }

    @Override
    public String output() {

	StringBuffer xOut = new StringBuffer(name());
	StringBuffer xAttr = new StringBuffer("");
	String xSeparator = "";

	for (Entry<String, String> attrs : getAttributes().entrySet()) {
	    if ("html".equals(attrs.getKey())) {
		xAttr.append(xSeparator + "label = " + attrs.getValue());
	    } else {
		xAttr.append(xSeparator + attrs.getKey() + " = \"" + attrs.getValue() + "\"");
	    }
	    xSeparator = ", ";
	}
	if (xAttr.length() > 0) {
	    xOut.append(" [" + xAttr.toString() + "]");
	}
	xOut.append(";");

	return (xOut.toString());

    }

    public Graph graph() {
	return graph;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((graph == null) ? 0 : graph.hashCode());
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
	Node other = (Node) obj;
	if (graph == null) {
	    if (other.graph != null)
		return false;
	} else if (!graph.equals(other.graph))
	    return false;
	return true;
    }

}
