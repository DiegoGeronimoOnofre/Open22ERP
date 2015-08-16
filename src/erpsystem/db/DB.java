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
import java.sql.*;

/**
 *
 * @author Diego
 */
public class DB {
    static {
        try{
            //Carregando o drive. Este drive é o intermediário 
            //entre a aplicação e o servidor de banco de dados.
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch ( Exception e ){
            Log.log(e);
        }			
    }
    
    private static Connection con = null;
    
    public static Connection getConnection()
    {
        try{
            
            if ( con == null ){
                //Abaixo há o código responsável por obter uma conexão com o banco de dados
                con = DriverManager.getConnection( "jdbc:mysql://192.168.1.2/db", 
                                                    "root", 
                                                    "admin" );
                //Para não permitir que o gerenciador da conexão faça auto commits,
                //Isso, porque se um commit é feito na hora errada, pode ocorrer problemas,
                //Considero melhor o próprio programador fazer o commit manualmente.
                con.setAutoCommit(false);
                return con;
            }
            else
                return con;
        }
        catch ( Exception e ){
            //Gerando um log se algum problema ocorrer.
            Log.log(e);
            return null;
        }
    }
}
