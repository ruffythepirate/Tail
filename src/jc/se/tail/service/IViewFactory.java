/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.service;

import jc.se.tail.model.document.Document;
import jc.se.tail.view.*;

/**
 *
 * @author ruffy
 */
public interface IViewFactory {
    DocumentViewPane createDocumentViewPane(Document documentToTrack);

    TailWindow createTailWindow();
}

