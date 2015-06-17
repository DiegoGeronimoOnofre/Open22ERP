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

/**
 *
 * @author Diego
 */


public class EstoqueDB {
    public static boolean addEstoque(Estoque estoqueAdd)
    {
        int codProd = estoqueAdd.getCodProd();
        int qt = estoqueAdd.getQt();       
        Connection con = DB.getConnection();
        
        try{
            
            Statement st = con.createStatement();          
            String update = " insert "
                          + " into estoque "
                          + " values(" + codProd + ","
                                       + qt
                          + ")";
            
            st.executeUpdate(update);
            con.commit();
            return true;

        }
        catch ( SQLException e ){
            try{
                Estoque estoque = find(codProd);
                
                if ( estoque != null ){
                    Boolean exists = exists(codProd);
                    
                    if ( exists != null){
                        if ( exists ){                           
                            
                            final int nowQt = estoque.getQt();
                            Statement st = con.createStatement();          
                            String update = " update "
                                          + " estoque "
                                          + " set qt = " + (nowQt + qt)
                                          + " where cod_prod = " + codProd;

                            st.executeUpdate(update);
                            con.commit(); 
                            return true;
                        }
                        else
                            System.out.println("Possibilidade: 456754");
                    }
                    else
                        System.out.println("Possibilidade: 456472");
                }
                else{
                    System.out.println("Possibilidade: 521845");
                }
      
            }
            catch ( Exception ee ){
                Log.log(e);
            }
        }
        
        return false;
    }    
    
    public static Boolean exists(int cod)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String query = " select estoque.cod_prod"
                          + " from estoque "
                          + " where estoque.cod_prod = " + cod;
            
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        }
        catch ( Exception e ){
            Log.log(e);
            return null;
        } 
    }
    
    public static Estoque find(int code)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = " select estoque.cod_prod as 'cod',"
                          + "        estoque.qt       as 'qt'"
                          + " from estoque "
                          + " where estoque.cod_prod = " + code;
            
            ResultSet rs = st.executeQuery(update);
            
            if (rs.next()){
                int cod = rs.getInt("cod");
                int qt   = rs.getInt("qt");
                Estoque estoque = new Estoque();
                estoque.setCodProd(cod);
                estoque.setQt(qt);
                return estoque;
            }
            else
                return null;
        }
        catch ( Exception e ){
            Log.log(e);
            return null;
        }        
    }    
}
