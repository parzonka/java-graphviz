package com.couggi.javagraphviz;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class DigraphTest {

    Graph graph;

    @Before
    public void onSetUp() {
	graph = new Graph("finite_state_machine");
    }

    @Test
    public void addNode() {
	Node node = graph.addNode("nodeA");
	Assert.assertEquals(new Node("nodeA", graph), node);
    }

    @Test
    public void addEdge() {
	Node nodeFrom = graph.addNode("nodeFrom");
	Node nodeTo = graph.addNode("nodeTo");
	Edge edge = graph.addEdge(nodeFrom, nodeTo);
	Assert.assertEquals(new Edge(nodeFrom, nodeTo, graph), edge);
    }

    @Test
    public void multipleNodes() {
	Node a = graph.addNode("nodeA");
	Node b = graph.addNode("nodeA");
	graph.addEdge(a, b);

	StringBuffer xData = new StringBuffer();
	xData.append("nodeA [label = \"nodeA\"]; ");
	xData.append("nodeA0 [label = \"nodeA\"]; ");
	xData.append("nodeA -> nodeA0;");

	StringBuffer out = new StringBuffer("digraph finite_state_machine { ");
	out.append(xData).append("}");

	Assert.assertEquals(out.toString(), graph.output());
    }

    @Test
    public void multipleEdges() {
	Node a = graph.addNode("nodeA");
	Node b = graph.addNode("nodeB");
	graph.addEdge(a, b);
	graph.addEdge(a, b);

	StringBuffer xData = new StringBuffer();
	xData.append("nodeB [label = \"nodeB\"]; ");
	xData.append("nodeA [label = \"nodeA\"]; ");
	xData.append("nodeA -> nodeB; ");
	xData.append("nodeA -> nodeB;");

	StringBuffer out = new StringBuffer("digraph finite_state_machine { ");
	out.append(xData).append("}");

	Assert.assertEquals(out.toString(), graph.output());
    }

    @Test
    public void testOutput() {

	graph.setAttribute("bgcolor", "#000");
	graph.setGlobalNodeAttribute("shape", "doublecircle");
	graph.getGlobalEdgeAttributes().put("shape", "folder");
	Node nodeA = graph.addNode("nodeA");
	nodeA.setAttribute("fillcolor", "#fff");
	Node nodeB = graph.addNode("nodeB");
	nodeB.setAttribute("shape", "circle");
	Edge edge = graph.addEdge(nodeA, nodeB);
	edge.setAttribute("label", "change_label");
	SubGraph subGraph = new SubGraph("hello_world");
	Node hello = subGraph.addNode("hello");
	Node world = subGraph.addNode("world");
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

	Assert.assertEquals(out.toString(), graph.output());
    }

    @Test
    public void testAddSubGraph() {
	SubGraph subGraph = new SubGraph("G");
	subGraph.setAttribute("label", "hello_world");
	Node hello = subGraph.addNode("hello");
	Node world = subGraph.addNode("world");
	subGraph.addEdge(hello, world);

	graph.addSubGraph(subGraph);

	Assert.assertEquals(Arrays.asList(subGraph), graph.subGraphs());
    }

    @Test
    public void testAddTwoSubGraphAndDefineExternalNodesRelationship() {
	SubGraph subGraphOne = new SubGraph("G");
	subGraphOne.setAttribute("label", "hello_world");
	Node hello = subGraphOne.addNode("hello");
	Node world = subGraphOne.addNode("world");
	subGraphOne.addEdge(hello, world);
	Graph subGraphTwo = new Graph("G");
	Node cat = subGraphTwo.addNode("cat");
	Node dog = subGraphTwo.addNode("dog");
	subGraphTwo.addEdge(cat, dog);
	graph.addSubGraph(subGraphOne);

	Edge edge = graph.addEdge(hello, dog);

	Assert.assertEquals(subGraphOne, edge.getStartNode().graph());
	Assert.assertEquals(subGraphTwo, edge.getEndNode().graph());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateIfExistsNodesWhenDefineExternalNodesRelationship() {
	SubGraph subGraphOne = new SubGraph("G");
	subGraphOne.setAttribute("label", "hello_world");
	Node hello = subGraphOne.addNode("hello");
	Node world = subGraphOne.addNode("world");
	subGraphOne.addEdge(hello, world);
	SubGraph subGraphTwo = new SubGraph("G");
	Node cat = subGraphTwo.addNode("cat");
	Node dog = subGraphTwo.addNode("dog");
	subGraphTwo.addEdge(cat, dog);
	graph.addSubGraph(subGraphOne);
	Graph graphOrphan = new Graph("G");
	Node nodeOphan = graphOrphan.addNode("node_orphan");

	graph.addEdge(nodeOphan, dog);
    }

}
