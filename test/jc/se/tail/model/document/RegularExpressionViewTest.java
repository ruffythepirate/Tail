/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.document;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ruffy
 */
public class RegularExpressionViewTest {
       DocumentViewMock _parent;
    private RegularExpressionView _target;

    @Before
    public void setUp() {

        _parent = new DocumentViewMock() {
        };

        _target = new RegularExpressionView();
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

        _target.setRegexExpression("is a");
        _target.setReplaceString("BAM");        

        List<String> filteredStrings = _target.getTextLines(0);

        assertEquals(3, filteredStrings.size());
        assertEquals("This BAM lot", filteredStrings.get(0));

    }
    
    @Test
    public void testUseReplacementExpression() throws Exception {
        System.out.println("getTextLines");

        _parent.setText(getTestString());

        _target.setRegexExpression("This (.+) lot");
        _target.setReplaceString("lot $1 This");        

        List<String> filteredStrings = _target.getTextLines(0);

        assertEquals(3, filteredStrings.size());
        assertEquals("lot is a This", filteredStrings.get(0));

    }

    @Test
    public void testGetViewPortalTotalRows() {
        System.out.println("getViewPortalTotalRows");
        _parent.setText(getTestString());

        _target.setRegexExpression("This");
        _target.setReplaceString("This");

        assertEquals(_parent.getViewPortalTotalRows(), _target.getViewPortalTotalRows());
    }
}
