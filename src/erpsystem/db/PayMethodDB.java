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
public class PayMethodDB {
    public static int genCode()
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = "select max(codigo) as 'cod' from formaspagamento";
            ResultSet rs = st.executeQuery(update);
            rs.next();
            return rs.getInt("cod") + 1;
        }
        catch ( Exception e ){
            Log.log(e);
            return -1;
        }
    }
    
    public static void add(PayMethod pm)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            
            int cod      = genCode();
            String desc  = pm.getDescricao();
            int limite   = pm.getLimite();
            
            String update = " insert "
                          + " into formaspagamento "
                          + " values(" + cod + ","
                                    + "'" + desc + "',"
                                    + "" + limite + ""
                          + ")";
            
            st.executeUpdate(update);
            con.commit();

        }
        catch ( Exception e ){
            Log.log(e);
        }
    }
    
    public static List<PayMethod> findAll()
    {
        return findPayMethod("%");
    }
    
    public static List<PayMethod> findPayMethod(String descricao)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = " select formaspagamento.codigo as 'cod',"
                          + "        formaspagamento.descricao as 'desc',"
                          + "        formaspagamento.limite_value as 'lim'"
                          + " from formaspagamento "
                          + " where upper(trim(formaspagamento.descricao)) like '%" + descricao.trim().toUpperCase() + "%'";
            
            ResultSet rs = st.executeQuery(update);
            List<PayMethod> pmList = new ArrayList<>();
            
            while (rs.next()){
                final int cod  = rs.getInt("cod");
                
                String desc = rs.getString("desc");
                int lim     = rs.getInt("lim");
                
                PayMethod pm = new PayMethod();
                
                pm.setCod(cod);
                pm.setDescricao(desc);
                pm.setLimite(lim);
                
                pmList.add(pm);
            }
            
            return pmList;
        }
        catch ( SQLException e ){
            Log.log(e);
            return null;
        }
    }
    
    public static PayMethod find(int code)
    {      
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = " select formaspagamento.codigo as 'cod',"
                          + "        formaspagamento.descricao as 'desc',"
                          + "        formaspagamento.limite_value as 'lim'"
                          + " from formaspagamento "
                          + " where codigo = " + code;
            
            ResultSet rs = st.executeQuery(update);
            
            if (rs.next()){
                final int cod  = rs.getInt("cod");
                
                String desc = rs.getString("desc");
                int lim     = rs.getInt("lim");
                
                PayMethod pm = new PayMethod();
                
                pm.setCod(cod);
                pm.setDescricao(desc);
                pm.setLimite(lim);
                
                return pm;
            }
            else
                return null;
        }
        catch ( SQLException e ){
            Log.log(e);
            return null;
        }        
    }
    
    public static boolean exists(String desc)
    {
        return !findPayMethod(desc).isEmpty();
    }
    
    public static boolean exists(int code)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = " select codigo as 'cod' "
                          + " from formaspagamento "
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
    
}
