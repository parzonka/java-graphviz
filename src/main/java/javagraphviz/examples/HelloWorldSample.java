package javagraphviz.examples;

import javagraphviz.Edge;
import javagraphviz.Graph;
import javagraphviz.GraphvizEngine;
import javagraphviz.Node;

public class HelloWorldSample {

    public static void main(String[] args) {

	// define a graph with the Graph Type.
	Graph graph = Graph.createDigraph("foobarworld");
	graph.setAttribute("rankdir", "LR");
	graph.setGlobalNodeAttribute("shape", "record");
	// create nodes with names
	Node hello = graph.getNode("Hello");
	hello.setAttribute("fixedsize", "true");
	hello.setAttribute("width", "0.8");
	hello.setAttribute("height", "0.6");
	hello.setAttribute("label", "");
	Edge label = graph.addEdge(hello, hello);
	label.setAttribute("taillabel", "Mp1x123");
	label.setAttribute("fontsize", "7");
	label.setAttribute("arrowtail", "none");
	label.setAttribute("arrowhead", "none");
	label.setAttribute("labeldistance", "1.0");
	label.setAttribute("labelangle", "-60.0");
	// create the Graphviz engine to the graph
	GraphvizEngine engine = new GraphvizEngine();
	// generate output.
	engine.process(graph);

    }
}
