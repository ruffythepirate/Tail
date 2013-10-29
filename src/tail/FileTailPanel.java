/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tail;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import jc.se.tail.model.impl.Document;
import jc.se.tail.model.impl.SearchResult;
import jc.se.tail.model.impl.SearchResultHit;

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

    /**
     * Creates new form FileTailPanel
     */
    public FileTailPanel(Document documentToTrack) {
        initComponents();
        doLayout();
        _documentToTrack = documentToTrack;
        _documentToTrack.addObserver(this);

        _highlightPainter =
                new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

        _lineHighlightPainter =
                new DefaultHighlighter.DefaultHighlightPainter(Color.BLUE);

        updateDisplayedDocumentText();

        _searchPane.setVisible(_showSearchBtn.isSelected());
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

    private void clearHighlighting(){
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

        jToolBar1 = new javax.swing.JToolBar();
        _tailFileEnd = new javax.swing.JToggleButton();
        _showSearchBtn = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
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

        jToolBar1.setRollover(true);

        _tailFileEnd.setSelected(true);
        _tailFileEnd.setText("Tail");
        _tailFileEnd.setFocusable(false);
        _tailFileEnd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        _tailFileEnd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        _tailFileEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _tailFileEndActionPerformed(evt);
            }
        });
        jToolBar1.add(_tailFileEnd);

        _showSearchBtn.setSelected(true);
        _showSearchBtn.setText("Search");
        _showSearchBtn.setFocusable(false);
        _showSearchBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        _showSearchBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        _showSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _showSearchBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(_showSearchBtn);

        jButton1.setText("Refresh");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        add(jToolBar1, java.awt.BorderLayout.PAGE_START);

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea _fileContentTxt;
    private javax.swing.JButton _nextSearchResultBtn;
    private javax.swing.JButton _previousResultBtn;
    private javax.swing.JLabel _resultHitsLbl;
    private javax.swing.JLabel _resultRowsLbl;
    private javax.swing.JScrollPane _scrollPane;
    private javax.swing.JPanel _searchPane;
    private javax.swing.JTextField _searchTxt;
    private javax.swing.JToggleButton _showSearchBtn;
    private javax.swing.JToggleButton _tailFileEnd;
    private javax.swing.JButton jButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    private int getTextAreaNumberOfLines() {
        double height = _scrollPane.getSize().getHeight();
        int lineHeight = getLineHeight();

        return (int) Math.ceil(height / lineHeight);
    }

    private void updateDisplayedDocumentText() {
        try {
            String newline = System.getProperty("line.separator");

            _documentToTrack.analyzeFile();

            List<String> allLines = _documentToTrack.getTextLines(_currentNumberOfShowedLines);

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
                if(rowNumber > lastRow) {
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
    
    private void clearSearchResult()
    {
        _currentSearchResult = null;
        updateSearchResultLabels(0);
        clearHighlighting();
    }

    private void updateSearchResultLabels(int numberOfRows) {
        _resultRowsLbl.setText("Total Rows: " + numberOfRows);
        if(_currentSearchResult != null)
        {
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
