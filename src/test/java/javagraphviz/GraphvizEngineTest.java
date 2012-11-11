/*
 * Copyright (c) 2012 Mateusz Parzonka, Eric Bodden
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Mateusz Parzonka - initial API and implementation
 */
package javagraphviz;

import static junit.framework.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class GraphvizEngineTest {
    
    @Before
    public void init() {
	new File("target/graphviz").delete();
    }
    
    @Test
    public void testOutput() {
	Graph graph = Graph.createDigraph("test");
	graph.addEdge(graph.getNode(), graph.getNode());
	
	GraphvizEngine ge = new GraphvizEngine();
	ge.setExecutionPath("target/graphviz");
	ge.process(graph);
	
	assertTrue(new File("target/graphviz/test.eps").exists());
    }

}
