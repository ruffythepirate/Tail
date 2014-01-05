/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.document.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import jc.se.tail.model.document.IDocumentViewPortal;

/**
 *
 * @author ruffy
 */
public abstract class DocumentViewBase extends Observable implements IDocumentViewPortal, Observer {

    protected List<String> _documentLines;
    protected DocumentViewBase _parentDocumentView;

    public DocumentViewBase() {
        _documentLines = new ArrayList<>();
    }

    public String getSourceName() {
        if (_parentDocumentView != null) {
            return _parentDocumentView.getSourceName();
        }
        return "N/A";
    }

    public String getShortSourceName() {
        if (_parentDocumentView != null) {
            return _parentDocumentView.getShortSourceName();
        }
        return "N/A";
    }
    
    public String getSourceId() {
        if (_parentDocumentView != null) {
            return _parentDocumentView.getSourceId();
        }
        return "N/A";
        
    }

    @Override
    public abstract List<String> getTextLines(int startLine) throws Exception;

    @Override
    public int getDocumentTotalRows() {
        if (_parentDocumentView != null) {
            return _parentDocumentView.getDocumentTotalRows();
        }
        return getViewPortalTotalRows();

    }

    @Override
    public int getViewPortalTotalRows() {
        try {
            return getTextLines(0).size();
        } catch (Exception ex) {
            Logger.getLogger(DocumentViewBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public void clearCachedData() {
        _documentLines.clear();
        if (_parentDocumentView != null) {
            _parentDocumentView.clearCachedData();
        }
        _documentLines = null;
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
        if (_parentDocumentView != null) {
            _parentDocumentView.deleteObserver(this);
        }

        this._parentDocumentView = _parentDocumentView;
        _parentDocumentView.addObserver(this);
        invalidateText(0);
    }

    protected void invalidateText(int fromLine) {
        while (_documentLines.size() > fromLine) {
            _documentLines.remove(fromLine);
        }
        DocumentViewUpdatedArgs event = new DocumentViewUpdatedArgs(fromLine);
        setChanged();
        notifyObservers(event);
    }

    @Override
    public abstract void update(Observable o, Object arg);

}
