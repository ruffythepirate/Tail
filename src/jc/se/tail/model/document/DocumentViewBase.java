/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.document;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author ruffy
 */
public abstract class DocumentViewBase extends Observable implements IDocumentViewPortal, Observer {

    protected List<String> _documentLines;
    protected DocumentViewBase _parentDocumentView;
    
    @Override
    public abstract List<String> getTextLines(int startLine) throws IOException;

    @Override
    public int getDocumentTotalRows(){
                if(_parentDocumentView != null) {
        return _parentDocumentView.getDocumentTotalRows();
        }
        return 0;

    }

    @Override
    public int getViewPortalTotalRows() {
      if(_parentDocumentView != null) {
        return _parentDocumentView.getViewPortalTotalRows();
        }
        return 0;

    }

    public void clearCachedData()
    {
        _documentLines.clear();
        if(_parentDocumentView != null) {
            _parentDocumentView.clearCachedData();
        }
    }
    
    public String getViewTitle() {
        return "Base";
    }
    
    /**
     * @return the _parentDocumentView
     */
    public DocumentViewBase getParentDocumentView() {
        return _parentDocumentView;
    }

    /**
     * @param _parentDocumentView the _parentDocumentView to set
     */
    public void setParentDocumentView(DocumentViewBase _parentDocumentView) {
        if(_parentDocumentView != null) {
            _parentDocumentView.deleteObserver(this);
        }
        
        this._parentDocumentView = _parentDocumentView;
        _parentDocumentView.addObserver(this);
    }

    @Override
    public abstract void update(Observable o, Object arg) ;

}
