/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.relatorios.producao;

import classes.ManipulaJTables;
import dao.cadastros.DAOEmpresas;
import dao.entradas.DAOEmpresaGuia;
import dao.entradas.DAOGuiaViagemEnt;
import dao.entradas.DAOGuias;
import dao.entradas.DAOProducaoGuia;
import dao.saidas.DAOResumo;
import gui.relatorios.guias.PRelatoriosGuiaEstoquePeriodo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.entradas.ProducaoGuia;
import model.saidas.Resumo;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PRelatoriosPdcResumoGuia extends javax.swing.JPanel {

    public PRelatoriosPdcResumoGuia() {
        initComponents();
    }

    private void initComponents() {
        //inicializando componentes do jpanel principal
        panelConsulta = new javax.swing.JPanel();
        panelRel = new javax.swing.JPanel();
        //fim

        //configurações do jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RESUMO DE GUIA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //INICIALIZANDO componentes jpanel consultar
        lblNumGuia = new javax.swing.JLabel("Informe o número da guia:");
        txtNumGuia = new javax.swing.JTextField(10);
        btGerarRelatório = new javax.swing.JButton("Gerar relatório");
        comboEmp = new javax.swing.JComboBox<>();
        lblCliente = new javax.swing.JLabel("Cliente:");
        //fim

        //configurações do jpanel consulta
        panelConsulta.setLayout(new MigLayout());
        panelConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar guia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        btImprimir = new javax.swing.JButton("Imprimir");
        panelT = new javax.swing.JPanel();
        btFechar = new javax.swing.JButton("Fechar");

        //adicionando componentes ao jpanel consulta
        panelConsulta.add(lblNumGuia, "split 2");
        panelConsulta.add(txtNumGuia, "wrap");
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

        //Tabela config
        String[] columnNames = {"Tipo", "Quantidade", "Peso"};
        Object[][] data = {};
        modelT = new javax.swing.table.DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabela = new javax.swing.JTable(modelT);
        tabela.setPreferredScrollableViewportSize(new java.awt.Dimension(400, 400));
        tabela.getColumnModel().getColumn(0).setMaxWidth(160);
        tabela.getColumnModel().getColumn(0).setMinWidth(160);
        tabela.getColumnModel().getColumn(1).setMaxWidth(120);
        tabela.getColumnModel().getColumn(1).setMinWidth(120);
        tabela.getColumnModel().getColumn(2).setMaxWidth(120);
        tabela.getColumnModel().getColumn(2).setMinWidth(120);

        scrollT = new javax.swing.JScrollPane(tabela);
       // scrollT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));

        //alinhando os valores das celulas da Tabela à direita
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);

        tabela.getColumnModel().getColumn(0).setCellRenderer(r);
        tabela.getColumnModel().getColumn(1).setCellRenderer(r);
        tabela.getColumnModel().getColumn(2).setCellRenderer(r);
        //fim

        panelT.add(scrollT);

        panelRel.add(btImprimir, "split 2");
        panelRel.add(btFechar, "wrap");
        panelRel.add(panelT);

        btImprimir.setEnabled(false);
        btFechar.setEnabled(false);

        //fim
        this.add(panelConsulta, "top");
        this.add(panelRel);

        //evento bts
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
                btFechartActionPerformed(e);
            }
        });
        //fim
    }

    private void btGerarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
        String s_num_guia = txtNumGuia.getText().toString();
        DAOEmpresaGuia daoeg = new DAOEmpresaGuia();
        DAOGuiaViagemEnt daogve = new DAOGuiaViagemEnt();
        DAOGuias daog = new DAOGuias();
        try {
            int id_emp = daoe.retornaIDPorNome(comboEmp.getSelectedItem().toString());
            if (daoeg.consultaGuiaEmp(s_num_guia, id_emp) == false) {
                JOptionPane.showMessageDialog(null, "Essa guia não existe para a empresa " + comboEmp.getSelectedItem().toString());
            } else {
                int id_guia = daog.retornaIdGuiaEmp(s_num_guia, id_emp);
                DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
                dtm.setNumRows(0);
                gerarResumo(id_guia);
                txtNumGuia.setEditable(false);
                btImprimir.setEnabled(true);
                btGerarRelatório.setEnabled(false);
                btFechar.setEnabled(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PRelatoriosPdcResumoGuia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {
        panelT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RESUMO DA GUIA " + txtNumGuia.getText().toString(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        printComponenet(panelT);
    }

    private void btFechartActionPerformed(java.awt.event.ActionEvent evt) {
        txtNumGuia.setText("");
        txtNumGuia.setEditable(true);
        btImprimir.setEnabled(false);
        btGerarRelatório.setEnabled(true);
        btFechar.setEnabled(false);
        DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
        dtm.setNumRows(0);
    }

    private void gerarResumo(int id_guia) {
        DAOResumo daor = new DAOResumo();
        Resumo r = new Resumo();
        DAOProducaoGuia daopg = new DAOProducaoGuia();
        ProducaoGuia pg = new ProducaoGuia();

        List<Resumo> dados_resumo;
        List<Resumo> dados_dif;
        List<ProducaoGuia> dados_pg;
        DecimalFormat df = new DecimalFormat("###,###.###");

        try {
            dados_resumo = daor.resumoGeral(id_guia);
            dados_dif = daor.diferencaEmb(id_guia);
            dados_pg = daopg.retornaPrevia(id_guia);
            double quebra = daor.quebra(id_guia);
            int cont_linhas = 0;
            int qtd_serv = 0;
            double qtd_ent = 0;
            double qtd_sai = 0;
            double dif_emb = 0;
            double total_quebra = 0;

            String[] linhas = {null, null, null};
            DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
            tabela.setModel(dtm);

            for (int i = 0; i < dados_resumo.size(); i++) {
                dtm.addRow(linhas);
                tabela.setValueAt(dados_resumo.get(i).getDesc_serv_sai(), cont_linhas, 0);
                tabela.setValueAt(dados_resumo.get(i).getQtd_sacas_sai(), cont_linhas, 1);
                tabela.setValueAt(dados_resumo.get(i).getPeso_sai(), cont_linhas, 2);

                qtd_serv++; //quantidade de serviços
                cont_linhas++;
            }
            
            double d_previa = 0;
            DecimalFormat dff = new DecimalFormat("#.00");
            for (int k = 0; k < dados_pg.size(); k++) {
                dtm.addRow(linhas);
                tabela.setValueAt("PRÉVIA - "+dados_pg.get(k).getDesc_serv(), cont_linhas, 0);
                tabela.setValueAt(dff.format(dados_pg.get(k).getSaldo_pdc()/60), cont_linhas, 1);
                tabela.setValueAt(dados_pg.get(k).getSaldo_pdc(), cont_linhas, 2);
                
                d_previa = d_previa + dados_pg.get(k).getSaldo_pdc();
                cont_linhas++;
            }

            //soltando uma linha branca para separar...
            dtm.addRow(linhas);
            tabela.setValueAt("-", cont_linhas, 0);
            tabela.setValueAt("-", cont_linhas, 1);
            tabela.setValueAt("-", cont_linhas, 2);
            cont_linhas++;

            for (int j = 0; j < dados_dif.size(); j++) {
                dtm.addRow(linhas);
                tabela.setValueAt(dados_dif.get(j).getTipo_op(), cont_linhas, 0);
                tabela.setValueAt(dados_dif.get(j).getQtd(), cont_linhas, 1);
                tabela.setValueAt(dados_dif.get(j).getKilos(), cont_linhas, 2);
                if (dados_dif.get(j).getTipo_op().equals("ENTRADA EM BAG")) {
                    qtd_ent = qtd_ent + dados_dif.get(j).getKilos();
                }
                if (dados_dif.get(j).getTipo_op().equals("ENTRADA EM SACARIA")) {
                    qtd_ent = qtd_ent + dados_dif.get(j).getKilos();
                }
                if (dados_dif.get(j).getTipo_op().equals("SAIDA EM BAG")) {
                    qtd_sai = qtd_sai + dados_dif.get(j).getKilos();
                }
                if (dados_dif.get(j).getTipo_op().equals("SAIDA EM SACARIA")) {
                    qtd_sai = qtd_sai + dados_dif.get(j).getKilos();
                }

                cont_linhas++;
            }

            //soltando uma linha branca para separar...
            dtm.addRow(linhas);
            tabela.setValueAt("-", cont_linhas, 0);
            tabela.setValueAt("-", cont_linhas, 1);
            tabela.setValueAt("-", cont_linhas, 2);
            cont_linhas++;

            dtm.addRow(linhas);
            tabela.setValueAt("QUEBRA", cont_linhas, 0);
            tabela.setValueAt("-", cont_linhas, 1);
            tabela.setValueAt(df.format(quebra), cont_linhas, 2);
            cont_linhas++;

            dif_emb = qtd_ent - qtd_sai; //diferença das embalagens = ENTRADA - SAÍDA
            total_quebra = quebra - dif_emb; //total quebra = QUEBRA - DIFERENÇA EMBALAGENS

            dtm.addRow(linhas);
            tabela.setValueAt("DIFERENÇA EMBALAGENS", cont_linhas, 0);
            tabela.setValueAt("-", cont_linhas, 1);
            tabela.setValueAt(dif_emb, cont_linhas, 2);
            cont_linhas++;

            dtm.addRow(linhas);
            tabela.setValueAt("TOTAL QUEBRA", cont_linhas, 0);
            tabela.setValueAt("-", cont_linhas, 1);
            tabela.setValueAt(dff.format(total_quebra-d_previa), cont_linhas, 2);
            cont_linhas = 0;

            ManipulaJTables.removeLinhasBrancas(tabela);

        } catch (SQLException ex) {
            Logger.getLogger(PRelatoriosPdcResumoGuia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void printComponenet(final javax.swing.JPanel panel) {

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(" RESUMO DE GUIA ");

        pj.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());

                panel.paint(g2);        // o JPanel aqui  

                return Printable.PAGE_EXISTS;
            }

        });
        if (pj.printDialog() == false) {
            return;
        }

        try {
            pj.print();
        } catch (PrinterException ex) {
            // handle exception  
        }
    }

    private javax.swing.JLabel lblNumGuia;
    private javax.swing.JTextField txtNumGuia;
    private javax.swing.JButton btGerarRelatório;
    private DAOEmpresas daoe = new DAOEmpresas();
    private javax.swing.JComboBox comboEmp;
    private javax.swing.JPanel panelConsulta;
    private javax.swing.JPanel panelRel;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JTable tabela;
    private javax.swing.table.DefaultTableModel modelT;
    private javax.swing.JScrollPane scrollT;
    private javax.swing.JPanel panelT;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btFechar;
}
