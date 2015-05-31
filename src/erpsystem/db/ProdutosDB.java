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
public class ProdutosDB{
    public static int genCode()
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = "select max(codigo) as 'cod' from produtos";
            ResultSet rs = st.executeQuery(update);
            rs.next();
            return rs.getInt("cod") + 1;
        }
        catch ( Exception e ){
            Log.log(e);
            return -1;
        }
    }
    
    public static void add(Produto prod)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            
            int cod          = genCode();
            String codBarras = prod.getCodBarras();
            String desc      = prod.getDescricao();
            double preco     = prod.getPreco();
            
            String update = " insert "
                          + " into produtos "
                          + " values(" + cod + ","
                                    + "'" + codBarras + "',"
                                    + "'" + desc + "',"
                                    + preco
                          + ")";
            
            st.executeUpdate(update);
            con.commit();
        }
        catch ( Exception e ){
            Log.log(e);
        }
    }
    
    public static boolean exists(int code)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = " select codigo as 'cod' "
                          + " from produtos "
                          + " where codigo = " + code;
            ResultSet rs = st.executeQuery(update);
            
            if ( rs.next() )
                return true;
            else
                return false;
        }
        catch ( Exception e ){
            Log.log(e);
            return false;
        }        
    }    
    
    public static Produto find(int cod)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            
            String sql = " select * "
                       + " from produtos "
                       + " where codigo = " + cod;
            
            ResultSet rs = st.executeQuery(sql);
            Produto prod = new Produto();
            
            if (rs.next()){
                int icod = rs.getInt("codigo");
                String codBarras = rs.getString("cod_barras");
                String desc = rs.getString("descricao");
                double preco = rs.getDouble("preco");

                prod.setCodigo(icod);
                prod.setCodBarras(codBarras);
                prod.setDescricao(desc);
                prod.setPreco(preco);

                return prod;
            }
            else
                return null;

        }
        catch ( Exception e ){
            Log.log(e);
            return null;
        }        
    }
    
    public static List<Produto> findProd(String desc)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = " select produtos.codigo     as 'cod',"
                          + "        produtos.cod_barras as 'cod_barras',"
                          + "        produtos.descricao  as 'desc',"
                          + "        produtos.preco      as 'preco'"
                          + " from produtos "
                          + " where upper(trim(produtos.descricao)) like '%" + desc.trim().toUpperCase() + "%'";
            
            ResultSet rs = st.executeQuery(update);
            List<Produto> prodList = new ArrayList<>();
            
            while (rs.next()){
                final int cod    = rs.getInt("cod");     
                String codBarras = rs.getString("cod_barras");
                String descricao = rs.getString("desc");
                double preco     = rs.getDouble("preco");
                
                Produto prod = new Produto();             
                prod.setCodigo(cod);
                prod.setCodBarras(codBarras);
                prod.setDescricao(descricao);
                prod.setPreco(preco);
                
                prodList.add(prod);
            }
            
            return prodList;
        }
        catch ( SQLException e ){
            Log.log(e);
            return null;
        }        
    }
}
