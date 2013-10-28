/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.impl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ruffy
 */
public class SearchResult {
    
    private int numberOfHits;
    
    private List<SearchResultHit> _searchResultHits;
    
    public SearchResult() {
        _searchResultHits = new ArrayList<SearchResultHit>();
    }
    
    public void addSearchHit(int rowNumber, int hitStart, int hitEnd) {
        _searchResultHits.add(new SearchResultHit(rowNumber, hitStart, hitEnd));
    }
    
    public List<SearchResultHit> getSearchHits() {
        return _searchResultHits;
    }
    
}
