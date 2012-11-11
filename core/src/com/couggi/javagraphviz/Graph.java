package com.couggi.javagraphviz;

import java.util.List;
import java.util.Map;

/**
 * Represents a GraphViz graph.
 * 
 * @author Everton Cardoso
 * @author Mateusz Parzonka
 * 
 */
public interface Graph {

    /**
     * Returns a node which serves as a prototype for all created nodes. All attributes to the default node apply to all
     * nodes of this graph, if they are not overwritten.
     * 
     * @return
     */
    Node getDefaultNode();

    /**
     * Returns an edge which serves as a prototype for all created edges. All attributes to the default edge apply to all
     * edges of this graph, if they are not overwritten.
     * 
     * @return
     */
    Map<String, String> getGlobalEdgeAttributes();

    /**
     * list of the nodes.
     */
    List<Node> nodes();

    /**
     * list of the edges.
     */
    List<Edge> edges();

    /**
     * type of the Graph componenet (graph, digraph.. etc)
     */
    String getType();

    /**
     * create a node with name to graph.
     */
    Node addNode(String name);
    
    String output();

    /**
     * create a edge with two nodes.
     */
    Edge addEdge(Node nodeFrom, Node nodeTo);

    boolean containsNode(Node node);
}
