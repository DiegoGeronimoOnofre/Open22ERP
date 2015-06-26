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

import erpsystem.db.chart.Chart001;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Diego
 */
public class Charts {
    /**Gŕafico criado para mostrar 
    o faturamento considerando o 
    valor bruto das saídas das compras R$
    e valor bruto das entradas das vendas R$
    realizadas no período especificado
    Também é de responsabilidade do gráfico
    monstrar graficamente o valor do lucro.
    */
    
    public static BufferedImage create001(int w, 
                                          int h, 
                                          Calendar initialCalendar,
                                          Calendar finalCalendar)
    {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        String COMPRA = "Compra R$";
        String VENDA  = "Venda R$";
        String LUCRO  = "Lucro R$";
        
        long initialDate = initialCalendar.getTime().getTime();
        long finalDate   = finalCalendar.getTime().getTime();
        
        //toString para depuração.
        String s1 = toString(initialCalendar);
        String s2 = toString(finalCalendar);  

        double compra = erpsystem.db.chart.Chart001.getValorCompraEm(initialDate, finalDate);
        double venda  = erpsystem.db.chart.Chart001.getValorVendaEm(initialDate, finalDate);
        double lucro  = erpsystem.db.chart.Chart001.getValorLucroEm(initialDate, finalDate);
        
        ds.addValue(compra,COMPRA, COMPRA);
        ds.addValue(venda,VENDA, VENDA);
        ds.addValue(lucro,LUCRO, LUCRO);
        
        JFreeChart chart = ChartFactory.createBarChart3D("Faturamento", 
                                                         "Faturamento", 
                                                         "Valor R$", 
                                                         ds);    
        
        return chart.createBufferedImage(w, h);
    }
    
    private static Calendar copyCalendar(Calendar calendar)
    {
        Calendar result = Calendar.getInstance();
        result.set(calendar.get(Calendar.YEAR),
                   calendar.get(Calendar.MONTH),
                   calendar.get(Calendar.DAY_OF_MONTH),
                   calendar.get(Calendar.HOUR_OF_DAY),
                   calendar.get(Calendar.MINUTE),
                   calendar.get(Calendar.SECOND));        
        return result;
    }
    
    private static Calendar getCurrentCalendar()
    {
        Calendar calendar = Calendar.getInstance();
        long ct = System.currentTimeMillis();
        calendar.setTimeInMillis(ct);
        return calendar;
    }
    
    private static String toString(Calendar calendar)
    {
        String result = "";
        int y  = calendar.get(Calendar.YEAR);
        int m  = calendar.get(Calendar.MONTH) + 1;
        int d  = calendar.get(Calendar.DAY_OF_MONTH);
        int h  = calendar.get(Calendar.HOUR_OF_DAY);
        int mm = calendar.get(Calendar.MINUTE);
        int s  = calendar.get(Calendar.SECOND);
        result += d + "/" + m + "/" + y + ":" + h + "/" + mm + "/" + s; 
        return result;        
    }
    
    /** Gráfico criado para mostrar o histórico de
     * faturamento periodicamente considerando o valor bruto
     * em R$ de lucro.
    */
    
    public static BufferedImage create002(int w, 
                                          int h, 
                                          Calendar initialCalendar,
                                          int interval)
    {
        //Inicio
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        String LUCRO  = "Lucro R$";
        
        //Configurando os Calendars.
        Calendar finalCalendar = getCurrentCalendar();
        Calendar finalValue    = copyCalendar(initialCalendar);
        Calendar initialValue  = copyCalendar(initialCalendar); 
        
        while (finalCalendar.after(finalValue)){
            
            //ajustando os Calendars ponteiros.
            initialValue = copyCalendar(finalValue);
            finalValue.add(Calendar.DAY_OF_MONTH, interval);
            
            //toString para depuração.
            String s1 = toString(initialValue);
            String s2 = toString(finalValue);            
            
            //Obtendo as datas como raw para 
            //possibilitar comparação com o banco de dados.
            long value1 = initialValue.getTimeInMillis();
            long value2 = finalValue.getTimeInMillis();
            
            //Obtendo a nova abstração de lucro com os intervalos especificados.
            double lucro  = Chart001.getValorLucroEm(value1, value2);
            double venda  = Chart001.getValorVendaEm(value1, value2);
            double compra = Chart001.getValorCompraEm(value1, value2);
            
            
            //Adicionando a nova abstração na coleção de dados do gráfico.
            int y = initialValue.get(Calendar.YEAR);
            int m = initialValue.get(Calendar.MONTH) + 1;
            int d = initialValue.get(Calendar.DAY_OF_MONTH);
            String identifier = d + "/" + m + "/" + y;
            ds.addValue(lucro,
                        "Lucro",
                        identifier);                                    
            ds.addValue(venda,
                        "Venda",
                        identifier);                                    
            ds.addValue(compra,
                        "Compra",
                        identifier);                                    
            
        }
        
        //Criando o gráfico abstrato em 3D.
        JFreeChart chart = ChartFactory.createBarChart3D("Faturamento", 
                                                         "Faturamento", 
                                                         "Valor R$", 
                                                         ds,
                                                         PlotOrientation.VERTICAL, 
                                                         true, 
                                                         true ,
                                                         false);  
        
        //Criando e retornando a imagem como 
        //mapa de pixels do gráfico.
        return chart.createBufferedImage(w, h);              
    }
}
