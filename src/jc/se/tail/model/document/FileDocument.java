/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.document;

import jc.se.tail.model.document.view.FilterView;
import jc.se.tail.model.document.view.DocumentViewBase;
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
import jc.se.tail.model.document.view.DocumentViewUpdatedArgs;
import jc.se.tail.model.impl.RowInfo;

/**
 *
 * @author ruffy
 */
public class FileDocument extends DocumentViewBase {

    private File _wrappedFile;
    private int _numberOfLines;
    private List<RowInfo> _lineReadPositions;
    private boolean _documentIsCached;

    public FileDocument(File file) {
        
        _wrappedFile = file;
        _lineReadPositions = new ArrayList<RowInfo>();
        _documentLines = new ArrayList<>();

    }

    @Override
    public String getSourceName() {
        if(getFile() != null)
        {
            return getFile().getAbsolutePath();
        }
        return "No File!";
    }

    @Override
    public String getShortSourceName() {
        if(getFile() != null)
        {
            return getFile().getName();
        }
        return "No File!";
    }

    @Override
    public String getSourceId() {
        if(getFile() != null)
        {
            return getFile().getAbsolutePath();
        }
        return "No File!";
    }
    
    

    
    
    public IDocumentViewPortal getFilterView(String filterText, int linesBefore, int linesAfter) {
        return new FilterView(this, filterText, linesBefore, linesAfter);
    }

    public IDocumentViewPortal getNormalView() {
        return new PlainDocumentViewPortal(this);
    }

    public int getNumberOfLines() {
        return _numberOfLines;
    }

    public File getFile() {
        return _wrappedFile;
    }

    public FileChannel getCurrentFileReader() throws IOException {
        return FileChannel.open(_wrappedFile.toPath(), READ);
    }

    private long _lastPositionWithNewline = 0;
    
    
    
    public long ReadLinesFromPosition(long startPosition, int maxNumberOfLines, List<String> collectionToAddLinesTo) throws Exception {
           try (RandomAccessFile reader = new RandomAccessFile(_wrappedFile, "r")) {
                reader.seek(startPosition);
                String line;
                int readLines = 0;
                while ((line = reader.readLine()) != null && readLines < maxNumberOfLines) {
                    collectionToAddLinesTo.add(line);
                    ++readLines;
                }
                return reader.getFilePointer();
            } 
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

        if (!_documentIsCached) {
            if(_documentLines == null) {
                _documentLines = new ArrayList<>();
            }
            while(startLine >= 0 && _documentLines.size() > startLine) {
                _documentLines.remove(_documentLines.size()-1);
            }
            long startPosition = getStartPositionOfLine(startLine);
            List<String> lines = new ArrayList<String>();
            try (RandomAccessFile reader = new RandomAccessFile(_wrappedFile, "r")) {
                reader.seek(startPosition);
                int lineNumber = startLine;
                String line;
                while ((line = reader.readLine()) != null) {
                    if (lineNumber >= startLine) {
                        lines.add(line);
                        _documentLines.add(line);
                    }
                    lineNumber++;
                }
            }
            _documentIsCached = true;
            return lines;
        } else {
            if (startLine > 0) {
                List<String> returnArray = new ArrayList<String>();
                for (int i = startLine; i < _documentLines.size(); i++) {
                    returnArray.add(_documentLines.get(i));
                }
                return returnArray;
            }
            return _documentLines;
        }

    }

    public void updateIsModified() {
        _documentIsCached = false;
        clearCachedData();
        DocumentViewUpdatedArgs eventArgs = new DocumentViewUpdatedArgs(0);
        setChanged();
        notifyObservers(eventArgs);
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

    public boolean isFileDefinedByPath(Path pathToFile) {
        return pathToFile.toFile().equals(_wrappedFile);
    }

    private long getStartPositionOfLine(int startLine) {
        long startPosition = 0;
        if (startLine - 1 >= 0
                && _lineReadPositions.size() > startLine - 1) {
            startPosition = _lineReadPositions.get(startLine - 1).getRowEndPosition();
        }
        return startPosition;
    }

    @Override
    public int getDocumentTotalRows() {
        return _numberOfLines;
    }

    @Override
    public int getViewPortalTotalRows() {
        return _numberOfLines;
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
