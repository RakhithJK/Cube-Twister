/*
/*
 * @(#)AbstractEntityView.java  1.0  February 1, 2006
 * Copyright (c) 2006 Werner Randelshofer, Switzerland.
 * You may only use this software in accordance with the license terms.
 */

package ch.randelshofer.cubetwister.doc;

import ch.randelshofer.util.*;
import javax.swing.*;
import javax.swing.event.*;
import org.jhotdraw.util.ResourceBundleUtil;
/**
 * AbstractEntityView.
 * <p>
 * FIXME - Can't make this class abstract, because it wouldn't work with
 * the NetBeans GUI builder anymore.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class AbstractEntityView extends JPanel implements EntityView {
    private final static long serialVersionUID = 1L;
    /**
     * The resource bundle used for internationalisation.
     */
    protected ResourceBundleUtil labels;
    
    
    
    /** Creates new form. */
    public AbstractEntityView() {
        labels = ResourceBundleUtil.getBundle("ch.randelshofer.cubetwister.Labels");
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void setModel(EntityModel newValue) {
    }

    public JComponent getViewComponent() {
        return this;
    }

    public void addUndoableEditListener(UndoableEditListener l) {
    }

    public void removeUndoableEditListener(UndoableEditListener l) {
    }
    
}
