/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.document.view;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author ruffy
 */
public class ProxyDocumentView extends DocumentViewBase {

    protected DocumentViewBase _wrappedDocumentView;

    public ProxyDocumentView(DocumentViewBase wrappedDocumentView) {
        _wrappedDocumentView = wrappedDocumentView;
    }

    @Override
    public List<String> getTextLines(int startLine) throws Exception {
        return _wrappedDocumentView.getTextLines(startLine);
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }

    @Override
    public int getDocumentTotalRows() {
        return _wrappedDocumentView.getDocumentTotalRows(); 
    }

    @Override
    public int getViewPortalTotalRows() {
        return _wrappedDocumentView.getViewPortalTotalRows(); 
    }
    
    
            

    /**
     * @return the _wrappedDocumentView
     */
    public DocumentViewBase getWrappedDocumentView() {
        return _wrappedDocumentView;
    }

    
    
    /**
     * @param _wrappedDocumentView the _wrappedDocumentView to set
     */
    public void setWrappedDocumentView(DocumentViewBase wrappedDocumentView) {
        if (wrappedDocumentView != _wrappedDocumentView) {
            _wrappedDocumentView.deleteObserver(this);
            _wrappedDocumentView = wrappedDocumentView;            
            _wrappedDocumentView.addObserver(this);
            
            DocumentViewUpdatedArgs event = new DocumentViewUpdatedArgs(0);
            setChanged();
            notifyObservers(event);
        }
    }

}
