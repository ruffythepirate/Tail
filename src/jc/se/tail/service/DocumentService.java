/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.service;

import java.io.File;
import jc.se.tail.model.impl.Document;
import jc.se.tail.model.impl.IDocument;

/**
 *
 * @author ruffy
 */
public class DocumentService {
    IDocument getDocument(File file) {
       return new Document(file); 
    }
    
    public int countDocumentRows(Document document){
        document
        
    }
}
