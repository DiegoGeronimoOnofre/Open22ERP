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

import erpsystem.chart.Chart001;
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
        
        final int y = 2015;
        final int m = 5; // O primeiro mês começa com 0, o segundo com 1...
        final int d = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(y, m, d);
        long initialDate = calendar.getTime().getTime();
        long finalDate = System.currentTimeMillis();
        
        double compra = erpsystem.db.chart.Chart001.getValorCompraEm(initialDate, finalDate);
        double venda = erpsystem.db.chart.Chart001.getValorVendaEm(initialDate, finalDate);
        double lucro = erpsystem.db.chart.Chart001.getValorLucroEm(initialDate, finalDate);
        BufferedImage image = Chart001.create001(w, h, compra, venda, lucro);
        showChart(w, h, image);
    }
}
