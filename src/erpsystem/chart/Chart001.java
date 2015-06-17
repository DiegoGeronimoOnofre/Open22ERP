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
package erpsystem.chart;

import java.awt.image.BufferedImage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Diego
 */
public class Chart001 {
    /*Gŕafico criado para mostrar 
    o faturamento considerando o 
    valor bruto das saídas das compras R$
    e valor bruto das entradas das vendas R$
    realizadas no período especificado.
    Também é de responsabilidade do gráfico
    monstrar graficamente o valor do lucro.*/
    
    public static BufferedImage create001(int w, 
                                          int h, 
                                          double compra, 
                                          double venda, 
                                          double lucro)
    {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        String COMPRA = "Compra Bruto";
        String VENDA = "Venda Bruto";
        String LUCRO = "Lucro Bruto";
        
        ds.addValue(compra,COMPRA, COMPRA);
        ds.addValue(venda,VENDA, VENDA);
        ds.addValue(lucro,LUCRO, LUCRO);
        
        JFreeChart chart = ChartFactory.createBarChart3D("Faturamento", 
                                                         "Faturamento", 
                                                         "Valor R$", 
                                                         ds);    
        
        return chart.createBufferedImage(w, h);
    }
}
