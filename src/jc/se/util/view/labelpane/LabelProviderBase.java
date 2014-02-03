/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.util.view.labelpane;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author ruffy
 */
public abstract class LabelProviderBase extends Observable {
    
    List<ILabelProviderListener> _labelListeners;
    
    public LabelProviderBase(){
        _labelListeners = new ArrayList<>();
    }
    
    public abstract void removeLabel(Object labelKey);
    
    public void addLabelListener(ILabelProviderListener labelListener) {
        _labelListeners.add(labelListener);
    }
    
    public void removeLabelListener(ILabelProviderListener labelListener) {
        _labelListeners.remove(labelListener);
    }
    
    protected void triggerLabelAdded(Object key, String labelText) {
        LabelEvent labelEvent = new LabelEvent(key, labelText);
        
        for(ILabelProviderListener labelListener : _labelListeners) {
            labelListener.OnLabelAdded(labelEvent);
        }
    }
    
    protected void triggerLabelRemove(Object key, String labelText) {
        LabelEvent labelEvent = new LabelEvent(key, labelText);
        
        for(ILabelProviderListener labelListener : _labelListeners) {
            labelListener.OnLabelRemoved(labelEvent);
        }        
    }
}
