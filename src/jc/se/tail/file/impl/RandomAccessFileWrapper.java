/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.tail.file.impl;

import java.io.IOException;
import java.io.RandomAccessFile;
import jc.se.tail.file.IRandomAccessFile;

/**
 *
 * @author ruffy
 */
public class RandomAccessFileWrapper implements IRandomAccessFile{
    
    private RandomAccessFile _randomAccessFile;
    
    public RandomAccessFileWrapper(RandomAccessFile randomAccessFile){
        _randomAccessFile = randomAccessFile;
    }

    @Override
    public void seek(long position) throws IOException{
        _randomAccessFile.seek(position);
    }

    @Override
    public String readLine()  throws IOException{
        return _randomAccessFile.readLine();
    }

    @Override
    public long getFilePointer() throws IOException {
        return _randomAccessFile.getFilePointer();
    }

    @Override
    public void close() throws Exception {
        _randomAccessFile.close();
    }
    
}
