package javagraphviz;

public class GvNode extends GvComponent {

    private GvGraph graph;

    /**
     * create a node with name
     */
    public GvNode(String id, GvGraph graph) {
	this(id, id, graph);
    }

    public GvNode(String label, String id, GvGraph graph) {
	super(toSafeId(id));
	setAttribute("label", label);
	this.graph = graph;
    }

    @Override
    public String getDescription() {
	StringBuilder sb = new StringBuilder();
	sb.append(getId());
	if (getAttributes().size() > 0) {
	    appendAttributes(sb, getAttributes());
	}
	sb.append(";");
	return (sb.toString());
    }

    public GvGraph getGraph() {
	return graph;
    }
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((graph == null) ? 0 : graph.hashCode());
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
	GvNode other = (GvNode) obj;
	if (graph == null) {
	    if (other.graph != null)
		return false;
	} else if (!graph.equals(other.graph))
	    return false;
	return true;
    }


    @Override
    public String toString() {
	return "N(" + getId() + "=\"" + getAttributeValue("label") +  "\")";
    }

    public GvComponent setFixedSize(boolean fixedSize) {
	return setAttribute("fixedsize", Boolean.toString(fixedSize));
    }

    public GvComponent setFontColor(String fontColor) {
	return setAttribute("fontcolor", fontColor);
    }
    
    // --------- Common Graphviz attributes (from A to Z) ------------ //
    
    /**
     * Height of node, in inches. This is taken as the initial, minimum height of the node. If fixedsize is true, this
     * will be the final height of the node. Otherwise, if the node label requires more height to fit, the node's height
     * will be increased to contain the label. Note also that, if the output format is dot, the value given to height
     * will be the final value.
     * <p>
     * If the node shape is regular, the width and height are made identical. In this case, if either the width or the
     * height is set explicitly, that value is used. In this case, if both the width or the height are set explicitly,
     * the maximum of the two values is used. If neither is set explicitly, the minimum of the two default values is
     * used.
     * 
     * @param height
     *            Minimum is 0.02, default is 0.5
     * @return this component
     */
    public GvComponent setHeight(double height) {
	return setAttribute("height", Double.toString(height));
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

    /**
     * Set the shape to any of solid, dashed, dotted, bold, rounded, diagonals, filled, striped or wedged
     * 
     * @param shape
     * @return this component
     */
    public GvComponent setStyle(String style) {
	return setAttribute("style", style);
    }

    public GvComponent setShape(String shape) {
	return setAttribute("shape", shape);
    }

    /**
     * Width of node, in inches. This is taken as the initial, minimum width of the node. If fixedsize is true, this
     * will be the final width of the node. Otherwise, if the node label requires more width to fit, the node's width
     * will be increased to contain the label. Note also that, if the output format is dot, the value given to width
     * will be the final value.
     * <p>
     * If the node shape is regular, the width and height are made identical. In this case, if either the width or the
     * height is set explicitly, that value is used. In this case, if both the width or the height are set explicitly,
     * the maximum of the two values is used. If neither is set explicitly, the minimum of the two default values is
     * used.
     * 
     * @param width
     *            Minimum is 0.01, default is 0.75
     * @return this component
     */
    public GvComponent setWidth(double width) {
	return setAttribute("width", Double.toString(width));
    }

}
