/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.document.view;

import jc.se.tail.model.document.view.DocumentViewBase;
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
    
    protected String _replaceString;
    
    protected Pattern _regexExpression;

    public RegularExpressionView() {
        
    }
    
    public RegularExpressionView(String regexString, String replaceString) {
        _replaceString = replaceString;
        
        _regexExpression = Pattern.compile(regexString);
    }

    @Override
    public String getViewTitle() {
        return "Regex: " + getRegexExpression().toString();
    }

    
    
    @Override
    public List<String> getTextLines(int startLine) throws IOException {
        List<String> documentLines = _parentDocumentView.getTextLines(startLine);
        
        List<String> returnRows = new ArrayList<String>();
        
        for(String line : documentLines) {
            String filteredRow = getRegexExpression().matcher(line).replaceAll(getReplaceString());
            returnRows.add(filteredRow);
            
        }
        
        return returnRows;
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

    /**
     * @return the _replaceString
     */
    public String getReplaceString() {
        return _replaceString;
    }

    /**
     * @param _replaceString the _replaceString to set
     */
    public void setReplaceString(String _replaceString) {
        this._replaceString = _replaceString;
    }

    /**
     * @return the _regexExpression
     */
    public Pattern getRegexExpression() {
        return _regexExpression;
    }

    /**
     * @param regexExpression the _regexExpression to set
     */
    public void setRegexExpression(String regexExpression) {
        this._regexExpression = Pattern.compile(regexExpression);
    }
   
    
    
    
}
