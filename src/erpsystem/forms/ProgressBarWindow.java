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
package erpsystem.forms;

import erpsystem.Util;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JProgressBar;

/**
 *
 * @author Diego
 */
public class ProgressBarWindow extends Window{

    private JProgressBar pbrProgress = new JProgressBar();
    
    private class MouseHandler extends MouseAdapter
    {
        private int x = -1;
        private int y = -1;
    
        @Override
        public void mousePressed( MouseEvent me )
        {
            x = -1;
            y = -1;
        }
    
        @Override
        public void mouseDragged( MouseEvent me )
        {
            if ( x == -1 )
                x = ( me.getX() - 1 );
            if ( y == -1 )
                y = ( me.getY() - 1 );

            ProgressBarWindow.this.setLocation( getX() + ( ( me.getX() ) - x ),
                                                getY() + ( ( me.getY() ) - y ) );
        }
    }
    
    public ProgressBarWindow()
    {
        super( null );
        final int w = 200;
        final int h = 20;
        Point p = Util.getCenterPoint( w, h );
        setSize( w, h );
        setLocation( p );
        setLayout( null );
        pbrProgress.setSize( w, h );
        pbrProgress.setLocation( 0, 0 );
        pbrProgress.setValue( 0 );
        MouseHandler mh = new MouseHandler();
        pbrProgress.addMouseListener( mh );
        pbrProgress.addMouseMotionListener( mh );
        add( pbrProgress );
    }
    
    public JProgressBar getProgressBar()
    {
        return pbrProgress;
    }
    
    public ProgressBarWindow( String s )
    {
        this();
        pbrProgress.setStringPainted( true );
        pbrProgress.setString( s );
    }
}
