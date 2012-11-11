package javagraphviz;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class GvComponent {

    private final String id;
    private final Map<String, String> attributes;

    public GvComponent(String id) {
	this.id = toSafeId(id);
	this.attributes = new HashMap<String, String>();
    }

    public String getAttributeValue(String name) {
	return this.attributes.get(name);
    }

    public Map<String, String> getAttributes() {
	return this.attributes;
    }

    /**
     * Sets the value of the given attribute.
     * 
     * @param attribute
     * @param value
     * @return this component (enabling 'fluent' API style)
     */
    public GvComponent setAttribute(String attribute, String value) {

	this.attributes.put(attribute, value);
	return this;
    }

    protected static String toSafeId(String id) {
	String result = id;
	result = result.replace("[", "_");
	result = result.replace("]", "_");
	result = result.replace(";", "_");
	result = result.replace(",", "_");
	result = result.replace(".", "_");
	result = result.replace(" ", "");
	return result;
    }

    public String getId() {
	return id;
    }

    /**
     * Create the textual graph description interpreted by Graphviz.
     * 
     * @return the description
     */
    public abstract String getDescription();

    protected static void appendAttributes(StringBuilder sb, Map<String, String> attributes) {
	sb.append(" [");
	String separator = "";
	for (Entry<String, String> attribute : attributes.entrySet()) {
	    sb.append(separator).append(attribute.getKey()).append(" = \"").append(attribute.getValue()).append("\"");
	    separator = ", ";
	}
	sb.append("]");
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
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
	GvComponent other = (GvComponent) obj;
	if (attributes == null) {
	    if (other.attributes != null)
		return false;
	} else if (!attributes.equals(other.attributes))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
