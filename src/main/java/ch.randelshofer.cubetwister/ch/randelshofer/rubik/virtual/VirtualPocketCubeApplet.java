/* @(#)RubiksCubeApplet.java
 * Copyright (c) 2007 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.rubik.virtual;

import ch.randelshofer.rubik.AbstractCubeIdx3D;
import ch.randelshofer.rubik.PocketCubeIdx3D;
import org.jhotdraw.annotation.Nonnull;

/**
 * RubiksCubeApplet.
 *
 * @author Werner Randelshofer
 */
public class VirtualPocketCubeApplet extends AbstractVirtualCubeApplet {
    private final static long serialVersionUID = 1L;

    @Nonnull
    @Override
    protected AbstractCubeIdx3D createCube3D() {
        return new PocketCubeIdx3D();
    }
    
        @Override
    protected boolean canBeDisassembled() {
        return false;
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
