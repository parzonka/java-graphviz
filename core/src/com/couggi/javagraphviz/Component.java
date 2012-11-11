package com.couggi.javagraphviz;

import java.util.HashMap;
import java.util.Map;

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

    public String name() {
	return id;
    }

    public abstract String output();

}
