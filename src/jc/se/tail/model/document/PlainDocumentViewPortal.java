/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.document;

import jc.se.tail.model.document.FileDocument;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author ruffy
 */
public class PlainDocumentViewPortal extends Observable implements Observer, IDocumentViewPortal{

    private FileDocument _document;
    
    public PlainDocumentViewPortal(FileDocument parentDocument){
        _document = parentDocument;
        parentDocument.addObserver(this);
    }
    
    @Override
    public List<String> getTextLines(int startLine) throws IOException {
        return _document.getTextLines(startLine);
    }

    @Override
    public int getDocumentTotalRows() {
        return _document.getNumberOfLines();
    }

    @Override
    public int getViewPortalTotalRows() {
        return _document.getNumberOfLines();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
    
}
