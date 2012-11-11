package javagraphviz;


public class Node extends Component {

    private Graph graph;

    /**
     * create a node with name
     */
    public Node(String name, Graph graph) {
	this(name, name, graph);
    }

    public Node(String label, String id, Graph graph) {
	super(id);
	this.graph = graph;
	setAttribute("label", label);
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

    public Graph getGraph() {
	return graph;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((graph == null) ? 0 : graph.hashCode());
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
	Node other = (Node) obj;
	if (graph == null) {
	    if (other.graph != null)
		return false;
	} else if (!graph.equals(other.graph))
	    return false;
	return true;
    }

}
