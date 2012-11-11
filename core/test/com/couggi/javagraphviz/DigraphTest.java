package com.couggi.javagraphviz;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class DigraphTest {

    Digraph digraph;

    @Before
    public void onSetUp() {
	digraph = new Digraph("finite_state_machine");
    }

    @Test
    public void addNode() {
	Node node = digraph.addNode("nodeA");
	Assert.assertEquals(new Node("nodeA", digraph), node);
    }

    @Test
    public void addEdge() {
	Node nodeFrom = digraph.addNode("nodeFrom");
	Node nodeTo = digraph.addNode("nodeTo");
	Edge edge = digraph.addEdge(nodeFrom, nodeTo);
	Assert.assertEquals(new Edge(nodeFrom, nodeTo, digraph), edge);
    }

    @Test
    public void multipleNodes() {
	Node a = digraph.addNode("nodeA");
	Node b = digraph.addNode("nodeA");
	digraph.addEdge(a, b);

	StringBuffer xData = new StringBuffer();
	xData.append("nodeA [label = \"nodeA\"]; ");
	xData.append("nodeA0 [label = \"nodeA\"]; ");
	xData.append("nodeA -> nodeA0;");

	StringBuffer out = new StringBuffer("digraph finite_state_machine { ");
	out.append(xData).append("}");

	Assert.assertEquals(out.toString(), digraph.output());
    }

    @Test
    public void multipleEdges() {
	Node a = digraph.addNode("nodeA");
	Node b = digraph.addNode("nodeB");
	digraph.addEdge(a, b);
	digraph.addEdge(a, b);

	StringBuffer xData = new StringBuffer();
	xData.append("nodeB [label = \"nodeB\"]; ");
	xData.append("nodeA [label = \"nodeA\"]; ");
	xData.append("nodeA -> nodeB; ");
	xData.append("nodeA -> nodeB;");

	StringBuffer out = new StringBuffer("digraph finite_state_machine { ");
	out.append(xData).append("}");

	Assert.assertEquals(out.toString(), digraph.output());
    }

    @Test
    public void testOutput() {

	digraph.setAttribute("bgcolor", "#000");
	digraph.getDefaultNode().setAttribute("shape", "doublecircle");
	digraph.getGlobalEdgeAttributes().put("shape", "folder");
	Node nodeA = digraph.addNode("nodeA");
	nodeA.setAttribute("fillcolor", "#fff");
	Node nodeB = digraph.addNode("nodeB");
	nodeB.setAttribute("shape", "circle");
	Edge edge = digraph.addEdge(nodeA, nodeB);
	edge.setAttribute("label", "change_label");
	SubGraph subGraph = new SubGraph("hello_world");
	Node hello = subGraph.addNode("hello");
	Node world = subGraph.addNode("world");
	subGraph.addEdge(hello, world);
	digraph.addSubGraph(subGraph);

	StringBuffer xData = new StringBuffer("graph [bgcolor = \"#000\"];");
	xData.append(" node [shape = \"doublecircle\"];");
	xData.append(" edge [shape = \"folder\"];");
	xData.append("subgraph cluster_hello_world {");
	xData.append(" hello [label = \"hello\"];");
	xData.append(" world [label = \"world\"];");
	xData.append(" hello -> world;");
	xData.append("}");
	xData.append(" nodeB [label = \"nodeB\", shape = \"circle\"];");
	xData.append(" nodeA [label = \"nodeA\", fillcolor = \"#fff\"];");
	xData.append(" nodeA -> nodeB [label = \"change_label\"];");

	StringBuffer out = new StringBuffer("digraph finite_state_machine { ");
	out.append(xData).append("}");

	Assert.assertEquals(out.toString(), digraph.output());
    }

    @Test
    public void testAddSubGraph() {
	SubGraph subGraph = new SubGraph("G");
	subGraph.setAttribute("label", "hello_world");
	Node hello = subGraph.addNode("hello");
	Node world = subGraph.addNode("world");
	subGraph.addEdge(hello, world);

	digraph.addSubGraph(subGraph);

	Assert.assertEquals(Arrays.asList(subGraph), digraph.subGraphs());
    }

    @Test
    public void testAddTwoSubGraphAndDefineExternalNodesRelationship() {
	SubGraph subGraphOne = new SubGraph("G");
	subGraphOne.setAttribute("label", "hello_world");
	Node hello = subGraphOne.addNode("hello");
	Node world = subGraphOne.addNode("world");
	subGraphOne.addEdge(hello, world);
	Graph subGraphTwo = new Digraph("G");
	Node cat = subGraphTwo.addNode("cat");
	Node dog = subGraphTwo.addNode("dog");
	subGraphTwo.addEdge(cat, dog);
	digraph.addSubGraph(subGraphOne);

	Edge edge = digraph.addEdge(hello, dog);

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
	digraph.addSubGraph(subGraphOne);
	Graph graphOrphan = new Digraph("G");
	Node nodeOphan = graphOrphan.addNode("node_orphan");

	digraph.addEdge(nodeOphan, dog);
    }

}
