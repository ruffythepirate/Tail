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
public class DocumentViewPortal {
    private int _startLine;
    
    private int _numberOfLines;
    
    private Document _document;
    
    public DocumentViewPortal(Document parentDocument){
        _document = parentDocument;
    }
    
    public List<String> getTextLines(int startLine, int numberOfLines) throws IOException {
        return _document.getTextLines(startLine, numberOfLines);
    }
    
    
}
