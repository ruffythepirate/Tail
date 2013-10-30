/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.service;

import java.io.File;
import jc.se.tail.model.document.Document;

/**
 *
 * @author ruffy
 */
public class DocumentService {
    Document getDocument(File file) {
       return new Document(file); 
    }
}
