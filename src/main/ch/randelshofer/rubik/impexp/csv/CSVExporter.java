/*
 * @(#)CSVExporter.java  1.0  April 12, 2004
 * Copyright (c) 2004 Werner Randelshofer, Switzerland.
 * You may only use this software in accordance with the license terms.
 */

package ch.randelshofer.rubik.impexp.csv;

import ch.randelshofer.cubetwister.doc.*;
import ch.randelshofer.rubik.impexp.*;
import ch.randelshofer.gui.*;
import ch.randelshofer.gui.tree.*;
import ch.randelshofer.util.*;
import ch.randelshofer.io.*;
import ch.randelshofer.rubik.*;
import ch.randelshofer.rubik.parser.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
/**
 * CSVExporter.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class CSVExporter extends AbstractExporter {
    private final static long serialVersionUID = 1L;
    private TranslateNotationComboBoxModel translateModel;
    private char delimiterChar;
    
    
    /** Creates new form. */
    public CSVExporter(char delimiterChar) {
        initComponents();
        translateModel = new TranslateNotationComboBoxModel();
        translateComboBox.setModel(translateModel);
        this.delimiterChar = delimiterChar;
    }
    
    public void setDocumentModel(DocumentModel documentModel) {
        super.setDocumentModel(documentModel);
        translateModel.setDocumentModel(documentModel);
    }
    
    /**
     * Exports the DocumentModel to the provided file.
     */
    public void exportFile(File file, ProgressObserver p) throws IOException {
        ArrayList records;
                if (translateModel.getSelectedItem() != TranslateNotationComboBoxModel.DO_NOT_TRANSLATE) {
            NotationModel translator = (NotationModel) translateModel.getSelectedItem();
            records = createScriptRecords(translator, p);
        } else {
            records = createScriptRecords(p);
            
        }

        p.setNote("Writing file...");
        
        CSVWriter out = null;
        
        try {
            out = new CSVWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file))), delimiterChar, '"', "\n");
            String line;
            
            // The first line contains the column headers
            out.writeln(new String[] {
                "Name", "Notation", "Script", "Macros", "Notes", "Author", "Date",
                "LTM","FTM","QTM","Visual Order","Real Order", "Visual Permutation",
                "Real Permutation"
            });
            
                
            Iterator iter = records.iterator();
            while (iter.hasNext()) {
                ScriptRecord sr = (ScriptRecord) iter.next();
                out.write(sr.name);
                out.write(sr.notation);
                out.write(sr.script);

                // Add the macros to the data record
                CharArrayWriter buf = new CharArrayWriter();
                CSVWriter csvBuf = new CSVWriter(buf, '=', '"', "\n");
                for (int i=0; i < sr.macros.length; i++ ) {
                    String identifier = sr.macros[i][0];
                    String macroScript = sr.macros[i][1];
                    
                    csvBuf.write(identifier);
                    csvBuf.write(macroScript);
                    csvBuf.writeln();
                }
                csvBuf.close();
                out.write(buf.toString());
                
                out.write(sr.description);
                out.write(sr.author);
                out.write(sr.date);
                out.writeInt(sr.ltm);
                out.writeInt(sr.ftm);
                out.writeInt(sr.qtm);
                out.writeInt(sr.visualOrder);
                out.writeInt(sr.realOrder);
                out.write(sr.visualPermutation);
                out.write(sr.realPermutation);
                out.writeln();
            }
        } finally {
            if (out != null) out.close();
        }
        
        p.setProgress(p.getProgress()+1);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        translateLabel = new javax.swing.JLabel();
        translateComboBox = new javax.swing.JComboBox();

        setLayout(new java.awt.GridBagLayout());

        translateLabel.setText("Translate Notation Into");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(translateLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(translateComboBox, gridBagConstraints);

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox translateComboBox;
    private javax.swing.JLabel translateLabel;
    // End of variables declaration//GEN-END:variables
    
}
