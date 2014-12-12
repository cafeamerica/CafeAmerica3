/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.relatorios.guias;

import classes.ComboBoxModel;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PRelFinanCtasReceber extends javax.swing.JPanel{
    
    public PRelFinanCtasReceber(){
        initComponents();
    }
    
    
    private void initComponents(){
        //configurações jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RELATÓRIO DE CONTAS A RECEBER", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim
        
        
        //inicializando componentes jpanel principal
        panelConsultar = new javax.swing.JPanel();
        panelConsultar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
     
        //fim
        
        //inicializando componens jpanel consultar
        lblTipoOp = new javax.swing.JLabel("Tipo de operação:");
        comboTipoOp = new javax.swing.JComboBox<>();
        btConsultar = new javax.swing.JButton("Consultar");
        //fim
        
        //configuração componentes jpanel consultar
        ComboBoxModel cmb = new ComboBoxModel();
        cmb.montaCombo();
        comboTipoOp = cmb;
        comboTipoOp.addItem("Guias");
        comboTipoOp.addItem("Frete de entrada");
        comboTipoOp.addItem("Frete de saída");
        comboTipoOp.setSelectedIndex(0);
        //fim
        
        //adicionando componentes jpanel consultar
        panelConsultar.add(lblTipoOp,"split 3");
        panelConsultar.add(comboTipoOp);
        panelConsultar.add(btConsultar);
        //fim
        
        //adicionando compontentes jpanel principal
        this.add(panelConsultar);
        //fim
        
        //evento bts
        btConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btConsultarActionPerformed(e);
            }
        });
        //fim
    }
    
    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt){
    
    }
    
    private javax.swing.JPanel panelConsultar;
    private javax.swing.JLabel lblTipoOp;
    private javax.swing.JComboBox comboTipoOp;
    private javax.swing.JButton btConsultar;
}
