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
public class ChartForm002 extends ChartForm{
    public ChartForm002()
    {
        this.setTitle("Histórico");
    }
    
    public void createAndShow()
    {
        final int w = 1024;
        final int h = 768;    
        
        Calendar finalCalendar = Calendar.getInstance();
        long time              = System.currentTimeMillis();
        finalCalendar.setTimeInMillis(time);
        Calendar initialCalendar = Calendar.getInstance();
        initialCalendar.setTimeInMillis(time);
        initialCalendar.add(Calendar.DAY_OF_MONTH, -8);
        int interval = 1;
        
        //Cria o gráfico em formato de pixels.
        BufferedImage image = Charts.create002(w, 
                                               h,
                                               initialCalendar,
                                               interval);//Dia do mês
        //Mostra o gráfico na janela de observação.                                       
        showChart(w, h, image);
    }
}
