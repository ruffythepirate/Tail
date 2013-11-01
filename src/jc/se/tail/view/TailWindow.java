/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.view;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.plaf.FileChooserUI;
import jc.se.tail.manager.DocumentManager;
import jc.se.tail.model.document.Document;

/**
 *
 * @author Ruffy
 */
public class TailWindow extends javax.swing.JFrame {

    private DocumentManager _documentManager;

    /**
     * Creates new form TailWindow
     */
    public TailWindow() {
        initComponents();

        _documentManager = new DocumentManager();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        _fileChooser = new javax.swing.JFileChooser();
        _tabbedPane = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        _searchMenuItem = new javax.swing.JMenuItem();
        _filterMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        _tabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        _tabbedPane.setPreferredSize(new java.awt.Dimension(300, 300));
        getContentPane().add(_tabbedPane, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Edit");

        _searchMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK));
        _searchMenuItem.setText("Search");
        _searchMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _searchMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(_searchMenuItem);

        _filterMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        _filterMenuItem.setText("Filter");
        _filterMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _filterMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(_filterMenuItem);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Help");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem3.setText("About");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // The user get to choose a file to tail.

        _fileChooser.setMultiSelectionEnabled(true);
        int fileSelected = _fileChooser.showDialog(this, "Open");
        if (fileSelected == JFileChooser.APPROVE_OPTION) {
            //We open the file.
            File[] selectedFiles = _fileChooser.getSelectedFiles();

            for (File selectedFile : selectedFiles) {
                Document document = new Document(selectedFile);
                try {
                    _documentManager.startTrackDocument(document);
                    FileTailPanel filePanel = new FileTailPanel(document);
                    _tabbedPane.addTab(selectedFile.getName(), filePanel);
                } catch (IOException ex) {
                    Logger.getLogger(TailWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(TailWindow.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        //Exits the program!
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void _filterMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__filterMenuItemActionPerformed

        FileTailPanel selectedPanel = getActiveFilePane();

        if (selectedPanel != null) {
            FilterDialog settings = new FilterDialog(this, true);

            settings.setVisible(true);

            boolean shouldFilter = settings.getShouldFilter();
            if (shouldFilter) {
                //We create a filter for the current tab.
                selectedPanel.appendFilter(settings.getFilterText(),
                        settings.getRowsBefore(), settings.getRowsAfter());
            }
        }

    }//GEN-LAST:event__filterMenuItemActionPerformed

    private FileTailPanel getActiveFilePane() {
        // TODO add your handling code here:
        FileTailPanel selectedPanel = (FileTailPanel) _tabbedPane.getSelectedComponent();
        return selectedPanel;
    }

    private void _searchMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__searchMenuItemActionPerformed
        FileTailPanel selectedPanel = getActiveFilePane();
        if(selectedPanel != null) {
            Action searchAction =  selectedPanel.getActionMap().get("activate search");
            if(searchAction != null) {
                searchAction.actionPerformed(null);
            }
        }
    }//GEN-LAST:event__searchMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TailWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TailWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TailWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TailWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TailWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser _fileChooser;
    private javax.swing.JMenuItem _filterMenuItem;
    private javax.swing.JMenuItem _searchMenuItem;
    private javax.swing.JTabbedPane _tabbedPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    // End of variables declaration//GEN-END:variables
}
