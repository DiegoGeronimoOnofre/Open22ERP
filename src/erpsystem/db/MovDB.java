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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class MovDB {
    private static int genCode()
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = "select max(codigo) as 'cod' from mov";
            ResultSet rs = st.executeQuery(update);
            rs.next();
            return rs.getInt("cod") + 1;
        }
        catch ( SQLException e ){
            Log.log(e);
            return -1;
        }
    }
    
    public static int add(Mov mov)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            
            final int cod          = genCode();
            final int codCli       = mov.getCod_cli();
            final int codPayMethod = mov.getPayMethodCode();
            final int movTypeCode  = mov.getMovType();
            long time              = System.currentTimeMillis();
            
            String update = " insert "
                          + " into mov "
                          + " values(" + cod + ","
                                       + codCli + ","
                                       + codPayMethod + ","
                                       + movTypeCode + ","
                                       + time
                          + ")";
            
            st.executeUpdate(update);
            con.commit();
            return cod;

        }
        catch ( SQLException e ){
            Log.log(e);
            return -1;
        }
    }
    
    public static List<PessoaMov> findClientMov(String pessoa)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = " select mov.codigo   as 'codmov',"
                          + "        mov.mov_time as 'mov_time',"
                          + "        pessoas.nome as 'nome',"
                          + "        mov.mov_type as 'mov_type',"
                          + "        mov.cod_pay_method as 'paym'"
                          + " from mov, pessoas "
                          + " where mov.cod_cli = pessoas.codigo "
                          + "   and upper(trim(pessoas.nome)) like '%" + pessoa.trim().toUpperCase() + "%'"
                          + " order by mov.mov_time";
            
            ResultSet rs = st.executeQuery(update);
            List<PessoaMov> cmList = new ArrayList<>();
            
            while (rs.next()){
                final int movCod = rs.getInt("codmov");
                long movTime = rs.getLong("mov_time");
                String cliName = rs.getString("nome");
                
                int type = rs.getInt("mov_type");
                int pm = rs.getInt("paym");
                
                PessoaMov cm = new PessoaMov();
                cm.setMovCod(movCod);
                cm.setData(new Date(movTime));
                cm.setClientName(cliName);
                cm.setType(type);
                cm.setPayMethod(pm);
                cmList.add(cm);
            }
            
            return cmList;
        }
        catch ( SQLException e ){
            Log.log(e);
            return null;
        }
    }
}
