/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.relatorios.guias;

import classes.BarraDeProgresso;
import dao.cadastros.DAOEmpresas;
import dao.entradas.DAOEmpresaGuia;
import dao.entradas.DAOGuias;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import relatorios.guias.Relatorios;

/**
 *
 * @author Rafael
 */
public class PRelModelGuia extends javax.swing.JPanel {

    public PRelModelGuia() {

    }

    protected void initComponents() {
        //inicializando componentes do jpanel principal
        panelConsulta = new javax.swing.JPanel();
        panelRel = new javax.swing.JPanel();
        panelDir = new javax.swing.JPanel();
        //fim

        //configurações do jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, nome_telaa, javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //INICIALIZANDO componentes jpanel consultar
        lblNumGuia = new javax.swing.JLabel("Informe o número da guia:");
        txtNumGuia = new javax.swing.JTextField(10);
        btGerarRelatório = new javax.swing.JButton("Gerar relatório");
        comboEmp = new javax.swing.JComboBox<>();
        lblCliente = new javax.swing.JLabel("Cliente:");
        btFechar = new javax.swing.JButton("Fechar");
        thr = new java.lang.Thread();
        jpb = new javax.swing.JProgressBar();
        proc = new BarraDeProgresso();
        //fim

        //configurações do jpanel consulta
        panelConsulta.setLayout(new MigLayout());
        panelConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar guia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //adicionando componentes ao jpanel consulta
        panelConsulta.add(lblNumGuia, "split 2");
        panelConsulta.add(txtNumGuia, "wrap");
        panelConsulta.add(lblCliente, "split 2, gapx 22");
        panelConsulta.add(comboEmp, "wrap");
        panelConsulta.add(btGerarRelatório, "gapx 80, wrap");
        panelConsulta.add(jpb, "align center");
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

        //adicionando componentes jpanel direita
        panelDir.add(btFechar, "split 2");
        panelDir.add(panelRel);
        //fim

        //fim
        this.add(panelConsulta, "top, split 2");
        this.add(panelDir, "top");

        btFechar.setVisible(false);

        //evento bts
        btGerarRelatório.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btGerarRelatorioActionPerformed(e);
            }
        });

        btFechar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btFecharActionPerformed(e);
            }
        });
        //fim

        proc.setProgressBar(jpb);
        jpb.setVisible(false);

    }

    private void imprimeNaTela() {

        panelRel.revalidate();
        DAOGuias daog = new DAOGuias();
        DAOEmpresaGuia daoeg = new DAOEmpresaGuia();
        String s_num_guia = txtNumGuia.getText().toString().trim();
        DAOEmpresas daoe = new DAOEmpresas();
        try {
            int id_emp = daoe.retornaIDPorNome(comboEmp.getSelectedItem().toString());
            boolean cond_num_guia = daoeg.consultaGuiaEmp(s_num_guia, id_emp);

            if (cond_num_guia == false) {
                JOptionPane.showMessageDialog(null, "Número da guia não existe!");
            } else {

                //inicia barra de progresso
                jpb.setVisible(true);
                startProcessing();
                //fim

                int id_guia = daog.retornaIdGuiaEmp(s_num_guia, id_emp);
                // r.geraRelatorio("SELECT g.data_abert_guia, g.num_guia, e.nome_emp as empresa, g.qtd_sacas_guia, g.vlr_total_guia, sum(g.qtd_sacas_guia*60) as peso_previa, count(gv.documento_viagem_gv) as qtd_viagens FROM guias g, guia_viagens gv, viagens v, empresas e, empresa_guia eg WHERE g.num_guia = gv.num_guia_gv AND v.documento_viagem = gv.documento_viagem_gv AND g.id_guia = eg.id_guia AND e.id_emp = eg.id_empresa AND g.data_abert_guia BETWEEN '" + s_dt_ini + "' AND '" + s_dt_fim + "'  GROUP BY g.data_abert_guia, g.num_guia,e.nome_emp,g.qtd_sacas_guia,g.vlr_total_guia;");
                HashMap param = new HashMap();
                param.put("id_guia", id_guia);
                Relatorios rel = new Relatorios();

                btFechar.setVisible(true);
                panelRel.add(rel.relatoriosEmTela(param, id_rell, 0));
                btGerarRelatório.setEnabled(false);

                //id_rell = -1;
                nome_telaa = "";

                proc.setValor(true);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PRelatoriosGuiaEntradaGuia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void btGerarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
        panelRel.removeAll();
        imprimeNaTela();

    }

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente fechar o relatório?", "Fechar relatório", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            panelRel.removeAll();
            btGerarRelatório.setEnabled(true);
            btFechar.setVisible(false);
            jpb.removeAll();
            jpb.setVisible(false);
            BarraDeProgresso.zeraJpb();
        }
    }

    public void setParametros(int id_rel, String nome_tela) {
        id_rell = id_rel;
        nome_telaa = nome_tela;
    }

    public void startProcessing() {
        proc.setInterrupted(false);
        thr = new Thread(proc);
        thr.start();
    }

    public void stopProcessing() {
        proc.setInterrupted(true);
        try {
            if (thr != null) {
                thr.join();
            }
        } catch (InterruptedException ex) {
        }
        jpb.setValue(0);
    }

    protected javax.swing.JLabel lblNumGuia;
    protected javax.swing.JTextField txtNumGuia;
    protected javax.swing.JButton btGerarRelatório;
    protected DAOEmpresas daoe = new DAOEmpresas();
    protected javax.swing.JComboBox comboEmp;
    protected javax.swing.JPanel panelConsulta;
    protected javax.swing.JPanel panelRel;
    protected javax.swing.JLabel lblCliente;
    protected javax.swing.JButton btFechar;
    protected int id_rell = -1;
    private String nome_telaa = "";
    protected javax.swing.JPanel panelDir;
    protected javax.swing.JProgressBar jpb;
    protected BarraDeProgresso proc;
    protected java.lang.Thread thr;
}
