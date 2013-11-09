/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.document.view;

import jc.se.tail.model.document.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import jc.se.tail.model.document.Document;

/**
 *
 * @author ruffy
 */
public class DocumentFilterView extends DocumentViewBase implements Observer{
    
    protected String _filterString;
    protected int _rowsAbove;
    protected int _rowsAfter;
        
    public DocumentFilterView(Document document, String filterString, int rowsAbove, int rowsAfter) {
        super();
        _filterString = filterString;
        _rowsAbove = rowsAbove;
        _rowsAfter = rowsAfter;
        
        setParentDocumentView(document);

    }
    
    public DocumentFilterView(String filterString, int rowsAbove, int rowsAfter) {
        super();
        _filterString = filterString;
        _rowsAbove = rowsAbove;
        _rowsAfter = rowsAfter;
    }
    
    public DocumentFilterView(){
        super();
    }

    @Override
    public List<String> getTextLines(int startLine) throws IOException {
        List<String> documentLines = _parentDocumentView.getTextLines(startLine - getRowsAbove());
        
        List<String> filteredDocumentRows = new ArrayList<String>();
        
        Queue<Integer> rowsPartOfFilter = new LinkedList<Integer>();
        
        for(int i = 0; i< documentLines.size(); i++) {
            String line = documentLines.get(i);
            if(line.indexOf(getFilterString()) > -1){
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
            } catch (IOException ex) {
                Logger.getLogger(DocumentFilterView.class.getName()).log(Level.SEVERE, null, ex);
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
   
    
    
    
}
