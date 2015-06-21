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

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import static javax.swing.JOptionPane.*;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego
 */
public class Util {

    public static void msg(String s)
    {
        showMessageDialog(null, s);
    }
    
    public static boolean isInt(String value)
    {
        try{
            Integer.parseInt(value);
            return true;
        }
        catch ( Exception e ){
            return false;
        }
    }
    
    public static boolean isDouble(String value)
    {
        try{
            Double.parseDouble(value);
            return true;
        }
        catch ( Exception e ){
            return false;
        }
    }
    
    public static Point getCenterPoint( final int w, final int h )
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        final int x = ( int ) ( d.getWidth() / 2 ) - ( w / 2 );
        final int y = ( int ) ( d.getHeight() / 2 ) - ( h / 2 );
        return new Point( x, y );
    }     
    
    public static boolean isValidPriceChar(char charValue)
    {
        String s =  String.valueOf(charValue);
        int value = isInt(s) ? Integer.parseInt(s):-1;
        
        if ( value >= 0 && value <= 9  )
            return true;
        else if ( charValue == '.' )
            return true;
        
        return false;
    }
    
    public static DefaultTableCellRenderer getDefaultCellRenderer(){
        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER); 
        return cellRender;
    }
    
}
