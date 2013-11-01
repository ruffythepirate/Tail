/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.document;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ruffy
 */
public class DocumentViewPackage {

    private DocumentViewBase _rootView;

    private final List<DocumentViewBase> _documentViews;

    public DocumentViewPackage(DocumentViewBase rootView) {
        _rootView = rootView;

        _documentViews = new ArrayList<DocumentViewBase>();
    }

    public void appendDocumentView(DocumentViewBase documentView) {

        DocumentViewBase parentView;
        if (_documentViews.size() > 0) {
            parentView = _documentViews.get(_documentViews.size() - 1);
        } else {
            parentView = _rootView;
        }

        documentView.setParentDocumentView(parentView);
        _documentViews.add(documentView);
    }

    public void removeDocumentView(DocumentViewBase documentView) {

        for (DocumentViewBase addedView : _documentViews) {
            if (addedView == documentView) {
                _documentViews.remove(addedView);
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

}
