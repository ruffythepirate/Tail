/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import jc.se.tail.model.document.Document;
import jc.se.tail.model.document.DocumentFilterView;
import jc.se.tail.model.document.DocumentViewBase;
import jc.se.tail.model.document.DocumentViewPackage;
import jc.se.tail.model.document.DocumentViewPackageCommunicator;
import jc.se.tail.model.document.IDocumentViewPortal;
import jc.se.tail.model.document.RegularExpressionView;
import jc.se.tail.model.impl.SearchResult;
import jc.se.tail.model.impl.SearchResultHit;
import jc.se.util.view.labelpane.LabelItem;
import jc.se.util.view.labelpane.LabelsUpdatedEvent;

/**
 *
 * @author Ruffy
 */
public class FileTailPanel extends javax.swing.JPanel implements Observer {

    private Document _documentToTrack;

    private int _currentNumberOfShowedLines;
    private DefaultHighlighter.DefaultHighlightPainter _highlightPainter;
    private DefaultHighlighter.DefaultHighlightPainter _lineHighlightPainter;
    private SearchResult _currentSearchResult;

    private DocumentViewBase _documentViewPortal;
    private DocumentViewPackageCommunicator _documentViewPackage;

    private void recompileDocumentView() {

        if (_documentViewPortal != null) {
            _documentViewPortal.deleteObserver(this);
        }

        _documentViewPortal = _documentViewPackage.getCompiledView();
        _documentViewPortal.addObserver(this);

    }

    /**
     * Creates new form FileTailPanel
     */
    public FileTailPanel(Document documentToTrack) {
        initComponents();
        doLayout();

        _labelPane.getLabelList().addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                if (arg instanceof LabelsUpdatedEvent) {
                    LabelsUpdatedEvent event = (LabelsUpdatedEvent) arg;
                    if (event.getEventType() == LabelsUpdatedEvent.EVENT_LABEL_REMOVED) {
                        LabelItem labelItem = event.getLabel();
                        if (labelItem.getTag() instanceof DocumentViewBase) {
                            removeFilter((DocumentViewBase) labelItem.getTag());
                        }
                    }
                }

            }
        });

        _documentToTrack = documentToTrack;
        _documentViewPackage = new DocumentViewPackageCommunicator(documentToTrack);

        _labelPane.setLabelList( _documentViewPackage.getLabelList());
        
        recompileDocumentView();

        _highlightPainter
                = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

        _lineHighlightPainter
                = new DefaultHighlighter.DefaultHighlightPainter(Color.BLUE);

        updateDisplayedDocumentText();

        _searchPane.setVisible(_showSearchBtn.isSelected());

        registerActivateSearchHotKey();

    }

    public void removeDocumentView(DocumentViewBase viewToRemove) {
        _documentViewPackage.removeDocumentView(viewToRemove);

        recompileDocumentView();
        refreshDisplayedDocumentText();
    }
    
    public void appendReformat(String searchText, String replacementText) {
        RegularExpressionView regularExpressionView = new RegularExpressionView(searchText, replacementText);
        
        _documentViewPackage.appendDocumentView(regularExpressionView);

        recompileDocumentView();

        refreshDisplayedDocumentText();
    
    }

    public void appendFilter(String filterText, int rowsBefore, int rowsAfter) {

        DocumentFilterView filterView = new DocumentFilterView(filterText, rowsBefore, rowsAfter);
        _documentViewPackage.appendDocumentView(filterView);

        recompileDocumentView();

        refreshDisplayedDocumentText();
    }

    public void removeFilter(DocumentViewBase documentViewBase) {
        _documentViewPackage.removeDocumentView(documentViewBase);
        recompileDocumentView();
        refreshDisplayedDocumentText();
    }

    private void registerActivateSearchHotKey() {
        //Register serach hot key
        AbstractAction activateSearchAction = new AbstractAction("activate search") {

            @Override
            public void actionPerformed(ActionEvent e) {
                _showSearchBtn.setSelected(true);
                _searchPane.setVisible(true);
                _searchTxt.requestFocus();
            }
        };

        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK, false);

        this.getActionMap().put("activate search", activateSearchAction);
        _fileContentTxt.getActionMap().put("activate search", activateSearchAction);

        this.getInputMap(JComponent.WHEN_FOCUSED).put(keyStroke, "activate search");
        _fileContentTxt.getInputMap(JComponent.WHEN_FOCUSED).put(keyStroke, "activate search");
    }

    private void scrollToNextSearchHit() {
        if (_currentSearchResult != null) {
            int caretPosition = _fileContentTxt.getCaretPosition();
            SearchResultHit nextHit = getNextSearchHit(caretPosition);

            if (nextHit != null) {
                scrollToSearchHit(nextHit);
            }
        }
    }

    private void scrollToSearchHit(SearchResultHit hitToScrollTo) {
        _fileContentTxt.setCaretPosition(hitToScrollTo.getHitStart());
    }

    private void clearHighlighting() {
        _fileContentTxt.getHighlighter().removeAllHighlights();
    }

    private void scrollToPreviousSearchHit() {
        if (_currentSearchResult != null) {
            int caretPosition = _fileContentTxt.getCaretPosition();
            SearchResultHit nextHit = getPreviousSearchHit(caretPosition);

            if (nextHit != null) {
                scrollToSearchHit(nextHit);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        _toolBarPane = new javax.swing.JPanel();
        _tailFileEnd = new javax.swing.JToggleButton();
        _showSearchBtn = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        _labelContainerPane = new javax.swing.JPanel();
        _addFilterBtn = new javax.swing.JButton();
        _labelPane = new jc.se.util.view.labelpane.LabelPane();
        _searchPane = new javax.swing.JPanel();
        _searchTxt = new javax.swing.JTextField();
        _previousResultBtn = new javax.swing.JButton();
        _nextSearchResultBtn = new javax.swing.JButton();
        _resultHitsLbl = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        _resultRowsLbl = new javax.swing.JLabel();
        _scrollPane = new javax.swing.JScrollPane();
        _fileContentTxt = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        _toolBarPane.setMinimumSize(new java.awt.Dimension(49, 0));
        _toolBarPane.setLayout(new javax.swing.BoxLayout(_toolBarPane, javax.swing.BoxLayout.LINE_AXIS));

        _tailFileEnd.setSelected(true);
        _tailFileEnd.setText("Tail");
        _tailFileEnd.setFocusable(false);
        _tailFileEnd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        _tailFileEnd.setMaximumSize(new java.awt.Dimension(49, 20));
        _tailFileEnd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        _tailFileEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _tailFileEndActionPerformed(evt);
            }
        });
        _toolBarPane.add(_tailFileEnd);

        _showSearchBtn.setSelected(true);
        _showSearchBtn.setText("Search");
        _showSearchBtn.setFocusable(false);
        _showSearchBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        _showSearchBtn.setMaximumSize(new java.awt.Dimension(65, 20));
        _showSearchBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        _showSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _showSearchBtnActionPerformed(evt);
            }
        });
        _toolBarPane.add(_showSearchBtn);

        jButton1.setText("Refresh");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMaximumSize(new java.awt.Dimension(31, 23));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        _toolBarPane.add(jButton1);

        _labelContainerPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));
        _labelContainerPane.setMaximumSize(new java.awt.Dimension(2147483647, 20));
        _labelContainerPane.setLayout(new java.awt.BorderLayout());

        _addFilterBtn.setText("+");
        _addFilterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _addFilterBtnActionPerformed(evt);
            }
        });
        _labelContainerPane.add(_addFilterBtn, java.awt.BorderLayout.EAST);

        _labelPane.setMaximumSize(new java.awt.Dimension(32767, 20));
        _labelPane.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        _labelContainerPane.add(_labelPane, java.awt.BorderLayout.CENTER);

        _toolBarPane.add(_labelContainerPane);

        add(_toolBarPane, java.awt.BorderLayout.NORTH);
        _toolBarPane.getAccessibleContext().setAccessibleName("");

        _searchPane.setLayout(new javax.swing.BoxLayout(_searchPane, javax.swing.BoxLayout.LINE_AXIS));

        _searchTxt.setMinimumSize(new java.awt.Dimension(100, 20));
        _searchTxt.setPreferredSize(new java.awt.Dimension(100, 20));
        _searchTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _searchTxtActionPerformed(evt);
            }
        });
        _searchTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                _searchTxtKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                _searchTxtKeyReleased(evt);
            }
        });
        _searchPane.add(_searchTxt);

        _previousResultBtn.setText("<");
        _searchPane.add(_previousResultBtn);

        _nextSearchResultBtn.setText(">");
        _nextSearchResultBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _nextSearchResultBtnActionPerformed(evt);
            }
        });
        _searchPane.add(_nextSearchResultBtn);

        _resultHitsLbl.setText("Result Hits: 0");
        _searchPane.add(_resultHitsLbl);
        _searchPane.add(jSeparator1);

        _resultRowsLbl.setText("Result Rows: 0");
        _searchPane.add(_resultRowsLbl);

        add(_searchPane, java.awt.BorderLayout.PAGE_END);

        _fileContentTxt.setEditable(false);
        _fileContentTxt.setBackground(new java.awt.Color(51, 51, 51));
        _fileContentTxt.setColumns(20);
        _fileContentTxt.setForeground(new java.awt.Color(153, 153, 153));
        _fileContentTxt.setRows(5);
        _fileContentTxt.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                handleContentTextSizeChanged(evt);
            }
        });
        _scrollPane.setViewportView(_fileContentTxt);

        add(_scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void handleContentTextSizeChanged(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_handleContentTextSizeChanged
    }//GEN-LAST:event_handleContentTextSizeChanged

    private void _tailFileEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__tailFileEndActionPerformed
        if (_tailFileEnd.isSelected()) {
            //We scroll to the bottom of the document.
            scrollToBottom();
        }
    }//GEN-LAST:event__tailFileEndActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        _fileContentTxt.setText(null);
        _currentNumberOfShowedLines = 0;
        updateDisplayedDocumentText();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void _showSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__showSearchBtnActionPerformed
        // TODO add your handling code here:
        _searchPane.setVisible(_showSearchBtn.isSelected());
    }//GEN-LAST:event__showSearchBtnActionPerformed

    private void _searchTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event__searchTxtKeyTyped

    }//GEN-LAST:event__searchTxtKeyTyped

    private void _nextSearchResultBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__nextSearchResultBtnActionPerformed
        scrollToNextSearchHit();
    }//GEN-LAST:event__nextSearchResultBtnActionPerformed

    private void _searchTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__searchTxtActionPerformed
        scrollToNextSearchHit();
    }//GEN-LAST:event__searchTxtActionPerformed

    private void _searchTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event__searchTxtKeyReleased
        //Pressing enter selects next entry;
        String searchText = _searchTxt.getText();
        if (searchText != null && searchText.length() > 0) {
            populateSearchResult(searchText);
            clearHighlighting();
            highlightSearchResults();
            highlightSearchResultRows();
        } else {
            clearSearchResult();
        }
    }//GEN-LAST:event__searchTxtKeyReleased

    private void _addFilterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__addFilterBtnActionPerformed

        FilterDialog settings = new FilterDialog(null, true);
        settings.setVisible(true);
        boolean shouldFilter = settings.getShouldFilter();
        if (shouldFilter) {

            DocumentFilterView filterView = new DocumentFilterView(
                    settings.getFilterText(),
                    settings.getRowsBefore(),
                    settings.getRowsAfter());

            _labelPane.getLabelList().addLabel(settings.getFilterText(), filterView);

            _documentViewPackage.appendDocumentView(filterView);

            recompileDocumentView();
            refreshDisplayedDocumentText();
            _labelContainerPane.invalidate();
            _labelPane.repaint();
            repaint();
        }
    }//GEN-LAST:event__addFilterBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton _addFilterBtn;
    private javax.swing.JTextArea _fileContentTxt;
    private javax.swing.JPanel _labelContainerPane;
    private jc.se.util.view.labelpane.LabelPane _labelPane;
    private javax.swing.JButton _nextSearchResultBtn;
    private javax.swing.JButton _previousResultBtn;
    private javax.swing.JLabel _resultHitsLbl;
    private javax.swing.JLabel _resultRowsLbl;
    private javax.swing.JScrollPane _scrollPane;
    private javax.swing.JPanel _searchPane;
    private javax.swing.JTextField _searchTxt;
    private javax.swing.JToggleButton _showSearchBtn;
    private javax.swing.JToggleButton _tailFileEnd;
    private javax.swing.JPanel _toolBarPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    private int getTextAreaNumberOfLines() {
        double height = _scrollPane.getSize().getHeight();
        int lineHeight = getLineHeight();

        return (int) Math.ceil(height / lineHeight);
    }

    private void refreshDisplayedDocumentText() {
        _fileContentTxt.setText(null);
        _currentNumberOfShowedLines = 0;
        updateDisplayedDocumentText();
    }

    private void updateDisplayedDocumentText() {
        try {
            String newline = System.getProperty("line.separator");

            _documentToTrack.analyzeFile();

            List<String> allLines = _documentViewPortal.getTextLines(_currentNumberOfShowedLines);

            for (String fileLine : allLines) {
                _fileContentTxt.append(fileLine);
                _fileContentTxt.append(newline);
            }

            if (_tailFileEnd.isSelected()) {
                scrollToBottom();
            }
            _currentNumberOfShowedLines += allLines.size();
        } catch (IOException ex) {
            Logger.getLogger(FileTailPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        updateDisplayedDocumentText();
    }

    private int getLineHeight() {
        int lineHeight = _fileContentTxt.getFontMetrics(_fileContentTxt.getFont()).getHeight();
        return lineHeight;
    }

    private void scrollToBottom() {
        _fileContentTxt.setCaretPosition(_fileContentTxt.getDocument().getLength());
    }

    private void highlightLine(int lineToHighlight) throws BadLocationException {

        int startIndex = _fileContentTxt.getLineStartOffset(lineToHighlight);
        int endIndex = _fileContentTxt.getLineEndOffset(lineToHighlight);
        _fileContentTxt.getHighlighter().addHighlight(startIndex, endIndex,
                _lineHighlightPainter);
    }

    private void highlightSearchResults() {
        //Highlights search results...
        for (SearchResultHit hit : _currentSearchResult.getSearchHits()) {
            try {
                _fileContentTxt.getHighlighter().addHighlight(hit.getHitStart(), hit.getHitEnd(),
                        _highlightPainter);
            } catch (BadLocationException ex) {
                Logger.getLogger(FileTailPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void highlightSearchResultRows() {
        //Highlight lines
        int lastHighlightedLine = -1;
        for (SearchResultHit hit : _currentSearchResult.getSearchHits()) {
            try {
                if (hit.getRowNumber() > lastHighlightedLine) {
                    highlightLine(hit.getRowNumber());
                    lastHighlightedLine = hit.getRowNumber();
                }
            } catch (BadLocationException ex) {
                Logger.getLogger(FileTailPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void populateSearchResult(String searchText) {
        _currentSearchResult = new SearchResult();
        String allContent = _fileContentTxt.getText();

        int nextIndex = allContent.indexOf(searchText, 0);
        int numberOfRows = 0;
        int lastRow = -1;
        while (nextIndex > -1) //We have an occurrance.
        {
            try {
                int rowNumber = _fileContentTxt.getLineOfOffset(nextIndex);
                _currentSearchResult.addSearchHit(rowNumber,
                        nextIndex,
                        nextIndex + searchText.length());
                if (rowNumber > lastRow) {
                    numberOfRows++;
                    lastRow = rowNumber;
                }
            } catch (BadLocationException ex) {
                Logger.getLogger(FileTailPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            nextIndex = allContent.indexOf(searchText, nextIndex + 1);
        }
        updateSearchResultLabels(numberOfRows);

    }

    private void clearSearchResult() {
        _currentSearchResult = null;
        updateSearchResultLabels(0);
        clearHighlighting();
    }

    private void updateSearchResultLabels(int numberOfRows) {
        _resultRowsLbl.setText("Total Rows: " + numberOfRows);
        if (_currentSearchResult != null) {
            _resultHitsLbl.setText("Total Hits: " + _currentSearchResult.getSearchHits().size());
        } else {
            _resultHitsLbl.setText("Total Hits: 0");
        }
    }

    private SearchResultHit getPreviousSearchHit(int caretPosition) {
        SearchResultHit previousHit = null;
        for (SearchResultHit hit : _currentSearchResult.getSearchHits()) {
            if (hit.getHitStart() < caretPosition
                    && (previousHit == null || hit.getHitStart() > previousHit.getHitStart())) {
                previousHit = hit;
            }
        }

        if (previousHit == null) {
            for (SearchResultHit hit : _currentSearchResult.getSearchHits()) {
                if ((previousHit == null || hit.getHitStart() > previousHit.getHitStart())) {
                    previousHit = hit;
                }
            }
        }
        return previousHit;

    }

    private SearchResultHit getNextSearchHit(int caretPosition) {
        SearchResultHit nextHit = null;
        for (SearchResultHit hit : _currentSearchResult.getSearchHits()) {
            if (hit.getHitStart() > caretPosition
                    && (nextHit == null || hit.getHitStart() < nextHit.getHitStart())) {
                nextHit = hit;
            }
        }

        if (nextHit == null) {
            for (SearchResultHit hit : _currentSearchResult.getSearchHits()) {
                if ((nextHit == null || hit.getHitStart() < nextHit.getHitStart())) {
                    nextHit = hit;
                }
            }
        }
        return nextHit;
    }
}