/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.service.impl;

import java.io.File;
import jc.se.tail.command.DisplayDocumentCommand;
import jc.se.tail.manager.IDocumentManager;
import jc.se.tail.model.document.Document;
import jc.se.tail.service.IDocumentService;
import jc.se.util.event.IEventManager;

/**
 *
 * @author ruffy
 */
public class DocumentService implements IDocumentService{
    
    
    private IDocumentManager _documentManager;
    
    private IEventManager _eventManager;
        
    /**
     * Opens the file as a document in the application. This is done creating a document form the file and then publishing a request 
     * to open that specific document in the views.
     * @param file The file that wants to be tailed.
     */
    public void openFile(File file) throws Exception {
        // 1) Create the document, and subscribe to it.
        Document newDocument = new Document(file);
        
        getDocumentManager().startTrackDocument(newDocument);
        
        // 2) Send an event to display the document.
        DisplayDocumentCommand displayCommand = new DisplayDocumentCommand(this, newDocument);
        getEventManager().publishEvent(DisplayDocumentCommand.PUBLISH_TOPIC,
                displayCommand);
    }

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

    /**
     * @return the _eventManager
     */
    public IEventManager getEventManager() {
        return _eventManager;
    }

    /**
     * @param _eventManager the _eventManager to set
     */
    public void setEventManager(IEventManager _eventManager) {
        this._eventManager = _eventManager;
    }
    
}
