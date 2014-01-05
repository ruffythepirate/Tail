/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.command;

import jc.se.tail.model.document.DocumentViewPackage;
import jc.se.util.event.EventBase;

/**
 *
 * @author ruffy
 */
public class DisplayDocumentCommand extends EventBase{
   
    public static final String PUBLISH_TOPIC = "tail.document.display";
    
    private DocumentViewPackage _documentViewPackageToDisplay;
       
    
    public DisplayDocumentCommand(Object sender, DocumentViewPackage documentToDisplay) {
        super(sender);
        setDocumentToDisplay(documentToDisplay);
    }

    /**
     * @return the _documentToDisplay
     */
    public DocumentViewPackage getDocumentToDisplay() {
        return _documentViewPackageToDisplay;
    }

    /**
     * @param _documentToDisplay the _documentToDisplay to set
     */
    public void setDocumentToDisplay(DocumentViewPackage _documentToDisplay) {
        this._documentViewPackageToDisplay = _documentToDisplay;
    }
}
