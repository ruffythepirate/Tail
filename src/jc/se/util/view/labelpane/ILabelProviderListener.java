/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.util.view.labelpane;

/**
 *
 * @author ruffy
 */
public interface ILabelProviderListener {
    
    void OnLabelAdded(LabelEvent labelEvent);
    
    void OnLabelRemoved(LabelEvent labelEvent);
}
