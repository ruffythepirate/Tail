/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.util.view.labelpane;

import java.util.Observable;

/**
 *
 * @author ruffy
 */
public class LabelPaneEventDispatcher extends Observable {
    
    public void triggerTryRemoveLabel(LabelItem itemToRemove) {
        setChanged();
        notifyObservers(this);
    }
    
    public void triggerTryMoveLabel(LabelItem itemToMove, int newIndex) {
        
    }
    
}
