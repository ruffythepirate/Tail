/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.view;

import java.awt.Color;
import javax.swing.BorderFactory;

/**
 *
 * @author ruffy
 */
public class FilterDialog extends javax.swing.JDialog {

    private boolean _shouldFilter;
            
    
    /**
     * Creates new form FilterDialog
     */
    public FilterDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getRootPane().setBorder( BorderFactory.createLineBorder(Color.BLUE) );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        _cancelBtn = new javax.swing.JButton();
        _filterBtn = new javax.swing.JButton();
        _rowsBelowNbr = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        _rowsAboveNbr = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        _filterStringTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        _cancelBtn.setText("Cancel");
        _cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _cancelBtnActionPerformed(evt);
            }
        });

        _filterBtn.setText("Filter");
        _filterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _filterBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Rows Below");

        jLabel2.setText("Rows Above");

        _filterStringTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _filterStringTxtActionPerformed(evt);
            }
        });

        jLabel1.setText("Filter Text");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(_filterStringTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(_rowsAboveNbr, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(_rowsBelowNbr, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(_filterBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_cancelBtn)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_filterStringTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_rowsAboveNbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_rowsBelowNbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_filterBtn)
                    .addComponent(_cancelBtn)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void _cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__cancelBtnActionPerformed
        // TODO add your handling code here:
        _shouldFilter = false;
        setVisible(false);
    }//GEN-LAST:event__cancelBtnActionPerformed

    private void _filterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__filterBtnActionPerformed
        // TODO add your handling code here:
        _shouldFilter = true;
        setVisible(false);
    }//GEN-LAST:event__filterBtnActionPerformed

    private void _filterStringTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__filterStringTxtActionPerformed
         _shouldFilter = true;
        setVisible(false);       
        // TODO add your handling code here:
    }//GEN-LAST:event__filterStringTxtActionPerformed

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
            java.util.logging.Logger.getLogger(FilterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FilterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FilterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FilterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FilterDialog dialog = new FilterDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton _cancelBtn;
    private javax.swing.JButton _filterBtn;
    private javax.swing.JTextField _filterStringTxt;
    private javax.swing.JSpinner _rowsAboveNbr;
    private javax.swing.JSpinner _rowsBelowNbr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the _shouldFilter
     */
    public boolean getShouldFilter() {
        return _shouldFilter;
    }

    /**
     * @return the _filterText
     */
    public String getFilterText() {
        return _filterStringTxt.getText();
    }

    /**
     * @param _filterText the _filterText to set
     */
    public void setFilterText(String filterText) {
        _filterStringTxt.setText(filterText);
    }

    /**
     * @return the _rowsBefore
     */
    public int getRowsBefore() {
        return (int)_rowsAboveNbr.getValue();
    }

    /**
     * @param _rowsBefore the _rowsBefore to set
     */
    public void setRowsBefore(int _rowsBefore) {
        _rowsAboveNbr.setValue(_rowsBefore);
    }

    /**
     * @return the _rowsAfter
     */
    public int getRowsAfter() {
        return (int)_rowsBelowNbr.getValue();
    }

    /**
     * @param _rowsAfter the _rowsAfter to set
     */
    public void setRowsAfter(int rowsAfter) {
        _rowsBelowNbr.setValue(rowsAfter);
    }
}
