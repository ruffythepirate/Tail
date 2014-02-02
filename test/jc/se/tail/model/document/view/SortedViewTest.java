/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.document.view;

import java.util.List;
import jc.se.tail.model.document.DocumentViewMock;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ruffy
 */
public class SortedViewTest {

    DocumentViewMock _parent;
    private SortView _target;

    @Before
    public void setUp() {

        _parent = new DocumentViewMock() {
        };

        _target = new SortView(true);
        _target.setParentDocumentView(_parent);
    }

    private String getTestString() {
        String testString = "A lot of\n"
                + "unsorted text\n"
                + "but will it\n"
                + "be sorted?\n";
        return testString;
    }

    @Test
    public void testSortDescending() throws Exception {
        System.out.println("testSortDescending");

        _target.setSortDescending(true);
        _parent.setText(getTestString());

        List<String> filteredStrings = _target.getTextLines(0);

        assertEquals(4, filteredStrings.size());
        assertEquals("unsorted text", filteredStrings.get(0));

    }

    @Test
    public void testSortAscending() throws Exception {
        System.out.println("testSortAscending");

        _target.setSortDescending(false);
        _parent.setText(getTestString());

        List<String> filteredStrings = _target.getTextLines(0);

        assertEquals(4, filteredStrings.size());
        assertEquals("A lot of", filteredStrings.get(0));
    }

    @Test
    public void testSortAscendingNumbers() throws Exception {
        System.out.println("testSortAscendingNumbers");

        String testString = "1\n"
                + "120\n"
                + "9\n"
                + "53\n";

        _target.setSortDescending(false);
        _parent.setText(testString);

        List<String> filteredStrings = _target.getTextLines(0);

        assertEquals(4, filteredStrings.size());
        assertEquals("1", filteredStrings.get(0));
        assertEquals("9", filteredStrings.get(1));
        assertEquals("53", filteredStrings.get(2));
        assertEquals("120", filteredStrings.get(3));

    }

}
