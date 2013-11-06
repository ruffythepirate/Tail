/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.model.document;

import java.util.HashMap;
import java.util.Map;
import jc.se.util.view.labelpane.LabelItem;
import jc.se.util.view.labelpane.LabelList;

/**
 *
 * @author ruffy
 */
public class DocumentViewPackageCommunicator extends DocumentViewPackage {

    protected LabelList _labelList;

    private Map<DocumentViewBase, LabelItem> _labelItems;
    
    public DocumentViewPackageCommunicator(DocumentViewBase rootView) {
        super(rootView);
        
        _labelItems = new HashMap<DocumentViewBase, LabelItem>();
        _labelList = new LabelList();
    }

    @Override
    public void appendDocumentView(DocumentViewBase documentView) {
        super.appendDocumentView(documentView); //To change body of generated methods, choose Tools | Templates.
   
        LabelItem addedLabel = getLabelList().addLabel(documentView.getViewTitle(), documentView);
        
        if(addedLabel != null) {
            _labelItems.put(documentView, addedLabel);
        }
    }

    @Override
    public void removeDocumentView(DocumentViewBase documentView) {
        super.removeDocumentView(documentView); //To change body of generated methods, choose Tools | Templates.
        
        if(_labelItems.containsKey(documentView)){
            LabelItem labelToRemove =  _labelItems.get(documentView);            
            getLabelList().removeLabel(labelToRemove);
            
        }

    }    

    /**
     * @return the _labelList
     */
    public LabelList getLabelList() {
        return _labelList;
    }
}
