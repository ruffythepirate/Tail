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
    
    private List<String> _labels;
    
    public LabelList()  {
        _labels = new ArrayList<String>( );
        
    }
    
    public void addLabel(String label) {
        if(!_labels.contains(label)) {
            getLabels().add(label);
            setChanged();
            LabelsUpdatedEvent event = new LabelsUpdatedEvent(label, LabelsUpdatedEvent.EVENT_LABEL_ADDED);
            notifyObservers(event);
        }        
    }
    
    public void removeLabel(String label) {
        if(getLabels().contains(label)) {
            getLabels().remove(label);
            setChanged();
            
            LabelsUpdatedEvent event = new LabelsUpdatedEvent(label, LabelsUpdatedEvent.EVENT_LABEL_REMOVED);
            notifyObservers(event);
        }
    }

    public List<String> getLabels() {
        return _labels;
    }
    
}
