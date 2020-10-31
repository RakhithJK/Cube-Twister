/*
 * @(#)TestJDnDList.java
 * CubeTwister. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.test;

import ch.randelshofer.gui.list.DefaultMutableListModel;
import org.jhotdraw.annotation.Nonnull;

import javax.swing.UIManager;

/**
 * A JFrame with two DnDJLists.
 *
 * @author  Werner Randelshofer
 */
public class TestJDnDList extends javax.swing.JFrame {
        private final static long serialVersionUID = 1L;

    /** Creates new form TestDnDList */
    public TestJDnDList() {
        initComponents();
        setTitle("TestJDnDList");

    DefaultMutableListModel<String> m = new DefaultMutableListModel<String>();
        m.add("anna");
        m.add("berta");
        m.add("carla");
        m.add("daniel");
        m.add("egon");
        m.add("franz");
        m.add("gabriel");
        m.add("helene");
        m.add("isabelle");
        m.add("josef");
        m.add("karl");
        m.add("lena");

        list1.setModel(m);
        //list1.set

        m = new DefaultMutableListModel<String>();
        m.add("1");
        m.add("2");
        m.add("3");

        list2.setModel(m);

        setSize(600,400);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("serial")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        scrollPane1 = new javax.swing.JScrollPane();
        list1 = new ch.randelshofer.gui.JDnDList();
        srollPane2 = new javax.swing.JScrollPane();
        list2 = new ch.randelshofer.gui.JDnDList();
        jLabel1 = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        scrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        list1.setModel(new javax.swing.AbstractListModel() {
            @Nonnull String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        scrollPane1.setViewportView(list1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 20, 20, 10);
        getContentPane().add(scrollPane1, gridBagConstraints);

        list2.setModel(new javax.swing.AbstractListModel() {
            @Nonnull String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        srollPane2.setViewportView(list2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 10, 20, 20);
        getContentPane().add(srollPane2, gridBagConstraints);

        jLabel1.setText("<html><b>DnDJList demo</b><br>\nDrag and drop elements from one list to the other.<br>\nSince DnDJList is a subclass of MutableJList, you can use the popup menu available on both lists to use the clipboard as an alternative method for transfering an element from one list to the other. The popup menus also allow the creation and deletion of elements.\n");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        getContentPane().add(jLabel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Throwable e) {
        }
        new TestJDnDList().setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private ch.randelshofer.gui.JDnDList list1;
    private ch.randelshofer.gui.JDnDList list2;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JScrollPane srollPane2;
    // End of variables declaration//GEN-END:variables

}
