/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.financeiro;

import classes.ManipulaDatas;
import classes.ManipulaJTables;
import classes.NumerosFloatCVirgula;
import dao.cadastros.DAOEmpresas;
import dao.financeiro.DAOFinanceiro;
import gui.relatorios.guias.PRelatoriosGuiaEstoquePeriodo;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import model.financeiro.Financeiro;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PFinContasReceberPeriodo extends javax.swing.JPanel {

    public PFinContasReceberPeriodo() {

        initComponents();
    }

    private void initComponents() {
        //configurações jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contas a receber", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel principal
        panelConsulta = new javax.swing.JPanel();
        panelBts = new javax.swing.JPanel();
        panelDebitar = new javax.swing.JPanel();
        //fim

        //inicializando componentes do jpanel consulta
        lblDataFim = new javax.swing.JLabel("Data fim:");
        lblDataInicio = new javax.swing.JLabel("Data início:");
        txtDataFim = new javax.swing.JFormattedTextField();
        txtDataInicio = new javax.swing.JFormattedTextField();
        btConsultar = new javax.swing.JButton("Consultar");
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
        panelConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //adicionando componentes ao jpanel consulta
        panelConsulta.add(lblDataInicio, "split 4");
        panelConsulta.add(txtDataInicio);
        panelConsulta.add(lblDataFim);
        panelConsulta.add(txtDataFim, "wrap");
        panelConsulta.add(lblCliente, "split 3, gapx 22");
        panelConsulta.add(comboEmp);
        panelConsulta.add(btConsultar);

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

        //fim
        //Tabela config
        String[] columnNames = {"ID", "Data", "Operação", "Documento", "Referência", "Crédito", "Débito", "Empresa"};
        Object[][] data = {};
        modelT = new javax.swing.table.DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabela = new javax.swing.JTable(modelT);
        tabela.setPreferredScrollableViewportSize(new java.awt.Dimension(900, 207));
        tabela.getColumnModel().getColumn(0).setMaxWidth(60);
        tabela.getColumnModel().getColumn(0).setMinWidth(60);
        tabela.getColumnModel().getColumn(1).setMaxWidth(120);
        tabela.getColumnModel().getColumn(1).setMinWidth(120);
        tabela.getColumnModel().getColumn(2).setMaxWidth(120);
        tabela.getColumnModel().getColumn(2).setMinWidth(120);
        tabela.getColumnModel().getColumn(3).setMaxWidth(120);
        tabela.getColumnModel().getColumn(4).setMinWidth(120);
        tabela.getColumnModel().getColumn(4).setMaxWidth(120);
        tabela.getColumnModel().getColumn(4).setMinWidth(120);
        tabela.getColumnModel().getColumn(5).setMaxWidth(120);
        tabela.getColumnModel().getColumn(5).setMinWidth(120);
        tabela.getColumnModel().getColumn(6).setMaxWidth(120);
        tabela.getColumnModel().getColumn(6).setMinWidth(120);
        tabela.getColumnModel().getColumn(7).setMaxWidth(120);
        tabela.getColumnModel().getColumn(7).setMinWidth(120);

        scrollT = new javax.swing.JScrollPane(tabela);
       // scrollT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contas a receber", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));

        //alinhando os valores das celulas da Tabela à direita
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(r);
        }

        //fim
        //config jpanel bts
        panelBts.setLayout(new MigLayout());

        //fim
        //inicializando componentes jpanel bts
        btDebitar = new javax.swing.JButton("Debitar");
        btDetalhes = new javax.swing.JButton("Detalhes");
        btFechar = new javax.swing.JButton("Fechar");
        //fim

        //adicionando componentes jpanel bts
        panelBts.add(btDebitar, "wrap");
        panelBts.add(btDetalhes, "wrap");
        panelBts.add(btFechar);
        //fim

        //configurações jpanel debitar
        panelDebitar.setLayout(new MigLayout());
        panelDebitar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Débito", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel debitar
        lblDoc = new javax.swing.JLabel("Documento:");
        lblNovoVlrDebito = new javax.swing.JLabel("Novo débito:");
        lblVlrCredito = new javax.swing.JLabel("Crédito:");
        lblVlrDebito = new javax.swing.JLabel("Débito:");
        txtDoc = new javax.swing.JTextField(8);
        txtVlrCredito = new javax.swing.JTextField(8);
        txtVlrDebito = new javax.swing.JTextField(8);
        txtNovoVlrDebito = new javax.swing.JTextField(8);
        btCancelar = new javax.swing.JButton("Cancelar");
        btSalvar = new javax.swing.JButton("Salvar");
        //fim

        //configurações componentes jpanel debitar
        txtNovoVlrDebito.setDocument(new NumerosFloatCVirgula());
        txtVlrCredito.setEditable(false);
        txtVlrDebito.setEditable(false);
        txtDoc.setEditable(false);
        //fim

        //adicionando compontens jpanel debitar
        panelDebitar.add(lblDoc, "split 2");
        panelDebitar.add(txtDoc, "wrap");
        panelDebitar.add(lblVlrCredito, "split 4, gapx 24");
        panelDebitar.add(txtVlrCredito);
        panelDebitar.add(lblVlrDebito);
        panelDebitar.add(txtVlrDebito, "wrap");
        panelDebitar.add(lblNovoVlrDebito, "split 2");
        panelDebitar.add(txtNovoVlrDebito, "wrap");
        panelDebitar.add(btCancelar, "split 2");
        panelDebitar.add(btSalvar, " gapx 20");
        //fim

        this.add(panelConsulta, "wrap");
        this.add(scrollT, "split 2");
        this.add(panelBts, "top, wrap");
        this.add(panelDebitar);

        scrollT.setVisible(false);
        panelBts.setVisible(false);
        panelDebitar.setVisible(false);

        //evento bts
        btConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btConsultarActionPerformed(e);
            }
        });

        btFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btFecharActionPerformed(e);
            }
        });

        btDebitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btDebitarActionPerformed(e);
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

    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {
        java.sql.Date dt_ini = ManipulaDatas.converteStrSql(txtDataInicio.getText().toString());
        java.sql.Date dt_fim = ManipulaDatas.converteStrSql(txtDataFim.getText().toString());
        String s_dt_ini = txtDataInicio.getText().toString();
        String s_dt_fim = txtDataFim.getText().toString();

        if (ManipulaDatas.validaData(s_dt_fim) == false || ManipulaDatas.validaData(s_dt_ini) == false) {
            JOptionPane.showMessageDialog(null, "Data(s) inválida(s)");
        } else {
            try {
                int id_emp = daoe.retornaIDPorNome(comboEmp.getSelectedItem().toString());
                listaContasReceberPeriodo(id_emp, dt_ini, dt_fim);
                scrollT.setVisible(true);
                panelBts.setVisible(true);
                txtDataFim.setEditable(false);
                txtDataInicio.setEditable(false);
            } catch (SQLException ex) {
                Logger.getLogger(PFinContasReceberPeriodo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja fechar esta consulta?", "Fechar", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            limpaCampos();
        }
    }

    private void btDebitarActionPerformed(java.awt.event.ActionEvent evt) {
        int li = tabela.getSelectedRow();

        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            panelDebitar.setVisible(true);
            txtDoc.setText("" + tabela.getValueAt(li, 3).toString());
            txtVlrCredito.setText("" + tabela.getValueAt(li, 5));
            txtVlrDebito.setText("" + tabela.getValueAt(li, 6));
            id_financeiro = Integer.parseInt(tabela.getValueAt(li, 0).toString());
        }
    }

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar o débito?", "Cancelar débito", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            txtNovoVlrDebito.setText("");
            txtVlrCredito.setText("");
            txtVlrDebito.setText("");
            panelDebitar.setVisible(false);
            listaContasReceberPeriodo(id_emp, ManipulaDatas.converteStrSql(txtDataInicio.getText().toString().trim()), ManipulaDatas.converteStrSql(txtDataFim.getText().toString().trim()));
        }
    }

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_novo_debito = txtNovoVlrDebito.getText().toString().trim();
        String s_vlr_credito = txtVlrCredito.getText().toString().trim();
        String s_antigo_debito = txtVlrDebito.getText().toString().trim();
        String s_vlr_debito_ant = s_antigo_debito.substring(s_antigo_debito.indexOf(" "));
        s_vlr_debito_ant = s_vlr_debito_ant.trim();
        s_vlr_credito = s_vlr_credito.substring(s_vlr_credito.indexOf(" "));
        s_vlr_credito = s_vlr_credito.trim();

        if (s_novo_debito.isEmpty() || s_novo_debito.equals(null)) {
            JOptionPane.showMessageDialog(null, "Valor nulo ou inválido!!");
        } else {
            s_vlr_debito_ant = s_vlr_debito_ant.replace(".", "");
            s_vlr_debito_ant = s_vlr_debito_ant.replace(",", ".");

            s_vlr_credito = s_vlr_credito.replace(".", "");
            s_vlr_credito = s_vlr_credito.replace(",", ".");

            s_novo_debito = s_novo_debito.replace(",", ".");

            double d_vlr_debito_ant = Double.parseDouble(s_vlr_debito_ant);
            double d_vlr_debito_novo = Double.parseDouble(s_novo_debito);
            double d_vlr_credito = Double.parseDouble(s_vlr_credito);

            if ((d_vlr_debito_novo + d_vlr_debito_ant) > d_vlr_credito) {
                JOptionPane.showMessageDialog(null, "Valor atual maior que o valor de crédito!");
            } else {
                DAOFinanceiro daof = new DAOFinanceiro();

                int resp = JOptionPane.showConfirmDialog(null, "Você deseja confirmar este débito?", "Confirmar débito", JOptionPane.YES_NO_OPTION);
                if (resp == 0) {
                    try {
                        daof.atualizaDebito(d_vlr_debito_novo + d_vlr_debito_ant, id_financeiro);
                        JOptionPane.showMessageDialog(null, "Debitado com sucesso!");
                        txtDoc.setText("");
                        txtNovoVlrDebito.setText("");
                        txtVlrCredito.setText("");
                        txtVlrDebito.setText("");
                        panelDebitar.setVisible(false);
                        listaContasReceberPeriodo(id_emp, ManipulaDatas.converteStrSql(txtDataInicio.getText().toString().trim()), ManipulaDatas.converteStrSql(txtDataFim.getText().toString().trim()));
                    } catch (SQLException ex) {
                        Logger.getLogger(PFinContasReceberPeriodo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    private void listaContasReceberPeriodo(int emp, java.sql.Date dt_ini, java.sql.Date dt_fim) {
        List<Financeiro> dados;
        DAOFinanceiro daof = new DAOFinanceiro();

        try {
            dados = daof.contasAReceberPeriodo(emp, dt_ini, dt_fim);

            if (dados.size() == 0) {
                JOptionPane.showMessageDialog(null, "Não há contas à receber neste período!");
            } else {
                String[] linhas = {null, null, null, null, null, null, null, null};
                id_emp = emp;
                for (int i = 0; i < dados.size(); i++) {
                    DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
                    dtm.addRow(linhas);
                    tabela.setModel(dtm);

                    tabela.setValueAt(dados.get(i).getId_financeiro(), i, 0);
                    tabela.setValueAt(ManipulaDatas.converteSqlString(dados.get(i).getDt_financeiro()), i, 1);
                    tabela.setValueAt(dados.get(i).getStr_operacao(), i, 2);
                    tabela.setValueAt(dados.get(i).getDocumento(), i, 3);
                    tabela.setValueAt(dados.get(i).getDocumento_referencia(), i, 4);
                    tabela.setValueAt(NumberFormat.getCurrencyInstance().format(dados.get(i).getVlr_financeiro()), i, 5);
                    tabela.setValueAt(NumberFormat.getCurrencyInstance().format(dados.get(i).getDebito()), i, 6);
                    tabela.setValueAt(dados.get(i).getStr_empresa(), i, 7);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PFinContasReceberPeriodo.class.getName()).log(Level.SEVERE, null, ex);
        }

        ManipulaJTables.removeLinhasBrancas(tabela);
    }

    private void limpaCampos() {
        txtDataInicio.setText("");
        txtDataFim.setText(ManipulaDatas.retornaDataAtual());
        DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
        dtm.setNumRows(0);
        panelBts.setVisible(false);
        scrollT.setVisible(false);
        id_financeiro = -1;
        panelDebitar.setVisible(false);
        txtDoc.setText("");
        txtVlrCredito.setText("");
        txtVlrDebito.setText("");
        txtNovoVlrDebito.setText("");
        id_emp = -1;
        txtDataFim.setEditable(true);
        txtDataInicio.setEditable(true);
    }

    private javax.swing.JTable tabela;
    private javax.swing.table.DefaultTableModel modelT;
    private javax.swing.JScrollPane scrollT;
    private javax.swing.JLabel lblDataInicio;
    private javax.swing.JComboBox comboEmp;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDataFim;
    private javax.swing.JFormattedTextField txtDataInicio;
    private javax.swing.JFormattedTextField txtDataFim;
    private javax.swing.JPanel panelConsulta;
    private javax.swing.JButton btConsultar;
    private DAOEmpresas daoe = new DAOEmpresas();
    private javax.swing.JPanel panelBts;
    private javax.swing.JButton btDebitar;
    private javax.swing.JButton btFechar;
    private javax.swing.JButton btDetalhes;
    private javax.swing.JPanel panelDebitar;
    private javax.swing.JLabel lblDoc;
    private javax.swing.JLabel lblVlrCredito;
    private javax.swing.JLabel lblVlrDebito;
    private javax.swing.JLabel lblNovoVlrDebito;
    private javax.swing.JTextField txtDoc;
    private javax.swing.JTextField txtVlrCredito;
    private javax.swing.JTextField txtVlrDebito;
    private javax.swing.JTextField txtNovoVlrDebito;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btSalvar;
    private int id_financeiro = -1;
    private int id_emp = -1;
}
