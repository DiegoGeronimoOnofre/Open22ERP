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

import erpsystem.chart.Charts;
import erpsystem.forms.IntervalView;

import java.awt.image.BufferedImage;
import java.util.Calendar;

/**
 *
 * @author Diego
 */
public class ChartForm001 extends ChartForm{
    public ChartForm001()
    {
        this.setTitle("Faturamento");
    }
    
    public void createAndShow()
    {
        final int w = 1024;
        final int h = 768;
        
        IntervalView intervalView = new IntervalView(null, true);
        intervalView.setVisible(true);
        IntervalView.Params params = intervalView.getParams();
        
        if ( params != null ){

            //Obtendo as datas iniciais e finais.
            Calendar initialCalendar = params.getInitialCalendar();
            Calendar finalCalendar = params.getFinalCalendar();

            //Cria o gráfico como um mapa de pixels.
            BufferedImage image = Charts.create001(w, 
                                                   h,
                                                   initialCalendar, 
                                                   finalCalendar);
            //Mostra o gráfico na janela de observação.                                       
            showChart(w, h, image);
        }
    }
}
