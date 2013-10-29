/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
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
    private List<RowInfo> _lineReadPositions;

    public Document(File file) {
        _wrappedFile = file;
        _lineReadPositions = new ArrayList<RowInfo>();
    }

    public IDocumentViewPortal getFilterView(String filterText, int linesBefore, int linesAfter) {
        return new DocumentFilterView(this, filterText, linesBefore, linesAfter);
    }
    
    public IDocumentViewPortal getNormalView() {
        return new PlainDocumentViewPortal(this);
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

    public List<String> getTextLines(int startLine, int numberOfLinesToReturn) throws IOException {

        List<String> lines = new ArrayList<String>();
        Charset charset = Charset.defaultCharset();
        try (BufferedReader reader = Files.newBufferedReader(_wrappedFile.toPath(), charset)) {
            String line = null;
            int lineNumber = 0;
            while (lineNumber <= startLine + numberOfLinesToReturn
                    && (line = reader.readLine()) != null) {
                if (lineNumber >= startLine) {
                    lines.add(line);
                }
                lineNumber++;
            }
        }
        return lines;
    }

    public List<String> getTextLines(int startLine) throws IOException {
        long startPosition = getStartPositionOfLine(startLine);
        List<String> lines = new ArrayList<String>();
        try (RandomAccessFile reader = new RandomAccessFile(_wrappedFile, "r")) {
            reader.seek(startPosition);
            int lineNumber = startLine;
            String line;
            while ((line = reader.readLine()) != null) {
                if (lineNumber >= startLine) {
                    lines.add(line);
                }
                lineNumber++;
            }
        }
        return lines;
    }

    public void updateIsModified() {
        setChanged();
        notifyObservers();
    }

    /**
     * Calculates how many lines big a file is, and saves the byte positions for
     * all of these files.
     */
    public void analyzeFile() throws IOException {
        _lineReadPositions.clear();
        Charset charset = Charset.defaultCharset();
        try (RandomAccessFile reader = new RandomAccessFile(_wrappedFile, "r")) {
            String line = null;
            int numberOfRows = 0;
            long startPosition = reader.getFilePointer();
            while ((line = reader.readLine()) != null) {
                numberOfRows++;
                RowInfo rowInfo = new RowInfo(reader.getFilePointer());
                _lineReadPositions.add(rowInfo);
            }
            _numberOfLines = numberOfRows;
        }

        //
    }

    public boolean equals(Path pathToFile) {
        return pathToFile.toFile().equals(_wrappedFile);
    }

    private long getStartPositionOfLine(int startLine) {
        long startPosition = 0;
        if (startLine-1 >= 0
                && _lineReadPositions.size() > startLine-1) {
            startPosition = _lineReadPositions.get(startLine - 1).getRowEndPosition();
        }
        return startPosition;
    }
}
