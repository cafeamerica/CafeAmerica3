/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.guias.notasfiscais;

import classes.ManipulaDatas;
import classes.ManipulaJTables;
import dao.entradas.DAONfeEnt;
import dao.entradas.DAONfeModel;
import dao.entradas.DAOViagemEnt;
import dao.saidas.DAONfeSai;
import dao.saidas.DAOViagemSai;
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
import model.entradas.ViagemEnt;
import model.saidas.ViagemSai;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PGuiasNotasFiscaisPendentes extends javax.swing.JPanel {

    public PGuiasNotasFiscaisPendentes() {
        initComponents();
    }

    private void initComponents() {
        //configuração panel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NFe pendentes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel principal
        panelLateral = new javax.swing.JPanel();
        panelAlterar = new javax.swing.JPanel();
        panelAlterar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nº nota", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        btAlterar = new javax.swing.JButton("Alterar nº");
        btSalvar = new javax.swing.JButton("Salvar");
        btCancelar = new javax.swing.JButton("Cancelar");
        lblNumNota = new javax.swing.JLabel("Número da nota:");
        txtNumNota = new javax.swing.JTextField(8);
        //fim

        //Tabela config
        String[] columnNames = {"ID", "Data", "Tipo", "Guia", "Nosso num", "Doc viagem", "Motorista", "Peso"};
        Object[][] data = {};
        modelNfe = new javax.swing.table.DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabelaNfe = new javax.swing.JTable(modelNfe);
        tabelaNfe.setPreferredScrollableViewportSize(new java.awt.Dimension(810, 300));
        tabelaNfe.getColumnModel().getColumn(0).setMaxWidth(100);
        tabelaNfe.getColumnModel().getColumn(0).setMinWidth(100);
        tabelaNfe.getColumnModel().getColumn(1).setMaxWidth(100);
        tabelaNfe.getColumnModel().getColumn(1).setMinWidth(100);
        tabelaNfe.getColumnModel().getColumn(2).setMaxWidth(100);
        tabelaNfe.getColumnModel().getColumn(2).setMinWidth(100);
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
        scrollTNfe = new javax.swing.JScrollPane(tabelaNfe);
        //fim

        //configuração panel Alterar
        panelAlterar.setLayout(new MigLayout());
        panelLateral.setLayout(new MigLayout());
        //fim

        panelLateral.add(btAlterar, "wrap, top");
        panelLateral.add(panelAlterar);

        panelAlterar.add(lblNumNota, "split 2");
        panelAlterar.add(txtNumNota, "wrap");
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

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_tipo = tabelaNfe.getValueAt(linha, 2).toString();
        int id_nota = Integer.parseInt(tabelaNfe.getValueAt(linha, 0).toString());
        boolean condicao = false;
        String s_num_nota = txtNumNota.getText().toString();
        int tipo_viagem = 0;
        String s_doc_viagem = tabelaNfe.getValueAt(linha, 5).toString();

        DAONfeEnt daone = new DAONfeEnt();
        DAONfeSai daons = new DAONfeSai();
        DAOViagemEnt daove = new DAOViagemEnt();
        DAOViagemSai daovs = new DAOViagemSai();

        try {
            if (s_num_nota.isEmpty() || s_num_nota.equals(null)) {
                JOptionPane.showMessageDialog(null, "Número da nota em branco!");
            } else {
                if (s_tipo.equals("E")) {
                    condicao = daone.consultaNumNota(s_num_nota);
                    tipo_viagem = 1;
                } else if (s_tipo.equals("S")) {
                    condicao = daons.consultaNumNota(s_num_nota);
                    tipo_viagem = 2;
                }

                if (condicao == true) {
                    JOptionPane.showMessageDialog(null, "Este número da nota já existe no sistema!");
                } else {
                    if (tipo_viagem == 1) {
                        int resp = JOptionPane.showConfirmDialog(null, "Você confirma o número da nota de entrada " + s_num_nota + " para a viagem " + s_doc_viagem + "?", "Confirma nº nota", JOptionPane.YES_NO_OPTION);

                        if (resp == 0) {
                            daone.alterarNumNfe(s_num_nota, id_nota);
                            daove.alterarNumNfe(s_num_nota, s_doc_viagem);
                            JOptionPane.showMessageDialog(null, "Nº da nota alterado com sucesso!");
                            limpaCampos();
                        }
                    } else if (tipo_viagem == 2) {
                        int resp = JOptionPane.showConfirmDialog(null, "Você confirma o número da nota de saída " + s_num_nota + " para a viagem " + s_doc_viagem + "?", "Confirma nº nota", JOptionPane.YES_NO_OPTION);

                        if (resp == 0) {
                            daons.alterarNumNfe(s_num_nota, id_nota);
                            daovs.alterarNumNfe(s_num_nota, s_doc_viagem);
                            JOptionPane.showMessageDialog(null, "Nº da nota alterado com sucesso!");
                            limpaCampos();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao alterar nº de nota fiscal!");
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PGuiasNotasFiscaisPendentes.class.getName()).log(Level.SEVERE, null, ex);
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
        txtNumNota.setText("");
        listaDados();
        linha = 0;
        panelAlterar.setVisible(false);
        btAlterar.setEnabled(true);
    }

    private void listaDados() {
        DAONfeModel daonm = new DAONfeModel();
        List<NfeModel> dados;

        try {
            dados = daonm.listaDadosPendentes();

            if (dados.size() == 0) {
                JOptionPane.showMessageDialog(null, "Não há notas fiscais pendentes!");
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
                    tabelaNfe.setValueAt(dados.get(i).getMotorista(), i, 6);
                    tabelaNfe.setValueAt(dados.get(i).getPeso(), i, 7);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasNotasFiscaisPendentes.class.getName()).log(Level.SEVERE, null, ex);
        }

        ManipulaJTables.removeLinhasBrancas(tabelaNfe);
    }

    private javax.swing.JTable tabelaNfe;
    private javax.swing.JScrollPane scrollTNfe;
    private javax.swing.table.DefaultTableModel modelNfe;
    private javax.swing.JPanel panelLateral;
    private javax.swing.JPanel panelAlterar;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JLabel lblNumNota;
    private javax.swing.JTextField txtNumNota;
    private int linha = 0;
}
