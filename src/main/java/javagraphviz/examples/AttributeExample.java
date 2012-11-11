package javagraphviz.examples;

import javagraphviz.Edge;
import javagraphviz.Graph;
import javagraphviz.GraphvizEngine;
import javagraphviz.Node;

public class AttributeExample {

    public static void main(String[] args) {

	// define a graph with the Graph Type.
	Graph graph = Graph.createDigraph("G");
	graph.setAttribute("rankdir", "LR");
	graph.setGlobalNodeAttribute("shape", "record");
	// create nodes with names
	Node hello = graph.getNode("Hello");
	hello.setAttribute("fixedsize", "true");
	hello.setAttribute("width", "0.8");
	hello.setAttribute("height", "0.6");
	hello.setAttribute("label", "");
	Edge edge = graph.addEdge(hello, hello);
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
