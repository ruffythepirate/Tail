/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.model.document.view;

import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author ruffy
 */
public class SortedView extends DocumentViewBase implements Observer {

    private boolean _sortDescending;

    public SortedView(boolean sortDescending) {
        _sortDescending = sortDescending;
    }

    @Override
    public List<String> getTextLines(int startLine) throws IOException {
        List<String> response = new ArrayList<>();
        if (_documentLines.size() > 0) {
            for (int i = startLine; i < _documentLines.size(); i++) {
                response.add(_documentLines.get(i));
            }
            return response;
        }

        if (_parentDocumentView != null) {
            _documentLines.clear();
            List<String> parentLines = _parentDocumentView.getTextLines(0);

            java.util.Collections.sort(parentLines, Collator.getInstance());
            if (!_sortDescending) {
                _documentLines.addAll(parentLines);
                response.addAll(parentLines);

            } else {
                for (int i = parentLines.size() - 1; i >= 0; i--) {
                    _documentLines.add(parentLines.get(i));
                    response.add(parentLines.get(i));
                }
            }

        }
        return response;
    }

    @Override
    public void update(Observable o, Object arg) {
        _documentLines.clear();;
        setChanged();
        DocumentViewUpdatedArgs args = new DocumentViewUpdatedArgs(0);
        notifyObservers(args);

    }

    @Override
    public String getViewTitle() {
        if (!_sortDescending) {
            return "Sort ASC";

        }
        return "Sort DESC";

    }

}
