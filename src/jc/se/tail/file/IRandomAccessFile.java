/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.file;

import java.io.IOException;

/**
 *
 * @author ruffy
 */
public interface IRandomAccessFile extends AutoCloseable {
    void seek(long position) throws IOException;
    
    String readLine()  throws IOException;
    
    long getFilePointer()  throws IOException;
    
}
