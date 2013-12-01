/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.document;

import jc.se.tail.model.document.view.DocumentViewBase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import jc.se.tail.model.document.view.DocumentViewUpdatedArgs;

/**
 *
 * @author ruffy
 */
public class SimpleDocumentView extends DocumentViewBase implements Observer{
    
    public SimpleDocumentView(DocumentViewBase parentDocumentView){
        _parentDocumentView = parentDocumentView;
        _documentLines = new ArrayList<>();
    }
    
    @Override
    public List<String> getTextLines(int startLine) throws Exception {
        return _parentDocumentView.getTextLines(startLine);
    }

    @Override
    public int getDocumentTotalRows() {
         return _parentDocumentView.getDocumentTotalRows();
    }

    @Override
    public int getViewPortalTotalRows() {
         return _parentDocumentView.getViewPortalTotalRows();
   }

        @Override
    public String getViewTitle() {
        return "Simple";
    }

    
    @Override
    public void update(Observable o, Object arg) {
        DocumentViewUpdatedArgs sendArgs;
        if(arg instanceof DocumentViewUpdatedArgs)
        {
            sendArgs = new DocumentViewUpdatedArgs( ((DocumentViewUpdatedArgs) arg).getUpdatedFromViewLine());
        } else {
            sendArgs = new DocumentViewUpdatedArgs(0);
        }
        setChanged();
        notifyObservers(sendArgs);
    }
    
    
}
