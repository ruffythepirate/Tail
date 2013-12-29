/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.service.impl;

import jc.se.tail.model.document.DocumentViewPackage;
import jc.se.tail.service.IApplicationService;

/**
 *
 * @author ruffy
 */
public class ApplicationService implements IApplicationService{

    private DocumentViewPackage _focusedDocumentPackage;
    
    @Override
    public void setFocusedDocumentPackage(DocumentViewPackage documentViewPackage) {
        _focusedDocumentPackage = documentViewPackage;
    }

    @Override
    public DocumentViewPackage getFocusedDocumentPackage() {
        return _focusedDocumentPackage;
    }
    
}
