/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.relatorios.guias;

import classes.ManipulaDatas;
import classes.ManipulaJTables;
import dao.cadastros.DAOEmpresas;
import dao.entradas.DAOGuias;
import gui.inicio.FInicio;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import model.entradas.Guias;
import net.miginfocom.swing.MigLayout;
import relatorios.guias.Relatorios;

/**
 *
 * @author Rafael
 */
public class PRelatoriosGuiaGeral extends javax.swing.JPanel {

    public PRelatoriosGuiaGeral() {
        initComponents();

    }

    private void initComponents() {
        //inicializando componentes do jpanel principal
        panelConsulta = new javax.swing.JPanel();
        panelRelatorio = new javax.swing.JPanel();
        //fim

        //configurações do jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RELATÓRIO GERAL DE GUIAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
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
            Logger.getLogger(PRelatoriosGuiaGeral.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtDataFim.setText(ManipulaDatas.retornaDataAtual());

        //Tabela config
        String[] columnNames = {"Data abert", "Nosso num", "Empresa", "Guia", "Qtd sacas", "Vlr unit", "Vlr total"};
        Object[][] data = {};
        modelGuias = new javax.swing.table.DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabelaGuias = new javax.swing.JTable(modelGuias);
        tabelaGuias.setPreferredScrollableViewportSize(new java.awt.Dimension(710, 300));
        tabelaGuias.getColumnModel().getColumn(0).setMaxWidth(100);
        tabelaGuias.getColumnModel().getColumn(0).setMinWidth(100);
        tabelaGuias.getColumnModel().getColumn(1).setMaxWidth(100);
        tabelaGuias.getColumnModel().getColumn(1).setMinWidth(100);
        tabelaGuias.getColumnModel().getColumn(2).setMaxWidth(100);
        tabelaGuias.getColumnModel().getColumn(2).setMinWidth(100);
        tabelaGuias.getColumnModel().getColumn(3).setMaxWidth(110);
        tabelaGuias.getColumnModel().getColumn(3).setMinWidth(110);
        tabelaGuias.getColumnModel().getColumn(4).setMaxWidth(100);
        tabelaGuias.getColumnModel().getColumn(4).setMinWidth(100);
        tabelaGuias.getColumnModel().getColumn(5).setMaxWidth(100);
        tabelaGuias.getColumnModel().getColumn(5).setMinWidth(100);
        tabelaGuias.getColumnModel().getColumn(6).setMaxWidth(100);
        tabelaGuias.getColumnModel().getColumn(6).setMinWidth(100);
        //alinhando os valores das celulas da Tabela à direita
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);

        tabelaGuias.getColumnModel().getColumn(0).setCellRenderer(r);
        tabelaGuias.getColumnModel().getColumn(2).setCellRenderer(r);
        tabelaGuias.getColumnModel().getColumn(3).setCellRenderer(r);
        tabelaGuias.getColumnModel().getColumn(4).setCellRenderer(r);
        tabelaGuias.getColumnModel().getColumn(5).setCellRenderer(r);
        tabelaGuias.getColumnModel().getColumn(6).setCellRenderer(r);
        tabelaGuias.getColumnModel().getColumn(1).setCellRenderer(r);
        scrollTGuias = new javax.swing.JScrollPane(tabelaGuias);

        tabelaGuias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tabelaMouseClicked(e);
            }
        });
        //fim

        //configurações do jpanel relatorios
        panelRelatorio.setLayout(new MigLayout());
        //fim

        //inicializando compoentens jpanel relatorios
        btImprimir = new javax.swing.JButton("Imprimir");
        btFechar = new javax.swing.JButton("Fechar");
        //fim

        //adicionando componentes panel Relatorio
        panelRelatorio.add(btImprimir, "split 2");
        panelRelatorio.add(btFechar, "wrap");
        panelRelatorio.add(scrollTGuias);

        //fim
        this.add(panelConsulta, "top");
        this.add(panelRelatorio);

        //adicionando evento botoes
        btGerarRelatório.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btGerarRelatorioActionPerformed(e);
            }
        });

        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btImprimirActionPerformed(e);
            }
        });

        btFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btFecharActionPerformed(e);
            }
        });

        //fim
        btImprimir.setEnabled(false);
        btFechar.setEnabled(false);
    }

    private void btGerarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel dtm = (DefaultTableModel) tabelaGuias.getModel();
        dtm.setNumRows(0);
        listaDados();
    }

    private void listaDados() {
        String dt_ini = txtDataInicio.getText().toString();
        String dt_fim = txtDataFim.getText().toString();
        //  java.sql.Date d_dt_ini = ManipulaDatas.converteStrSql(dt_ini);
        //  java.sql.Date d_dt_fim = ManipulaDatas.converteStrSql(dt_fim);

        if ((ManipulaDatas.validaData(dt_ini) == false) || (ManipulaDatas.validaData(dt_fim) == false)) {
            JOptionPane.showMessageDialog(null, "Data inválida!");
        } else {
            DAOGuias daog = new DAOGuias();
            List<Guias> dadosg;

            try {
                String nome_emp = comboEmp.getSelectedItem().toString();
                int id_emp = daoe.retornaIDPorNome(nome_emp);
                comboEmp.setEnabled(false);
                dadosg = daog.consultaDadosGuiaPeriodo(ManipulaDatas.converteStrSql(dt_ini), ManipulaDatas.converteStrSql(dt_fim), id_emp);
                if (dadosg.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Não existe guia neste período!");
                } else {
                    DecimalFormat df = new DecimalFormat("###,###,###.00");
                    DecimalFormat dfs = new DecimalFormat("###,###,###");
                    btImprimir.setEnabled(true);
                    btFechar.setEnabled(true);
                    btGerarRelatório.setEnabled(false);
                    String[] linhas = {null, null, null, null, null};
                    for (int i = 0; i < dadosg.size(); i++) {
                        DefaultTableModel dtm = (DefaultTableModel) tabelaGuias.getModel();
                        dtm.addRow(linhas);
                        //{"Data abert", "Nosso num", "Empresa", "Guia", "Qtd sacas", "Vlr unit", "Vlr total"};
                        tabelaGuias.setModel(dtm);
                        tabelaGuias.setValueAt(ManipulaDatas.converteSqlString(dadosg.get(i).getDt_entrada_guia()), i, 0);
                        tabelaGuias.setValueAt(dadosg.get(i).getNosso_num_guia(), i, 1);
                        tabelaGuias.setValueAt(dadosg.get(i).getStr_emp(), i, 2);
                        tabelaGuias.setValueAt(dadosg.get(i).getNum_guia(), i, 3);
                        tabelaGuias.setValueAt(dfs.format(dadosg.get(i).getQtd_sacas_guia()), i, 4);
                        tabelaGuias.setValueAt(df.format(dadosg.get(i).getVlr_unit_guia()), i, 5);
                        tabelaGuias.setValueAt(df.format(dadosg.get(i).getVlr_total_guia()), i, 6);
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(PRelatoriosGuiaGeral.class.getName()).log(Level.SEVERE, null, ex);
            }

            ManipulaJTables.removeLinhasBrancas(tabelaGuias);
        }

    }

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {
        Relatorios r = new Relatorios();
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

            r.gerarRelatorio(param, 0, 0);
        }

    }

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {
        btImprimir.setEnabled(false);
        btFechar.setEnabled(false);
        DefaultTableModel dtm = (DefaultTableModel) tabelaGuias.getModel();
        dtm.setNumRows(0);
        btGerarRelatório.setEnabled(true);
        comboEmp.setEnabled(true);
    }

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int li = tabelaGuias.getSelectedRow();
            String s_num_guia = tabelaGuias.getValueAt(li, 3).toString();
            String s_emp = tabelaGuias.getValueAt(li, 2).toString();
            DAOEmpresas daoe = new DAOEmpresas();
            DAOGuias daog = new DAOGuias();

            try {
                int id_emp = daoe.retornaIDPorNome(s_emp);
                int id_guia = daog.retornaIdGuiaEmp(s_num_guia, id_emp);

                FRelatoriosGuiaGeralDados fr = new FRelatoriosGuiaGeralDados(id_guia);
                fr.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(PRelatoriosGuiaGeral.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private javax.swing.JLabel lblDataInicio;
    private javax.swing.JComboBox comboEmp;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDataFim;
    private javax.swing.JFormattedTextField txtDataInicio;
    private javax.swing.JFormattedTextField txtDataFim;
    private javax.swing.JPanel panelConsulta;
    private javax.swing.JButton btGerarRelatório;
    private javax.swing.JPanel panelRelatorio;
    private javax.swing.JTable tabelaGuias;
    private javax.swing.JScrollPane scrollTGuias;
    private javax.swing.table.DefaultTableModel modelGuias;
    private DAOEmpresas daoe = new DAOEmpresas();
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btFechar;
}
