/*
 * The MIT License
 *
 * Copyright 2015 Diego Geronimo D' Onofre.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package erpsystem;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

/**
 *
 * @author Diego
 */
 
 //Este arquivo foi criar para facilitar a geração de logs no sistema
 
public class Log {
    private static final Charset encoding = Charset.forName("UTF8");
    
    private static void persist(Exception e)
    {
        String path = erpsystem.ERPSystem.getWorkDir();
        final char sep = File.separatorChar;
        File logDir = new File(path + sep + "logs");
        
        if ( !logDir.exists() ){
            if (! logDir.mkdir() ){
                return;
            }
        }
        
        final long time = System.currentTimeMillis();
        String fileName = String.valueOf(logDir.getAbsolutePath() + sep + time);
        File newFile = new File(fileName);

        try{
            if ( newFile.createNewFile() ){
                String filePath = newFile.getAbsolutePath();
                RandomAccessFile f = new RandomAccessFile(filePath, "rw");
                byte[] bytes = e.toString().getBytes(encoding);
                f.write(bytes);
                StackTraceElement[] stackList = e.getStackTrace();
                
                for (int i = 0; i < stackList.length; i++){
                    StackTraceElement se = stackList[i];
                    String summary = createSummary(se);                 
                    byte[] bs = summary.getBytes(encoding);
                    f.write(bs);
                }
                
                f.close();
            }
        }
        catch ( IOException ee ){
            System.out.println(ee.toString());
        }
    }
    
    private static String readException(File file)
    {
        try{
            RandomAccessFile f = new RandomAccessFile(file, "rw");
            byte[] bytes = new byte[(int)f.length()];
            f.read(bytes);
            f.close();
            String s = new String(bytes, encoding);
            return s;
        }
        catch ( Exception e ){
            System.out.println(e.toString());
            return null;
        }
    }
    
    private static String createSummary(StackTraceElement se)
    {
        String s = "\n";
        s += "File:" + se.getFileName() + "\n";
        s += "Class:" + se.getClassName() + "\n";
        s += "Method:" + se.getMethodName() + "\n";
        s += "Line:" + se.getLineNumber() + "\n";
        return s;
    }
    
    //Este método pode ser usado para mostrar uma mensagem na saída.
    public static void log(Object s)
    {
        System.out.println(s);
    }
    
    //Este método pode ser usado para mostrar uma mensagem na saída, mas
    //Também armazena esta mensagem, para posteriormente seja possível ter acesso a ela.
    public static void log(Exception e)
    {
        System.out.println(e.toString());
        persist(e);
    }
}
