/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.controller;

import jc.se.tail.model.document.*;
import jc.se.tail.model.document.view.FilterView;
import jc.se.tail.model.document.view.DocumentViewBase;
import jc.se.tail.model.document.view.IFilterViewSettings;
import jc.se.tail.model.document.view.IRegularExpressionViewSettings;
import jc.se.tail.model.document.view.RegularExpressionView;
import jc.se.tail.model.document.view.SortView;

/**
 *
 * @author ruffy
 */
public class DocumentViewController {

    protected DocumentViewPackage _documentViewPackage;

    public DocumentViewController() {

    }

    public void addDocumentView(DocumentViewBase documentView) {

    }

    public void removeDocumentView(DocumentViewBase documentView) {

    }

    public void addFilterView(IFilterViewSettings filterViewSettings) {
        if (_documentViewPackage == null) {
            throw new IllegalStateException("The controller has no document package associated with it!");
        }

        FilterView filterView = new FilterView(
                filterViewSettings.getFilterText(), filterViewSettings.getRowsBefore(),
                filterViewSettings.getRowsAfter());
        filterView.setShouldExcludeRows(filterViewSettings.getShouldExcludeRows());

        _documentViewPackage.addDocumentView(filterView);
    }

    public void addRegularExpressionView(IRegularExpressionViewSettings settings) {
        if (_documentViewPackage == null) {
            throw new IllegalStateException("The controller has no document package associated with it!");
        }

        RegularExpressionView filterView = new RegularExpressionView(
                settings.getSearchText(),
                settings.getReplacementText());

        _documentViewPackage.addDocumentView(filterView);
    }

    public void addSortView(boolean sortDescending) {
        if (_documentViewPackage == null) {
            throw new IllegalStateException("The controller has no document package associated with it!");
        }

        SortView view = new SortView(sortDescending);
        _documentViewPackage.addDocumentView(view);
    }

    /**
     * @return the _documentViewPackage
     */
    public DocumentViewPackage getDocumentViewPackage() {
        return _documentViewPackage;
    }

    /**
     * @param _documentViewPackage the _documentViewPackage to set
     */
    public void setDocumentViewPackage(DocumentViewPackage _documentViewPackage) {
        this._documentViewPackage = _documentViewPackage;
    }

}
