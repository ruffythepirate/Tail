/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.util.view.labelpane;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Ruffy
 */
public class LabelList extends Observable {
    
    private List<LabelItem> _labels;
    
    public LabelList()  {
        _labels = new ArrayList<LabelItem>( );
        
    }
    
    public LabelItem addLabel(String text, Object tag) {        
        LabelItem label = new LabelItem(text, tag);
        
        if(!_labels.contains(label)) {
            getLabels().add(label);
            setChanged();
            LabelsUpdatedEvent event = new LabelsUpdatedEvent(label, LabelsUpdatedEvent.EVENT_LABEL_ADDED);
            notifyObservers(event);
            clearChanged();
            return label;
        }        
        return null;
    }
    
    public void removeLabel(LabelItem label) {
        if(getLabels().contains(label)) {
            getLabels().remove(label);
            setChanged();
            
            LabelsUpdatedEvent event = new LabelsUpdatedEvent(label, LabelsUpdatedEvent.EVENT_LABEL_REMOVED);
            notifyObservers(event);
            clearChanged();
        }
    }

    public List<LabelItem> getLabels() {
        return _labels;
    }
    
}
