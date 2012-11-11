package javagraphviz;

import static javagraphviz.Edge.connect;
import static org.junit.Assert.assertEquals;
import javagraphviz.Edge;
import javagraphviz.Graph;
import javagraphviz.Node;

import org.junit.Before;
import org.junit.Test;


public class EdgeTest {

    Edge edge;

    @Before
    public void onSetUp() {
	Graph graph = Graph.createDigraph("test");
	edge = new Edge(new Node("nodeA", graph), new Node("nodeB", graph), graph);
    }

    @Test
    public void testOutput() {

	edge.setAttribute("shape", "folder");

	StringBuffer xAttr = new StringBuffer("");
	xAttr.append("shape" + " = " + "\"folder\"");
	StringBuffer xOut = new StringBuffer("nodeA -> nodeB [" + xAttr.toString() + "];");

	assertEquals(xOut.toString(), edge.getDescription());
    }

    public void staticConnect() {
	Graph graph = Graph.createDigraph("test");
	Node a = graph.getNode();
	Node b = graph.getNode();
	edge = connect(a, b);

	assertEquals(a, edge.getStartNode());
	assertEquals(b, edge.getEndNode());
    }

}
