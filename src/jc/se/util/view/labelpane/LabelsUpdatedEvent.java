/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.util.view.labelpane;

/**
 *
 * @author Ruffy
 */
public class LabelsUpdatedEvent {
    
    private LabelItem _label;
    
    private String _eventType;
    
    public static String EVENT_LABEL_ADDED = "Added";
    public static String EVENT_LABEL_REMOVED = "Removed";
    
    public LabelsUpdatedEvent(LabelItem label, String eventType) {
        _label = label;
        _eventType = eventType;
    }

    public LabelItem getLabel() {
        return _label;
    }

    public String getEventType() {
        return _eventType;
    }
    
}
