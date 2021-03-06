/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.manager.impl;

import jc.se.tail.model.document.DocumentViewPackage;
import jc.se.tail.model.document.view.DocumentViewBase;

/**
 *
 * @author ruffy
 */
public class DocumentViewManager {
    
    private DocumentViewPackage _currentDocumentPackage;
    
    
    public void AddViewToCurrentPackage(DocumentViewBase documentView){
        if(_currentDocumentPackage != null) {
            _currentDocumentPackage.addDocumentView(documentView);
        }
    }
    
    public void RemoveViewFromCurrentPackage(DocumentViewBase documentView) {
        if(_currentDocumentPackage != null) {
            _currentDocumentPackage.removeDocumentView(documentView);
        }
    }
    
    public void SetCurrentPackage(DocumentViewPackage packageToFocus) {
        _currentDocumentPackage = packageToFocus;
    }
    
}
