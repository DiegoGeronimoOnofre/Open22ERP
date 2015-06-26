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
package erpsystem.forms;

import erpsystem.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JComboBox;

/**
 *
 * @author developer
 */
public class IntervalView extends javax.swing.JDialog {

    /**
     * Creates new form IntervalView
     */
    public IntervalView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configMeses();
        configComps();
        final int w = this.getWidth();
        final int h = this.getHeight();
        java.awt.Point p = Util.getCenterPoint(w, h);
        this.setLocation(p);      
        this.setResizable(false);
    }
    
    private List<String> mesList = new ArrayList<String>();
    
    private void configMeses()
    {
        mesList.add("Janeiro");
        mesList.add("Fevereiro");
        mesList.add("Março");
        mesList.add("Abril");
        mesList.add("Maio");
        mesList.add("Junho");
        mesList.add("Julho");
        mesList.add("Agosto");
        mesList.add("Setembro");
        mesList.add("Outubro");
        mesList.add("Novembro");
        mesList.add("Dezembro");
    }
    
    private void configDefaultCombobox(JComboBox cbxBox)
    {
        cbxBox.removeAllItems();
        cbxBox.setEditable(false);        
    }
    
    private void fillDays(JComboBox cbxBox)
    {
        int length = 31;
        for ( int i = 1; i <= length; i++ ){
            String s = String.valueOf(i);
            cbxBox.addItem(s);
        }
    }
    
    private void fillMonths(JComboBox cbxBox)
    {
        int length = mesList.size();
        for ( int i = 0; i < length; i++ ){
            String s = mesList.get(i);
            cbxBox.addItem(s);
        }
    }
    
    private void fillYears(JComboBox cbxBox)
    {
        int len = 2000;
        for ( int i = 2015; i >= len; i-- ){
            String s = String.valueOf(i);
            cbxBox.addItem(s);
        }
    }
    
    private void configComps()
    {
        
        //Configurando a data inicial
        configDefaultCombobox(cbxDiaInicial);
        fillDays(cbxDiaInicial);
        
        configDefaultCombobox(cbxMesInicial);
        fillMonths(cbxMesInicial);
        
        configDefaultCombobox(cbxAnoInicial);
        fillYears(cbxAnoInicial);

        //Configurando a data final
        configDefaultCombobox(cbxDiaFinal);
        fillDays(cbxDiaFinal);
        
        configDefaultCombobox(cbxMesFinal);
        fillMonths(cbxMesFinal);
        
        configDefaultCombobox(cbxAnoFinal);
        fillYears(cbxAnoFinal);
        
        //Adicionando a data atual na data final
        long now = System.currentTimeMillis();
        //TimeZone;
        //Locale.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);
        final int d = calendar.get(Calendar.DAY_OF_MONTH);
        final int m = calendar.get(Calendar.MONTH);
        final int y = calendar.get(Calendar.YEAR);
        cbxDiaFinal.setSelectedIndex(d-1);
        cbxMesFinal.setSelectedIndex(m);
        cbxAnoFinal.setSelectedItem(String.valueOf(y));
    }
    
    public class Params
    {
        private final Calendar initialCalendar;
        
        private final Calendar finalCalendar;

        public Params( Calendar c1, Calendar c2 )
        {
            this.initialCalendar = c1;
            this.finalCalendar = c2;
        }
        
        public Calendar getInitialCalendar() {
            return initialCalendar;
        }

        public Calendar getFinalCalendar() {
            return finalCalendar;
        }
    }
    
    private Params params;
    
    public Params getParams()
    {
        return params;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDia1 = new javax.swing.JLabel();
        lblMes1 = new javax.swing.JLabel();
        lblAno1 = new javax.swing.JLabel();
        cbxDiaInicial = new javax.swing.JComboBox();
        cbxMesInicial = new javax.swing.JComboBox();
        cbxAnoInicial = new javax.swing.JComboBox();
        lblDia2 = new javax.swing.JLabel();
        lblMes2 = new javax.swing.JLabel();
        lblAno2 = new javax.swing.JLabel();
        cbxDiaFinal = new javax.swing.JComboBox();
        cbxMesFinal = new javax.swing.JComboBox();
        cbxAnoFinal = new javax.swing.JComboBox();
        btnCreateChart = new javax.swing.JButton();
        lblInitialDate = new javax.swing.JLabel();
        lblFinalDate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblDia1.setText("Dia:");

        lblMes1.setText("Mês:");

        lblAno1.setText("Ano:");

        cbxDiaInicial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxMesInicial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxAnoInicial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblDia2.setText("Dia:");

        lblMes2.setText("Mês:");

        lblAno2.setText("Ano:");

        cbxDiaFinal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxMesFinal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxAnoFinal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCreateChart.setText("Criar gráfico");
        btnCreateChart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCreateChartMouseClicked(evt);
            }
        });

        lblInitialDate.setText("Data Inicial:");

        lblFinalDate.setText("Data Final:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCreateChart)
                            .addComponent(lblFinalDate)
                            .addComponent(lblInitialDate))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxDiaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDia1))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMes1)
                                    .addComponent(cbxMesInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDia2)
                                    .addComponent(cbxDiaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMes2)
                                    .addComponent(cbxMesFinal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxAnoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAno1)
                            .addComponent(lblAno2)
                            .addComponent(cbxAnoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblInitialDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDia1)
                    .addComponent(lblMes1)
                    .addComponent(lblAno1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxDiaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxMesInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxAnoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(lblFinalDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDia2)
                    .addComponent(lblMes2)
                    .addComponent(lblAno2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxDiaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxAnoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxMesFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCreateChart)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateChartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateChartMouseClicked
        // TODO add your handling code here:
        Calendar initialCalendar = Calendar.getInstance();
        Calendar finalCalendar = Calendar.getInstance();
        
        //definindo o initialCalendar
        final int y1 = Integer.parseInt((String)cbxAnoInicial.getSelectedItem());
        final int m1 = cbxMesInicial.getSelectedIndex();
        final int d1 = Integer.parseInt((String)cbxDiaInicial.getSelectedItem());
        initialCalendar.set(y1, m1, d1,0,0,0);
        
        //definindo o finalCalendar
        final int y2 = Integer.parseInt((String)cbxAnoFinal.getSelectedItem());
        final int m2 = cbxMesFinal.getSelectedIndex();
        final int d2 = Integer.parseInt((String)cbxDiaFinal.getSelectedItem());
        finalCalendar.set(y2, m2, d2,23,59,59);
        
        params = new Params(initialCalendar,finalCalendar);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnCreateChartMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IntervalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IntervalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IntervalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IntervalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IntervalView dialog = new IntervalView(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateChart;
    private javax.swing.JComboBox cbxAnoFinal;
    private javax.swing.JComboBox cbxAnoInicial;
    private javax.swing.JComboBox cbxDiaFinal;
    private javax.swing.JComboBox cbxDiaInicial;
    private javax.swing.JComboBox cbxMesFinal;
    private javax.swing.JComboBox cbxMesInicial;
    private javax.swing.JLabel lblAno1;
    private javax.swing.JLabel lblAno2;
    private javax.swing.JLabel lblDia1;
    private javax.swing.JLabel lblDia2;
    private javax.swing.JLabel lblFinalDate;
    private javax.swing.JLabel lblInitialDate;
    private javax.swing.JLabel lblMes1;
    private javax.swing.JLabel lblMes2;
    // End of variables declaration//GEN-END:variables
}
