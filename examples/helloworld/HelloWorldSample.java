package helloworld;

import com.couggi.javagraphviz.Digraph;
import com.couggi.javagraphviz.Edge;
import com.couggi.javagraphviz.GraphvizEngine;
import com.couggi.javagraphviz.Node;

public class HelloWorldSample {

    public static void main(String[] args) {

	// define a graph with the Digraph Type.
	Digraph graph = new Digraph("G");
	graph.setAttribute("rankdir", "LR");
	graph.getDefaultNode().setAttribute("shape", "record");
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
	// create a edge with hello node and world node.
	// create the Graphviz engine to the graph
	GraphvizEngine engine = new GraphvizEngine(graph);
	// define the file name of the output.
	engine.toFilePath("helloworld.png");
	// generate output.
	engine.output();

    }
}
