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
public class LabelItem {
    
    protected String _text;
    
    protected Object _tag;
    
    public LabelItem( String text, Object tag) {
        _text = text;
        
        _tag = tag;
    }

    /**
     * @return the _text
     */
    public String getText() {
        return _text;
    }

    /**
     * @return the _tag
     */
    public Object getTag() {
        return _tag;
    }
    
    
    
}
