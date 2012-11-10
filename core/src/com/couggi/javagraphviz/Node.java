package com.couggi.javagraphviz;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * the Node Component of graphviz tools.
 * 
 * @author Everton Cardoso
 *
 */
public class Node implements Component {

	private Graph graph;
	private String name;
	private Map<String, String> attrs;
	
	
	/**
	 * node default.
	 */
	private Node(String name) { 
		this.name = name;
		this.attrs = new HashMap<String, String>();
	}
	
	/**
	 * create a node with name
	 */
	public Node(String name, Graph graph) {
		this(name, name, graph);
	}
	
	public Node(String label, String id, Graph graph) {
		this(id);
		this.graph = graph;
		this.attrs.put("label", label);
	}
	
	/* 
	 * @see net.javagraphviz.Component#attribute(java.lang.String)
	 */
	@Override
	public String attr(String name) {
		return this.attrs.get(name);
	}

	/* 
	 * @see net.javagraphviz.Component#attributes()
	 */
	@Override
	public Map<String, String> getAttributes() {
		return this.attrs;
	}
	
	 @Override
	    public void setAttribute(String attribute, String value) {
		this.attrs.put(attribute, value);
	    }
	
	/* 
	 * @see net.javagraphviz.Component#name()
	 */
	public String name() {
		return this.name;
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
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String output() {
		
		StringBuffer xOut = new StringBuffer(this.name);
	    StringBuffer xAttr = new StringBuffer("");
	    String xSeparator = "";
	    
	    for (Entry<String, String> attrs : this.attrs.entrySet()) {   
		      if  ("html".equals(attrs.getKey())) {
			      xAttr.append(xSeparator + "label = " + attrs.getValue());
		      }else {
	          xAttr.append(xSeparator + attrs.getKey() + " = " + attrs.getValue());
		      }
	        xSeparator = ", ";
	    }
	      if (xAttr.length() > 0) {
	        xOut.append(" [" + xAttr.toString() + "]");
			}
	      xOut.append(";");

	      return(xOut.toString());
		
	}
	
	public Graph graph() { 
		return graph;
	}

}
