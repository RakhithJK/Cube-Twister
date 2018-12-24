/* @(#)UndoableCubeEdit.java
 * Copyright (c) 2006 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.cubetwister.doc;

import ch.randelshofer.rubik.*;
import javax.swing.undo.*;

/**
 * UndoableCubeEdit.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class UndoableCubeEdit extends AbstractUndoableEdit {
    private final static long serialVersionUID = 1L;
    private String name;
    private Cube model;
    private Cube oldState;
    private Cube newState;
    
    /** Creates a new instance. */
    public UndoableCubeEdit(String name, Cube model, Cube oldState, Cube newState) {
        this.name = name;
        this.model = model;
        this.oldState = oldState;
        this.newState = newState;
    }
    public String getPresentationName() {
        return name;
    }
    public void undo() {
        super.undo();
        model.setTo(oldState);
    }
    public void redo() {
        super.redo();
        model.setTo(newState);
    }
}
