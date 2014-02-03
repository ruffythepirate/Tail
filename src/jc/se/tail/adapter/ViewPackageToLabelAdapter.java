/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.adapter;

import java.util.Observable;
import java.util.Observer;
import jc.se.tail.model.document.DocumentViewPackage;
import jc.se.tail.model.document.IViewPackageListener;
import jc.se.tail.model.document.view.DocumentViewBase;
import jc.se.util.view.labelpane.LabelProviderBase;

/**
 *
 * @author ruffy
 */
public class ViewPackageToLabelAdapter extends LabelProviderBase {

    private DocumentViewPackage _documentViewPackage;

    public ViewPackageToLabelAdapter(DocumentViewPackage documentViewPackage) {
        _documentViewPackage = documentViewPackage;

        _documentViewPackage.addViewPackageListener(new IViewPackageListener() {

            @Override
            public void viewAddedReceived(DocumentViewBase view) {
                
                triggerLabelAdded(view, view.getViewTitle());
            }

            @Override
            public void viewRemovedReceived(DocumentViewBase view) {
                triggerLabelRemove(view, "");
            }
        });
    }

    @Override
    public void removeLabel(Object labelKey) {

        if (labelKey instanceof DocumentViewBase) {
            DocumentViewBase viewToRemove = (DocumentViewBase) labelKey;
           _documentViewPackage.removeDocumentView(viewToRemove);
        }
    }

}
