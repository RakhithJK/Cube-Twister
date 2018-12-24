/* @(#)VirtualRubiksCuboctahedronApplet.java
 * Copyright (c) 2007 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.rubik.virtual;

import ch.randelshofer.rubik.AbstractCubeIdx3D;
import ch.randelshofer.rubik.RubiksCuboctahedronIdx3D;
/**
 * VirtualRubiksCuboctahedronApplet.
 * 
 * 
 * @author Werner Randelshofer
 * @version $Id$
 */
public class VirtualRubiksCuboctahedronApplet extends AbstractVirtualCubeApplet {
        private final static long serialVersionUID = 1L;

        @Override
    protected AbstractCubeIdx3D createCube3D() {
        return new RubiksCuboctahedronIdx3D();
    }
    
    
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}