/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tail;

import java.awt.Dimension;
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
    private int _currentNumberOfShowedLines;

    /**
     * Creates new form FileTailPanel
     */
    public FileTailPanel(Document documentToTrack) {
        initComponents();
        doLayout();
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
        jButton1 = new javax.swing.JButton();
        _scrollPane = new javax.swing.JScrollPane();
        _fileContentTxt = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);

        _tailFileEnd.setText("Tail");
        _tailFileEnd.setFocusable(false);
        _tailFileEnd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        _tailFileEnd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        _tailFileEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _tailFileEndActionPerformed(evt);
            }
        });
        jToolBar1.add(_tailFileEnd);

        jButton1.setText("Refresh");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        _fileContentTxt.setEditable(false);
        _fileContentTxt.setBackground(new java.awt.Color(51, 51, 51));
        _fileContentTxt.setColumns(20);
        _fileContentTxt.setForeground(new java.awt.Color(153, 153, 153));
        _fileContentTxt.setRows(5);
        _fileContentTxt.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                handleContentTextSizeChanged(evt);
            }
        });
        _scrollPane.setViewportView(_fileContentTxt);

        add(_scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void handleContentTextSizeChanged(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_handleContentTextSizeChanged
    }//GEN-LAST:event_handleContentTextSizeChanged

    private void _tailFileEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__tailFileEndActionPerformed
        if (_tailFileEnd.isEnabled()) {
            //We scroll to the bottom of the document.
            scrollToBottom();
        }
    }//GEN-LAST:event__tailFileEndActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        _fileContentTxt.setText(null);
        _currentNumberOfShowedLines = 0;
       updateDisplayedDocumentText();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea _fileContentTxt;
    private javax.swing.JScrollPane _scrollPane;
    private javax.swing.JToggleButton _tailFileEnd;
    private javax.swing.JButton jButton1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    private int getTextAreaNumberOfLines() {
        double height = _scrollPane.getSize().getHeight();
        int lineHeight = getLineHeight();

        return (int) Math.ceil(height / lineHeight);
    }

    private void updateDisplayedDocumentText() {
        try {
            String newline = System.getProperty("line.separator");

            _documentToTrack.analyzeFile();
            
            List<String> allLines = _documentToTrack.getTextLines(_currentNumberOfShowedLines);

            for (String fileLine : allLines) {
                _fileContentTxt.append(fileLine);
                _fileContentTxt.append(newline);
            }

            if (_tailFileEnd.isEnabled()) {
                scrollToBottom();
            }
            _currentNumberOfShowedLines += allLines.size();
        } catch (IOException ex) {
            Logger.getLogger(FileTailPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        updateDisplayedDocumentText();
    }

    private int getLineHeight() {
        int lineHeight = _fileContentTxt.getFontMetrics(_fileContentTxt.getFont()).getHeight();
        return lineHeight;
    }

    private void scrollToBottom() {
        _fileContentTxt.setCaretPosition(_fileContentTxt.getDocument().getLength());
    }
}
