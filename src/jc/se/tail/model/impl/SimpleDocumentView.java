/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.impl;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author ruffy
 */
public class SimpleDocumentView extends DocumentViewBase{

    private DocumentViewBase _parentDocumentView;
    
    public SimpleDocumentView(DocumentViewBase parentDocumentView){
        _parentDocumentView = parentDocumentView;
    }
    
    @Override
    public List<String> getTextLines(int startLine) throws IOException {
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
    
    
}
