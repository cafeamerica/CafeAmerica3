/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.relatorios.guias;

import classes.ManipulaDatas;
import dao.cadastros.DAOEmpresas;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import net.miginfocom.swing.MigLayout;
import relatorios.guias.Relatorios;

/**
 *
 * @author Rafael
 */
public class PRelModelPeriodo extends javax.swing.JPanel{
    
    public PRelModelPeriodo(){
       // initComponents();
    }
    
    
    protected void initComponents(){
        //inicializando componentes do jpanel principal
        panelConsulta = new javax.swing.JPanel();
        panelRel = new javax.swing.JPanel();
        //fim

        //configurações do jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, nome_telaa, javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes do jpanel consulta
        lblDataFim = new javax.swing.JLabel("Data fim:");
        lblDataInicio = new javax.swing.JLabel("Data início:");
        txtDataFim = new javax.swing.JFormattedTextField();
        txtDataInicio = new javax.swing.JFormattedTextField();
        btGerarRelatório = new javax.swing.JButton("Gerar relatório");
        comboEmp = new javax.swing.JComboBox<>();
        lblCliente = new javax.swing.JLabel("Cliente:");
        //fim

        //configurações dos componentes do jpanel consulta
        txtDataFim.setPreferredSize(ManipulaDatas.retornaDimensaoDt());
        txtDataInicio.setPreferredSize(ManipulaDatas.retornaDimensaoDt());
        txtDataFim.setFormatterFactory(new DefaultFormatterFactory(ManipulaDatas.retornaMascaraDt()));
        txtDataInicio.setFormatterFactory(new DefaultFormatterFactory(ManipulaDatas.retornaMascaraDt()));
        
        String s_data_atual = ManipulaDatas.retornaDataAtual();
        txtDataFim.setText(s_data_atual);
        //fim

        //configurações do jpanel consulta
        panelConsulta.setLayout(new MigLayout());
        panelConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar guia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //adicionando componentes ao jpanel consulta
        panelConsulta.add(lblDataInicio, "split 2");
        panelConsulta.add(txtDataInicio, "wrap");
        panelConsulta.add(lblDataFim, "split 2, gapx 13");
        panelConsulta.add(txtDataFim, "wrap");
        panelConsulta.add(lblCliente, "split 2, gapx 22");
        panelConsulta.add(comboEmp, "wrap");
        panelConsulta.add(btGerarRelatório, "gapx 80");

        //fim
        comboEmp.setPreferredSize(new java.awt.Dimension(210, 24));
        comboEmp.removeAllItems();
        try {
            ArrayList empresas = daoe.consultarEmpresas();
            Iterator i = empresas.iterator();
            while (i.hasNext()) {
                String t = String.valueOf(i.next());
                comboEmp.addItem(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PRelatoriosGuiaEstoquePeriodo.class.getName()).log(Level.SEVERE, null, ex);
        }

        //configuração panel rel
        panelRel.setLayout(new MigLayout());
        //fim

        //fim
        this.add(panelConsulta, "top");
        this.add(panelRel);
        
        //evento bts
        btGerarRelatório.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btGerarRelatorioActionPerformed(e);
            }
        });
        //fim
    }
    
    protected void btGerarRelatorioActionPerformed(java.awt.event.ActionEvent evt){
        panelRel.removeAll();
        imprimeNaTela();
    }
    
    protected void imprimeNaTela() {
        panelRel.revalidate();
       java.sql.Date dt_ini = ManipulaDatas.converteStrSql(txtDataInicio.getText().toString());
        java.sql.Date dt_fim = ManipulaDatas.converteStrSql(txtDataFim.getText().toString());
        String s_dt_ini = txtDataInicio.getText().toString();
        String s_dt_fim = txtDataFim.getText().toString();

        if (ManipulaDatas.validaData(s_dt_fim) == false || ManipulaDatas.validaData(s_dt_ini) == false) {
            JOptionPane.showMessageDialog(null, "Data(s) inválida(s)");
        } else {
            // r.geraRelatorio("SELECT g.data_abert_guia, g.num_guia, e.nome_emp as empresa, g.qtd_sacas_guia, g.vlr_total_guia, sum(g.qtd_sacas_guia*60) as peso_previa, count(gv.documento_viagem_gv) as qtd_viagens FROM guias g, guia_viagens gv, viagens v, empresas e, empresa_guia eg WHERE g.num_guia = gv.num_guia_gv AND v.documento_viagem = gv.documento_viagem_gv AND g.id_guia = eg.id_guia AND e.id_emp = eg.id_empresa AND g.data_abert_guia BETWEEN '" + s_dt_ini + "' AND '" + s_dt_fim + "'  GROUP BY g.data_abert_guia, g.num_guia,e.nome_emp,g.qtd_sacas_guia,g.vlr_total_guia;");
            HashMap param = new HashMap();
            param.put("dt_ini", dt_ini);
            param.put("dt_fim", dt_fim);
            
            Relatorios rel = new Relatorios();

            panelRel.add(rel.relatoriosEmTela(param, id_rell,0));
            
            id_rell = -1;
            nome_telaa = "";
        }
        
    }
    
    public void setParametros(int id_rel, String nome_tela){
        id_rell = id_rel;
        nome_telaa = nome_tela;
    }
    
    protected javax.swing.JLabel lblDataInicio;
    protected javax.swing.JComboBox comboEmp;
    protected javax.swing.JLabel lblCliente;
    protected javax.swing.JLabel lblDataFim;
    protected javax.swing.JFormattedTextField txtDataInicio;
    protected javax.swing.JFormattedTextField txtDataFim;
    protected javax.swing.JPanel panelConsulta;
    protected javax.swing.JPanel panelRel;
    protected javax.swing.JButton btGerarRelatório;
    protected DAOEmpresas daoe = new DAOEmpresas();
    protected int id_rell = -1;
    private String nome_telaa = "";
}
