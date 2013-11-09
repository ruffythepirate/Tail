/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.document;

import jc.se.tail.model.document.view.DocumentViewBase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import jc.se.tail.model.document.view.ProxyDocumentView;

/**
 *
 * @author ruffy
 */
public class DocumentViewPackage extends DocumentViewBase {

    private DocumentViewBase _rootView;

    private final List<DocumentViewBase> _documentViews;
    
    protected ProxyDocumentView _packageResultView;
    
    

    public DocumentViewPackage(DocumentViewBase rootView) {
        _rootView = rootView;

        _packageResultView = new ProxyDocumentView(rootView);
        
        _documentViews = new ArrayList<DocumentViewBase>();
    }

    public void appendDocumentView(DocumentViewBase documentView) {

        DocumentViewBase parentView = getLastDocumentInPackage();
        if (parentView == null) {
            parentView = _rootView;
        }
        documentView.setParentDocumentView(parentView);
        _documentViews.add(documentView);        
        _packageResultView.setWrappedDocumentView(documentView);
    }

    public void removeDocumentView(DocumentViewBase documentView) {

        
        for (DocumentViewBase addedView : _documentViews) {
            if (addedView == documentView) {
                addedView.deleteObserver(this);
                _documentViews.remove(addedView);
                if( getPackageResultView().getWrappedDocumentView() == addedView)
                {
                    getPackageResultView().setWrappedDocumentView(addedView.getParentDocumentView());
                }
                break;
            }
        }

        DocumentViewBase previousView = _rootView;

        for (DocumentViewBase addedView : _documentViews) {
            addedView.setParentDocumentView(previousView);
            previousView = addedView;

        }

    }

    public DocumentViewBase getCompiledView() {
        if (_documentViews.size() > 0) {
            return _documentViews.get(_documentViews.size() - 1);
        }
        return _rootView;
    }

    @Override
    public List<String> getTextLines(int startLine) throws IOException {
        DocumentViewBase lastView = getLastDocumentInPackage();
        if(lastView != null)
        {
            return lastView.getTextLines(startLine);
        }
        return new ArrayList<>();
    }

    private DocumentViewBase getLastDocumentInPackage(){
        if(_documentViews.size() > 0) {
            return _documentViews.get(_documentViews.size() - 1);
        }
        return null;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(o == getLastDocumentInPackage()) {
            setChanged();
            notifyObservers(arg);
        }
    }

    /**
     * @return the _packageResultView
     */
    public ProxyDocumentView getPackageResultView() {
        return _packageResultView;
    }

}
