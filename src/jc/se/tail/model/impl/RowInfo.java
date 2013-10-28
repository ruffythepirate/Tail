/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.impl;

/**
 *
 * @author Ruffy
 */
public class RowInfo {
    
    public RowInfo(long endPosition) {
        _rowEndPosition = endPosition;
    }
    
    private long _rowEndPosition;

    public long getRowEndPosition() {
        return _rowEndPosition;
    }

    public void setRowEndPosition(long rowEndPosition) {
        this._rowEndPosition = rowEndPosition;
    }
       
}
