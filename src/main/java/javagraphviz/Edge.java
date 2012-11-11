package javagraphviz;


public class Edge extends Component {

    private final Graph graph;
    private final Node startNode;
    private final Node endNode;

    /**
     * Creates an edge between two nodes in the given graph.
     * 
     * @param startNode
     * @param endNode
     * @param graph
     */
    public Edge(Node startNode, Node endNode, Graph graph) {
	super(startNode.getId() + " -> " + endNode.getId());
	this.graph = graph;
	this.startNode = startNode;
	this.endNode = endNode;
    }

    /**
     * Creates an edge between two nodes.
     * 
     * @param startNode
     * @param endNode
     * @return the created edge
     */
    public static Edge connect(Node startNode, Node endNode) {
	final Graph graph = startNode.getGraph();
	if (graph != endNode.getGraph()) {
	    throw new IllegalArgumentException("Nodes must be part of the same graph!");
	}
	return new Edge(startNode, endNode, graph);
    }

    public Node getStartNode() {
	return startNode;
    }

    public Node getEndNode() {
	return endNode;
    }

    @Override
    public String output() {
	StringBuilder sb = new StringBuilder();
	sb.append(this.startNode.getId()).append(" -> ").append(this.endNode.getId());
	if (getAttributes().size() > 0) {
	    appendAttributes(sb, getAttributes());
	}
	sb.append(";");
	return (sb.toString());

    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((endNode == null) ? 0 : endNode.hashCode());
	result = prime * result + ((graph == null) ? 0 : graph.hashCode());
	result = prime * result + ((startNode == null) ? 0 : startNode.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Edge other = (Edge) obj;
	if (endNode == null) {
	    if (other.endNode != null)
		return false;
	} else if (!endNode.equals(other.endNode))
	    return false;
	if (graph == null) {
	    if (other.graph != null)
		return false;
	} else if (!graph.equals(other.graph))
	    return false;
	if (startNode == null) {
	    if (other.startNode != null)
		return false;
	} else if (!startNode.equals(other.startNode))
	    return false;
	return true;
    }

    public Graph getGraph() {
	return graph;
    }

}
