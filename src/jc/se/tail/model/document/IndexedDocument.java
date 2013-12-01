/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.document;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import jc.se.tail.file.IRandomAccessFile;
import jc.se.tail.file.impl.RandomAccessFileWrapper;
import jc.se.tail.model.document.view.DocumentFilterView;
import jc.se.tail.model.document.view.DocumentViewBase;
import jc.se.tail.model.document.view.DocumentViewUpdatedArgs;
import jc.se.tail.model.impl.RowInfo;
import jc.se.tail.service.IFileService;

/**
 *
 * @author ruffy
 */
public class IndexedDocument extends DocumentViewBase {

    private String _filePath;
    private IFileService _fileService;
    
    private int _endPosition;
    private List<RowInfo> _documentIndexPositions;
    private boolean _documentIsIndexed;

    public IndexedDocument(String filePath, IFileService fileService) {

        _filePath = filePath;
        _fileService = fileService;
        _documentIndexPositions = new ArrayList<RowInfo>();
        _documentLines = new ArrayList<>();

    }


    public List<String> getTextLines(int startLine, int numberOfLinesToReturn) throws Exception {

        List<String> lines = new ArrayList<String>();
        Charset charset = Charset.defaultCharset();

        long startPosition = getLineStartPosition(startLine);

        try (IRandomAccessFile reader = _fileService.getRandomAccessFile(_filePath, "r")) {
            reader.seek(startPosition);
            String line;
            int readLines = 0;
            while ((line = reader.readLine()) != null && readLines < numberOfLinesToReturn) {
                lines.add(line);

                ++readLines;
                updateLineStartPosition(startLine + readLines, reader);

                int positionIndex = startLine + readLines;
                if (positionIndex < _documentIndexPositions.size()) {
                    _documentIndexPositions.get(positionIndex).setRowEndPosition(reader.getFilePointer());
                }
            }
        }
        return lines;
    }

    private long getLineStartPosition(int startLine) throws IOException {
        if (_documentIndexPositions.size() < startLine) {
            try {
                indexDocument(startLine);
            } catch (Exception ex) {
                Logger.getLogger(IndexedDocument.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        long startPosition;
        if (startLine == 0) {
            startPosition = 0;
        } else if (startLine - 1 < _documentIndexPositions.size()) {
            startPosition = _documentIndexPositions.get(startLine - 1).getRowEndPosition();
        } else {
            startPosition = -1;
        }
        return startPosition;
    }

    public List<String> getTextLines(int startLine) throws Exception {

        return getTextLines(startLine, Integer.MAX_VALUE);

    }

    public void updateIsModified() {
        _documentIsIndexed = false;
        clearCachedData();
        DocumentViewUpdatedArgs eventArgs = new DocumentViewUpdatedArgs(0);
        setChanged();
        notifyObservers(eventArgs);
    }

    /**
     * Calculates how many lines big a file is, and saves the byte positions for
     * all of these files.
     */
    public void indexDocument(int fromLine) throws Exception {

        int indexStartLine = 0;
        long indexStartPosition = 0;
        if (_documentIndexPositions.size() != 0) {

            if (fromLine > _documentIndexPositions.size()) {
                indexStartPosition = _documentIndexPositions.get(_documentIndexPositions.size() - 1).getRowEndPosition();
                indexStartLine = _documentIndexPositions.size();
            } else {
                indexStartPosition = _documentIndexPositions.get(fromLine - 1).getRowEndPosition();
                indexStartLine = fromLine;
            }
        }

        Charset charset = Charset.defaultCharset();
        try (IRandomAccessFile reader = _fileService.getRandomAccessFile(_filePath, "r")) {
            int indexLastReadLine = indexStartLine;
            reader.seek(indexStartPosition);
            while ((reader.readLine()) != null) {
                updateLineStartPosition(indexLastReadLine, reader);
                indexLastReadLine++;
            }
            indexLastReadLine--; 
            _documentIsIndexed = true;
        }

        //
    }

    private void updateLineStartPosition(int indexLastReadLine, final IRandomAccessFile reader) throws IOException {
        if (indexLastReadLine >= _documentIndexPositions.size()) {
            RowInfo rowInfo = new RowInfo(reader.getFilePointer());
            _documentIndexPositions.add(rowInfo);
        } else {
            _documentIndexPositions.get(indexLastReadLine).setRowEndPosition(reader.getFilePointer());
        }
    }

    public boolean equals(Path pathToFile) {
        return pathToFile.toString().equals(_filePath);
    }

    private long getStartPositionOfLine(int startLine) {
        long startPosition = 0;
        if (startLine - 1 >= 0
                && _documentIndexPositions.size() > startLine - 1) {
            startPosition = _documentIndexPositions.get(startLine - 1).getRowEndPosition();
        }
        return startPosition;
    }

    @Override
    public int getViewPortalTotalRows() {
        return _documentIndexPositions.size();
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
