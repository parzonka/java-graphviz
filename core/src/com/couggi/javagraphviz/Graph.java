package com.couggi.javagraphviz;

import java.util.List;

/**
 * Represents a GraphViz graph.
 * 
 * @author Everton Cardoso
 * @author Mateusz Parzonka
 * 
 */
public interface Graph extends Component {

    /**
     * Returns a node which serves as a prototype for all created nodes. All attributes to the default node apply to all
     * nodes of this graph, if they are not overwritten.
     * 
     * @return
     */
    Node getDefaultNode();

    /**
     * attributes default of the edges.
     */
    Edge edge();

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

    /**
     * create a edge with two nodes.
     */
    Edge addEdge(Node nodeFrom, Node nodeTo);

    boolean containsNode(Node node);
}
