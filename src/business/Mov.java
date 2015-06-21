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

package business;

import erpsystem.db.*;

/**
 *
 * @author Diego
 */

public class Mov {
    
    /* Está sendo usado o objeto lock para bloquear a possibilidade de
    duas movimentações serem realizadas ao mesmo tempo.
    */
    
    private static final Object lock = new Object();
    
    public static String persistMov(int codCli, 
                                    int payMethodCode,
                                    int movTypeCode,
                                    java.util.List<MovProd> mpList)
    {
        synchronized ( lock ){
            erpsystem.db.Mov mov = new erpsystem.db.Mov();
            mov.setCod_cli(codCli);
            mov.setPayMethodCode(payMethodCode);
            mov.setMovType(movTypeCode);
            final int movCod = MovDB.add(mov);

            if ( movCod != -1 ){
                for ( int i = 0; i < mpList.size(); i++ ){ 
                    MovProd mp = mpList.get(i);
                    mp.setCodMov(movCod);
                    boolean result = MovProdDB.add(mp, movTypeCode);

                    if (! result ){
                        return error;
                    }
                }

                return null;
            }
            else
                return error;
        }
    }
    
    private static String error = "Ocorreu um problema ao gravar as \n"
                   + "informações da movimentação no banco de dados.\n"
                    + "Isso pode ter ocorrido por diversos fatores como \n"
                    + "problema de hardware ou o servidor de banco de dados \n"
                    + " não estava em execução ou seu sistema está corrompido. \n"
                    + " é recomendável realizar a movimentação novamente, assim se \n"
                    + "o problema persistir entre em contato com o Desenvolvedor.";
    
}
