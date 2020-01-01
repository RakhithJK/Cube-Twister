/* @(#)ProgressFrame.java
 * Copyright (c) 2002 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.gui;

import org.jhotdraw.annotation.Nonnull;
import org.jhotdraw.util.prefs.PreferencesUtil;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.Component;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.prefs.Preferences;

/**
 * The ProgressFrame holds all ProgressView's.
 *
 * @author Werner Randelshofer
 */
public class ProgressFrame extends javax.swing.JFrame {
    private final static long serialVersionUID = 1L;

    private static ProgressFrame instance;
    private JPanel progressPanel;

    /**
     * Creates new form ProgressFrame
     */
    private ProgressFrame() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        progressPanel = new javax.swing.JPanel() {
            private final static long serialVersionUID = 1L;

            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.width = 300;
                return d;
            }
        };
        progressPanel.setLayout(new javax.swing.BoxLayout(progressPanel, javax.swing.BoxLayout.Y_AXIS));
        //getContentPane().add(progressPanel, java.awt.BorderLayout.CENTER);
        scrollPane.setViewportView(progressPanel);

        disclosureToggle.setIcon(UIManager.getIcon("Tree.collapsedIcon"));
        disclosureToggle.setSelectedIcon(UIManager.getIcon("Tree.expandedIcon"));
        disclosureToggle.setUI((MetalToggleButtonUI) MetalToggleButtonUI.createUI(disclosureToggle));

        PreferencesUtil.installFramePrefsHandler(Preferences.userNodeForPackage(ProgressFrame.class), "progressFrame", this);
    }

    public static ProgressFrame getInstance() {
        if (instance == null) {
            instance = new ProgressFrame();
        }
        return instance;
    }

    public void addProgressView(@Nonnull final ProgressView viewer) {
        invokeAndWait(new Runnable() {
            @Override
            public void run() {
                progressPanel.add(viewer);
                updateInfoPanel();
                pack();
                if (!isVisible()) {
                    setVisible(true);
                }
                viewer.repaint();
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.height = Math.min(400, d.height);
        return d;
    }

    public void removeProgressView(@Nonnull final ProgressView viewer) {
        invokeAndWait(new Runnable() {
            @Override
            public void run() {
                progressPanel.remove(viewer);
                updateInfoPanel();
                if (progressPanel.getComponentCount() == 0) {
                    setVisible(false);
                } else {
                    pack();
                }
            }
        });
    }

    /**
     * Updates the info label and the cancel all button on the info panel.
     */
    private void updateInfoPanel() {
        int count = progressPanel.getComponentCount();
        switch (count) {
            case 0:
                infoLabel.setText("No processes running.");
                cancelAllButton.setEnabled(false);
                break;
            case 1:
                infoLabel.setText("1 process running.");
                cancelAllButton.setEnabled(true);
                break;
            default:
                infoLabel.setText(count + " processes running.");
                cancelAllButton.setEnabled(true);
                break;
        }
    }

    private static void invokeAndWait(@Nonnull Runnable r) {
        if (SwingUtilities.isEventDispatchThread()) {
            r.run();
        } else {
            try {
                SwingUtilities.invokeAndWait(r);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        infoPanel = new javax.swing.JPanel();
        disclosureToggle = new javax.swing.JToggleButton();
        infoLabel = new javax.swing.JLabel();
        cancelAllButton = new javax.swing.JButton();
        strutPanel = new javax.swing.JPanel();
        viewPanel = new javax.swing.JPanel();
        separator = new javax.swing.JSeparator();
        scrollPane = new javax.swing.JScrollPane();

        setTitle("Progress Monitor");
        infoPanel.setLayout(new java.awt.GridBagLayout());

        infoPanel.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(12, 12, 12, 12)));
        disclosureToggle.setSelected(true);
        disclosureToggle.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(0, 0, 0, 0)));
        disclosureToggle.setFocusPainted(false);
        disclosureToggle.setContentAreaFilled(false);
        disclosureToggle.setBorderPainted(false);
        disclosureToggle.setRequestFocusEnabled(false);
        disclosureToggle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                disclosureStateChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        infoPanel.add(disclosureToggle, gridBagConstraints);

        infoLabel.setText("No processes running.");
        infoLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        gridBagConstraints.weightx = 1.0;
        infoPanel.add(infoLabel, gridBagConstraints);

        cancelAllButton.setText("Cancel All");
        cancelAllButton.setEnabled(false);
        cancelAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelAll(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        infoPanel.add(cancelAllButton, gridBagConstraints);

        getContentPane().add(infoPanel, java.awt.BorderLayout.NORTH);

        strutPanel.setLayout(null);

        strutPanel.setPreferredSize(new java.awt.Dimension(400, 0));
        getContentPane().add(strutPanel, java.awt.BorderLayout.SOUTH);

        viewPanel.setLayout(new java.awt.BorderLayout());

        viewPanel.add(separator, java.awt.BorderLayout.NORTH);

        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        viewPanel.add(scrollPane, java.awt.BorderLayout.CENTER);

        getContentPane().add(viewPanel, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

    private void disclosureStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_disclosureStateChanged
        viewPanel.setVisible(disclosureToggle.isSelected());
        pack();
    }//GEN-LAST:event_disclosureStateChanged

    private void cancelAll(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelAll
        Component[] components = progressPanel.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof ProgressView) {
                ((ProgressView) components[i]).cancel();
            }
        }
    }//GEN-LAST:event_cancelAll

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JSeparator separator;
    private javax.swing.JPanel strutPanel;
    private javax.swing.JToggleButton disclosureToggle;
    private javax.swing.JButton cancelAllButton;
    private javax.swing.JPanel viewPanel;
    private javax.swing.JPanel infoPanel;
    // End of variables declaration//GEN-END:variables

}
