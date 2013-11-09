/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.controller;

import jc.se.tail.model.document.view.DocumentViewBase;
import jc.se.tail.model.document.DocumentViewPackage;
import jc.se.util.view.labelpane.ILabelController;
import jc.se.util.view.labelpane.LabelItem;

/**
 *
 * @author ruffy
 */
public class FilterLabelController implements ILabelController{

    protected DocumentViewPackage _documentViewPackage;
    
    public FilterLabelController(DocumentViewPackage documentViewPackage) {
        _documentViewPackage = documentViewPackage;
    }
    
    @Override
    public void RemoveLabel(LabelItem labelItem) {
        if(labelItem.getTag() instanceof DocumentViewBase) {
            _documentViewPackage.removeDocumentView((DocumentViewBase) labelItem.getTag());
        }
    }

    /**
     * @return the _documentViewPackage
     */
    public DocumentViewPackage getDocumentViewPackage() {
        return _documentViewPackage;
    }

    /**
     * @param _documentViewPackage the _documentViewPackage to set
     */
    public void setDocumentViewPackage(DocumentViewPackage _documentViewPackage) {
        this._documentViewPackage = _documentViewPackage;
    }
    
}
