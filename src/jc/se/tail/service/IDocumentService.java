/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.service;

import java.io.File;
import jc.se.tail.model.document.DocumentViewPackage;

/**
 *
 * @author ruffy
 */
public interface IDocumentService {
    void openFile(File file) throws Exception;
    
    void closeDocumentViewPackage(DocumentViewPackage viewPackageToClose);
    
}
