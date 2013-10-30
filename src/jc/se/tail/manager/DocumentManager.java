/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.manager;

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
import jc.se.tail.model.document.Document;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.WatchEvent;

/**
 *
 * @author ruffy
 */
public class DocumentManager {

    Map<String, List<Document>> _watchedFiles;
    Map<String, WatchKey> _watchedDirectoryKeys;
    Map< WatchKey, Path> _watchKeyPaths;
    DirectoryEventsListener _directoriesListener;

    public DocumentManager() {
        _watchedFiles = new HashMap<String, List<Document>>();
        _watchedDirectoryKeys = new HashMap<String, WatchKey>();
         _watchKeyPaths = new HashMap<WatchKey, Path>(); 
    }

    private void activateDirectoriesListener(WatchService watchService) {
        _directoriesListener = new DirectoryEventsListener(watchService, this);
        _directoriesListener.setPriority(Thread.MIN_PRIORITY);
        _directoriesListener.start();
    }

    public void startTrackDocument(Document document) throws IOException, Exception {



        File fileToTrack = document.getFile();
        File directory = getFileDirectory(fileToTrack);

        if (directory != null) {
            String directoryPath = directory.getPath();
            boolean directoryIsWatched = _watchedFiles.containsKey(directoryPath);
            if (!directoryIsWatched) {
                startListeningToDirectory(directory, directoryPath);
            }
            _watchedFiles.get(directoryPath).add(document);

        } else {
            throw new Exception(String.format("Could not find parent directory for file '%s'!", fileToTrack.getPath()));
        }
    }

    public void stopTrackDocument(Document document) throws Exception {

        File fileToTrack = document.getFile();
        File directory = getFileDirectory(fileToTrack);

        if (directory != null) {
            String directoryPath = directory.getPath();
            boolean directoryIsWatched = _watchedFiles.containsKey(directoryPath);
            if (directoryIsWatched) {
                _watchedFiles.get(directoryPath).remove(document);
                if (_watchedFiles.get(directoryPath).isEmpty()) {
                    stopListeningToDirectory(directoryPath);
                }
            }
        } else {
            throw new Exception(String.format("Could not find parent directory for file '%s'!", fileToTrack.getPath()));
        }
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

            if (_watchedFiles.containsKey(directoryPathString)) {
                List<Document> arrayOfFiles = _watchedFiles.get(directoryPathString);
                if (arrayOfFiles != null) {
                    for (Document document : arrayOfFiles) {
                        if (document.equals(completePath)) {
                            //We notify that the document has been updated.
                            document.updateIsModified();
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
        _watchedFiles.put(directoryPath, new ArrayList<Document>());
        _watchKeyPaths.put(watchKey, directory.toPath());
    }

    private void stopListeningToDirectory(String directoryPath) {
        //We no longer need to follow the directory.
        WatchKey watchKey = _watchedDirectoryKeys.get(directoryPath);
        if (watchKey != null) {
            watchKey.cancel();
            _watchedDirectoryKeys.remove(directoryPath);
            _watchedFiles.remove(directoryPath);
        }
    }
}
