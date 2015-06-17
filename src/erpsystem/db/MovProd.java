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

/**
 *
 * @author Diego
 */
public class MovProd {
    private int codMov;
    
    private int codProd;
    
    private String desc;
    
    private double preco;
    
    private int qt;
    
    private double total;
    
    

    public int getCodMov() {
        return codMov;
    }

    public int getCodProd() {
        return codProd;
    }

    public double getPreco() {
        return preco;
    }

    public int getQt() {
        return qt;
    }

    public double getTotal() {
        return total;
    }

    
    public void setCodMov(int codMov) {
        this.codMov = codMov;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
}
