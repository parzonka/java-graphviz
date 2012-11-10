package com.couggi.javagraphviz;

import java.util.Map;

/**
 * the graph components (node, edges, etc)
 * 
 * @author Everton Cardoso
 *
 */
public interface Component {

	/**
	 * return the attribute of the component.
	 * 
	 */
	String attr(String name);

	Map<String, String> getAttributes();
	
	void setAttribute(String attribute, String value);
	
	/**
	 * name of the componenet.
	 */
	String name();
	
	
	/**
	 * output the component structure 
	 */
	String output();
	
	
}
