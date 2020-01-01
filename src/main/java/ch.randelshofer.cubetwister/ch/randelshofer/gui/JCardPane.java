/* @(#)JCardPane.java
 * Copyright (c) 2005 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.gui;

import org.jhotdraw.annotation.Nonnull;

import javax.swing.DefaultComboBoxModel;
import java.awt.CardLayout;
import java.awt.Container;
/**
 * JCardPane.
 *
 * @author  Werner Randelshofer
 */
public class JCardPane extends javax.swing.JPanel {
        private final static long serialVersionUID = 1L;

    /**
     * Creates a new instance.
     */
    public JCardPane() {
        initComponents();
    }
    
    public Container getContentPane() {
        return contentPane;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        comboBox = new javax.swing.JComboBox();
        contentPane = new javax.swing.JPanel();

        FormListener formListener = new FormListener();

        setLayout(new java.awt.BorderLayout());

        comboBox.addItemListener(formListener);

        add(comboBox, java.awt.BorderLayout.NORTH);

        contentPane.setLayout(new java.awt.CardLayout());

        contentPane.addComponentListener(formListener);
        contentPane.addContainerListener(formListener);

        add(contentPane, java.awt.BorderLayout.CENTER);

    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ComponentListener, java.awt.event.ContainerListener, java.awt.event.ItemListener {
        public void componentHidden(java.awt.event.ComponentEvent evt) {
        }

        public void componentMoved(java.awt.event.ComponentEvent evt) {
        }

        public void componentResized(@Nonnull java.awt.event.ComponentEvent evt) {
            if (evt.getSource() == contentPane) {
                JCardPane.this.componentRemoved(evt);
            }
        }

        public void componentShown(java.awt.event.ComponentEvent evt) {
        }

        public void componentAdded(@Nonnull java.awt.event.ContainerEvent evt) {
            if (evt.getSource() == contentPane) {
                JCardPane.this.componentAdded(evt);
            }
        }

        public void componentRemoved(java.awt.event.ContainerEvent evt) {
        }

        public void itemStateChanged(@Nonnull java.awt.event.ItemEvent evt) {
            if (evt.getSource() == comboBox) {
                JCardPane.this.itemStateChanged(evt);
            }
        }
    }//GEN-END:initComponents

    private void componentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_componentAdded
        DefaultComboBoxModel m = (DefaultComboBoxModel) comboBox.getModel();
        
    }//GEN-LAST:event_componentAdded

    private void componentRemoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_componentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_componentRemoved

    private void itemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_itemStateChanged
       ((CardLayout) contentPane.getLayout()).show(contentPane, (String) comboBox.getSelectedItem());
    }//GEN-LAST:event_itemStateChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboBox;
    private javax.swing.JPanel contentPane;
    // End of variables declaration//GEN-END:variables
    
}
