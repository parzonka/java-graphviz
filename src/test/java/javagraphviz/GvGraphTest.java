package javagraphviz;

import java.util.Arrays;

import javagraphviz.GvEdge;
import javagraphviz.GvGraph;
import javagraphviz.GvNode;
import javagraphviz.GvSubGraph;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class GvGraphTest {

    GvGraph graph;

    @Before
    public void onSetUp() {
	graph = GvGraph.createDigraph("finite_state_machine");
    }

    @Test
    public void addNode() {
	GvNode node = graph.addNode("nodeA");
	Assert.assertEquals(new GvNode("nodeA", graph), node);
    }

    @Test
    public void addEdge() {
	GvNode nodeFrom = graph.addNode("nodeFrom");
	GvNode nodeTo = graph.addNode("nodeTo");
	GvEdge edge = graph.addEdge(nodeFrom, nodeTo);
	Assert.assertEquals(new GvEdge(nodeFrom, nodeTo, graph), edge);
    }

    @Test
    public void multipleNodes() {
	GvNode a = graph.addNode("nodeA");
	GvNode b = graph.addNode("nodeA");
	graph.addEdge(a, b);

	StringBuffer xData = new StringBuffer();
	xData.append("nodeA [label = \"nodeA\"]; ");
	xData.append("nodeA0 [label = \"nodeA\"]; ");
	xData.append("nodeA -> nodeA0;");

	StringBuffer out = new StringBuffer("digraph finite_state_machine { ");
	out.append(xData).append("}");

	Assert.assertEquals(out.toString(), graph.getDescription());
    }

    @Test
    public void multipleEdges() {
	GvNode a = graph.addNode("nodeA");
	GvNode b = graph.addNode("nodeB");
	graph.addEdge(a, b);
	graph.addEdge(a, b);

	StringBuffer xData = new StringBuffer();
	xData.append("nodeB [label = \"nodeB\"]; ");
	xData.append("nodeA [label = \"nodeA\"]; ");
	xData.append("nodeA -> nodeB; ");
	xData.append("nodeA -> nodeB;");

	StringBuffer out = new StringBuffer("digraph finite_state_machine { ");
	out.append(xData).append("}");

	Assert.assertEquals(out.toString(), graph.getDescription());
    }

    @Test
    public void testOutput() {

	graph.setAttribute("bgcolor", "#000");
	graph.setGlobalNodeAttribute("shape", "doublecircle");
	graph.getGlobalEdgeAttributes().put("shape", "folder");
	GvNode nodeA = graph.addNode("nodeA");
	nodeA.setAttribute("fillcolor", "#fff");
	GvNode nodeB = graph.addNode("nodeB");
	nodeB.setAttribute("shape", "circle");
	GvEdge edge = graph.addEdge(nodeA, nodeB);
	edge.setAttribute("label", "change_label");
	GvSubGraph subGraph = new GvSubGraph("hello_world");
	GvNode hello = subGraph.addNode("hello");
	GvNode world = subGraph.addNode("world");
	subGraph.addEdge(hello, world);
	graph.addSubGraph(subGraph);

	StringBuffer xData = new StringBuffer("graph [bgcolor = \"#000\"];");
	xData.append(" node [shape = \"doublecircle\"];");
	xData.append(" edge [shape = \"folder\"];");
	xData.append(" subgraph cluster_hello_world {");
	xData.append(" hello [label = \"hello\"];");
	xData.append(" world [label = \"world\"];");
	xData.append(" hello -> world;");
	xData.append("}");
	xData.append(" nodeB [label = \"nodeB\", shape = \"circle\"];");
	xData.append(" nodeA [label = \"nodeA\", fillcolor = \"#fff\"];");
	xData.append(" nodeA -> nodeB [label = \"change_label\"];");

	StringBuffer out = new StringBuffer("digraph finite_state_machine { ");
	out.append(xData).append("}");

	Assert.assertEquals(out.toString(), graph.getDescription());
    }

    @Test
    public void testAddSubGraph() {
	GvSubGraph subGraph = new GvSubGraph("G");
	subGraph.setAttribute("label", "hello_world");
	GvNode hello = subGraph.addNode("hello");
	GvNode world = subGraph.addNode("world");
	subGraph.addEdge(hello, world);

	graph.addSubGraph(subGraph);

	Assert.assertEquals(Arrays.asList(subGraph), graph.subGraphs());
    }

    @Test
    public void testAddTwoSubGraphAndDefineExternalNodesRelationship() {
	GvSubGraph subGraphOne = new GvSubGraph("G");
	subGraphOne.setAttribute("label", "hello_world");
	GvNode hello = subGraphOne.addNode("hello");
	GvNode world = subGraphOne.addNode("world");
	subGraphOne.addEdge(hello, world);
	GvGraph subGraphTwo = GvGraph.createDigraph("G");
	GvNode cat = subGraphTwo.addNode("cat");
	GvNode dog = subGraphTwo.addNode("dog");
	subGraphTwo.addEdge(cat, dog);
	graph.addSubGraph(subGraphOne);

	GvEdge edge = graph.addEdge(hello, dog);

	Assert.assertEquals(subGraphOne, edge.getStartNode().getGraph());
	Assert.assertEquals(subGraphTwo, edge.getEndNode().getGraph());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateIfExistsNodesWhenDefineExternalNodesRelationship() {
	GvSubGraph subGraphOne = new GvSubGraph("G");
	subGraphOne.setAttribute("label", "hello_world");
	GvNode hello = subGraphOne.addNode("hello");
	GvNode world = subGraphOne.addNode("world");
	subGraphOne.addEdge(hello, world);
	GvSubGraph subGraphTwo = new GvSubGraph("G");
	GvNode cat = subGraphTwo.addNode("cat");
	GvNode dog = subGraphTwo.addNode("dog");
	subGraphTwo.addEdge(cat, dog);
	graph.addSubGraph(subGraphOne);
	GvGraph graphOrphan = GvGraph.createDigraph("G");
	GvNode nodeOphan = graphOrphan.addNode("node_orphan");

	graph.addEdge(nodeOphan, dog);
    }

}
