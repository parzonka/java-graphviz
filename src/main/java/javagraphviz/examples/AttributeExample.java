package javagraphviz.examples;

import javagraphviz.GvEdge;
import javagraphviz.GvGraph;
import javagraphviz.GraphvizEngine;
import javagraphviz.GvNode;

public class AttributeExample {

    public static void main(String[] args) {

	// define a graph with the Graph Type.
	GvGraph graph = GvGraph.createDigraph("G");
	graph.setAttribute("rankdir", "LR");
	graph.setGlobalNodeAttribute("shape", "record");
	// create nodes with names
	GvNode hello = graph.getNode();
	hello.setFixedSize(true);
	hello.setHeight(0.8);
	hello.setWidth(0.6);
	hello.setLabel("foo");
	GvEdge edge = graph.addEdge(hello, hello);
	edge.setAttribute("taillabel", "Mp1x123");
	edge.setAttribute("fontsize", "7");
	edge.setAttribute("arrowtail", "none");
	edge.setAttribute("arrowhead", "none");
	edge.setAttribute("labeldistance", "1.0");
	edge.setAttribute("labelangle", "-60.0");
	// create the Graphviz engine to the graph
	GraphvizEngine engine = new GraphvizEngine();
	// generate output.
	engine.process(graph);

    }
}
