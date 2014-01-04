/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.controller;

import jc.se.tail.model.document.*;
import jc.se.tail.model.document.view.DocumentViewBase;

/**
 *
 * @author ruffy
 */
public class DocumentViewController {
    
    protected DocumentViewPackage _documentViewPackage;
    
    public DocumentViewController() {
        
    } 
    
    public void addDocumentView(DocumentViewBase documentView) {
        
    }
    
    public void removeDocumentView(DocumentViewBase documentView) {
        
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
