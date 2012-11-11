package javagraphviz;

public class GvEdge extends GvComponent {

    private final GvGraph graph;
    private final GvNode startNode;
    private final GvNode endNode;

    /**
     * Creates an edge between two nodes in the given graph.
     * 
     * @param startNode
     * @param endNode
     * @param graph
     */
    public GvEdge(GvNode startNode, GvNode endNode, GvGraph graph) {
	super(startNode.getId() + " -> " + endNode.getId());
	this.graph = graph;
	this.startNode = startNode;
	this.endNode = endNode;
	this.graph.getEdges().add(this);
    }

    /**
     * Creates an edge between two nodes.
     * 
     * @param startNode
     * @param endNode
     * @return the created edge
     */
    public static GvEdge connect(GvNode startNode, GvNode endNode) {
	final GvGraph graph = startNode.getGraph();
	if (graph != endNode.getGraph()) {
	    throw new IllegalArgumentException("Nodes must be part of the same graph!");
	}
	return new GvEdge(startNode, endNode, graph);
    }

    public GvNode getStartNode() {
	return startNode;
    }

    public GvNode getEndNode() {
	return endNode;
    }

    @Override
    public String getDescription() {
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
	int result = super.hashCode();
	result = prime * result + ((endNode == null) ? 0 : endNode.hashCode());
	result = prime * result + ((graph == null) ? 0 : graph.hashCode());
	result = prime * result + ((startNode == null) ? 0 : startNode.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	GvEdge other = (GvEdge) obj;
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

    public GvGraph getGraph() {
	return graph;
    }
    
    // --------- Common Graphviz attributes (from A to Z) ------------ //
    
    /**
     * Set the type of the arrow to any in normal, inv, dot, invdot, odot, invodot, none, tee, empty, invempty, diamond,
     * odiamond, ediamond, crow, box, obox, open, halfopen or vee.
     * 
     * @param arrowType
     * @return this component
     */
    public GvComponent setArrowType(String arrowType) {
	return setAttribute("arrowtype", arrowType);
    }

    public GvComponent setFixedSize(boolean fixedSize) {
	return setAttribute("fixedsize", Boolean.toString(fixedSize));
    }

    public GvComponent setFontColor(String fontColor) {
	return setAttribute("fontcolor", fontColor);
    }

    public GvComponent setLabel(String label) {
	return setAttribute("label", label);
    }

    /**
     * Specifies the width of the pen, in points, used to draw lines and curves, including the boundaries of edges and
     * clusters. The value is inherited by subclusters. It has no effect on text.
     * 
     * @param penwidth
     *            Minimum is 0.0, default is 1.0
     * @return this component
     */
    public GvComponent setPenwidth(double penwidth) {
	return setAttribute("penwidth", Double.toString(penwidth));
    }

    public GvComponent setShape(String shape) {
	return setAttribute("shape", shape);
    }

    /**
     * Set the shape to any of solid, dashed, dotted or bold.
     * 
     * @param shape
     * @return this component
     */
    public GvComponent setStyle(String style) {
	return setAttribute("style", style);
    }

}
