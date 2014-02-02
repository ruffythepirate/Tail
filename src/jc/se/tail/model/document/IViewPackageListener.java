/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.document;

import jc.se.tail.model.document.view.DocumentViewBase;

/**
 *
 * @author ruffy
 */
public interface IViewPackageListener {
    void viewAddedReceived(DocumentViewBase view);
    
    void viewRemovedReceived(DocumentViewBase view);
}
