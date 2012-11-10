package com.couggi.javagraphviz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.couggi.javagraphviz.Attr;
import com.couggi.javagraphviz.Attrs;
import com.couggi.javagraphviz.Component;
import com.couggi.javagraphviz.Digraph;

public class AttributeTest {

	Attrs attributes;
	Component component;
	
	@Before
	public void onSetUp() {
		component = new Digraph("test");
		this.attributes = new Attrs(component);
	}
	
	@Test
	public void testAttribute() {
		Attr attribute = new Attr(attributes,"rankdir");
		assertEquals("rankdir",attribute.name());
		assertEquals(null,attribute.value());
	}

	@Test
	public void testSetValue() {
		component.setAttribute("rankdir", "#999");
		assertEquals("#999", component.getAttributeValue("rankdir"));
		
	}
	
	@Test
	public void testSetValueWithNullRemovedAttribute() {
	    	component.setAttribute("rankdir", "#999");
	    	component.setAttribute("rankdir", null);
		assertEquals(0, component.getAttributes().size());
	}
	
}
