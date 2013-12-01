/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import jc.se.tail.file.IRandomAccessFile;
import jc.se.tail.file.impl.RandomAccessFileWrapper;
import jc.se.tail.service.IFileService;

/**
 *
 * @author ruffy
 */
public class FileService implements IFileService{

    @Override
    public IRandomAccessFile getRandomAccessFile(String filePath, String readWriteMode) throws FileNotFoundException{
        File fileToOpen = new File(filePath);
        
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileToOpen, readWriteMode);
        RandomAccessFileWrapper fileWrapper = new RandomAccessFileWrapper(randomAccessFile);
        
        return fileWrapper;
    }
    
}
