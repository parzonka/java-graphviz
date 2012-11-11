package javagraphviz.examples;

import javagraphviz.GvGraph;
import javagraphviz.GraphvizEngine;
import javagraphviz.GvNode;
import javagraphviz.GvSubGraph;

public class SubgraphExample {

    public static void main(String[] args) {

	GvSubGraph names = new GvSubGraph("names");
	names.setAttribute("label", "#names");
	GvSubGraph animals = new GvSubGraph("animals");
	animals.setAttribute("label", "#animals");

	// define a graph with the Graph Type.
	GvGraph graph = GvGraph.createDigraph("G");
	graph.addSubGraph(names);
	graph.addSubGraph(animals);

	GvNode hello = names.addNode("hello");
	GvNode world = names.addNode("world");

	GvNode everton = names.addNode("everton");
	GvNode cardoso = names.addNode("cardoso");
	GvNode montengero = names.addNode("montenegro");

	GvNode cat = animals.addNode("cat");
	GvNode dog = animals.addNode("dog");
	GvNode chicken = animals.addNode("chicken");

	names.addEdge(hello, world);
	names.addEdge(everton, cardoso);

	graph.addEdge(montengero, cat);

	animals.addEdge(chicken, dog);
	graph.addEdge(cat, world);
	graph.addEdge(chicken, hello);
	names.addEdge(cardoso, world);

	GraphvizEngine engine = new GraphvizEngine();
	engine.process(graph);

    }
}
