package com.couggi.javagraphviz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.couggi.javagraphviz.Digraph;
import com.couggi.javagraphviz.Edge;
import com.couggi.javagraphviz.Graph;
import com.couggi.javagraphviz.Node;

public class EdgeTest {

    Edge edge;

    @Before
    public void onSetUp() {
	Graph graph = new Digraph("test");
	edge = new Edge(new Node("nodeA", graph), new Node("nodeB", graph), graph);
    }

    @Test
    public void testOutput() {

	edge.attr("shape").value("folder");

	StringBuffer xAttr = new StringBuffer("");
	xAttr.append("shape" + " = " + "\"folder\"");
	StringBuffer xOut = new StringBuffer("nodeA -> nodeB [" + xAttr.toString() + "];");

	assertEquals(xOut.toString(), edge.output());
    }

}
