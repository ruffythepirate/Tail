/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.impl;

/**
 *
 * @author ruffy
 */
public class DocumentFilterView {
    
    private String _filterString;
    private int _rowsAbove;
    private int _rowsAfter;
    
    public DocumentFilterView(String filterString, int rowsAbove, int rowsAfter) {
        _filterString = filterString;
        _rowsAbove = rowsAbove;
        _rowsAfter = rowsAfter;
    }
    
}
