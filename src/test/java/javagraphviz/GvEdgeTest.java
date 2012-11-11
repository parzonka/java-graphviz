package javagraphviz;

import static javagraphviz.GvEdge.connect;
import static org.junit.Assert.assertEquals;
import javagraphviz.GvEdge;
import javagraphviz.GvGraph;
import javagraphviz.GvNode;

import org.junit.Before;
import org.junit.Test;


public class GvEdgeTest {

    GvEdge edge;

    @Before
    public void onSetUp() {
	GvGraph graph = GvGraph.createDigraph("test");
	edge = new GvEdge(new GvNode("nodeA", graph), new GvNode("nodeB", graph), graph);
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
	GvGraph graph = GvGraph.createDigraph("test");
	GvNode a = graph.getNode();
	GvNode b = graph.getNode();
	edge = connect(a, b);

	assertEquals(a, edge.getStartNode());
	assertEquals(b, edge.getEndNode());
    }

}
