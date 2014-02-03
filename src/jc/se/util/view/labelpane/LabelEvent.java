/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.util.view.labelpane;

/**
 *
 * @author ruffy
 */
public class LabelEvent {
    private Object _key;
    private String _labelText;

    public LabelEvent(Object key, String labelText) {
        _key = key;
        _labelText = labelText;
    }
    
    public Object getKey() {
        return _key;
    }

    public String getLabelText() {
        return _labelText;
    }
}
