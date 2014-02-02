/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.document;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import jc.se.tail.file.IRandomAccessFile;
import jc.se.tail.file.impl.RandomAccessFileWrapper;
import jc.se.tail.model.document.view.FilterView;
import jc.se.tail.service.IFileService;
import jc.se.tail.service.impl.FileService;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import org.mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author ruffy
 */
public class IndexedDocumentTest {
    
    private IndexedDocument _target;
    
    @Mock
    private IFileService _fileService;
    
    @Mock
    private IRandomAccessFile _randomAccessFile;
    
        @Before
    public void setUp() throws FileNotFoundException, IOException {
        _fileService = mock( FileService.class);
        _randomAccessFile = mock( RandomAccessFileWrapper.class);
        when(_fileService.getRandomAccessFile("test", "r")).thenReturn(_randomAccessFile);
        when(_randomAccessFile.getFilePointer()).thenReturn(500l, 1000l, 2000l); 
        when(_randomAccessFile.readLine()).thenReturn("This is a lot", 
                "of rows put together", "pretty cool, ey?", null);
                
        _target = new IndexedDocument("test", _fileService );
    }

    private String getTestString() {
        String testString = "This is a lot\n"
                + "of rows put together\n"
                + "pretty cool, ey?\n";
        return testString;
    }

    @Test
    public void testGetTextLines() throws Exception {
        System.out.println("getTextLines");


        List<String> filteredStrings = _target.getTextLines(0);

        assertEquals(3, filteredStrings.size());
        assertEquals("This is a lot", filteredStrings.get(0));

    }
    
        @Test
    public void testGetTextLinesAfterIndexed() throws Exception {

        List<String> filteredStrings = _target.getTextLines(0);

        assertEquals(3, filteredStrings.size());
        assertEquals("This is a lot", filteredStrings.get(0));

        _target.getTextLines(2);

        verify(_randomAccessFile).seek(1000l);
    }

}
