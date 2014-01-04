/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.manager;

import java.io.IOException;
import jc.se.tail.model.document.Document;

/**
 *
 * @author ruffy
 */
public interface IDocumentManager {
    
    public void startTrackDocument(Document document) throws Exception;
    
    public void stopTrackDocument(Document document) throws Exception;
    
}
