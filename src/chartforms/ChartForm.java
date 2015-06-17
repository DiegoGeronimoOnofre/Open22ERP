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
package chartforms;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Diego
 */
public class ChartForm extends JDialog{
    
    final private JPanel pnlPanel = new JPanel()
    {
        @Override 
        public void paint(Graphics g)
        {
            super.paint(g);
            g.drawImage(image, 0, 0, null);
        }
    };
    
    public ChartForm()
    {
        setModal(false);
        pnlPanel.setLayout(new BorderLayout());
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(pnlPanel, BorderLayout.CENTER);
    }
    
    private BufferedImage image = null;
    
    public void showChart(final int w, final int h, BufferedImage image)
    {
        this.image = image;
        pnlPanel.setPreferredSize(new Dimension(w, h));
        this.setSize(w, h);
        this.setLocationRelativeTo(null);
        this.repaint();
        pnlPanel.repaint();
        this.setVisible(true);
    }
}
