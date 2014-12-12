/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.guias.saida.nova.viagem;

import classes.ComboBoxModel;
import classes.ManipulaJTables;
import classes.NumerosFloatCVirgula;
import classes.NumerosInt;
import dao.cadastros.DAOEmpresas;
import dao.entradas.DAOEmpresaGuia;
import dao.entradas.DAOGuias;
import dao.entradas.DAOProducaoGuia;
import dao.entradas.DAOServicos;
import dao.saidas.DAOAddServGuia;
import dao.saidas.DAOGuiaViagemSai;
import gui.guias.entrada.nova.viagem.PGuiasEntradaNovaViagem;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.entradas.ProducaoGuia;
import model.saidas.AddServGuia;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PGuiasSaidaAddServico extends javax.swing.JPanel {

    public PGuiasSaidaAddServico() {
        initComponents();
    }

    private void initComponents() {
        //configuração jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RETRABLAHO DE CAFÉ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpane principal
        panelConsultar = new javax.swing.JPanel();
        btAddServ = new javax.swing.JButton("Add serviço");
        panelDados = new javax.swing.JPanel();
        //fim

        //configuração componentes jpanel principal
        panelConsultar.setLayout(new MigLayout());
        panelConsultar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar guia", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelDados.setLayout(new MigLayout());
        //fim

        //inicializando componentes jpanel consultar
        lblNumGuia = new javax.swing.JLabel("Informe o número da guia:");
        txtNumGuia = new javax.swing.JTextField(10);
        btConsultar = new javax.swing.JButton("Consultar");
        comboEmpGuia = new javax.swing.JComboBox<>();
        lblEmpresa = new javax.swing.JLabel("Empresa:");
        //fim

        //configuração componentes jpanel consultar
        ComboBoxModel cbm_emp = new ComboBoxModel();
        //combo emp   
        try {
            ArrayList empresas = daoe.consultarEmpresas();
            cbm_emp.montaCombo();
            cbm_emp.listaDadosCombo(empresas);
            comboEmpGuia = cbm_emp;
            //fim
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasEntradaNovaViagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        //fim
        //Tabela config
        String[] columnNames = {"Tipo café", "Peso total", "Peso disponível", "Qtd sacas"};
        Object[][] data = {};
        modelT = new javax.swing.table.DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabela = new javax.swing.JTable(modelT);
        tabela.setPreferredScrollableViewportSize(new java.awt.Dimension(460, 150));
        tabela.getColumnModel().getColumn(0).setMaxWidth(120);
        tabela.getColumnModel().getColumn(0).setMinWidth(120);
        tabela.getColumnModel().getColumn(1).setMaxWidth(120);
        tabela.getColumnModel().getColumn(1).setMinWidth(120);
        tabela.getColumnModel().getColumn(2).setMaxWidth(120);
        tabela.getColumnModel().getColumn(2).setMinWidth(120);
        tabela.getColumnModel().getColumn(3).setMaxWidth(100);
        tabela.getColumnModel().getColumn(3).setMinWidth(100);

        scrollT = new javax.swing.JScrollPane(tabela);
        scrollT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produção", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));

        //alinhando os valores das celulas da Tabela à direita
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);

        tabela.getColumnModel().getColumn(0).setCellRenderer(r);
        tabela.getColumnModel().getColumn(1).setCellRenderer(r);
        tabela.getColumnModel().getColumn(2).setCellRenderer(r);
        tabela.getColumnModel().getColumn(3).setCellRenderer(r);
        //fim

        //adicionando componentes jpanel consultar
        panelConsultar.add(lblNumGuia, "split 2");
        panelConsultar.add(txtNumGuia, "wrap");
        panelConsultar.add(lblEmpresa, "split 3");
        panelConsultar.add(comboEmpGuia);
        panelConsultar.add(btConsultar);
        //fim

        //inicializando componentes panel dados
        lblDescServ = new javax.swing.JLabel("Descrição:");
        lblQtdSaca = new javax.swing.JLabel("Qtd de sacas:");
        lblVlrSaca = new javax.swing.JLabel("Valor p/ saca:");
        lblTipoServ = new javax.swing.JLabel("Tipo de café:");
        txtDescServ = new javax.swing.JTextField(20);
        txtQtdSaca = new javax.swing.JTextField(8);
        txtTipoServ = new javax.swing.JTextField(10);
        txtVlrSaca = new javax.swing.JTextField(6);
        btSalvar = new javax.swing.JButton("Salvar");
        btCancelar = new javax.swing.JButton("Cancelar");
        //fim

        //adicionando componentes jpanel dados
        panelDados.add(lblDescServ, "split 2, gapx 17");
        panelDados.add(txtDescServ, "wrap");
        panelDados.add(lblTipoServ, "split 2, gapx 7");
        panelDados.add(txtTipoServ, "wrap");
        panelDados.add(lblQtdSaca, "split 2");
        panelDados.add(txtQtdSaca, "wrap");
        panelDados.add(lblVlrSaca, "split 2");
        panelDados.add(txtVlrSaca, "wrap");
        panelDados.add(btCancelar, "split 2");
        panelDados.add(btSalvar, "gapx 30");
        //fim

        //configuração componetes jpanel dados
        txtQtdSaca.setDocument(new NumerosFloatCVirgula());
        txtVlrSaca.setDocument(new NumerosFloatCVirgula());
        //fim

        //adicionando componentes jpanel principal
        this.add(panelConsultar, "top, split 3");
        this.add(scrollT, "top");
        this.add(btAddServ, "wrap");
        this.add(panelDados);
        //fim

        scrollT.setVisible(false);
        btAddServ.setVisible(false);
        panelDados.setVisible(false);

        //evento bts
        btConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btConsultarActionPerformed(e);
            }
        });

        btAddServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btAddServActionPerformed(e);
            }
        });

        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelarActionPerformed(e);
            }
        });

        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btSalvarActionPerformed(e);
            }
        });
        //fim
    }

    private void listaDados(int id_guia) {
        DAOProducaoGuia daopg = new DAOProducaoGuia();
        List<ProducaoGuia> dados;

        try {
            dados = daopg.retornaDados(id_guia);
            if (dados.size() == 0) {
                JOptionPane.showMessageDialog(null, "Não há dados!");
            } else {
                String[] linhas = {null, null, null, null};
                for (int i = 0; i < dados.size(); i++) {
                    DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
                    dtm.addRow(linhas);
                    DecimalFormat df = new DecimalFormat("0.#");
                    tabela.setModel(dtm);
                    tabela.setValueAt(dados.get(i).getDesc_serv(), i, 0);
                    tabela.setValueAt(dados.get(i).getPeso_pdc(), i, 1);
                    tabela.setValueAt(dados.get(i).getSaldo_pdc(), i, 2);
                    tabela.setValueAt(df.format(dados.get(i).getSaldo_pdc() / 60), i, 3);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasSaidaNovaViagem.class.getName()).log(Level.SEVERE, null, ex);
        }

        ManipulaJTables.removeLinhasBrancas(tabela);
    }

    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {

        String s_num_guia = txtNumGuia.getText().toString();
        DAOEmpresaGuia daoeg = new DAOEmpresaGuia();
        DAOGuiaViagemSai daogvs = new DAOGuiaViagemSai();
        DAOGuias daog = new DAOGuias();
        try {
            int id_emp = daoe.retornaIDPorNome(comboEmpGuia.getSelectedItem().toString());
            if (daoeg.consultaGuiaEmp(s_num_guia, id_emp) == false) {
                JOptionPane.showMessageDialog(null, "Essa guia não existe para a empresa " + comboEmpGuia.getSelectedItem().toString());
            } else {
                int id_guia = daog.retornaIdGuiaEmp(s_num_guia, id_emp);
                id_g = id_guia;
                id_e = id_emp;
                scrollT.setVisible(true);
                btConsultar.setEnabled(false);
                listaDados(id_g);
                btAddServ.setVisible(true);
                txtNumGuia.setEditable(false);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PGuiasEntradaNovaViagem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void btAddServActionPerformed(java.awt.event.ActionEvent evt) {
        int li = tabela.getSelectedRow();
        double dd_peso = Double.parseDouble(tabela.getValueAt(li, 2).toString());
        String s_desc_serv = tabela.getValueAt(li, 0).toString();
        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            if (dd_peso <= 0) {
                JOptionPane.showMessageDialog(null, "Peso menor ou igual a 0!");
            } else {
                panelDados.setVisible(true);
                panelDados.setVisible(true);
                btAddServ.setEnabled(false);
                txtTipoServ.setText(s_desc_serv);
                txtTipoServ.setEditable(false);
                linha = li;
            }
        }
    }

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar este serviço?", "Cancelar serviço", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            limpaCampos();
        }
    }

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_desc_serv = txtDescServ.getText().toString().toUpperCase();
        String s_tipo_serv = txtTipoServ.getText().toString();
        String s_qtd_saca = txtQtdSaca.getText().toString();
        String s_vlr_saca = txtVlrSaca.getText().toString();
        String s_qtd_total_saca = tabela.getValueAt(linha, 3).toString();
        if (s_desc_serv.isEmpty() || s_desc_serv.equals(null) || s_qtd_saca.isEmpty()
                || s_qtd_saca.equals(null) || s_vlr_saca.isEmpty() || s_vlr_saca.equals(null)) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            s_vlr_saca = s_vlr_saca.replace(",", ".");
            s_qtd_saca = s_qtd_saca.replace(",", ".");
            s_qtd_total_saca = s_qtd_total_saca.replace(",", ".");

            double d_qtd_total_saca = Double.parseDouble(s_qtd_total_saca);
            double d_vlr_saca = Double.parseDouble(s_vlr_saca);
            double d_qtd_saca = Double.parseDouble(s_qtd_saca);

            if (d_qtd_saca <= 0) {
                JOptionPane.showMessageDialog(null, "Valor menor ou igual a 0!");
            } else {
                if (d_qtd_saca > d_qtd_total_saca) {
                    JOptionPane.showMessageDialog(null, "Qtd de saca informado maior que a qtd de saca da produção!");
                } else {
                    if (d_vlr_saca <= 0) {
                        JOptionPane.showMessageDialog(null, "Valor inválido! Valor menor ou igual a 0!");
                    } else {
                        DAOAddServGuia daoasg = new DAOAddServGuia();
                        AddServGuia asg = new AddServGuia();
                        DAOServicos daos = new DAOServicos();
                        DAOGuias daog = new DAOGuias();
                        try {
                            int id_serv = daos.retornaIDPorNome(s_tipo_serv);
                            double vlr_atual = daog.retornaVlrTotal(id_g);
                            

                            asg.setDesc_serv(s_desc_serv);
                            asg.setId_guia(id_g);
                            asg.setPeso_add(d_qtd_saca * 60);
                            asg.setQtd_sacas_add(d_qtd_saca);
                            asg.setVlr_total_add(d_qtd_saca * d_vlr_saca);
                            asg.setVlr_unit_add(d_vlr_saca);
                            asg.setId_serv(id_serv);
                            
                            //inserindo
                            daoasg.inserir(asg);
                            daog.alteraVlrTotal(vlr_atual+(d_qtd_saca * d_vlr_saca), id_g); //alterando valor da guia
                            JOptionPane.showMessageDialog(null,"Serviço inserido com sucesso!");
                            limpaCampos();
                        } catch (SQLException ex) {
                            Logger.getLogger(PGuiasSaidaAddServico.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }

    }

    private void limpaCampos() {
        txtDescServ.setText("");
        txtQtdSaca.setText("");
        txtTipoServ.setText("");
        txtVlrSaca.setText("");
        btConsultar.setEnabled(true);
        btAddServ.setEnabled(true);
        txtNumGuia.setEditable(true);
        panelDados.setVisible(false);
        DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
        dtm.setNumRows(0);
        scrollT.setVisible(false);
        btAddServ.setVisible(false);
        linha = 0;
    }

    private javax.swing.JLabel lblNumGuia;
    private javax.swing.JTextField txtNumGuia;
    private javax.swing.JComboBox comboEmpGuia;
    private javax.swing.JLabel lblEmpresa;
    private javax.swing.JButton btConsultar;
    private javax.swing.JPanel panelConsultar;
    private javax.swing.JTable tabela;
    private javax.swing.table.DefaultTableModel modelT;
    private javax.swing.JScrollPane scrollT;
    private javax.swing.JPanel panelDados;
    private DAOEmpresas daoe = new DAOEmpresas();
    private javax.swing.JButton btAddServ;
    private javax.swing.JLabel lblDescServ;
    private javax.swing.JLabel lblTipoServ;
    private javax.swing.JLabel lblQtdSaca;
    private javax.swing.JLabel lblVlrSaca;
    private javax.swing.JTextField txtTipoServ;
    private javax.swing.JTextField txtDescServ;
    private javax.swing.JTextField txtQtdSaca;
    private javax.swing.JTextField txtVlrSaca;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btCancelar;
    private int linha = 0;
    private int id_g = 0; //id guia
    private int id_v = 0;//id
    private int id_e = 0; //id empresa
}
