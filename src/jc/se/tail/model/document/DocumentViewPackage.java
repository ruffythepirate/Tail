/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.document;

import jc.se.tail.model.document.view.DocumentViewBase;
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
    
    private final List<IViewPackageListener> _viewPackageListeners;
    
    protected ProxyDocumentView _packageResultView;

    @Override
    public String getShortSourceName() {
        return _packageResultView.getShortSourceName();
    }

    @Override
    public String getSourceName() {
        return _packageResultView.getSourceName();
    }

    @Override
    public String getSourceId() {
        return _packageResultView.getSourceId();
    }

    public DocumentViewPackage(DocumentViewBase rootView) {
        _rootView = rootView;

        _packageResultView = new ProxyDocumentView(rootView);
        
        _documentViews = new ArrayList<DocumentViewBase>();
        _viewPackageListeners = new ArrayList<>();
    }
    
    public void addViewPackageListener(IViewPackageListener listener) {
        _viewPackageListeners.add(listener);
    }
    
    public void removeViewPackageListener(IViewPackageListener listener) {
        _viewPackageListeners.remove(listener);
    }
    
    private void notifyViewAdded(DocumentViewBase view) {
        for(IViewPackageListener listener : _viewPackageListeners) {
            listener.viewAddedReceived(view);
        }        
    }
    
    private void notifyViewRemoved(DocumentViewBase view) {
        for(IViewPackageListener listener : _viewPackageListeners) {
            listener.viewRemovedReceived(view);
        }
    }

    public void addDocumentView(DocumentViewBase documentView) {

        DocumentViewBase parentView = getLastDocumentInPackage();
        if (parentView == null) {
            parentView = _rootView;
        }
        documentView.setParentDocumentView(parentView);
        _documentViews.add(documentView);        
        _packageResultView.setWrappedDocumentView(documentView);
        
        notifyViewAdded(documentView);
    }

    public void removeDocumentView(DocumentViewBase documentViewToRemove) {

        
        for (DocumentViewBase addedView : _documentViews) {
            if (addedView == documentViewToRemove) {
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

        notifyViewRemoved(documentViewToRemove);
    }

    public DocumentViewBase getCompiledView() {
        if (_documentViews.size() > 0) {
            return _documentViews.get(_documentViews.size() - 1);
        }
        return _rootView;
    }

    @Override
    public List<String> getTextLines(int startLine) throws Exception {
        DocumentViewBase lastView = getLastDocumentInPackage();
        if(lastView != null)
        {
            return lastView.getTextLines(startLine);
        }
        return _packageResultView.getTextLines(startLine);
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
