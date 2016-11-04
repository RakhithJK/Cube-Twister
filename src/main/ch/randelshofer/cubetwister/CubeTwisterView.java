/*
 * @(#)CubeTwisterView.java  1.0  January 3, 2006
 * Copyright (c) 2005 Werner Randelshofer, Switzerland.
 * You may only use this software in accordance with the license terms.
 */
package ch.randelshofer.cubetwister;

import ch.randelshofer.cubetwister.doc.*;
import ch.randelshofer.gui.*;
import ch.randelshofer.rubik.impexp.csv.CSVExporter;
import ch.randelshofer.rubik.impexp.cubeexplorer.CubeExplorerExporter;
import ch.randelshofer.undo.*;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import nanoxml.*;
import org.jhotdraw.app.*;
import org.jhotdraw.app.action.edit.RedoAction;
import org.jhotdraw.app.action.edit.UndoAction;
import org.jhotdraw.gui.BackgroundTask;
import org.jhotdraw.gui.JFileURIChooser;
import org.jhotdraw.gui.URIChooser;
import org.jhotdraw.gui.Worker;
import org.jhotdraw.gui.filechooser.ExtensionFileFilter;
import org.jhotdraw.net.URIUtil;

/**
 * CubeTwisterView.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class CubeTwisterView extends AbstractView implements Viewer {

    private final static long serialVersionUID = 1L;
    /**
     * The Cube view.
     */
    private CubeView cubeView;
    /**
     * The Notation view.
     */
    private NotationView notationView;
    /**
     * The script view.
     */
    private ScriptView scriptView;
    /**
     * The text view.
     */
    private TextView textView;
    /**
     * The Undo Manager.
     */
    private UndoRedoManager undo;
    private JExplorer explorer;
    private ChangeListener changeListener;
    /**
     * We cache the template document, so that we can faster open new
     * application windows.
     */
    private static XMLElement cachedTemplate;

    /**
     * Creates a new instance.
     */
    public CubeTwisterView() {
        initComponents();
        setPreferredSize(new Dimension(600, 400));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void init() {
        super.init();

        undo = new UndoRedoManager();
        undo.addChangeListener(changeListener = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent evt) {
                updateUnsavedChanges();
            }
        });

        getActionMap().put(UndoAction.ID, undo.getUndoAction());
        getActionMap().put(RedoAction.ID, undo.getRedoAction());

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                explorer = new JExplorer();
                explorer.getTree().setCellRenderer(new DocumentModelTreeCellRenderer());
                add(explorer);
                validate();
            }
        });
    }

    @Override
    public void clear() {
        initFromTemplate();
    }

    @Override
    public boolean canSaveTo(URI uri) {
        return new File(uri).getName().endsWith(".xml");
    }

    /**
     * Reads the template file - if it exists.
     *
     * <p>The template file is called template.xml and
     * is expected to be at the users home directory
     * in the subdirectory Library/Preferences/CubeTwister
     * or in the same directory where the DocumentView
     * class is stored.
     *
     * <p>This method may take a lot of time. Don't call
     * it in the AWTEventDispatcher thread.
     */
    public void initFromTemplate() {
        setEnabled(false);
        execute(new Worker<DocumentModel>() {

            @Override
            public DocumentModel construct() {
                return readTemplate();
            }

            @Override
            public void done(DocumentModel value) {
                DocumentModel model = value;
                if (explorer == null || model == null) {
                    return;
                }
                explorer.setViewer(CubeTwisterView.this);
                explorer.setTreeModel(model);
                explorer.expandAll(1, 5);

                getActionMap().put(UndoAction.ID, undo.getUndoAction());
                getActionMap().put(RedoAction.ID, undo.getRedoAction());

                model.addUndoableEditListener(undo);
                explorer.setUndoManager(undo);
                setEnabled(true);
            }
        });
    }

    private void updateUnsavedChanges() {
        setHasUnsavedChanges(undo.hasSignificantEdits());
    }

    public static DocumentModel readTemplate() {
        DocumentModel documentModel = new DocumentModel();

        long start = System.currentTimeMillis();
        if (cachedTemplate == null) {
            InputStream in = null;
            try {
                File userTemplate = userTemplate = new File(
                        System.getProperty("user.home")
                        + File.separatorChar + "Library"
                        + File.separatorChar + "Preferences"
                        + File.separatorChar + "CubeTwister"
                        + File.separatorChar + "template.xml");

                if (userTemplate.exists()) {
                    in = new FileInputStream(userTemplate);
                } else {
                    in = DocumentModel.class.getResourceAsStream("template.xml");
                    if (in == null) {
                        throw new IOException(userTemplate.toString());
                    }
                }
                in = new BufferedInputStream(in);
                Reader r = new InputStreamReader(in, "UTF8");
                XMLElement xml = new XMLElement(new HashMap<String, char[]>(), false, false);
                //.newInstance().newDocumentBuilder().parse(in);
                xml.parseFromReader(r);
                cachedTemplate = xml;

            } catch (Throwable e) {
                System.out.println("Error XML parsing template: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        long end1 = System.currentTimeMillis();

        try {
            documentModel.addXMLNode(cachedTemplate);
        } catch (Throwable e) {
            System.out.println("Error creating document from template: " + e.getMessage());
            e.printStackTrace();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("DocumentModel.readXML buildDOM=" + (end1 - start) + " parseDOM=" + (end2 - end1));

        return documentModel;
    }

    /**
     * Sets the value of the viewer to value.
     *
     * @param parent This is the component into which the viewer will be
     * embedded.
     * @param value This is the object to be displayed.
     */
    @Override
    public Component getComponent(Component parent, final Object value) {
        // FIXME - Get rid of all these instanceof tests.

        Component c = null;
        if (value instanceof CubeModel) {
            if (cubeView == null) {
                cubeView = new CubeView();
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        cubeView.init();
                        cubeView.validate();
                        SwingUtilities.invokeLater(new Runnable() {

                            @Override
                            public void run() {
                                cubeView.setModel((CubeModel) value);
                            }
                        });
                    }
                });
            } else {
                cubeView.setModel((CubeModel) value);
            }
            c = cubeView;
        } else if (value instanceof NotationModel) {
            if (notationView == null) {
                notationView = new NotationView((NotationModel) value);
            } else {
                notationView.setModel((NotationModel) value);
            }
            c = notationView;
        } else if (value instanceof ScriptModel) {
            if (scriptView == null) {
                scriptView = new ScriptView((ScriptModel) value);
            } else {
                scriptView.setModel((ScriptModel) value);
            }
            c = scriptView;
        } else if (value instanceof TextModel) {
            if (textView == null) {
                textView = new TextView((TextModel) value);
            } else {
                textView.setModel((TextModel) value);
            }
            c = textView;
        } else {
            c = null;
        }

        if (cubeView != null && c != cubeView) {
            cubeView.setModel(null);
        }
        if (notationView != null && c != notationView) {
            notationView.setModel(null);
        }
        if (scriptView != null && c != scriptView) {
            scriptView.setModel(null);
        }
        if (textView != null && c != textView) {
            textView.setModel(null);
        }

        return c;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void read(URI uri, URIChooser chooser) throws IOException {
        File f = new File(uri);

        final DocumentModel newModel = new DocumentModel();

        InputStream in = null;
        try {
            in = new FileInputStream(f);
            in = new BufferedInputStream(in);

            newModel.addSerializedNode(in);

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        // We need a worker here, because we must avoid race conditions
        // with initFromTemplate().
        execute(new BackgroundTask() {

            @Override
            public void construct() {
                // empty
            }

            @Override
            public void finished() {
                /*
                 if (explorer.getTreeModel() instanceof DocumentModel) {
                 DocumentModel oldModel = (DocumentModel) explorer.getTreeModel();
                 oldModel.removeUndoableEditListener(undo);
                 }
                 explorer.setTreeModel(newModel);
                 explorer.expandAll(1, 5);
                 newModel.addUndoableEditListener(undo);*/
                DocumentModel model = newModel;
                if (explorer == null || model == null) {
                    return;
                }
                if (explorer.getTreeModel() instanceof DocumentModel) {
                    DocumentModel oldModel = (DocumentModel) explorer.getTreeModel();
                    oldModel.removeUndoableEditListener(undo);
                }
                explorer.setViewer(CubeTwisterView.this);
                explorer.setTreeModel(model);
                explorer.expandAll(1, 5);

                getActionMap().put(UndoAction.ID, undo.getUndoAction());
                getActionMap().put(RedoAction.ID, undo.getRedoAction());

                undo.addChangeListener(changeListener = new ChangeListener() {

                    @Override
                    public void stateChanged(ChangeEvent evt) {
                        updateUnsavedChanges();
                    }
                });

                model.addUndoableEditListener(undo);
                explorer.setUndoManager(undo);
                setEnabled(true);
            }
        });
    }

    public DocumentModel getModel() {
        return (DocumentModel) explorer.getTreeModel();
    }

    @Override
    public void write(URI uri, URIChooser chooser) throws IOException {
        JFileURIChooser fc = (JFileURIChooser) chooser;
        // ------
        if (fc != null && (fc.getFileFilter() instanceof ExtensionFileFilter)) {
            ExtensionFileFilter eff = (ExtensionFileFilter) fc.getFileFilter();
            if (eff.getExtensions().contains("html")) {
                File f = new File(uri);
                HTMLExporter exporter = new HTMLExporter();
                String documentName = (getURI() == null) ? "unnamed" : URIUtil.getName(getURI());
                if (documentName.endsWith(".xml")) {
                    documentName = documentName.substring(0, documentName.length() - 4);
                }
                ProgressView p = new ProgressView("Exporting " + documentName + " as HTML", "Initializing...");
                p.setIndeterminate(true);
                try {
                    exporter.exportToDirectory(documentName, getModel(), f, p);
                } finally {
                    p.close();
                }
                return;
            } else if (eff.getExtensions().contains("csv")) {
                File f = new File(uri);
                CSVExporter exporter = new CSVExporter(',');
                String documentName = (getURI() == null) ? "unnamed" : URIUtil.getName(getURI());
                if (documentName.endsWith(".xml")) {
                    documentName = documentName.substring(0, documentName.length() - 4);
                }
                ProgressView p = new ProgressView("Exporting " + documentName + " as CSV", "Initializing...");
                p.setIndeterminate(true);
                try {
                    exporter.setDocumentModel(getModel());
                    exporter.exportFile(f, p);
                } finally {
                    p.close();
                }
                return;
            } else if (eff.getExtensions().contains("txt")) {
                File f = new File(uri);
                CubeExplorerExporter exporter = new CubeExplorerExporter();
                String documentName = (getURI() == null) ? "unnamed" : URIUtil.getName(getURI());
                if (documentName.endsWith(".xml")) {
                    documentName = documentName.substring(0, documentName.length() - 4);
                }
                ProgressView p = new ProgressView("Exporting " + documentName + " as CubeExplorer", "Initializing...");
                p.setIndeterminate(true);
                try {
                    exporter.setDocumentModel(getModel());
                    exporter.exportFile(f, p);
                } finally {
                    p.close();
                }
                return;
            }
        }
        // -----
        File f = new File(uri);
        DocumentModel model = (DocumentModel) explorer.getTreeModel();
        PrintWriter out = null;
        try {
            // Defensively write the data in a buffer before we overwrite
            // the file on this.
            StringWriter buf = new StringWriter();
            out = new PrintWriter(buf);
            model.writeXML(out);
            out.close();
            out = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(f)), "UTF8"));
            out.write(buf.toString());
            //model.writeXML(out);
        } finally {
            if (out != null) {
                out.close();
            }
        }
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                undo.discardAllEdits();
            }
        });
    }

    @Override
    public void dispose() {
        //    System.out.println("CubeTwisterView.dispose "+this);
        removeAll();

        if (cubeView != null) {
            cubeView.dispose();
        }
        if (notationView != null) {
            notationView.setModel(null);
        }
        if (scriptView != null) {
            scriptView.setModel(null);
        }
        if (textView != null) {
            textView.setModel(null);
        }

        explorer = null;
        cubeView = null;
        notationView = null;
        scriptView = null;
        textView = null;

        if (explorer != null) {
            DocumentModel m = (DocumentModel) explorer.getTreeModel();
            explorer.setTreeModel(null);
            explorer.setUndoManager(null);
            m.removeUndoableEditListener(undo);
            m.dispose();
        }

        if (undo != null) {
            undo.removeChangeListener(changeListener);
            changeListener = null;
            undo.discardAllEdits();
            undo = null;
        }
        super.dispose();
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        if (explorer != null) {
            explorer.setEnabled(b);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CubeTwisterProject.finalize " + this);
    }
}
