/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.impl;

import java.io.File;
import java.util.Observer;

/**
 *
 * @author ruffy
 */
public interface IDocument
{
    public int getNumberOfLines();
    
    public File getFile();
    
    void addObserver(Observer observer);
}
