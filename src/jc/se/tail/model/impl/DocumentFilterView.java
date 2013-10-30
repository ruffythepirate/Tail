/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

/**
 *
 * @author ruffy
 */
public class DocumentFilterView extends DocumentViewBase implements Observer{
    
    private String _filterString;
    private int _rowsAbove;
    private int _rowsAfter;
    
    private Document _document;
    
    public DocumentFilterView(Document document, String filterString, int rowsAbove, int rowsAfter) {
        _filterString = filterString;
        _rowsAbove = rowsAbove;
        _rowsAfter = rowsAfter;
        _document = document;
        
        document.addObserver(this);

    }

    @Override
    public List<String> getTextLines(int startLine) throws IOException {
        List<String> documentLines = _document.getTextLines(startLine - _rowsAbove);
        
        List<String> filteredDocumentRows = new ArrayList<String>();
        
        Queue<Integer> rowsPartOfFilter = new LinkedList<Integer>();
        
        for(int i = 0; i< documentLines.size(); i++) {
            String line = documentLines.get(i);
            if(line.indexOf(_filterString) > -1){
                rowsPartOfFilter.add(i);
            }
        }
        
        Integer lastIndex = null;
        Integer nextIndex = rowsPartOfFilter.poll();
        for(int i = 0; i< documentLines.size(); i++) {
            if(nextIndex != null && i == nextIndex) {
                filteredDocumentRows.add(documentLines.get(i));
                nextIndex = rowsPartOfFilter.poll();
            } else if( (lastIndex != null && i <= lastIndex + _rowsAfter)
                    || (nextIndex != null && i >= nextIndex - _rowsAbove)) 
            {
                filteredDocumentRows.add(documentLines.get(i));
            }
        }
        
        return filteredDocumentRows;
    }

    @Override
    public int getDocumentTotalRows() {
        return _document.getNumberOfLines();
    }

    @Override
    public int getViewPortalTotalRows() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
   
    
    
    
}
