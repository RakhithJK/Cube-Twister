/*
 * @(#)BackdropBorder.java  1.0  September 24, 2006
 * Copyright (c) 2006 Werner Randelshofer, Switzerland.
 * You may only use this software in accordance with the license terms.
 */

package ch.randelshofer.gui.border;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 * BackdropBorder has a foreground border and a background border.
 * The foreground border is drawn, when paintBorder() is invoked.
 * The background border can be retrieved using getBackgroundBorder().
 * 
 * @author Werner Randelshofer
 * @version $Id$
 */
public class BackdropBorder implements Border {
    private Border foregroundBorder;
    private Border backgroundBorder;
    
    /** Creates a new instance. */
    public BackdropBorder(Border backdropBorder) {
        this(null, backdropBorder);
    }
    public BackdropBorder(Border foregroundBorder, Border backdropBorder) {
        this.backgroundBorder = backdropBorder;
        this.foregroundBorder = foregroundBorder;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        if (foregroundBorder != null) {
            foregroundBorder.paintBorder(c, g, x, y, width, height);
        }
    }
    
    public Border getBackgroundBorder() {
        return backgroundBorder;
    }

    public Insets getBorderInsets(Component c) {
        if (foregroundBorder != null) {
            return foregroundBorder.getBorderInsets(c);
        } else {
            return backgroundBorder.getBorderInsets(c);
        }
    }

    public boolean isBorderOpaque() {
        return false;
    }    
}