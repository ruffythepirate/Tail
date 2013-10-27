/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import static java.nio.file.StandardOpenOption.READ;
import java.util.Observable;

/**
 *
 * @author ruffy
 */
public class Document extends Observable implements IDocument {
    
    private File _wrappedFile;
    private int _numberOfLines;
    
    
    public Document(File file) {
        _wrappedFile = file;
        
    }

    @Override
    public int getNumberOfLines() {
        return _numberOfLines;
    }

    @Override
    public File getFile() {
        return _wrappedFile;
    }
    
    public FileChannel getCurrentFileReader() throws IOException {
        return FileChannel.open(_wrappedFile.toPath(), READ);
    }
    
    /**
     * Calculates how many lines big a file is, and saves the byte positions for all of these files.
     */
    public void analyzeFile() throws IOException{
        Charset charset = Charset.defaultCharset();
        try(BufferedReader reader = Files.newBufferedReader(_wrappedFile.toPath(), charset)) {
            String line = null;
            while((line = reader.readLine() ) != null) {
                
            }
            
        }
        //
    }
    
}
