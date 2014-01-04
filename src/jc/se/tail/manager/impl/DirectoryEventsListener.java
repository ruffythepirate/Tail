/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.manager.impl;

import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 *
 * @author Ruffy
 */
public class DirectoryEventsListener extends Thread {

    private boolean _isRunning;
    
    private WatchService _watchService;
    private DocumentManager _documentManager;
    
    public DirectoryEventsListener(WatchService watchService, DocumentManager documentManager) {
        _watchService = watchService;
        _documentManager = documentManager;
    }
    
    public void run() {
        _isRunning = true;
        while(_isRunning) {
            try {
                WatchKey key = _watchService.take();
                _documentManager.handleUpdatedWatchKey(key);
                key.reset();
            } catch(InterruptedException ex) {
                _isRunning = false;
            }
        }
        
    }
    
    
    
}
