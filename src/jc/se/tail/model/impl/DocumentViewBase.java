/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.impl;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author ruffy
 */
public abstract class DocumentViewBase extends Observable implements IDocumentViewPortal {

    @Override
    public abstract List<String> getTextLines(int startLine) throws IOException;

    @Override
    public abstract int getDocumentTotalRows();

    @Override
    public abstract int getViewPortalTotalRows();

}
