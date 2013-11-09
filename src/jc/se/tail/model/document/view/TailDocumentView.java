/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.document.view;

import jc.se.tail.model.document.view.DocumentViewBase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author ruffy
 */
public class TailDocumentView extends DocumentViewBase implements Observer{

    
    public TailDocumentView(DocumentViewBase parentDocument){
        _parentDocumentView = parentDocument;
        
        _documentLines = new ArrayList<String>();
    }
    
    @Override
    public List<String> getTextLines(int startLine) throws IOException {

        List<String> returnLines = _parentDocumentView.getTextLines(
                Math.max(startLine, _documentLines.size()));
        
        for(String returnLine : returnLines) {
            _documentLines.add(returnLine);
        }

        if(startLine > 0) {
            List<String> returnArray = new ArrayList<String>();
            for(int i = startLine; i < _documentLines.size(); i++) {
                returnArray.add(_documentLines.get(i));
            }
            return returnArray;
        }
        
        return _documentLines;
    }

            @Override
    public String getViewTitle() {
        return "Tail";
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
