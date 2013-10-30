/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.impl;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author ruffy
 */
public class TailDocumentView extends DocumentViewBase implements Observer{
    private int _startLine;
    
    private int _numberOfLines;
    
    private DocumentViewBase _parentDocumentView;
    
    public TailDocumentView(DocumentViewBase parentDocument){
        _parentDocumentView = parentDocument;
        
        
    }
    
    @Override
    public List<String> getTextLines(int startLine) throws IOException {
        return _parentDocumentView.getTextLines(startLine);
    }

    @Override
    public int getDocumentTotalRows() {
         return _parentDocumentView.getDocumentTotalRows();
    }

    @Override
    public int getViewPortalTotalRows() {
         return _parentDocumentView.getViewPortalTotalRows();
   }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
    
    
}
