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

/**
 *
 * @author ruffy
 */
public class DocumentViewMock extends DocumentViewBase {

    public void setText(String text) {
        String[] allLines = text.split("\n");
        _documentLines = new ArrayList<String>();

        for (String line : allLines) {
            _documentLines.add(line);
        }
    }

    @Override
    public List<String> getTextLines(int startLine) throws IOException {
        return _documentLines;        
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
