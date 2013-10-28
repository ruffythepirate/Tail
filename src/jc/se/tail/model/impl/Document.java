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
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.READ;
import java.util.ArrayList;
import java.util.List;
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
    
    public List<String> getTextLines(int startLine, int numberOfLinesToReturn) throws IOException{
        
        List<String> lines = new ArrayList<String>();
        Charset charset = Charset.defaultCharset();
        try(BufferedReader reader = Files.newBufferedReader(_wrappedFile.toPath(), charset)) {
            String line = null;
            int lineNumber = 0;
            while(lineNumber <= startLine + numberOfLinesToReturn 
                    && (line = reader.readLine() ) != null) {
                if(lineNumber >= startLine) {
                    lines.add(line);
                }
                lineNumber ++;
            }
            _numberOfLines = lineNumber;
        }
        return lines;
    }
    
    public void updateIsModified() {
        setChanged();
        notifyObservers();
    }
    
    /**
     * Calculates how many lines big a file is, and saves the byte positions for all of these files.
     */
    public void analyzeFile() throws IOException{
        Charset charset = Charset.defaultCharset();
        try(BufferedReader reader = Files.newBufferedReader(_wrappedFile.toPath(), charset)) {
            String line = null;
            int numberOfRows = 0;
            while((line = reader.readLine() ) != null) {
                numberOfRows ++;
                
                
            }
            _numberOfLines = numberOfRows;
        }
        
        //
    }
    
    public boolean equals(Path pathToFile) {
        return pathToFile.toFile().equals(_wrappedFile);
    }
    
}
