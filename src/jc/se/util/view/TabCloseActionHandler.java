/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.util.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
import sun.swing.SwingUtilities2;

/**
 *
 * @author ruffy
 */
public class TabCloseActionHandler implements ActionListener {

    protected Component _component;
    protected JTabbedPane _tabbedPane;

    public TabCloseActionHandler(JTabbedPane tabbedPane, Component component) {
        _component = component;
        _tabbedPane = tabbedPane;
    }

    public void actionPerformed(ActionEvent evt) {

        Component paneToClose = getComponent();
        int index = getTabbedPane().indexOfComponent(paneToClose);
        if (index >= 0) {
            getTabbedPane().removeTabAt(index);
        }

    }

    /**
     * @return the _component
     */
    public Component getComponent() {
        return _component;
    }

    /**
     * @return the _tabbedPane
     */
    public JTabbedPane getTabbedPane() {
        return _tabbedPane;
    }

} 
