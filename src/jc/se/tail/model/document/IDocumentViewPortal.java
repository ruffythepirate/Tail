/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.document;

import java.io.IOException;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author ruffy
 */
public interface IDocumentViewPortal {
    List<String> getTextLines(int startLine) throws IOException;
    
    int getDocumentTotalRows();
    
    int getViewPortalTotalRows();
    
    void addObserver(Observer observer);
    void deleteObserver(Observer observer);
}
