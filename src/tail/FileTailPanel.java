/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tail;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import jc.se.tail.model.impl.Document;

/**
 *
 * @author Ruffy
 */
public class FileTailPanel extends javax.swing.JPanel implements Observer {

    private Document _documentToTrack;

    /**
     * Creates new form FileTailPanel
     */
    public FileTailPanel(Document documentToTrack) {
        initComponents();

        _documentToTrack = documentToTrack;
        _documentToTrack.addObserver(this);
        
        updateDisplayedDocumentText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        _tailFileEnd = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        _fileContentTxt = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);

        _tailFileEnd.setText("Tail");
        _tailFileEnd.setFocusable(false);
        _tailFileEnd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        _tailFileEnd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(_tailFileEnd);

        add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        _fileContentTxt.setEditable(false);
        _fileContentTxt.setBackground(new java.awt.Color(51, 51, 51));
        _fileContentTxt.setColumns(20);
        _fileContentTxt.setForeground(new java.awt.Color(153, 153, 153));
        _fileContentTxt.setRows(5);
        jScrollPane1.setViewportView(_fileContentTxt);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea _fileContentTxt;
    private javax.swing.JToggleButton _tailFileEnd;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    private void updateDisplayedDocumentText() {
        String newline = System.getProperty("line.separator");
        _fileContentTxt.setText(null);
        File fileToTrack = _documentToTrack.getFile();
        List<String> allLines;
        try {
            allLines = Files.readAllLines(fileToTrack.toPath(), Charset.defaultCharset());
            for (String fileLine : allLines) {
                _fileContentTxt.append(fileLine);
                _fileContentTxt.append(newline);
            }

        } catch (IOException ex) {
            Logger.getLogger(FileTailPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(_tailFileEnd.isEnabled()) {
            _fileContentTxt.setCaretPosition(_fileContentTxt.getDocument().getLength());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        updateDisplayedDocumentText();
    }
}
