package helloworld;

import com.couggi.javagraphviz.Digraph;
import com.couggi.javagraphviz.GraphvizEngine;
import com.couggi.javagraphviz.Node;
import com.couggi.javagraphviz.SubGraph;

public class UsingLayers {

public static void main(String[] args) {

		SubGraph names = new SubGraph("names");
		names.setAttribute("label", "#names");
		SubGraph animals = new SubGraph("animals");
		animals.setAttribute("label", "#animals");

		// define a graph with the Digraph Type.
		Digraph graph = new Digraph("G");
		graph.addSubGraph(names);
		graph.addSubGraph(animals);

		Node hello = names.addNode("hello");
		Node world = names.addNode("world");

		Node everton = names.addNode("everton");
		Node cardoso = names.addNode("cardoso");
		Node montengero = names.addNode("montenegro");

		Node cat = animals.addNode("cat");
		Node dog = animals.addNode("dog");
		Node chicken = animals.addNode("chicken");

		names.addEdge(hello, world);
		names.addEdge(everton, cardoso);

		graph.addEdge(montengero, cat);

		animals.addEdge(chicken, dog);
		graph.addEdge(cat, world);
		graph.addEdge(chicken, hello);
		names.addEdge(cardoso, world);

		GraphvizEngine engine = new GraphvizEngine(graph);
		engine.toFilePath("helloworld.png");
		engine.output();

	}
}
