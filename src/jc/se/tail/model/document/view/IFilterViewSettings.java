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
public interface IFilterViewSettings {

    String getFilterText();

    int getRowsBefore();

    int getRowsAfter();

    boolean getShouldExcludeRows();
}
