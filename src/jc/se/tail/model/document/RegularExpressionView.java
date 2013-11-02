/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.regex.Pattern;

/**
 *
 * @author ruffy
 */
public class RegularExpressionView extends DocumentViewBase implements Observer{
    
    private String _replaceString;
    
    private Pattern _regexExpression;
           
    public RegularExpressionView(String regexString, String replaceString) {
        _replaceString = replaceString;
        
        _regexExpression = Pattern.compile(regexString);
    }

    @Override
    public List<String> getTextLines(int startLine) throws IOException {
        List<String> documentLines = _parentDocumentView.getTextLines(startLine);
        
        List<String> returnRows = new ArrayList<String>();
        
        for(String line : documentLines) {
            String filteredRow = _regexExpression.matcher(line).replaceAll(_replaceString);
            returnRows.add(filteredRow);
            
        }
        
        return returnRows;
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
   
    
    
    
}
