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
package erpsystem.db.chart;

import erpsystem.Log;
import erpsystem.db.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Diego
 */
public class Chart001 {
    public static Double getValorCompraEm(long initialDate, 
                                          long finalDate)
    {
        try{
            Connection conn = DB.getConnection();
            Statement st = conn.createStatement();
            String sql = " select sum(coalesce(coalesce(mov_prod.preco) * coalesce(mov_prod.qt))) as 'compravalue'"
                       + " from mov, mov_prod"
                       + " where mov.codigo = mov_prod.cod_mov"
                       + "   and mov.mov_type = 1"
                       + "   and ( mov.mov_time >= " + initialDate + " and mov.mov_time <= " + finalDate + ")";
            
            ResultSet rs = st.executeQuery(sql);
            
            if ( rs.next()){
                double value = rs.getDouble("compravalue");
                return value;
            }
            else
                return null;
        }
        catch ( Exception e ){
            Log.log(e);
            return null;
        }
    }
    
    public static Double getValorVendaEm(long initialDate, 
                                          long finalDate)
    {
        try{
            Connection conn = DB.getConnection();
            Statement st = conn.createStatement();
            String sql = " select sum(coalesce(coalesce(mov_prod.preco) * coalesce(mov_prod.qt))) as 'compravalue'"
                       + " from mov, mov_prod"
                       + " where mov.codigo = mov_prod.cod_mov"
                       + "   and mov.mov_type = 2"
                       + "   and ( mov.mov_time >= " + initialDate + " and mov.mov_time <= " + finalDate + ")";
            
            ResultSet rs = st.executeQuery(sql);
            
            if ( rs.next()){
                double value = rs.getDouble("compravalue");
                return value;
            }
            else
                return null;
        }
        catch ( Exception e ){
            Log.log(e);
            return null;
        }
    }    
    
    public static Double getValorLucroEm(long initialDate, long finalDate)
    {
        Double compra = getValorCompraEm(initialDate, finalDate);
        Double venda = getValorVendaEm(initialDate, finalDate);
        return venda - compra;
    }
}
