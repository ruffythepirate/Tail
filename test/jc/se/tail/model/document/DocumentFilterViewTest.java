/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ruffy
 */
public class DocumentFilterViewTest {

    public DocumentFilterViewTest() {
    }

    DocumentViewBase _parent;
    private String _parentString;

    private DocumentFilterView _target;

    @Before
    public void setUp() {

        _parent = new DocumentViewBase() {

            String[] _allLines;
            @Override
            public List<String> getTextLines(int startLine) throws IOException {
                _allLines = _parentString.split("\n");

                List<String> returnStrings = new ArrayList<String>();

                for (int i = startLine; i < _allLines.length; i++) {
                    returnStrings.add(_allLines[i]);
                }
                return returnStrings;
            }

            @Override
            public void update(Observable o, Object arg) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getDocumentTotalRows() {
                return _parentString.split("\n").length;
            }

            @Override
            public int getViewPortalTotalRows() {
                return _parentString.split("\n").length;
            }
        };

        _target = new DocumentFilterView();
        _target.setParentDocumentView(_parent);
    }

    @Test
    public void testGetTextLines() throws Exception {
        System.out.println("getTextLines");

        _parentString = "This is a lot\n"
                + "of rows put together\n"
                + "pretty cool, ey?\n";

        _target.setFilterString("This");

        List<String> filteredStrings = _target.getTextLines(0);

        assertEquals(1, filteredStrings.size());
        assertEquals("This is a lot", filteredStrings.get(0));

    }

    @Test
    public void testGetDocumentTotalRows() {
        System.out.println("getDocumentTotalRows");
        _parentString = "This is a lot\n"
                + "of rows put together\n"
                + "pretty cool, ey?\n";

        _target.setFilterString("This");

        assertEquals(_parent.getDocumentTotalRows(), _target.getDocumentTotalRows());
    }

    @Test
    public void testGetViewPortalTotalRows() {
        System.out.println("getViewPortalTotalRows");
        _parentString = "This is a lot\n"
                + "of rows put together\n"
                + "pretty cool, ey?\n";

        _target.setFilterString("This");

        assertEquals(1, _target.getViewPortalTotalRows());
    }
}
