/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.guias.notasfiscais;

import classes.ManipulaDatas;
import classes.ManipulaJTables;
import classes.NumerosFloatCVirgula;
import classes.NumerosInt;
import dao.entradas.DAONfeEnt;
import dao.entradas.DAONfeModel;
import dao.saidas.DAONfeSai;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.entradas.NfeModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PGuiasNotasFiscaisConferencia extends javax.swing.JPanel {

    public PGuiasNotasFiscaisConferencia() {
        initComponents();
    }

    private void initComponents() {
        //configuração panel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Conferência de sacas de NFe", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel principal
        panelLateral = new javax.swing.JPanel();
        panelAlterar = new javax.swing.JPanel();
        panelAlterar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qtd de sacas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        btAlterar = new javax.swing.JButton("Alterar qtd");
        btSalvar = new javax.swing.JButton("Salvar");
        btCancelar = new javax.swing.JButton("Cancelar");
        lblQtdSaca = new javax.swing.JLabel("Qtd de sacas:");
        txtQtdSaca = new javax.swing.JTextField(8);
        //fim

        //Tabela config
        String[] columnNames = {"ID", "Data", "Tipo", "Guia", "Nosso num", "Doc viagem", "Nº nota", "Motorista", "Peso", "Qtd sacas Real"};
        Object[][] data = {};
        modelNfe = new javax.swing.table.DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabelaNfe = new javax.swing.JTable(modelNfe);
        tabelaNfe.setPreferredScrollableViewportSize(new java.awt.Dimension(910, 300));
        tabelaNfe.getColumnModel().getColumn(0).setMaxWidth(50);
        tabelaNfe.getColumnModel().getColumn(0).setMinWidth(60);
        tabelaNfe.getColumnModel().getColumn(1).setMaxWidth(100);
        tabelaNfe.getColumnModel().getColumn(1).setMinWidth(100);
        tabelaNfe.getColumnModel().getColumn(2).setMaxWidth(50);
        tabelaNfe.getColumnModel().getColumn(2).setMinWidth(50);
        tabelaNfe.getColumnModel().getColumn(3).setMaxWidth(110);
        tabelaNfe.getColumnModel().getColumn(3).setMinWidth(110);
        tabelaNfe.getColumnModel().getColumn(4).setMaxWidth(100);
        tabelaNfe.getColumnModel().getColumn(4).setMinWidth(100);
        tabelaNfe.getColumnModel().getColumn(5).setMaxWidth(100);
        tabelaNfe.getColumnModel().getColumn(5).setMinWidth(100);
        tabelaNfe.getColumnModel().getColumn(6).setMaxWidth(100);
        tabelaNfe.getColumnModel().getColumn(6).setMinWidth(100);
        tabelaNfe.getColumnModel().getColumn(7).setMaxWidth(100);
        tabelaNfe.getColumnModel().getColumn(7).setMinWidth(100);
        tabelaNfe.getColumnModel().getColumn(8).setMaxWidth(100);
        tabelaNfe.getColumnModel().getColumn(8).setMinWidth(100);
        tabelaNfe.getColumnModel().getColumn(9).setMaxWidth(100);
        tabelaNfe.getColumnModel().getColumn(9).setMinWidth(100);
        //alinhando os valores das celulas da Tabela à direita
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);

        tabelaNfe.getColumnModel().getColumn(0).setCellRenderer(r);
        tabelaNfe.getColumnModel().getColumn(2).setCellRenderer(r);
        tabelaNfe.getColumnModel().getColumn(3).setCellRenderer(r);
        tabelaNfe.getColumnModel().getColumn(4).setCellRenderer(r);
        tabelaNfe.getColumnModel().getColumn(5).setCellRenderer(r);
        tabelaNfe.getColumnModel().getColumn(1).setCellRenderer(r);
        tabelaNfe.getColumnModel().getColumn(6).setCellRenderer(r);
        tabelaNfe.getColumnModel().getColumn(7).setCellRenderer(r);
        tabelaNfe.getColumnModel().getColumn(8).setCellRenderer(r);
        tabelaNfe.getColumnModel().getColumn(9).setCellRenderer(r);
        scrollTNfe = new javax.swing.JScrollPane(tabelaNfe);
        //fim

        //configuração panel Alterar
        panelAlterar.setLayout(new MigLayout());
        panelLateral.setLayout(new MigLayout());
        txtQtdSaca.setDocument(new NumerosFloatCVirgula());
        //fim

        panelLateral.add(btAlterar, "wrap, top");
        panelLateral.add(panelAlterar);

        panelAlterar.add(lblQtdSaca, "split 2");
        panelAlterar.add(txtQtdSaca, "wrap");
        panelAlterar.add(btCancelar, "split 2");
        panelAlterar.add(btSalvar, "gapx 30");

        //adicionando compoentens panel principal
        this.add(scrollTNfe);
        this.add(panelLateral, "top");
        //fim

        //evento bts
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btAlterarActionPerformed(e);
            }
        });

        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btSalvarActionPerformed(e);
            }
        });

        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelarActionPerformed(e);
            }
        });
        //fim

        //listando dados
        listaDados();
        //fim

        panelAlterar.setVisible(false);

    }

    private void listaDados() {
        DAONfeModel daonm = new DAONfeModel();
        List<NfeModel> dados;

        try {
            dados = daonm.listaDadosConferencia();

            if (dados.size() == 0) {
                JOptionPane.showMessageDialog(null, "Não há notas fiscais sem qtd de sacas!");
            } else {
                String[] linhas = {null, null, null, null, null, null, null, null};

                for (int i = 0; i < dados.size(); i++) {
                    DefaultTableModel dtm = (DefaultTableModel) tabelaNfe.getModel();
                    dtm.addRow(linhas);
                    tabelaNfe.setModel(dtm);

                    tabelaNfe.setValueAt(dados.get(i).getId(), i, 0);
                    tabelaNfe.setValueAt(ManipulaDatas.converteSqlString(dados.get(i).getDt()), i, 1);
                    tabelaNfe.setValueAt(dados.get(i).getTipo(), i, 2);
                    tabelaNfe.setValueAt(dados.get(i).getNum_guia(), i, 3);
                    tabelaNfe.setValueAt(dados.get(i).getNosso_num(), i, 4);
                    tabelaNfe.setValueAt(dados.get(i).getDoc_viagem(), i, 5);
                    tabelaNfe.setValueAt(dados.get(i).getNum_nota(), i, 6);
                    tabelaNfe.setValueAt(dados.get(i).getMotorista(), i, 7);
                    tabelaNfe.setValueAt(dados.get(i).getPeso(), i, 8);
                    tabelaNfe.setValueAt(dados.get(i).getQtd_sacas_real(), i, 9);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasNotasFiscaisPendentes.class.getName()).log(Level.SEVERE, null, ex);
        }

        ManipulaJTables.removeLinhasBrancas(tabelaNfe);
    }

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {
        int li = tabelaNfe.getSelectedRow();

        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            panelAlterar.setVisible(true);
            linha = li;
            btAlterar.setEnabled(false);
        }
    }

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            limpaCampos();
        }
    }

    private void limpaCampos() {
        DefaultTableModel dtm = (DefaultTableModel) tabelaNfe.getModel();
        dtm.setNumRows(0);
        txtQtdSaca.setText("");
        listaDados();
        linha = 0;
        panelAlterar.setVisible(false);
        btAlterar.setEnabled(true);
    }

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_tipo = tabelaNfe.getValueAt(linha, 2).toString();
        int id_nota = Integer.parseInt(tabelaNfe.getValueAt(linha, 0).toString());
        boolean condicao = false;
        String s_qtd_saca = txtQtdSaca.getText().toString();
        int tipo_viagem = 0;
        String s_num_nota = tabelaNfe.getValueAt(linha, 6).toString();

        DAONfeEnt daone = new DAONfeEnt();
        DAONfeSai daons = new DAONfeSai();

        if (s_qtd_saca.isEmpty() || s_qtd_saca.equals(null)) {
            JOptionPane.showMessageDialog(null, "Qtd de sacas em branco!");
        } else {
            s_qtd_saca = s_qtd_saca.replace(",", ".");
            double d_qtd_saca = Double.parseDouble(s_qtd_saca);

            if (d_qtd_saca <= 0) {
                JOptionPane.showMessageDialog(null, "Qtde de sacas menor ou igual a 0!");
            } else {
                if (s_tipo.equals("E")) {
                    tipo_viagem = 1;
                } else if (s_tipo.equals("S")) {
                    tipo_viagem = 2;
                }
                try {
                    if (tipo_viagem == 1) {
                        int resp = JOptionPane.showConfirmDialog(null, "Você confirma " + d_qtd_saca + " sacas para a nota fiscal de entrada " + s_num_nota + "?", "Confirma qtd sacas nota", JOptionPane.YES_NO_OPTION);

                        if (resp == 0) {

                            daone.alteraStatusConf(id_nota);
                            daone.alterarQtdSacas(id_nota, d_qtd_saca);
                            JOptionPane.showMessageDialog(null, "Qtde de sacas alterado com sucesso!");
                            limpaCampos();
                        }
                    } else if (tipo_viagem == 2) {
                        int resp = JOptionPane.showConfirmDialog(null, "Você confirma " + d_qtd_saca + " sacas para a nota fiscal de saída " + s_num_nota + "?", "Confirma qtd sacas nota", JOptionPane.YES_NO_OPTION);

                        if (resp == 0) {

                            daons.alteraStatusConf(id_nota);
                            daons.alterarQtdSacas(id_nota, d_qtd_saca);
                            JOptionPane.showMessageDialog(null, "Qtde de sacas alterado com sucesso!");
                            limpaCampos();
                        }

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PGuiasNotasFiscaisConferencia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private javax.swing.JTable tabelaNfe;
    private javax.swing.JScrollPane scrollTNfe;
    private javax.swing.table.DefaultTableModel modelNfe;
    private javax.swing.JPanel panelLateral;
    private javax.swing.JPanel panelAlterar;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JLabel lblQtdSaca;
    private javax.swing.JTextField txtQtdSaca;
    private int linha = 0;
}
