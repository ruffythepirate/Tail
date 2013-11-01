/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.document;

import jc.se.tail.model.document.Document;
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
        
    public DocumentFilterView(Document document, String filterString, int rowsAbove, int rowsAfter) {
        _filterString = filterString;
        _rowsAbove = rowsAbove;
        _rowsAfter = rowsAfter;
        
        setParentDocumentView(document);

    }
    
    public DocumentFilterView(String filterString, int rowsAbove, int rowsAfter) {
        _filterString = filterString;
        _rowsAbove = rowsAbove;
        _rowsAfter = rowsAfter;
    }

    @Override
    public List<String> getTextLines(int startLine) throws IOException {
        List<String> documentLines = _parentDocumentView.getTextLines(startLine - _rowsAbove);
        
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
        if(_parentDocumentView != null) {
        return _parentDocumentView.getDocumentTotalRows();
        }
        return 0;
    }

    @Override
    public int getViewPortalTotalRows() {
        return 0;
    }    

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
   
    
    
    
}
