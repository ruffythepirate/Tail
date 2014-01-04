/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.command;

import jc.se.tail.model.document.Document;
import jc.se.util.event.EventBase;

/**
 *
 * @author ruffy
 */
public class DisplayDocumentCommand extends EventBase{
   
    public static final String PUBLISH_TOPIC = "tail.document.display";
    
    private Document _documentToDisplay;
       
    
    public DisplayDocumentCommand(Object sender, Document documentToDisplay) {
        super(sender);
        setDocumentToDisplay(documentToDisplay);
    }

    /**
     * @return the _documentToDisplay
     */
    public Document getDocumentToDisplay() {
        return _documentToDisplay;
    }

    /**
     * @param _documentToDisplay the _documentToDisplay to set
     */
    public void setDocumentToDisplay(Document _documentToDisplay) {
        this._documentToDisplay = _documentToDisplay;
    }
}
