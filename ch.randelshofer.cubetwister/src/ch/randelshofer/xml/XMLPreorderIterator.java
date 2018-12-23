/* @(#)XMLPreorderIterator.java
 * Copyright (c) 2004 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.xml;

import java.util.*;
import nanoxml.*;

/**
 * XMLPreorderIterator.
 * 
 * @author Werner Randelshofer
 * @version $Id$
 * <br>2.0 2007-11-16 Upgraded to Java 1.4. 
 * <br>1.0  05 February 2005  Created.
 */
public class XMLPreorderIterator implements Iterator {
	private Deque<Iterator> stack;
    
    /** Creates a new instance. */
    public XMLPreorderIterator(XMLElement rootNode) {
	    Vector<XMLElement> v = new Vector<XMLElement>(1);
	    v.addElement(rootNode);	
	    stack = new ArrayDeque<Iterator>();
	    stack.add(v.iterator());
	}

	public boolean hasNext() {
	    return (!stack.isEmpty() &&
		    stack.getLast().hasNext());
	}

	public Object next() {
	    Iterator	enu = stack.getLast();
	    XMLElement node = (XMLElement) enu.next();
	    Iterator	children = node.getChildren().iterator();

	    if (! enu.hasNext()) {
		stack.removeLast();
	    }
	    if (children.hasNext()) {
		stack.add(children);
	    }
	    return node;
	}

    public void remove() {
        throw new UnsupportedOperationException();
    }
}