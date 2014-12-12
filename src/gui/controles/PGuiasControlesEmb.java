/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controles;

import classes.ManipulaArquivo;
import classes.ManipulaDatas;
import classes.ManipulaJTables;
import classes.NumerosFloatCVirgula;
import classes.NumerosInt;
import dao.cadastros.DAOEmpresas;
import dao.entradas.DAOCtrlEmbEmp;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.entradas.CtrlEmbEmp;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PGuiasControlesEmb extends javax.swing.JPanel {

    public PGuiasControlesEmb() {
        initComponents();
    }

    private void initComponents() {
        panelBaixa = new javax.swing.JPanel();
        btDarBaixa = new javax.swing.JButton("Dar baixa");

        //configurações da tabela com dados da viagem da guia
        //Tabela config
        String[] columnNamess = {"Tipo", "Qtd total", "Qtd baixadas", "Disponível"};
        Object[][] dataa = {};
        modelViagens = new javax.swing.table.DefaultTableModel(dataa, columnNamess) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabelaViagens = new javax.swing.JTable(modelViagens);
        tabelaViagens.setPreferredScrollableViewportSize(new java.awt.Dimension(480, 32));
        tabelaViagens.getColumnModel().getColumn(0).setMaxWidth(160);
        tabelaViagens.getColumnModel().getColumn(0).setMinWidth(160);
        tabelaViagens.getColumnModel().getColumn(1).setMaxWidth(120);
        tabelaViagens.getColumnModel().getColumn(1).setMinWidth(120);
        tabelaViagens.getColumnModel().getColumn(2).setMaxWidth(100);
        tabelaViagens.getColumnModel().getColumn(2).setMinWidth(100);
        tabelaViagens.getColumnModel().getColumn(3).setMaxWidth(100);
        tabelaViagens.getColumnModel().getColumn(3).setMinWidth(100);
        scrollTViagens = new javax.swing.JScrollPane(tabelaViagens);
        scrollTViagens.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Embalagens", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //alinhando os valores das celulas da Tabela à direita
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);

        tabelaViagens.getColumnModel().getColumn(0).setCellRenderer(r);
        tabelaViagens.getColumnModel().getColumn(1).setCellRenderer(r);
        tabelaViagens.getColumnModel().getColumn(2).setCellRenderer(r);
        tabelaViagens.getColumnModel().getColumn(3).setCellRenderer(r);
        //fim

        //inicializando componentes do panel baixa
        lblQtdABaixar = new javax.swing.JLabel("Qtd a baixar:");
        lblQtdTotal = new javax.swing.JLabel("Qtd total:");
        lblTipo = new javax.swing.JLabel("Tipo:");
        lblQtdBaixada = new javax.swing.JLabel("Qtd baixada:");
        txtQtdABaixar = new javax.swing.JTextField(6);
        txtQtdTotal = new javax.swing.JTextField(6);
        txtTipo = new javax.swing.JTextField(10);
        txtQtdBaixada = new javax.swing.JTextField(6);
        btBaixar = new javax.swing.JButton("Baixar");
        comboEmpEmb = new javax.swing.JComboBox<>();
        btConsultarEmp = new javax.swing.JButton("Consultar");
        panelConsultarEmp = new javax.swing.JPanel();
        btCancelar = new javax.swing.JButton("Cancelar");
        //fim

        //panel consultar
        panelConsultarEmp.setLayout(new MigLayout());
        panelConsultarEmp.add(comboEmpEmb, "split 2");
        panelConsultarEmp.add(btConsultarEmp);
        //fim

        //configurações componentes do panel consulta
        comboEmpEmb.setPreferredSize(new java.awt.Dimension(210, 24));
        comboEmpEmb.removeAllItems();
        try {
            ArrayList empresas = daoemp.consultarEmpresas();
            Iterator i = empresas.iterator();
            while (i.hasNext()) {
                String t = String.valueOf(i.next());
                comboEmpEmb.addItem(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasControlesEmb.class.getName()).log(Level.SEVERE, null, ex);
        }
        //fim

        //Configurações do panel baixa
        panelBaixa.setLayout(new MigLayout());
        panelBaixa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da viagem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelBaixa.add(lblTipo, "split 2, gapx 45");
        panelBaixa.add(txtTipo, "wrap");
        panelBaixa.add(lblQtdTotal, "split 4, gapx 21");
        panelBaixa.add(txtQtdTotal);
        panelBaixa.add(lblQtdBaixada);
        panelBaixa.add(txtQtdBaixada, "wrap");
        panelBaixa.add(lblQtdABaixar, "split 2");
        panelBaixa.add(txtQtdABaixar, "wrap");
        panelBaixa.add(btCancelar, "split 2");
        panelBaixa.add(btBaixar, "gapx 70");

        txtQtdABaixar.setDocument(new NumerosFloatCVirgula());
        //fim

        //configurações do jpanel principal
        this.setLayout(new MigLayout());
        this.add(panelConsultarEmp, "wrap");
        this.add(scrollTViagens, "split 2");
        this.add(btDarBaixa, "wrap");
        this.add(panelBaixa);
        panelBaixa.setVisible(false);
        scrollTViagens.setVisible(false);
        btDarBaixa.setVisible(false);
        //fim

        //evento de botoes
        btDarBaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btDarBaixaActionPerformed(e);
            }
        });

        btBaixar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btBaixarActionPerformed(e);
            }
        });

        btConsultarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btConsultarEmpActionPerfomed(e);
            }
        });

        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelarActionPerformed(e);
            }
        });

        //fim
    }

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {
       int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar a baixa de embalagens?", "Cancelar baixar", JOptionPane.YES_NO_OPTION);
       
       if(resp == 0){
           limpaCampos();
       }
    }

    private void btDarBaixaActionPerformed(java.awt.event.ActionEvent evt) {
        int li = tabelaViagens.getSelectedRow();

        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            String s_tipo = tabelaViagens.getValueAt(li, 0).toString().toUpperCase();
            String s_qtd_t = tabelaViagens.getValueAt(li, 1).toString();
            String s_qtd_b = tabelaViagens.getValueAt(li, 2).toString();

            panelBaixa.setVisible(true);
            linha_tabela = li;
            txtQtdBaixada.setText(s_qtd_b);
            txtQtdTotal.setText(s_qtd_t);
            txtTipo.setText(s_tipo);
            txtQtdBaixada.setEditable(false);
            txtQtdTotal.setEditable(false);
            txtTipo.setEditable(false);
            btDarBaixa.setEnabled(false);
        }

    }

    private void listaDados() {
        DAOCtrlEmbEmp daocee = new DAOCtrlEmbEmp();
        //CtrlEmbEmp cee = new CtrlEmbEmp();
        DAOEmpresas daoe = new DAOEmpresas();
        List<CtrlEmbEmp> dados;
        try {
            int id_emp = daoe.retornaIDPorNome(s_empresa);

            dados = daocee.retornaDadosBaixa(id_emp);

            if (dados.size() == 0) {
                JOptionPane.showMessageDialog(null, "Não há dados!");
            } else {
                String[] linhas = {null, null, null, null};

                for (int i = 0; i < dados.size(); i++) {
                    DefaultTableModel dtm = (DefaultTableModel) tabelaViagens.getModel();
                    dtm.addRow(linhas);
                    tabelaViagens.setModel(dtm);
                    tabelaViagens.setValueAt(dados.get(i).getStr_tipo(), i, 0);
                    tabelaViagens.setValueAt(dados.get(i).getQtd_emb_total(), i, 1);
                    tabelaViagens.setValueAt(dados.get(i).getQtd_emb_baixada(), i, 2);
                    tabelaViagens.setValueAt(dados.get(i).getQtd_emb_total() - dados.get(i).getQtd_emb_baixada(), i, 3);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasControlesEmb.class.getName()).log(Level.SEVERE, null, ex);
        }

        ManipulaJTables.removeLinhasBrancas(tabelaViagens);

    }

    private void btBaixarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_tipo = txtTipo.getText().toString().toUpperCase();
        String s_qtd_a_baixar = txtQtdABaixar.getText().toString();
        String s_emp = comboEmpEmb.getSelectedItem().toString();
        int i_tipo = 0;

        if (s_tipo.equals("BBAG")) {
            i_tipo = 1;
        } else if (s_tipo.equals("SACARIA")) {
            i_tipo = 3;
        } else {
            i_tipo = 2;
        }

        if (s_qtd_a_baixar.isEmpty() || s_qtd_a_baixar.equals(null)) {
            JOptionPane.showMessageDialog(null, "Informe uma quantidade para dar baixa!");
        } else {
            s_qtd_a_baixar = s_qtd_a_baixar.replace(",", ".");
            double d_qtd_a_baixar = Double.parseDouble(s_qtd_a_baixar);

            if (d_qtd_a_baixar <= 0) {
                JOptionPane.showMessageDialog(null, "Quantidade inválida! Menor ou igual a zero!");
            } else {
                String s_qtd_disponivel = tabelaViagens.getValueAt(linha_tabela, 3).toString();
                s_qtd_disponivel = s_qtd_disponivel.replace(",", ".");

                double d_qtd_disponivel = Double.parseDouble(s_qtd_disponivel);

                if (d_qtd_a_baixar > d_qtd_disponivel) {
                    JOptionPane.showMessageDialog(null, "Quantidade inválida! Excede a qtd disponível!");
                } else {
                    int resp = JOptionPane.showConfirmDialog(null, "Você confirma a baixa de " + d_qtd_a_baixar + " unidades de " + s_tipo + " para a empresa "+ s_emp+"?", "Confirmar baixar de embalagem", JOptionPane.YES_NO_OPTION);

                    if (resp == 0) {
                        DAOCtrlEmbEmp daocee = new DAOCtrlEmbEmp();
                        DAOEmpresas daoe = new DAOEmpresas();
                        try {
                            
                            int id_emp = daoe.retornaIDPorNome(s_emp);
                            double d_qtd_ja_baixada = daocee.retornaQtdBaixada(id_emp, i_tipo);
                            d_qtd_a_baixar = d_qtd_a_baixar+d_qtd_ja_baixada;
                            daocee.alterarQtdBaixadas(i_tipo, d_qtd_a_baixar, id_emp);
                            
                            JOptionPane.showMessageDialog(null,"Quantidade baixada com sucesso!");
                            limpaCampos();
                        } catch (SQLException ex) {
                            Logger.getLogger(PGuiasControlesEmb.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }

    }

    private void limpaCampos() {
        panelBaixa.setVisible(false);
        txtQtdABaixar.setText("");
        linha_tabela = 0;
        btConsultarEmp.setEnabled(true);
        btDarBaixa.setEnabled(true);
     
        DefaultTableModel dtm = (DefaultTableModel)tabelaViagens.getModel();
        dtm.setNumRows(0);
    }

    private void btConsultarEmpActionPerfomed(java.awt.event.ActionEvent evt) {
        scrollTViagens.setVisible(true);
        btDarBaixa.setVisible(true);
        s_empresa = comboEmpEmb.getSelectedItem().toString();
        listaDados();
        btConsultarEmp.setEnabled(false);
    }

    private javax.swing.JTable tabelaViagens;
    private javax.swing.JScrollPane scrollTViagens;
    private javax.swing.table.DefaultTableModel modelViagens;
    private javax.swing.JButton btDarBaixa;
    private javax.swing.JButton btBaixar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JPanel panelBaixa;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JLabel lblQtdTotal;
    private javax.swing.JTextField txtQtdTotal;
    private javax.swing.JLabel lblQtdABaixar;
    private javax.swing.JTextField txtQtdABaixar;
    private javax.swing.JLabel lblQtdBaixada;
    private javax.swing.JTextField txtQtdBaixada;
    private javax.swing.JComboBox comboEmpEmb;
    private javax.swing.JButton btConsultarEmp;
    private javax.swing.JPanel panelConsultarEmp;
    DAOEmpresas daoemp = new DAOEmpresas();
    String s_empresa = "";
    private int linha_tabela = 0;
}
