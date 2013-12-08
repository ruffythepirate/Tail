/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.FileChooserUI;
import jc.se.tail.manager.DocumentManager;
import jc.se.tail.model.document.Document;
import jc.se.tail.model.document.view.SortedView;
import jc.se.util.view.TabCloseActionHandler;

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

    private class CloseFileTabActionHandler extends TabCloseActionHandler {

        Document _document;
        
        public CloseFileTabActionHandler(JTabbedPane tabbedPane, Component tabComponent, Document document) {
            super(tabbedPane, tabComponent);
            _document = document;
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            super.actionPerformed(evt);
            try {
                //Unregisters listener for the document.
                _documentManager.stopTrackDocument(_document);
            } catch (Exception ex) {
                Logger.getLogger(TailWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        _fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        _editMenu = new javax.swing.JMenu();
        _searchMenuItem = new javax.swing.JMenuItem();
        _sortingMenuItem = new javax.swing.JMenu();
        _sortMenuItem = new javax.swing.JMenuItem();
        _sortDescMenuItem = new javax.swing.JMenuItem();
        _filterMenuItem = new javax.swing.JMenuItem();
        _reformatRowsMenuItem = new javax.swing.JMenuItem();
        _helpMenu = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        _tabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        _tabbedPane.setPreferredSize(new java.awt.Dimension(300, 300));
        _tabbedPane.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                _tabbedPaneComponentAdded(evt);
            }
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                _tabbedPaneComponentRemoved(evt);
            }
        });
        getContentPane().add(_tabbedPane, java.awt.BorderLayout.CENTER);

        _fileMenu.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        _fileMenu.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        _fileMenu.add(jMenuItem2);

        jMenuBar1.add(_fileMenu);

        _editMenu.setText("Edit");
        _editMenu.setEnabled(false);

        _searchMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK));
        _searchMenuItem.setText("Search");
        _searchMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _searchMenuItemActionPerformed(evt);
            }
        });
        _editMenu.add(_searchMenuItem);

        _sortingMenuItem.setText("Sorting");

        _sortMenuItem.setText("Sort Asc");
        _sortMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _sortMenuItemActionPerformed(evt);
            }
        });
        _sortingMenuItem.add(_sortMenuItem);

        _sortDescMenuItem.setText("Sort Desc");
        _sortDescMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _sortDescMenuItemActionPerformed(evt);
            }
        });
        _sortingMenuItem.add(_sortDescMenuItem);

        _editMenu.add(_sortingMenuItem);

        _filterMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        _filterMenuItem.setText("Filter");
        _filterMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _filterMenuItemActionPerformed(evt);
            }
        });
        _editMenu.add(_filterMenuItem);

        _reformatRowsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        _reformatRowsMenuItem.setText("Reformat Rows");
        _reformatRowsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _reformatRowsMenuItemActionPerformed(evt);
            }
        });
        _editMenu.add(_reformatRowsMenuItem);

        jMenuBar1.add(_editMenu);

        _helpMenu.setText("Help");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem3.setText("About");
        _helpMenu.add(jMenuItem3);

        jMenuBar1.add(_helpMenu);

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

                    FileTailPane filePanel = new FileTailPane(document);
                    addClosableTabbedPane(selectedFile, filePanel);

                } catch (IOException ex) {
                    Logger.getLogger(TailWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(TailWindow.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void addClosableTabbedPane(File selectedFile, FileTailPane filePanel) {
        _tabbedPane.addTab(selectedFile.getName(), null, filePanel, selectedFile.getAbsolutePath());

        int index = _tabbedPane.indexOfComponent(filePanel);
        JPanel pnlTab = new JPanel(new GridBagLayout());
        pnlTab.setOpaque(false);
        JLabel lblTitle = new JLabel(selectedFile.getName());
        JButton btnClose = new JButton("x");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;

        pnlTab.add(lblTitle, gbc);

        gbc.gridx++;
        gbc.weightx = 0;
        pnlTab.add(btnClose, gbc);

        _tabbedPane.setTabComponentAt(index, pnlTab);

        btnClose.addActionListener(new TabCloseActionHandler(_tabbedPane, filePanel));
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        //Exits the program!
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void _filterMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__filterMenuItemActionPerformed

        FileTailPane selectedPanel = getActiveFilePane();

        if (selectedPanel != null) {
            FilterDialog settings = new FilterDialog(this, true);

            settings.setVisible(true);

            boolean shouldFilter = settings.getShouldFilter();
            if (shouldFilter) {
                //We create a filter for the current tab.
                selectedPanel.appendFilter(settings.getFilterText(),
                        settings.getRowsBefore(), settings.getRowsAfter(),
                        settings.getShouldExcludeRows());
            }
        }

    }//GEN-LAST:event__filterMenuItemActionPerformed

    private FileTailPane getActiveFilePane() {
        FileTailPane selectedPanel = (FileTailPane) _tabbedPane.getSelectedComponent();
        return selectedPanel;
    }

    private void _searchMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__searchMenuItemActionPerformed
        FileTailPane selectedPanel = getActiveFilePane();
        if (selectedPanel != null) {
            Action searchAction = selectedPanel.getActionMap().get("activate search");
            if (searchAction != null) {
                searchAction.actionPerformed(null);
            }
        }
    }//GEN-LAST:event__searchMenuItemActionPerformed

    private void _reformatRowsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__reformatRowsMenuItemActionPerformed

        FileTailPane selectedPanel = getActiveFilePane();

        if (selectedPanel != null) {
            RegularExpressionDialog settings = new RegularExpressionDialog(this, true);

            settings.setVisible(true);

            boolean shouldFilter = settings.isShouldFilter();
            if (shouldFilter) {
                //We create a filter for the current tab.
                selectedPanel.appendReformat(
                        settings.getSearchText(),
                        settings.getReplacementText());
            }
        }
    }//GEN-LAST:event__reformatRowsMenuItemActionPerformed

    private void _sortMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__sortMenuItemActionPerformed
        //A sort view is added to the view bag.
        FileTailPane selectedPanel = getActiveFilePane();

        if (selectedPanel != null) {
            SortedView sortView = new SortedView(false);
            selectedPanel.addDocumentView(sortView);
        }        
    }//GEN-LAST:event__sortMenuItemActionPerformed

    private void _sortDescMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__sortDescMenuItemActionPerformed
         FileTailPane selectedPanel = getActiveFilePane();

        if (selectedPanel != null) {
            SortedView sortView = new SortedView(true);
            selectedPanel.addDocumentView(sortView);
        } 
    }//GEN-LAST:event__sortDescMenuItemActionPerformed

    private void _tabbedPaneComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event__tabbedPaneComponentAdded
        updateMenuItemsEnabled();
    }//GEN-LAST:event__tabbedPaneComponentAdded

    private void _tabbedPaneComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event__tabbedPaneComponentRemoved
        updateMenuItemsEnabled();
    }//GEN-LAST:event__tabbedPaneComponentRemoved

    private void updateMenuItemsEnabled(){
        _editMenu.setEnabled(_tabbedPane.getTabCount() > 0);
    }
    
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
    private javax.swing.JMenu _editMenu;
    private javax.swing.JFileChooser _fileChooser;
    private javax.swing.JMenu _fileMenu;
    private javax.swing.JMenuItem _filterMenuItem;
    private javax.swing.JMenu _helpMenu;
    private javax.swing.JMenuItem _reformatRowsMenuItem;
    private javax.swing.JMenuItem _searchMenuItem;
    private javax.swing.JMenuItem _sortDescMenuItem;
    private javax.swing.JMenuItem _sortMenuItem;
    private javax.swing.JMenu _sortingMenuItem;
    private javax.swing.JTabbedPane _tabbedPane;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    // End of variables declaration//GEN-END:variables
}
