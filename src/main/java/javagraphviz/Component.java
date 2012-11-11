package javagraphviz;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Component {

    private final String id;
    private final Map<String, String> attributes;

    public Component(String id) {
	this.id = id;
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
    public Component setAttribute(String attribute, String value) {
	this.attributes.put(attribute, value);
	return this;
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

}
