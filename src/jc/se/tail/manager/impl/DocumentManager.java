/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.manager.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jc.se.tail.model.document.FileDocument;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.WatchEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import jc.se.tail.manager.IDocumentManager;
import jc.se.tail.model.document.DocumentViewPackage;

/**
 *
 * @author ruffy
 */
public class DocumentManager implements IDocumentManager {

    Map<String, FileDocument> _openedDocuments;
    Map<String, List<DocumentViewPackage>> _openedViewPackages;

    Map<String, List<FileDocument>> _watchedDirectories;
    Map<String, WatchKey> _watchedDirectoryKeys;
    Map< WatchKey, Path> _watchKeyPaths;
    DirectoryEventsListener _directoriesListener;

    public DocumentManager() {

        _openedDocuments = new HashMap<>();
        _openedViewPackages = new HashMap<>();

        _watchedDirectories = new HashMap<String, List<FileDocument>>();
        _watchedDirectoryKeys = new HashMap<String, WatchKey>();
        _watchKeyPaths = new HashMap<WatchKey, Path>();
    }

    private void activateDirectoriesListener(WatchService watchService) {
        _directoriesListener = new DirectoryEventsListener(watchService, this);
        _directoriesListener.setPriority(Thread.MIN_PRIORITY);
        _directoriesListener.start();
    }

    @Override
    public DocumentViewPackage openDocumentViewPackage(File file) throws Exception {

        String filePath = getFilePath(file);

        FileDocument openDocument = null;
        // 1. We check if the document is already open.
        if (_openedDocuments.containsKey(filePath)) {
            openDocument = _openedDocuments.get(filePath);
        } else {
            // 2. Otherwise we create a new document.
            openDocument = new FileDocument(file);
            openDocument.analyzeFile();
            _openedDocuments.put(filePath, openDocument);
            startTrackDocument(openDocument);

        }
        // 3. We create a new document package for the file.
        DocumentViewPackage viewPackage = new DocumentViewPackage(openDocument);
        addViewPackage(filePath, viewPackage);

        return viewPackage;
    }

    private void addViewPackage(String filePath, DocumentViewPackage viewPackage) {
        // 4. The package is registered and returned
        if (!_openedViewPackages.containsKey(filePath)) {
            _openedViewPackages.put(filePath, new ArrayList<DocumentViewPackage>());
        }
        _openedViewPackages.get(filePath).add(viewPackage);
    }

    public void closeDocumentViewPackage(DocumentViewPackage viewPackage) throws Exception {

        // 1. We find the document for the view package.
        String documentId = viewPackage.getSourceId();
        // 2. We unregister the document view package and removes the document.
        if(_openedViewPackages.containsKey(documentId)) {
            _openedViewPackages.get(documentId).remove(viewPackage);
            if(_openedViewPackages.get(documentId).size() < 1) {
                _openedViewPackages.remove(documentId);
            }
        }
        // 3. We might stop tracking the document.
        if(!_openedViewPackages.containsKey(documentId));
        {
            closeDocument(documentId);
        }
    }
    
    private void closeDocument(String documentId) {
        if(_openedDocuments.containsKey(documentId)) {
            try {
                stopTrackDocument(_openedDocuments.get(documentId));
            } catch (Exception ex) {
                Logger.getLogger(DocumentManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            _openedDocuments.remove(documentId);
        }
    }

    private void startTrackDocument(FileDocument document) throws IOException, Exception {
        File fileToTrack = document.getFile();
        File directory = getFileDirectory(fileToTrack);

        if (directory != null) {
            String directoryPath = directory.getPath();
            boolean directoryIsWatched = _watchedDirectories.containsKey(directoryPath);
            if (!directoryIsWatched) {
                startListeningToDirectory(directory, directoryPath);
            }
            _watchedDirectories.get(directoryPath).add(document);

        } else {
            throw new Exception(String.format("Could not find parent directory for file '%s'!", fileToTrack.getPath()));
        }
    }

    private void stopTrackDocument(FileDocument document) throws Exception {

        File fileToTrack = document.getFile();
        File directory = getFileDirectory(fileToTrack);

        if (directory != null) {
            String directoryPath = directory.getPath();
            boolean directoryIsWatched = _watchedDirectories.containsKey(directoryPath);
            if (directoryIsWatched) {
                _watchedDirectories.get(directoryPath).remove(document);
                if (_watchedDirectories.get(directoryPath).isEmpty()) {
                    stopListeningToDirectory(directoryPath);
                }
            }
        } else {
            throw new Exception(String.format("Could not find parent directory for file '%s'!", fileToTrack.getPath()));
        }
    }

    private String getDocumentPath(FileDocument document) {
        if (document != null) {
            return getFilePath(document.getFile());
        }
        return null;
    }

    private String getFilePath(File file) {
        if (file != null) {
            return file.getAbsolutePath();
        }
        return null;
    }

    private File getFileDirectory(File fileToTrack) {
        if (fileToTrack.isFile()) {
            return fileToTrack.getParentFile();
        }
        return null;
    }

    public void handleUpdatedWatchKey(WatchKey watchKey) {
        for (WatchEvent<?> event : watchKey.pollEvents()) {
            WatchEvent.Kind<?> kind = event.kind();

            if (kind == OVERFLOW) {
                continue;
            }
            // Context for directory entry event is the file name of entry
            WatchEvent<Path> ev = (WatchEvent<Path>) event;
            Path fileInDirectory = ev.context();
            Path directoryPath = _watchKeyPaths.get(watchKey);
            Path completePath = directoryPath.resolve(fileInDirectory);
            String directoryPathString = directoryPath.toString();

            if (_watchedDirectories.containsKey(directoryPathString)) {
                List<FileDocument> arrayOfFiles = _watchedDirectories.get(directoryPathString);
                if (arrayOfFiles != null) {
                    for (FileDocument document : arrayOfFiles) {
                        if (document.isFileDefinedByPath(completePath)) {
                            try {
                                //We notify that the document has been updated.
                                document.analyzeFile();
                                document.updateIsModified();
                            } catch (IOException ex) {
                                Logger.getLogger(DocumentManager.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    private void startListeningToDirectory(File directory, String directoryPath) throws IOException {
        WatchService watchService = FileSystems.getDefault().newWatchService();

        if (_directoriesListener == null) {
            activateDirectoriesListener(watchService);
        }

        WatchKey watchKey = directory.toPath().register(watchService, ENTRY_MODIFY);
        _watchedDirectoryKeys.put(directoryPath, watchKey);
        _watchedDirectories.put(directoryPath, new ArrayList<FileDocument>());
        _watchKeyPaths.put(watchKey, directory.toPath());
    }

    private void stopListeningToDirectory(String directoryPath) {
        //We no longer need to follow the directory.
        WatchKey watchKey = _watchedDirectoryKeys.get(directoryPath);
        if (watchKey != null) {
            watchKey.cancel();
            _watchedDirectoryKeys.remove(directoryPath);
            _watchedDirectories.remove(directoryPath);
        }
    }
}
