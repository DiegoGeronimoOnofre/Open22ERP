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

package erpsystem;

import erpsystem.forms.MainFormView;

/**
*
* @author Diego Geronimo Onofre
* @channel https://www.youtube.com/user/cursostd
* @facebook https://www.facebook.com/diegogeronimoonofre
* @Github https://github.com/DiegoGeronimoOnofre
*/

/**Arquivo principal da aplicação.
*/

public class ERPSystem {
    
    public static final String appName = "Open22ERP";
    
    public static String getWorkDir()
    {
        return System.getProperty("user.dir");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Abaixo está sendo invocado o a janela principal da aplicação
        //Onde será possível ter acesso a todas as funcionadades do sistema,
        //como consulta movimentações, gráficos de faturamento e outros.
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFormView().setVisible(true);               
            }
        });    
    }
    
}
