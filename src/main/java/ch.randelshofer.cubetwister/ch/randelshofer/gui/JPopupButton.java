/* @(#)JPopupButton.java
 * Copyright (c) 2003 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.gui;

import org.jhotdraw.annotation.Nonnull;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
/**
 * JPopupButton.
 * @author  Wermer Randelshofer
 */
public class JPopupButton extends javax.swing.JButton {
    private final static long serialVersionUID = 1L;
    private JPopupMenu popupMenu;
    private int columnCount = 1;
    private Action action;
    private Rectangle actionArea;
    public final static Font itemFont = new Font("Dialog", Font.PLAIN, 10);
    
    /** Creates new form JToolBarMenu */
    public JPopupButton() {
        initComponents();
    }

    public void setAction(@Nonnull Action action, Rectangle actionArea) {
        this.action = action;
        this.actionArea = actionArea;
        action.addPropertyChangeListener(new PropertyChangeListener() {
                                             public void propertyChange(@Nonnull PropertyChangeEvent evt) {
                                                 if ("enabled".equals(evt.getPropertyName())) {
                                                     setEnabled(((Boolean) evt.getNewValue()).booleanValue());
                                                 } else {
                                                     repaint();
                                                 }
                                             }
        }
        );
    }
    
    public int getColumnCount() {
        return columnCount;
    }
    public void setColumnCount(int count, boolean isVertical) {
        columnCount = count;
        getPopupMenu().setLayout(new VerticalGridLayout(0, getColumnCount(), isVertical));
    }
    
    public void add(Action action) {
        JMenuItem item = getPopupMenu().add(action);
        if (getColumnCount() > 1) {
            item.setUI(new PaletteMenuItemUI());
        }
        item.setFont(itemFont);
    }
    public void add(JMenu submenu) {
        JMenuItem item = getPopupMenu().add(submenu);
    }
    
    public void setPopupMenu(JPopupMenu popupMenu) {
        this.popupMenu = popupMenu;
    }
    public JPopupMenu getPopupMenu() {
        if (popupMenu == null) {
            popupMenu = new JPopupMenu();
            popupMenu.setLayout(new VerticalGridLayout(0, getColumnCount()));
        }
        return popupMenu;
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents

        FormListener formListener = new FormListener();

        addMouseListener(formListener);

    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.MouseListener {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(@Nonnull java.awt.event.MouseEvent evt) {
            if (evt.getSource() == JPopupButton.this) {
                JPopupButton.this.showPopup(evt);
            }
        }

        public void mouseReleased(@Nonnull java.awt.event.MouseEvent evt) {
            if (evt.getSource() == JPopupButton.this) {
                JPopupButton.this.performAction(evt);
            }
        }
    }//GEN-END:initComponents

    private void performAction(@Nonnull java.awt.event.MouseEvent evt) {//GEN-FIRST:event_performAction
        // Add your handling code here:
        if (actionArea != null && actionArea.contains(evt.getX() - getInsets().left, evt.getY() - getInsets().top)) {
            action.actionPerformed(
                    new ActionEvent(this,
                            ActionEvent.ACTION_PERFORMED,
                            null,
                            //evt.getWhen(), <- requires JDK 1.4
                            evt.getModifiers())
            );
            
        }
    }//GEN-LAST:event_performAction

    private void showPopup(@Nonnull java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPopup
        // Add your handling code here:
        if (popupMenu != null
                && (actionArea == null || !actionArea.contains(evt.getX() - getInsets().left, evt.getY() - getInsets().top))
        ) {
            int x, y;

            x = 0;
            y = getHeight();
            if (getParent() instanceof JToolBar) {
                JToolBar toolbar = (JToolBar) getParent();
                if (toolbar.getOrientation() == JToolBar.VERTICAL) {
                    y = 0;
                    if (toolbar.getX() > toolbar.getParent().getInsets().left) {
                        x = -popupMenu.getPreferredSize().width;
                    } else {
                        x = getWidth();
                    }
                } else {
                    if (toolbar.getY() > toolbar.getParent().getInsets().top) {
                        y = -popupMenu.getPreferredSize().height;
                    }
                }
            }
            
            popupMenu.show(this, x, y);
        }
    }//GEN-LAST:event_showPopup
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
