/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.guias.saida.nova.viagem;

import classes.ManipulaArquivo;
import classes.ManipulaDatas;
import classes.ManipulaJTables;
import classes.NumerosFloatCVirgula;
import classes.NumerosInt;
import dao.cadastros.DAOEmpresas;
import dao.cadastros.DAOProdutores;
import dao.entradas.DAOCtrlEmbEmp;
import dao.entradas.DAOCtrlEmbViagemEnt;
import dao.entradas.DAOEmpresaGuia;
import dao.entradas.DAOGuiaViagemEnt;
import dao.entradas.DAOGuias;
import dao.entradas.DAONfeEnt;
import dao.entradas.DAOPdcGuiaData;
import dao.entradas.DAOPdcUnicaGuia;
import dao.entradas.DAOProducaoGuia;
import dao.entradas.DAOProdutorGuia;
import dao.entradas.DAOServicos;
import dao.entradas.DAOServicosGuia;
import dao.entradas.DAOViagemEnt;
import dao.financeiro.DAOFinanceiro;
import dao.saidas.DAOCtrlEmbViagemSai;
import dao.saidas.DAOGuiaViagemSai;
import dao.saidas.DAONfeSai;
import dao.saidas.DAOTipoCafeViagem;
import dao.saidas.DAOViagemSai;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.entradas.CtrlEmbEmp;
import model.entradas.CtrlEmbViagemEnt;
import model.entradas.EmpresaGuia;
import model.entradas.GuiaViagemEnt;
import model.entradas.Guias;
import model.entradas.NfeEnt;
import model.entradas.PdcGuiaData;
import model.entradas.PdcUnicaGuia;
import model.entradas.ProducaoGuia;
import model.entradas.ProdutorGuia;
import model.entradas.ServicosGuia;
import model.entradas.ViagemEnt;
import model.financeiro.Financeiro;
import model.saidas.CtrlEmbViagemSai;
import model.saidas.GuiaViagemSai;
import model.saidas.NfeSai;
import model.saidas.TipoCafeViagem;
import model.saidas.ViagemSai;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PGuiasSaidaTransformacaoGuia extends javax.swing.JPanel {

    public PGuiasSaidaTransformacaoGuia() {
        initComponents();
    }

    private void initComponents() {
        //configuração jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TRANSFORMAÇÃO DE GUIA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel guias
        panelGuias = new javax.swing.JPanel();
        panelGuias.setLayout(new MigLayout());
        panelGuias.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GUIAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        btProduzir = new javax.swing.JButton("Produção >");

        //Tabela config
        String[] columnNames = {"Cliente", "Guia", "Nosso nº"};
        Object[][] data = {};
        modelG = new javax.swing.table.DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabelaG = new javax.swing.JTable(modelG);
        tabelaG.setPreferredScrollableViewportSize(new java.awt.Dimension(300, 150));
        tabelaG.getColumnModel().getColumn(0).setMaxWidth(100);
        tabelaG.getColumnModel().getColumn(0).setMinWidth(100);
        tabelaG.getColumnModel().getColumn(1).setMaxWidth(100);
        tabelaG.getColumnModel().getColumn(1).setMinWidth(100);
        tabelaG.getColumnModel().getColumn(2).setMaxWidth(100);
        tabelaG.getColumnModel().getColumn(2).setMinWidth(100);
        //alinhando os valores das celulas da Tabela à direita
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);

        tabelaG.getColumnModel().getColumn(0).setCellRenderer(r);
        tabelaG.getColumnModel().getColumn(1).setCellRenderer(r);
        tabelaG.getColumnModel().getColumn(2).setCellRenderer(r);
        scrollTG = new javax.swing.JScrollPane(tabelaG);
        //fim

        //fim
        //adicionando componentes jpanel guias
        panelGuias.add(scrollTG, "top");
        panelGuias.add(btProduzir, "top");
        //fim

        //inicializando componentes jpanel producao
        panelProducao = new javax.swing.JPanel();
        panelProducao.setLayout(new MigLayout());
        panelProducao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produção", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        btGerarGuia = new javax.swing.JButton("Gerar guia");
        //Tabela config
        String[] columnNamess = {"Tipo café", "Peso total", "Peso disponível"};
        Object[][] dataa = {};
        modelTP = new javax.swing.table.DefaultTableModel(dataa, columnNamess) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabelaP = new javax.swing.JTable(modelTP);
        tabelaP.setPreferredScrollableViewportSize(new java.awt.Dimension(360, 150));
        tabelaP.getColumnModel().getColumn(0).setMaxWidth(120);
        tabelaP.getColumnModel().getColumn(0).setMinWidth(120);
        tabelaP.getColumnModel().getColumn(1).setMaxWidth(120);
        tabelaP.getColumnModel().getColumn(1).setMinWidth(120);
        tabelaP.getColumnModel().getColumn(2).setMaxWidth(120);
        tabelaP.getColumnModel().getColumn(2).setMinWidth(120);

        scrollTP = new javax.swing.JScrollPane(tabelaP);

        tabelaP.getColumnModel().getColumn(0).setCellRenderer(r);
        tabelaP.getColumnModel().getColumn(1).setCellRenderer(r);
        tabelaP.getColumnModel().getColumn(2).setCellRenderer(r);
        //fim

        //fim
        //adicionando componentes jpanel producao
        panelProducao.add(scrollTP);
        panelProducao.add(btGerarGuia);
        //fim

        //inicializando componentes jpanel dados guia
        panelDadosGuia = new javax.swing.JPanel();
        panelDadosGuia.setLayout(new MigLayout());
        panelDadosGuia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da guia", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        lblGuia = new javax.swing.JLabel("Guia:");
        lblPeso = new javax.swing.JLabel("Peso:");
        lblServico = new javax.swing.JLabel("Serviço:");
        txtGuia = new javax.swing.JTextField(10);
        txtPeso = new javax.swing.JTextField(8);
        txtServico = new javax.swing.JTextField(12);
        btAddMemoria = new javax.swing.JButton("Add a memória");
        radioTipoEmbBag = new javax.swing.JRadioButton("Bbags");
        radioTipoEmbGranel = new javax.swing.JRadioButton("Granel");
        radioTipoEmbSaca = new javax.swing.JRadioButton("Sacaria");
        txtTipoEmbBag = new javax.swing.JTextField(6);
        txtTipoEmbSaca = new javax.swing.JTextField(6);
        grupoBtsTipoEmb = new javax.swing.ButtonGroup();
        lblTipoEmb = new javax.swing.JLabel("Tipo de embalagem:");
        //fim

        //configuração componentes jpanel dados guia
        //Tabela config
        txtPeso.setDocument(new NumerosFloatCVirgula());

        String[] columnNamesss = {"Guia", "Serviço", "Peso", "Qtd sacas", "Bags"};
        Object[][] dataaa = {};
        modelT = new javax.swing.table.DefaultTableModel(dataaa, columnNamesss) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabela = new javax.swing.JTable(modelT);
        tabela.setPreferredScrollableViewportSize(new java.awt.Dimension(600, 150));
        tabela.getColumnModel().getColumn(0).setMaxWidth(120);
        tabela.getColumnModel().getColumn(0).setMinWidth(120);
        tabela.getColumnModel().getColumn(1).setMaxWidth(120);
        tabela.getColumnModel().getColumn(1).setMinWidth(120);
        tabela.getColumnModel().getColumn(2).setMaxWidth(120);
        tabela.getColumnModel().getColumn(2).setMinWidth(120);
        tabela.getColumnModel().getColumn(3).setMaxWidth(120);
        tabela.getColumnModel().getColumn(3).setMinWidth(120);
        tabela.getColumnModel().getColumn(4).setMaxWidth(120);
        tabela.getColumnModel().getColumn(4).setMinWidth(120);

        scrollT = new javax.swing.JScrollPane(tabela);
        scrollT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Memória de Guias", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));

        tabela.getColumnModel().getColumn(0).setCellRenderer(r);
        tabela.getColumnModel().getColumn(1).setCellRenderer(r);
        tabela.getColumnModel().getColumn(2).setCellRenderer(r);
        tabela.getColumnModel().getColumn(3).setCellRenderer(r);
        tabela.getColumnModel().getColumn(4).setCellRenderer(r);

        txtTipoEmbBag.setDocument(new NumerosInt());
        txtTipoEmbSaca.setDocument(new NumerosFloatCVirgula());
        grupoBtsTipoEmb.add(radioTipoEmbBag);
        grupoBtsTipoEmb.add(radioTipoEmbGranel);
        grupoBtsTipoEmb.add(radioTipoEmbSaca);
        radioTipoEmbBag.setSelected(true);
        //fim
        //fim

        panelDadosGuia.add(lblGuia, "split 2, gapx 49");
        panelDadosGuia.add(txtGuia, "wrap");
        panelDadosGuia.add(lblServico, "split 2, gapx 31");
        panelDadosGuia.add(txtServico, "wrap");
        panelDadosGuia.add(lblPeso, "split 2, gapx 44");
        panelDadosGuia.add(txtPeso, "wrap");
        panelDadosGuia.add(lblTipoEmb, "split 3");
        panelDadosGuia.add(radioTipoEmbBag);
        panelDadosGuia.add(txtTipoEmbBag, "wrap");
        panelDadosGuia.add(radioTipoEmbGranel, "wrap, gapx 118");
        panelDadosGuia.add(radioTipoEmbSaca, "split 2, gapx 118");
        panelDadosGuia.add(txtTipoEmbSaca, "wrap");
        panelDadosGuia.add(btAddMemoria, "gapx 45");
        //fim

        //inicializando componetens jpanel dados final
        panelDadosFinal = new javax.swing.JPanel();
        panelDadosFinal.setLayout(new MigLayout());
        panelDadosFinal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Finalizar transformação", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        lblVlrSaca = new javax.swing.JLabel("Valor por saca:");
        txtVlrSaca = new javax.swing.JTextField(8);
        btSalvar = new javax.swing.JButton("Salvar");
        btCancelar = new javax.swing.JButton("Cancelar");
        lblNumGuia = new javax.swing.JLabel("Número da guia:");
        txtNumGuia = new javax.swing.JTextField(8);

        txtVlrSaca.setDocument(new NumerosFloatCVirgula());

        //fim
        //adicionando componententes jpanel dados final
        panelDadosFinal.add(lblNumGuia, "split 2");
        panelDadosFinal.add(txtNumGuia, "wrap");
        panelDadosFinal.add(lblVlrSaca, "split 2, gapx 6");
        panelDadosFinal.add(txtVlrSaca, "wrap");
        panelDadosFinal.add(btCancelar, "split 2");
        panelDadosFinal.add(btSalvar, "gapx 45");
        //fim

        //inicializando componentens jpanel bts finalizar e desfazer tranformação
        panelBts = new javax.swing.JPanel();
        btDesfazer = new javax.swing.JButton("Desfazer");
        btFinalizarTrans = new javax.swing.JButton("Transformar");
        panelBts.setLayout(new MigLayout());
        //fim

        //adicionando componentes jpanel bts
        panelBts.add(btDesfazer, "wrap");
        panelBts.add(btFinalizarTrans);
        //fim

        //adicionando componetnes jpanel principal
        this.add(panelGuias, "top,split 2");
        this.add(panelProducao, "top, wrap");
        this.add(panelDadosGuia, "wrap");
        this.add(scrollT, "split 3");
        this.add(panelBts);
        this.add(panelDadosFinal, "top");
        //fim

        //evento bts
        btProduzir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btProduzirActionPerformed(e);
            }
        });

        btGerarGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btGerarGuiaActionPerformed(e);
            }
        });

        btAddMemoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btAddMemoriaActionPerformed(e);
            }
        });
        btDesfazer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btDesfazerActionPerformed(e);
            }
        });

        btFinalizarTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btFinalizarTransActionPerformed(e);
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
        //fims

        listaDadosGuias();
        panelProducao.setVisible(false);
        panelDadosGuia.setVisible(false);
        panelDadosFinal.setVisible(false);
        btDesfazer.setEnabled(false);
        btFinalizarTrans.setEnabled(false);
    }

    private void listaDadosGuias() {

        DAOGuias daog = new DAOGuias();
        List<Guias> dados;
        try {
            dados = daog.consultaNumGuiaNossoNum();

            if (dados.size() == 0) {
                JOptionPane.showMessageDialog(null, "Não há guias no sistema!");
            } else {
                String[] linhas = {null, null};
                for (int i = 0; i < dados.size(); i++) {
                    DefaultTableModel dtm = (DefaultTableModel) tabelaG.getModel();
                    dtm.addRow(linhas);
                    tabelaG.setModel(dtm);
                    tabelaG.setValueAt(dados.get(i).getStr_emp(), i, 0);
                    tabelaG.setValueAt(dados.get(i).getNum_guia(), i, 1);
                    tabelaG.setValueAt(dados.get(i).getNosso_num_guia(), i, 2);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasSaidaTransformacaoGuia.class.getName()).log(Level.SEVERE, null, ex);
        }

        ManipulaJTables.removeLinhasBrancas(tabelaG);
    }

    private void listaDadosProducaoGuia(int id_guia) {
        DAOProducaoGuia daopg = new DAOProducaoGuia();
        DAOServicos daos = new DAOServicos();
        List<ProducaoGuia> dados;
        boolean condicao_guia = false;
        boolean condicao_serv = false;
        int kk = 0;

        int qtd_li = tabela.getRowCount();

        try {
            dados = daopg.retornaDados(id_guia);
            if (dados.size() == 0) {
                JOptionPane.showMessageDialog(null, "Não há dados!");
            } else {
                String[] linhas = {null, null, null};
                panelProducao.setVisible(true);
                DecimalFormat df = new DecimalFormat("0.##");
                for (int i = 0; i < dados.size(); i++) {

                    DefaultTableModel dtm = (DefaultTableModel) tabelaP.getModel();
                    dtm.addRow(linhas);
                    tabelaP.setModel(dtm);

                    tabelaP.setValueAt(dados.get(i).getDesc_serv(), i, 0);
                    tabelaP.setValueAt(dados.get(i).getPeso_pdc(), i, 1);

                    for (int j = 0; j < qtd_li; j++) {
                        if (id_g == Integer.parseInt(tabela.getValueAt(j, 0).toString())) {
                            condicao_guia = true;
                            break;
                        }
                    }
                    if (condicao_guia == true) {
                        int id_s = daos.retornaIDPorNome(dados.get(i).getDesc_serv());
                        for (int k = 0; k < qtd_li; k++) {
                            if (id_s == Integer.parseInt(tabela.getValueAt(k, 1).toString())) {
                                condicao_serv = true;
                                kk = k; //pegando o valor da linha onde enconta a ID do serviço
                                break;
                            }
                        }
                    }

                    if (condicao_serv == true) {
                        String s_peso = tabela.getValueAt(kk, 2).toString();
                        s_peso = s_peso.replace(",", ".");
                        double d_peso_memoria = Double.parseDouble(s_peso);
                        double d_peso_retornado = dados.get(i).getSaldo_pdc();

                        d_peso_retornado = d_peso_retornado - d_peso_memoria;
                        tabelaP.setValueAt(df.format(d_peso_retornado), i, 2);
                        condicao_serv = false;
                        condicao_guia = false;
                        kk = 0;
                    } else {
                        tabelaP.setValueAt(df.format(dados.get(i).getSaldo_pdc()), i, 2);
                    }

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasSaidaNovaViagem.class.getName()).log(Level.SEVERE, null, ex);
        }

        ManipulaJTables.removeLinhasBrancas(tabelaP);
    }

    private void btProduzirActionPerformed(java.awt.event.ActionEvent evet) {
        int li = tabelaG.getSelectedRow();
        DAOEmpresas daoe = new DAOEmpresas();
        DAOGuias daog = new DAOGuias();

        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            try {
                String s_emp = tabelaG.getValueAt(li, 0).toString();
                int id_emp = daoe.retornaIDPorNome(s_emp);
                boolean condicao_pos = false;

                if ((id_empresa_selecionada == 0)) {
                    condicao_pos = true;
                } else if ((id_empresa_selecionada != 0) && (id_emp == id_empresa_selecionada)) {
                    condicao_pos = true;
                } else {
                    condicao_pos = false;
                }

                if (condicao_pos == true) {
                    id_empresa_selecionada = id_emp;
                    DefaultTableModel dtmM = (DefaultTableModel) tabelaP.getModel();
                    dtmM.setNumRows(0);

                    String s_num_guia = tabelaG.getValueAt(li, 1).toString();
                    li_guia = li;

                    int id_guia = daog.retornaIdGuiaEmp(s_num_guia, id_emp);
                    id_g = id_guia;

                    listaDadosProducaoGuia(id_guia);

                    btDesfazer.setEnabled(false);
                    btFinalizarTrans.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Algumas guias não são da mesma empresa!");
                }

            } catch (SQLException ex) {
                Logger.getLogger(PGuiasSaidaTransformacaoGuia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void btGerarGuiaActionPerformed(java.awt.event.ActionEvent evnt) {
        int li = tabelaP.getSelectedRow();

        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            String s_peso_dispo = tabelaP.getValueAt(li, 2).toString();

            s_peso_dispo = s_peso_dispo.replace(",", ".");

            double d_peso_disp = Double.parseDouble(s_peso_dispo);

            if (d_peso_disp < 1) {
                JOptionPane.showMessageDialog(null, "Qtd de sacas inválida! Menor ou igual a 0!");
            } else {
                li_prod = li;
                txtGuia.setText(tabelaG.getValueAt(li_guia, 1).toString());
                txtServico.setText(tabelaP.getValueAt(li, 0).toString());
                txtGuia.setEditable(false);
                txtServico.setEditable(false);
                btGerarGuia.setEnabled(false);
                btProduzir.setEnabled(false);
                panelDadosGuia.setVisible(true);
            }

        }
    }

    private void btAddMemoriaActionPerformed(java.awt.event.ActionEvent evt) {
        String s_peso_total = tabelaP.getValueAt(li_prod, 2).toString();
        s_peso_total = s_peso_total.replace(",", ".");
        double d_peso_total = Double.parseDouble(s_peso_total);
        String s_qtd_bag = txtTipoEmbBag.getText().toString();
        String s_qtd_saca = txtTipoEmbSaca.getText().toString();
        int tipo_emb = 0; //1 para bag, 2 granel, 3 sacaria

        String s_peso_atual = txtPeso.getText().toString();
        if (s_peso_atual.isEmpty() || s_peso_atual.equals(null)) {
            JOptionPane.showMessageDialog(null, "Peso nulo ou vazio!");
        } else {
            if (radioTipoEmbBag.isSelected() == true) {
                tipo_emb = 1;
            } else if (radioTipoEmbSaca.isSelected() == true) {
                tipo_emb = 3;
            } else {
                tipo_emb = 2;
            }
            if ((s_qtd_bag.isEmpty() || s_qtd_bag.equals(null)) && (s_qtd_saca.isEmpty() || s_qtd_saca.equals(null)) && tipo_emb != 2) {
                JOptionPane.showMessageDialog(null, "Informe uma qtd de embalagem!");
            } else {
                if (s_qtd_bag.isEmpty() || s_qtd_bag.equals(null)) {
                    s_qtd_bag = "" + 0;
                }
                if (s_qtd_saca.isEmpty() || s_qtd_saca.equals(null)) {
                    s_qtd_saca = "" + 0;
                }

                double d_qtd_bag = Double.parseDouble(s_qtd_bag);
                double d_qtd_saca = Double.parseDouble(s_qtd_saca);
                if ((d_qtd_bag <= 0) && (d_qtd_saca <= 0) && tipo_emb != 2) {
                    JOptionPane.showMessageDialog(null, "Qtde de bag e sacas zeradas!");
                } else {
                    s_peso_atual = s_peso_atual.replace(",", ".");
                    double d_qtd_sacas_atual = Double.parseDouble(s_peso_atual);

                    if (d_qtd_sacas_atual <= 0) {
                        JOptionPane.showMessageDialog(null, "Peso zerado ou inválido!");
                    } else {
                        if (d_qtd_sacas_atual > d_peso_total) {
                            JOptionPane.showMessageDialog(null, "Peso inválido, maior que a produzido!");
                        } else {
                            //  int resp = JOptionPane.showConfirmDialog(null, "Você confirma a transformação da guia?", "Confirmar transformação", JOptionPane.YES_NO_OPTION);
                            //  if (resp == 0) {
                            DAOProducaoGuia daopg = new DAOProducaoGuia();
                            DAOServicos daos = new DAOServicos();
                            String[] linhas = {null, null, null};
                            DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
                            dtm.addRow(linhas);
                            tabela.setModel(dtm);
                            try {
                                String qtd_sacas_aa = txtPeso.getText().toString();
                                qtd_sacas_aa = qtd_sacas_aa.replace(",", ".");
                                int id_serv = daos.retornaIDPorNome(tabelaP.getValueAt(li_prod, 0).toString());
                                double d_peso = Double.parseDouble(qtd_sacas_aa) * 60;
                                int linha_existente = 0;
                                boolean condicao_existe_linha = false; //irá condicionar se já existe uma linha igual criada

                                if (tabela.getRowCount() > 1) {
                                    for (int i = 0; i < tabela.getRowCount(); i++) {
                                        //consulta se ja existe este serviço pra esta guia
                                        if ((id_g == Integer.parseInt(tabela.getValueAt(i, 0).toString())) && (id_serv == Integer.parseInt(tabela.getValueAt(i, 1).toString()))) {
                                            linha_existente = i;
                                            condicao_existe_linha = true;
                                            break;
                                        } else {
                                            break;
                                        }
                                    }
                                }

                                if (condicao_existe_linha == true) { //atualiza qtd de sacas para registro ja existente
                                    double qtd_atual = Double.parseDouble(tabela.getValueAt(linha_existente, 2).toString());
                                    double qtd_baixar = Double.parseDouble(txtPeso.getText().toString());

                                    double d_qtd_atual_bag = Double.parseDouble(tabela.getValueAt(linha_existente, 4).toString());
                                    double d_qtd_baixar_bag = d_qtd_bag;

                                    double d_qtd_atual_saca = Double.parseDouble(tabela.getValueAt(linha_existente, 3).toString());
                                    double d_qtd_baixar_saca = d_qtd_saca;

                                    qtd_baixar = qtd_baixar + qtd_atual;
                                    d_qtd_baixar_bag = d_qtd_baixar_bag + d_qtd_atual_bag;
                                    d_qtd_baixar_saca = d_qtd_baixar_saca + d_qtd_atual_saca;

                                    tabela.setValueAt(qtd_baixar, linha_existente, 2);
                                    tabela.setValueAt(d_qtd_baixar_saca, linha_existente, 3);
                                    tabela.setValueAt(d_qtd_baixar_bag, linha_existente, 4);
                                } else {
                                    tabela.setValueAt(id_g, cont_linhas_tab_mem, 0);
                                    tabela.setValueAt(id_serv, cont_linhas_tab_mem, 1);
                                    tabela.setValueAt(txtPeso.getText().toString(), cont_linhas_tab_mem, 2);
                                    tabela.setValueAt(d_qtd_saca, cont_linhas_tab_mem, 3);
                                    tabela.setValueAt(d_qtd_bag, cont_linhas_tab_mem, 4);
                                    cont_linhas_tab_mem++;
                                }

                                ManipulaJTables.removeLinhasBrancas(tabela);

                                txtPeso.setText("");
                                txtTipoEmbBag.setText("");
                                txtTipoEmbSaca.setText("");
                                btGerarGuia.setEnabled(true);
                                btProduzir.setEnabled(true);
                                panelProducao.setVisible(false);
                                panelDadosGuia.setVisible(false);
                                panelDadosFinal.setVisible(false);

                                btDesfazer.setEnabled(true);
                                btFinalizarTrans.setEnabled(true);
                            } catch (SQLException ex) {
                                Logger.getLogger(PGuiasSaidaTransformacaoGuia.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }

                }
            }

        }

    }

    private void btDesfazerActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja desfazer todas as transformações?", "Desfazer tudo", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
            dtm.setNumRows(0);
            txtPeso.setText("");
            cont_linhas_tab_mem = 0;
            btDesfazer.setEnabled(false);
            btFinalizarTrans.setEnabled(false);
            id_empresa_selecionada = 0;
        }
    }

    private void btFinalizarTransActionPerformed(java.awt.event.ActionEvent evt) {
        int li = tabela.getRowCount();

        if (li <= 0) {
            JOptionPane.showMessageDialog(null, "Não há guias para haver a transformação!");
        } else {
            panelDadosFinal.setVisible(true);
            btFinalizarTrans.setEnabled(false);
            btProduzir.setEnabled(false);
            btDesfazer.setEnabled(false);
        }
    }

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_vlr_saca = txtVlrSaca.getText().toString().trim();
        String s_num_guia = txtNumGuia.getText().toString().trim();

        if (s_vlr_saca.isEmpty() || s_vlr_saca.equals(null) || s_num_guia.isEmpty() || s_num_guia.equals(null)) {
            JOptionPane.showMessageDialog(null, "Preencha os campos!");
        } else {

            try {
                DAOEmpresaGuia daoeg = new DAOEmpresaGuia();
                if (daoeg.consultaGuiaEmp(s_num_guia, id_empresa_selecionada) == true) {
                    JOptionPane.showMessageDialog(null, "Esta guia já existe para esta empresa!");
                } else {
                    s_vlr_saca = s_vlr_saca.replace(",", ".");
                    double d_vlr_saca = Double.parseDouble(s_vlr_saca);

                    if (d_vlr_saca <= 0) {
                        JOptionPane.showMessageDialog(null, "Valor menor ou igual a 0!");
                    } else {
                        int resp = JOptionPane.showConfirmDialog(null, "Confirme a sua transformação antes de prosseguir...  \n" + "Deseja realmente concluir esta transformação?", "Realizar transformação", JOptionPane.YES_NO_OPTION);

                        if (resp == 0) {
                            double qtd_sacas = 0;
                            String s_pesos = "";
                            double d_pesos = 0;
                            String s_data = ManipulaDatas.retornaDataAtual();
                            for (int i = 0; i < tabela.getRowCount(); i++) {
                                //           String qtd_sacas_tab = tabela.getValueAt(i, 3).toString();
                                //             qtd_sacas_tab = qtd_sacas_tab.replace(",", ".");
                                //               qtd_sacas = qtd_sacas + Double.parseDouble(qtd_sacas_tab);
                                s_pesos = tabela.getValueAt(i, 2).toString();
                                s_pesos = s_pesos.replace(",", ".");
                                d_pesos = d_pesos + Double.parseDouble(s_pesos);

                            }
                            qtd_sacas = d_pesos / 60;
                            //instanciando daos
                            DAOGuias daog = new DAOGuias();
                            DAOProdutorGuia daopg = new DAOProdutorGuia();
                            DAOServicosGuia daosg = new DAOServicosGuia();
                            DAOProdutores daop = new DAOProdutores();
                            DAOEmpresas daoe = new DAOEmpresas();
                            DAOServicos daos = new DAOServicos();
                            DAOPdcUnicaGuia daopug = new DAOPdcUnicaGuia();
                            DAOFinanceiro daof = new DAOFinanceiro();
                            //fim instancia daos

                            //instanciando objetos
                            Guias g = new Guias();
                            ProdutorGuia pg = new ProdutorGuia();
                            EmpresaGuia eg = new EmpresaGuia();
                            ServicosGuia sg = new ServicosGuia();
                            PdcUnicaGuia pug = new PdcUnicaGuia();
                            Financeiro f = new Financeiro();
                            //fim instancia objetos

                            int id_guia = daog.retornaUltimaID();
                            double d_peso = qtd_sacas * 60;
                            String s_ano = s_data.substring(6, 10);
                            String s_nosso_num = id_guia + "/" + s_ano;
                            int id_financeiro = daof.retornaUltimaID();
                            String s_dt_atual = ManipulaDatas.retornaDataAtual();

                            //montando objetos
                            //objeto guia
                            g.setDt_entrada_guia(ManipulaDatas.converteStrSql(s_data));
                            g.setNum_guia(s_num_guia);
                            g.setPeso_guia(d_peso);
                            g.setQtd_sacas_guia(qtd_sacas);
                            g.setReferencia_guia(0);
                            g.setStatus_guia(0);
                            g.setUsuario_cad_guia(ManipulaArquivo.lerArquivoUsuario());
                            g.setVlr_total_guia(d_vlr_saca * qtd_sacas);
                            g.setVlr_unit_guia(d_vlr_saca);
                            g.setNosso_num_guia(s_nosso_num);
                            g.setVlr_total_ini_guia(d_vlr_saca * qtd_sacas);
                            g.setNosso_num_guia(s_nosso_num);
                            //fim objeto guia

                            //objeto produtores da guia
                            pg.setId_pdtr_guia(id_guia);
                            pg.setId_pdtr(1); //nenhum produtor
                            pg.setNum_guia(s_num_guia);
                            //fim objeto produtores da guia

                            //objeto empresa da guia
                            eg.setId_emp(id_empresa_selecionada);
                            eg.setId_guia(id_guia);
                            eg.setNum_guia(s_num_guia);
                            //fim objeto empresa da guia

                            //montando o objeto producao unica guia
                            pug.setId_guia(id_guia);
                            pug.setPeso(0);
                            pug.setSaldo(0);
                            daopug.inserir(pug);
                        //fim

                            //montando financeiro
                            f.setCredito(d_vlr_saca * qtd_sacas);
                            f.setDebito(0);
                            f.setDocumento("G" + id_financeiro + s_ano);
                            f.setDocumento_referencia(s_nosso_num);
                            f.setDt_financeiro(ManipulaDatas.converteStrSql(s_dt_atual));
                            f.setTipo_financeiro(1); //contas a receber
                            f.setTipo_operacao(1); //operação para guias
                            f.setVlr_financeiro(d_vlr_saca * qtd_sacas);
                            f.setUsuario_cria(Integer.parseInt(ManipulaArquivo.lerArquivoUsuario().trim()));
                            f.setEmpresa(id_empresa_selecionada);
                            //fim

                            //objeto serviços da guia
                            for (int i = 0; i < tabela.getRowCount(); i++) {
                                int id_serv = Integer.parseInt(tabela.getValueAt(i, 1).toString()); //pegando a id do serviço selecionado

                                sg.setId_guia(id_guia);
                                sg.setId_serv(id_serv);
                                id_serv = 0;
                                daosg.inserir(sg);
                            }
                            //fim objeto serviços da guia

                            int linhax = tabela.getRowCount();
                            for (int i = 0; i < linhax; i++) {
                                String s_qtd_bag = tabela.getValueAt(i, 4).toString();
                                String s_qtd_saca = tabela.getValueAt(i, 3).toString();

                                if (s_qtd_bag.isEmpty() || s_qtd_bag.equals(null)) {
                                    s_qtd_bag = "" + 0;
                                }
                                if (s_qtd_saca.isEmpty() || s_qtd_saca.equals(null)) {
                                    s_qtd_saca = "" + 0;
                                }

                                double d_qtd_bag = Double.parseDouble(s_qtd_bag);
                                double d_qtd_saca = Double.parseDouble(s_qtd_saca);

                                //INICIO ENTRADA
                                DAOViagemEnt daove = new DAOViagemEnt();
                                DAOGuiaViagemEnt daogve = new DAOGuiaViagemEnt();
                                GuiaViagemEnt gve = new GuiaViagemEnt();
                                ViagemEnt ve = new ViagemEnt();
                                DAONfeEnt daone = new DAONfeEnt();
                                NfeEnt ne = new NfeEnt();
                                DAOPdcUnicaGuia daopugg = new DAOPdcUnicaGuia();
                                PdcUnicaGuia pugg = new PdcUnicaGuia();
                                DAOProducaoGuia daopgg = new DAOProducaoGuia();
                                ProducaoGuia pgg = new ProducaoGuia();
                                DAOPdcGuiaData daopgd = new DAOPdcGuiaData(); //armazena historico de producao
                                PdcGuiaData pgd = new PdcGuiaData();

                                int id_viagem_ent = daogve.retornaIdMaisUm(id_guia);
                                String peso_tab = tabela.getValueAt(i, 2).toString();
                                peso_tab = peso_tab.replace(",", ".");
                                double d_peso_filho = Double.parseDouble(peso_tab);
                                int id_guia_pai = Integer.parseInt(tabela.getValueAt(i, 0).toString());
                                int id_tipo_cafe_pai = Integer.parseInt(tabela.getValueAt(i, 1).toString());
                                String s_tipo_cafe_filho = daos.retornaNomePorID(id_tipo_cafe_pai);
                                String num_guia_pai = daog.retornaNumGuia(id_guia_pai);
                                boolean condicao_existe_filho = daopgg.retornaExiste(id_tipo_cafe_pai, id_guia);
                                double d_saldoo = daopugg.retornaSaldoAtualPdc(id_guia);

                                //objeto viagem
                                ve.setDoc_viagem_ent(s_nosso_num + "-E" + id_viagem_ent);
                                ve.setDt_ent(ManipulaDatas.converteStrSql(s_data));
                                ve.setNome_motor_ent("T" + num_guia_pai);
                                ve.setNum_nota("PENDENTE");
                                ve.setPeso_ent(d_peso_filho);
                                ve.setPlaca_cam_ent("");
                                ve.setQtd_sacas_ent(d_peso_filho / 60);
                                ve.setTipo_cafe_ent(s_tipo_cafe_filho);
                                ve.setUsuario_cad_ent(ManipulaArquivo.lerArquivoUsuario());
                                ve.setVlr_frete_total(0);
                                ve.setVlr_frete_unit(0);
                                ve.setTipo_frete_ent(3); //nenhum frete
                                //fim

                                //montando objeto da nfe
                                ne.setDt_nfe_ent(ManipulaDatas.converteStrSql(s_data));
                                ne.setId_guia(id_guia);
                                ne.setId_viagem_ent(id_viagem_ent);
                                ne.setNum_nota("PENDENTE");
                                ne.setStatus(0);
                                ne.setQtd_sacas_ent_fiscal(d_peso_filho/60);
                                ne.setPeso_nfe_ent(d_peso_filho);
                            //fim

                                //alterando o peso para a producao da guia
                                double peso_atual = daopugg.retornaPesoAtualPdc(id_guia); //pegando o peso atual
                                peso_atual = peso_atual + d_peso_filho; //somando com o peso da viagem
                                daopugg.alteraPesoPdc(id_guia, peso_atual); //alterando o peso
                                //fim

                                //montando objeto guia viagem ent
                                gve.setId_guia(id_guia);
                                gve.setId_viagem(id_viagem_ent);
                                gve.setNum_guia(txtNumGuia.getText().toString());
                                //fim

                                //inserções de entrada
                                daogve.inserir(gve);
                                daove.inserir(ve);
                                daone.inserir(ne);
                                //fim

                                //montando objeto de historico de producao
                                pgd.setData_pdc(ManipulaDatas.converteStrSql(s_data));
                                pgd.setId_guia(id_guia);
                                pgd.setPeso(d_peso_filho);
                                pgd.setTipo_cafe(id_tipo_cafe_pai);
                                pgd.setUsuario_cad_pdc(ManipulaArquivo.lerArquivoUsuario());
                                //fim

                                //entrada na produção da guia
                                if (condicao_existe_filho == true) {
                                    int id_pdc = daopgg.retornaIdPorTipoGuia(id_tipo_cafe_pai, id_guia);
                                    double d_peso_atual = daopgg.retornaPeso(id_pdc);
                                    double d_saldo_atual = daopgg.retornaSaldo(id_pdc);

                                    d_peso_atual = d_peso_atual + d_peso_filho;
                                    d_saldo_atual = d_saldo_atual + d_peso_filho;
                                    daopgg.alteraPeso(id_pdc, d_peso_atual);
                                    daopgg.alteraSaldo(id_pdc, d_saldo_atual);

                                    double d_saldo_atual_pug = daopugg.retornaSaldoAtualPdc(id_guia);
                                    daopgd.inserir(pgd); //inserindo historico de producao
                                    daopugg.alteraSaldoPdc(id_guia, d_saldo_atual_pug + d_peso_filho);
                                } else {
                                    ProducaoGuia pgp = new ProducaoGuia();
                                    pgp.setId_guia(id_guia);
                                    pgp.setDt_producao_pdc(ManipulaDatas.converteStrSql(s_data));
                                    pgp.setPeso_pdc(d_peso_filho);
                                    pgp.setQtd_sacas_pdc(d_peso_filho / 60);
                                    pgp.setSaldo_pdc(d_peso_filho);
                                    pgp.setTipo_cafe_pdc(id_tipo_cafe_pai);

                                    double d_saldo_atual_pug = daopugg.retornaSaldoAtualPdc(id_guia);

                                    daopgd.inserir(pgd); //inserindo historico de producao
                                    daopugg.alteraSaldoPdc(id_guia, d_saldo_atual_pug + d_peso_filho);
                                    daopgg.inserir(pgp);
                                }
                                        //fim entrada produção guia

                                //entrada nas embalagens
                                if (d_qtd_bag > 0) {
                                    DAOCtrlEmbEmp daocee = new DAOCtrlEmbEmp();
                                    DAOCtrlEmbViagemEnt daoceve = new DAOCtrlEmbViagemEnt();
                                    CtrlEmbEmp cee = new CtrlEmbEmp();
                                    CtrlEmbViagemEnt ceve = new CtrlEmbViagemEnt();

                                    ceve.setId_guia(id_guia);
                                    ceve.setId_tipo_emb(1);
                                    ceve.setId_viagem(id_viagem_ent);
                                    ceve.setQtd_emb(d_qtd_bag);

                                    double calc_t = daocee.retornaQtdTotal(1, id_empresa_selecionada) + d_qtd_bag;
                                    daocee.alteraQtdTotal(id_empresa_selecionada, 1, calc_t);
                                    daoceve.inserir(ceve);

                                }

                                if (d_qtd_saca > 0) {
                                    DAOCtrlEmbEmp daocee = new DAOCtrlEmbEmp();
                                    DAOCtrlEmbViagemEnt daoceve = new DAOCtrlEmbViagemEnt();
                                    CtrlEmbEmp cee = new CtrlEmbEmp();
                                    CtrlEmbViagemEnt ceve = new CtrlEmbViagemEnt();

                                    ceve.setId_guia(id_guia);
                                    ceve.setId_tipo_emb(3);
                                    ceve.setId_viagem(id_viagem_ent);
                                    ceve.setQtd_emb(d_qtd_saca);

                                    double calc_t = daocee.retornaQtdTotal(3, id_empresa_selecionada) + d_qtd_saca;
                                    daocee.alteraQtdTotal(id_empresa_selecionada, 3, calc_t);
                                    daoceve.inserir(ceve);

                                }

                                //fim
                                //FIM ENTRADA
                                //INICIO SAÍDA
                                //  DAOServicos daos = new DAOServicos();
                                DAOGuiaViagemSai daogvs = new DAOGuiaViagemSai();
                                DAOViagemSai daovs = new DAOViagemSai();
                                ViagemSai vs = new ViagemSai();
                                DAOProducaoGuia daopgs = new DAOProducaoGuia();
                                GuiaViagemSai gvs = new GuiaViagemSai();
                                DAONfeSai daons = new DAONfeSai();
                                NfeSai ns = new NfeSai();
                                TipoCafeViagem tcv = new TipoCafeViagem();
                                DAOTipoCafeViagem daotcv = new DAOTipoCafeViagem();

                                int id_guia_sai = Integer.parseInt(tabela.getValueAt(i, 0).toString());
                                int id_viagem_sai = daogvs.retornaIdMaisUm(id_guia_sai);
                                String nosso_num_sai = daog.retornaNossoNumId(id_guia_sai);

                                //montando objeto guia viagem saida
                                gvs.setId_guia(id_guia_sai);
                                gvs.setId_viagem(id_viagem_sai);
                                gvs.setNum_guia(s_num_guia);
                                //fim

                                //motando objeto viagem saida
                                vs.setDoc_viagem_sai(nosso_num_sai + "-S" + id_viagem_sai);
                                vs.setDt_sai(ManipulaDatas.converteStrSql(s_data));
                                vs.setNome_motor_sai("T" + s_num_guia);
                                vs.setNum_nota("PENDENTE");
                                vs.setPeso_sai(d_peso_filho);
                                vs.setPlaca_cam_sai("");
                                vs.setQtd_sacas_sai(d_peso_filho / 60);
                                vs.setTipo_cafe_sai(id_tipo_cafe_pai);
                                vs.setTipo_frete_sai(3);
                                vs.setUsuario_cad_sai(ManipulaArquivo.lerArquivoUsuario());
                                vs.setVlr_frete_unit(0);
                                vs.setVlr_frete_total(0 * (d_peso_filho / 60));
                                //fim

                                //montando objeto nfe saida
                                ns.setDt_nfe_sai(ManipulaDatas.converteStrSql(s_data));
                                ns.setId_guia(id_guia_sai);
                                ns.setId_viagem_sai(id_viagem_sai);
                                ns.setNum_nota("PENDENTE");
                                ns.setStatus_nfe(0);
                                ns.setQtd_sacas_sai_fiscal(d_peso_filho/60);
                                ns.setPeso_nfe_sai(d_peso_filho);
                                //fim

                                //dando saida no tipo de cafe
                                tcv.setId_guia(id_g);
                                tcv.setId_viagem(id_viagem_sai);
                                tcv.setTipo_viagem(2);
                                tcv.setTipo_cafe(id_tipo_cafe_pai);
                                tcv.setPeso(d_peso_filho);
                                //fim
                                
                                
                                d_peso_filho = d_peso_filho - (d_qtd_bag * 5) - (d_qtd_saca * 0.5);
                                //dando baixa na produção
                                int id_pdc = daopgs.retornaIdPorTipoGuia(id_tipo_cafe_pai, id_guia_sai);
                                double d_saldo_pdc = daopgs.retornaSaldo(id_pdc);
                                d_saldo_pdc = d_saldo_pdc - d_peso_filho;
                                daopgs.alteraSaldo(id_pdc, d_saldo_pdc);
                                        //fim baixa produção

                                //dando baixa no controle de embalagens saída
                                if (d_qtd_bag > 0) {
                                    DAOCtrlEmbEmp daocee = new DAOCtrlEmbEmp();
                                    DAOCtrlEmbViagemSai daocees = new DAOCtrlEmbViagemSai();
                                    CtrlEmbViagemSai cees = new CtrlEmbViagemSai();

                                    cees.setId_guia(id_guia_sai);
                                    cees.setId_viagem(id_viagem_sai);
                                    cees.setId_tipo_emb(1);
                                    cees.setQtd_emb(d_qtd_bag);

                                    double d_qtd_baixada = daocee.retornaQtdBaixada(id_empresa_selecionada, 1);
                                    double calc = d_qtd_baixada + d_qtd_bag;
                                    daocees.inserir(cees);
                                    daocee.alterarQtdBaixadas(1, calc, id_empresa_selecionada);

                                }
                                if (d_qtd_saca > 0) {
                                    DAOCtrlEmbEmp daocee = new DAOCtrlEmbEmp();
                                    DAOCtrlEmbViagemSai daocees = new DAOCtrlEmbViagemSai();
                                    CtrlEmbViagemSai cees = new CtrlEmbViagemSai();

                                    cees.setId_guia(id_guia_sai);
                                    cees.setId_viagem(id_viagem_sai);
                                    cees.setId_tipo_emb(3);
                                    cees.setQtd_emb(d_qtd_saca);

                                    double d_qtd_baixada = daocee.retornaQtdBaixada(id_empresa_selecionada, 3);
                                    double calc = d_qtd_baixada + d_qtd_saca;
                                    daocees.inserir(cees);
                                    daocee.alterarQtdBaixadas(3, calc, id_empresa_selecionada);

                                }
                                //fim da baixa controle de embalagens saida

                                daogvs.inserir(gvs);
                                daons.inserir(ns);
                                daovs.inserir(vs);

                                //FIM SAÍDA
                            }

                            daog.inserir(g);
                            daoeg.inserir(eg);
                            daopg.inserir(pg);
                            daof.inserir(f);

                            JOptionPane.showMessageDialog(null, "GUIA TRANSFORMADA COM SUCESSO!");
                            limpaCampos();

                        }
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(PGuiasSaidaTransformacaoGuia.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar a transformação?", "Cancelar transformação", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            btFinalizarTrans.setEnabled(true);
            btProduzir.setEnabled(true);
            btDesfazer.setEnabled(true);
            txtVlrSaca.setText("");
            txtNumGuia.setText("");
            panelDadosFinal.setVisible(false);
        }
    }

    private void limpaCampos() {
        btFinalizarTrans.setEnabled(false);
        btProduzir.setEnabled(true);
        btDesfazer.setEnabled(false);
        txtVlrSaca.setText("");
        txtNumGuia.setText("");
        panelDadosFinal.setVisible(false);
        panelProducao.setVisible(false);
        DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
        dtm.setNumRows(0);
        DefaultTableModel dtmp = (DefaultTableModel) tabelaP.getModel();
        dtmp.setNumRows(0);
        listaDadosGuias();

    }

    private javax.swing.JTable tabelaG;
    private javax.swing.JScrollPane scrollTG;
    private javax.swing.table.DefaultTableModel modelG;
    private javax.swing.JButton btProduzir;
    private javax.swing.JPanel panelGuias;
    private javax.swing.JTable tabelaP;
    private javax.swing.table.DefaultTableModel modelTP;
    private javax.swing.JScrollPane scrollTP;
    private javax.swing.JPanel panelProducao;
    private javax.swing.JButton btGerarGuia;
    private javax.swing.JLabel lblGuia;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblServico;
    private javax.swing.JTextField txtGuia;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtServico;
    private javax.swing.JButton btAddMemoria;
    private javax.swing.JTable tabela;
    private javax.swing.table.DefaultTableModel modelT;
    private javax.swing.JScrollPane scrollT;
    private javax.swing.JPanel panelDadosGuia;
    private int li_guia = 0;
    private int li_prod = 0;
    private int cont_linhas_tab_mem = 0;
    private int id_g = 0;
    private int id_empresa_selecionada = 0;
    private javax.swing.JButton btDesfazer;
    private javax.swing.JButton btFinalizarTrans;
    private javax.swing.JPanel panelBts;
    private javax.swing.JPanel panelDadosFinal;
    private javax.swing.JLabel lblNumGuia;
    private javax.swing.JTextField txtNumGuia;
    private javax.swing.JLabel lblVlrSaca;
    private javax.swing.JTextField txtVlrSaca;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JRadioButton radioTipoEmbBag;
    private javax.swing.JRadioButton radioTipoEmbSaca;
    private javax.swing.JRadioButton radioTipoEmbGranel;
    private javax.swing.JTextField txtTipoEmbBag;
    private javax.swing.JTextField txtTipoEmbSaca;
    private javax.swing.ButtonGroup grupoBtsTipoEmb;
    private javax.swing.JLabel lblTipoEmb;
}
