/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.document;

import jc.se.tail.model.document.view.DocumentFilterView;
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

    DocumentViewMock _parent;
    private DocumentFilterView _target;

    @Before
    public void setUp() {

        _parent = new DocumentViewMock() {
        };

        _target = new DocumentFilterView();
        _target.setParentDocumentView(_parent);
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

        _parent.setText(getTestString());

        _target.setFilterString("This");

        List<String> filteredStrings = _target.getTextLines(0);

        assertEquals(1, filteredStrings.size());
        assertEquals("This is a lot", filteredStrings.get(0));

    }

    @Test
    public void testGetDocumentTotalRows() {
        System.out.println("getDocumentTotalRows");

        _parent.setText(getTestString());

        _target.setFilterString("This");

        assertEquals(_parent.getDocumentTotalRows(), _target.getDocumentTotalRows());
    }

    @Test
    public void testGetViewPortalTotalRows() {
        System.out.println("getViewPortalTotalRows");
        _parent.setText(getTestString());

        _target.setFilterString("This");

        assertEquals(1, _target.getViewPortalTotalRows());
    }
}
