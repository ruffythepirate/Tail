/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.document.view;

/**
 *
 * @author ruffy
 */
public class DocumentViewUpdatedArgs {
    private int _updatedFromViewLine;
    
    public DocumentViewUpdatedArgs(int updatedFromViewLine) {
        _updatedFromViewLine = updatedFromViewLine;
    }

    /**
     * @return the _updatedFromViewLine
     */
    public int getUpdatedFromViewLine() {
        return _updatedFromViewLine;
    }

}
