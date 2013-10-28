/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.impl;

/**
 *
 * @author Ruffy
 */
public class SearchResultHit {
    private int _rowNumber;
    private int _hitStart;
    private int _hitEnd;
    
    public SearchResultHit(int rowNumber, int hitStart, int hitEnd) {
        _rowNumber = rowNumber;
        _hitStart = hitStart;
        _hitEnd = hitEnd;
    }

    public int getRowNumber() {
        return _rowNumber;
    }

    public int getHitStart() {
        return _hitStart;
    }

    public int getHitEnd() {
        return _hitEnd;
    }
}
