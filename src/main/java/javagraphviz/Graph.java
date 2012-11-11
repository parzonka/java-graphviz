package javagraphviz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph extends Component {

    private final String graphType;
    private int idCount;
    private Map<String, String> globalNodeAttributes;
    private Map<String, String> edgeAttributes;
    private Map<String, Node> nodes;
    private List<Edge> edges;
    private List<SubGraph> subGraphs;
    private boolean prefIdEqualsLabel;

    protected Graph(String graphType, String name) {
	super(name);
	this.graphType = graphType;
	this.globalNodeAttributes = new HashMap<String, String>();
	this.edgeAttributes = new HashMap<String, String>();
	this.nodes = new HashMap<String, Node>();
	this.edges = new ArrayList<Edge>();
	this.subGraphs = new ArrayList<SubGraph>();
    }
    
    /**
     * Creates a new directed graph with the given name.
     * @param name
     * @return the digraph
     */
    public static Graph createDigraph(String name) {
	return new Graph("digraph", name);
    }

    public Map<String, String> getGlobalNodeAttributes() {
	return this.globalNodeAttributes;
    }

    public Node addNode(String name) {
	String id = nodes.containsKey(name) ? name + idCount++ : name;
	Node node = new Node(name, id, this);
	nodes.put(id, node);
	return node;
    }

    /**
     * Retrieves a node with given id, or creates a node if the id is not known to this graph.
     * 
     * @param id
     * @return a node
     */
    public Node getNode(String id) {
	Node node = nodes.get(id);
	if (node == null) {
	    node = new Node(id, this);
	    nodes.put(id, node);
	}
	if (prefIdEqualsLabel) {
	    node.getAttributes().put("label", id);
	}
	return node;
    }

    /**
     * Creates a node with auto generated id.
     * 
     * @return a node
     */
    public Node getNode() {
	String id;
	do {
	    id = "node" + idCount++;
	} while (nodes.containsKey(id));
	return getNode(id);
    }

    public Edge addEdge(Node nodeFrom, Node nodeTo) {
	if (!containsNode(nodeFrom) || !containsNode(nodeFrom))
	    throw new IllegalArgumentException("nodes not found");
	Edge edge = new Edge(nodeFrom, nodeTo, this);
	edges.add(edge);
	return edge;
    }

    public boolean containsNode(Node node) {
	boolean contains = this.getNodes().contains(node);
	if (!contains)
	    for (Graph graph : subGraphs) {
		contains = graph.containsNode(node);
		if (contains)
		    break;
	    }
	return contains;
    }

    public Map<String, String> getGlobalEdgeAttributes() {
	return this.edgeAttributes;
    }

    public void setGlobalEdgeAttribute(String attribute, String value) {
	this.edgeAttributes.put(attribute, value);
    }

    public void getGlobalEdgeAttributeValue(String attribute) {
	this.edgeAttributes.get(attribute);
    }

    public void getGlobalNodeAttributeValue(String attribute) {
	this.globalNodeAttributes.get(attribute);
    }

    public void setGlobalNodeAttribute(String attribute, String value) {
	this.globalNodeAttributes.put(attribute, value);
    }

    public List<Edge> getEdges() {
	return Collections.unmodifiableList(this.edges);
    }

    public List<Node> getNodes() {
	return Collections.unmodifiableList(new ArrayList<Node>(this.nodes.values()));
    }

    public List<SubGraph> subGraphs() {
	return this.subGraphs;
    }

    public String getType() {
	return graphType;
    }

    public String getDescription() {

	StringBuilder sb = new StringBuilder();
	
	sb.append(this.getType()).append(" ").append(this.getId()).append(" {");
	if (!getAttributes().isEmpty()) {
	    sb.append(" graph");
	    appendAttributes(sb, getAttributes());
	    sb.append(";");
	}
	if (!getGlobalNodeAttributes().isEmpty()) {
	    sb.append(" node");
	    appendAttributes(sb, getGlobalNodeAttributes());
	    sb.append(";");
	}
	if (!getGlobalEdgeAttributes().isEmpty()) {
	    sb.append(" edge");
	    appendAttributes(sb, getGlobalEdgeAttributes());
	    sb.append(";");
	}
	for (SubGraph subGraph : subGraphs) {
	    sb.append(" ").append(subGraph.getDescription());
	}
	for (Component component : this.getNodes()) {
	    sb.append(" ").append(component.getDescription());
	}
	for (Component component : this.getEdges()) {
	    sb.append(" ").append(component.getDescription());
	}
	sb.append("}");

	return (sb.toString());

    }

    public void addSubGraph(SubGraph graph) {
	this.subGraphs.add(graph);
    }

    /**
     * If set to <code>true</code>, the graphviz label of a node is set identical to the node id. Otherwise, the label
     * is set to the empty string, and can be set via attributes.
     * 
     * @param deriveNodeLabelFromId
     */
    public boolean isIdEqualsLabel() {
	return prefIdEqualsLabel;
    }

    /**
     * If set to <code>true</code>, the graphviz label of a node is set identical to the node id. Otherwise, the label
     * is set to the empty string, and can be set via attributes.
     * 
     * @param idEqualsLabel
     */
    public void setIdEqualsLabel(boolean idEqualsLabel) {
	this.prefIdEqualsLabel = idEqualsLabel;
    }

}
