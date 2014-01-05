/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.controller;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import jc.se.tail.manager.IDocumentManager;
import jc.se.tail.model.document.DocumentViewPackage;
import jc.se.tail.service.IDocumentService;

/**
 *
 * @author ruffy
 */
public class TailController {
    
    private IDocumentManager _documentManager;

    private IDocumentService _documentService;
    
    /**
     * @return the _documentManager
     */
    public IDocumentManager getDocumentManager() {
        return _documentManager;
    }

    /**
     * @param _documentManager the _documentManager to set
     */
    public void setDocumentManager(IDocumentManager _documentManager) {
        this._documentManager = _documentManager;
    }
    
    public void closeDocument(DocumentViewPackage documentViewPackageToClose) {
        try {
           
            _documentService.closeDocumentViewPackage(documentViewPackageToClose);
        } catch (Exception ex) {
            Logger.getLogger(TailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void openDocument(File fileToOpen) {
        try {
            getDocumentService().openFile(fileToOpen);
        } catch (Exception ex) {
            Logger.getLogger(TailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    /**
     * @return the _documentService
     */
    public IDocumentService getDocumentService() {
        return _documentService;
    }

    /**
     * @param _documentService the _documentService to set
     */
    public void setDocumentService(IDocumentService _documentService) {
        this._documentService = _documentService;
    }
    
}
