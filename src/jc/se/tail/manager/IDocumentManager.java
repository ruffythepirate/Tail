/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.manager;

import java.io.File;
import jc.se.tail.model.document.DocumentViewPackage;

/**
 *
 * @author ruffy
 */
public interface IDocumentManager {
    
    public DocumentViewPackage openDocumentViewPackage(File file) throws Exception;
    
    public void closeDocumentViewPackage(DocumentViewPackage documentViewPackage) throws Exception;
    
}
