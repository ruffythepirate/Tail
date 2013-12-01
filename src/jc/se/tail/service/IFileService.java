/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.service;

import java.io.FileNotFoundException;
import jc.se.tail.file.IRandomAccessFile;

/**
 *
 * @author ruffy
 */
public interface IFileService {

    public IRandomAccessFile getRandomAccessFile(String filePath, String readWriteMode) throws FileNotFoundException;
}
