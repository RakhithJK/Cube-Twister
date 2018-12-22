/**
 * @(#)MutableTreeTransferable.java  1.1  2011-01-19
 * Copyright (c) 2010 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.gui.tree;

import java.awt.datatransfer.*;
import java.io.*;
import javax.swing.JComponent;
import javax.swing.tree.TreePath;

/**
 * The MutableTreeTransferable is a proxy for the actual Transferable of a JTree,
 * plus a list of the transferred paths.
 *
 * @author Werner Randelshofer
 * @version $Id$
 * <br>1.0 2010-01-09 Created.
 */
public class MutableTreeTransferable implements Transferable {
    private JComponent source;
    private Transferable target;
    private TreePath[] paths;
    
    public MutableTreeTransferable(JComponent source, TreePath[] paths, Transferable target) {
        this.source = source;
        this.paths = paths;
        this.target = target;
    }
    
    public JComponent getSource() {
        return source;
    }
    public TreePath[] getTransferedPaths() {
        return paths;
    }
    
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return target.getTransferDataFlavors();
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return target.isDataFlavorSupported(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return target.getTransferData(flavor);
    }

}
