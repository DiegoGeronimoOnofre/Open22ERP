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

package erpsystem.db;

import erpsystem.Log;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class MovProdDB {
    
    public static boolean add(MovProd movProd, int movType)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            
            int codMov   = movProd.getCodMov();
            int codProd  = movProd.getCodProd();
            int qt       = movProd.getQt();
            double preco = movProd.getPreco();
            double total = movProd.getTotal();
            
            String update = " insert "
                          + " into mov_prod "
                          + " values(" + codMov  + ","
                                       + codProd + ","
                                       + qt      + ","
                                       + preco   + ","
                                       + total
                          + ")";
            
            st.executeUpdate(update);
            
            //Atualizando o estoque.
            
            Estoque estoque = new Estoque();
            estoque.setCodProd(codProd);
            
            if ( movType == 1 )//compra
                estoque.setQt(qt);
            else//venda
                estoque.setQt(-qt);
                
            boolean result = EstoqueDB.addEstoque(estoque);    
            
            if ( result ){
                con.commit();
                return true;
            }
            else{
                con.rollback();
                return false;
            }
            
        }
        catch ( SQLException e ){
            Log.log(e);
            return false;
        }
    }
    
    public static java.util.List<MovProd> findProds(int codMov)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = " select mov_prod.cod_prod            as 'codprod',"
                          + "        produtos.descricao           as 'desc',"
                          + "        mov_prod.preco               as 'preco',"
                          + "        mov_prod.qt                  as 'qt',"
                          + "        mov_prod.qt * mov_prod.preco as 'total'"
                          + " from mov_prod, produtos "
                          + " where mov_prod.cod_prod = produtos.codigo "
                          + "   and mov_prod.cod_mov = " + codMov;
            
            ResultSet rs = st.executeQuery(update);
            List<MovProd> mpList = new ArrayList<>();
            
            while (rs.next()){
                MovProd mp = new MovProd();
                
                int codProd    = rs.getInt("codprod");
                String desc    = rs.getString("desc");
                double preco   = rs.getDouble("preco");
                int qt         = rs.getInt("qt");
                double total   = rs.getDouble("total");
                
                mp.setCodProd(codProd);
                mp.setDesc(desc);
                mp.setPreco(preco);
                mp.setQt(qt);
                mp.setTotal(total);
                mpList.add(mp);
            }
            
            return mpList;
        }
        catch ( SQLException e ){
            Log.log(e);
            return null;
        }        
    }
    
}
