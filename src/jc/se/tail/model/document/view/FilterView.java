/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.document.view;

import jc.se.tail.model.document.FileDocument;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruffy
 */
public class FilterView extends DocumentViewBase implements Observer{
    
    protected String _filterString;
    protected int _rowsAbove;
    protected int _rowsAfter;
    protected boolean _shouldExcludeRows;
        
    public FilterView(FileDocument document, String filterString, int rowsAbove, int rowsAfter) {
        super();
        _filterString = filterString;
        _rowsAbove = rowsAbove;
        _rowsAfter = rowsAfter;
        
        setParentDocumentView(document);

    }
    
    public FilterView(String filterString, int rowsAbove, int rowsAfter) {
        super();
        _filterString = filterString;
        _rowsAbove = rowsAbove;
        _rowsAfter = rowsAfter;
    }
    
    public FilterView(){
        super();
    }

    @Override
    public List<String> getTextLines(int startLine) throws Exception {
        List<String> documentLines = _parentDocumentView.getTextLines(startLine - getRowsAbove());
        
        List<String> filteredDocumentRows = new ArrayList<String>();
        
        Queue<Integer> rowsPartOfFilter = new LinkedList<Integer>();
        
        for(int i = 0; i< documentLines.size(); i++) {
            String line = documentLines.get(i);
            if(    (!_shouldExcludeRows
                    && line.indexOf(getFilterString()) > -1)
                || (_shouldExcludeRows
                   && line.indexOf(getFilterString()) == -1)){
                rowsPartOfFilter.add(i);
            } 
        }
        
        Integer lastIndex = null;
        Integer nextIndex = rowsPartOfFilter.poll();
        for(int i = 0; i< documentLines.size(); i++) {
            if(nextIndex != null && i == nextIndex) {
                filteredDocumentRows.add(documentLines.get(i));
                nextIndex = rowsPartOfFilter.poll();
            } else if( (lastIndex != null && i <= lastIndex + getRowsAfter())
                    || (nextIndex != null && i >= nextIndex - getRowsAbove())) 
            {
                filteredDocumentRows.add(documentLines.get(i));
            }
        }
        
        _documentLines = filteredDocumentRows;
        
        return filteredDocumentRows;
    }

    @Override
    public String getViewTitle() {
        return "F: " + _filterString;
    }
    
    @Override
    public int getDocumentTotalRows() {
        if(_parentDocumentView != null) {
        return _parentDocumentView.getDocumentTotalRows();
        }
        return 0;
    }

    @Override
    public int getViewPortalTotalRows() {
        if(_documentLines == null ) {
            try {
                getTextLines(0);
            } catch (Exception ex) {
                Logger.getLogger(FilterView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return _documentLines.size();
    }    

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

    /**
     * @return the _filterString
     */
    public String getFilterString() {
        return _filterString;
    }

    /**
     * @param _filterString the _filterString to set
     */
    public void setFilterString(String _filterString) {
        this._filterString = _filterString;
        _documentLines = null;
    }

    /**
     * @return the _rowsAbove
     */
    public int getRowsAbove() {
        return _rowsAbove;
    }

    /**
     * @param _rowsAbove the _rowsAbove to set
     */
    public void setRowsAbove(int _rowsAbove) {
        this._rowsAbove = _rowsAbove;
    }

    /**
     * @return the _rowsAfter
     */
    public int getRowsAfter() {
        return _rowsAfter;
    }

    /**
     * @param _rowsAfter the _rowsAfter to set
     */
    public void setRowsAfter(int _rowsAfter) {
        this._rowsAfter = _rowsAfter;
    }

    /**
     * @return the _shouldExcludeRows
     */
    public boolean isShouldExcludeRows() {
        return _shouldExcludeRows;
    }

    /**
     * @param _shouldExcludeRows the _shouldExcludeRows to set
     */
    public void setShouldExcludeRows(boolean shouldExcludeRows) {
        this._shouldExcludeRows = shouldExcludeRows;
    }
   
    
    
    
}
