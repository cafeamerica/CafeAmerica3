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
import dao.entradas.DAOCtrlEmbEmp;
import dao.entradas.DAOCtrlEmbViagemEnt;
import dao.entradas.DAOEmpresaGuia;
import dao.entradas.DAOGuiaViagemEnt;
import dao.entradas.DAOGuias;
import dao.entradas.DAONfeEnt;
import dao.entradas.DAOPdcGuiaData;
import dao.entradas.DAOPdcUnicaGuia;
import dao.entradas.DAOProducaoGuia;
import dao.entradas.DAOServicos;
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
import model.entradas.GuiaViagemEnt;
import model.entradas.Guias;
import model.entradas.NfeEnt;
import model.entradas.PdcGuiaData;
import model.entradas.PdcUnicaGuia;
import model.entradas.ProducaoGuia;
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
public class PGuiasSaidaLigaGuiaExistente extends javax.swing.JPanel {

    public PGuiasSaidaLigaGuiaExistente() {
        initComponents();
    }

    private void initComponents() {
        //configurações jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LIGA ENTRE GUIAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando compontens jpanel principal
        btSelecionarProd = new javax.swing.JButton("Selecionar produção");
        panelBts = new javax.swing.JPanel();
        panelTrans = new javax.swing.JPanel();
        panelGuias = new javax.swing.JPanel();
        //fim

        //configuração componentes jpanel principal
        panelGuias.setLayout(new MigLayout());
        panelGuias.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Guias", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //Tabela config
        String[] columnNames = {"Cliente", "Guia"/*, "Nosso nº"*/};
        Object[][] data = {};
        modelG = new javax.swing.table.DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabelaG = new javax.swing.JTable(modelG);
        tabelaG.setPreferredScrollableViewportSize(new java.awt.Dimension(200, 150));
        tabelaG.getColumnModel().getColumn(0).setMaxWidth(100);
        tabelaG.getColumnModel().getColumn(0).setMinWidth(100);
        tabelaG.getColumnModel().getColumn(1).setMaxWidth(100);
        tabelaG.getColumnModel().getColumn(1).setMinWidth(100);
        //  tabelaG.getColumnModel().getColumn(2).setMaxWidth(100);
        // tabelaG.getColumnModel().getColumn(2).setMinWidth(100);
        //alinhando os valores das celulas da Tabela à direita
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);

        tabelaG.getColumnModel().getColumn(0).setCellRenderer(r);
        tabelaG.getColumnModel().getColumn(1).setCellRenderer(r);
        // tabelaG.getColumnModel().getColumn(2).setCellRenderer(r);
        scrollTG = new javax.swing.JScrollPane(tabelaG);
        //fim

        //Tabela config
        String[] columnNamess = {"Tipo café", "Peso total", "Peso disponível"};
        Object[][] dataa = {};
        modelTP = new javax.swing.table.DefaultTableModel(dataa, columnNamess) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabelaP = new javax.swing.JTable(modelTP);
        tabelaP.setPreferredScrollableViewportSize(new java.awt.Dimension(360, 80));
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

        //configuração jpanel bts
        panelBts.setLayout(new MigLayout());
        //fim

        //inicializando compontens jpanel bts
        btSelecionarGuias = new javax.swing.JButton("Selecionar guias");
        btCancelarAll = new javax.swing.JButton("Cancelar");
        //fim

        //adicionando compontenes jpanel bts
        panelBts.add(btSelecionarGuias, "wrap");
        panelBts.add(btCancelarAll);
        //fim

        //inicializando compontens jpanel guias
        lblGuiaReceber = new javax.swing.JLabel("Guia à receber:");
        lblGuiaRetirar = new javax.swing.JLabel("Guia à retirar:");
        txtGuiaReceber = new javax.swing.JTextField(8);
        txtGuiaRetirar = new javax.swing.JTextField(8);
        //fim

        //adicionando compontens jpanel guias
        panelGuias.add(lblGuiaRetirar, "split 2, gapx 9");
        panelGuias.add(txtGuiaRetirar, "wrap");
        panelGuias.add(lblGuiaReceber, "split 2");
        panelGuias.add(txtGuiaReceber);
        //fim

        //configuração compontenes jpanel guias
        txtGuiaReceber.setEditable(false);
        txtGuiaRetirar.setEditable(false);
        //fim

        //configuração jpanel transformacao
        panelTrans.setLayout(new MigLayout());
        panelTrans.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transformação", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando compontenes jpanel trans
        lblPeso = new javax.swing.JLabel("Peso:");
        lblServ = new javax.swing.JLabel("Serviço:");
        txtPeso = new javax.swing.JTextField(8);
        txtServ = new javax.swing.JTextField(12);
        radioTipoEmbBag = new javax.swing.JRadioButton("Bbags");
        radioTipoEmbGranel = new javax.swing.JRadioButton("Granel");
        radioTipoEmbSaca = new javax.swing.JRadioButton("Sacaria");
        txtTipoEmbBag = new javax.swing.JTextField(6);
        txtTipoEmbSaca = new javax.swing.JTextField(6);
        grupoBtsTipoEmb = new javax.swing.ButtonGroup();
        lblTipoEmb = new javax.swing.JLabel("Tipo de embalagem:");
        btCancelarTrans = new javax.swing.JButton("Cancelar");
        btSalvar = new javax.swing.JButton("Salvar");
        //fim

        //configurações compontens jpanel trans
        txtServ.setEditable(false);
        txtTipoEmbBag.setText("0");
        txtTipoEmbSaca.setText("0");
        grupoBtsTipoEmb.add(radioTipoEmbBag);
        grupoBtsTipoEmb.add(radioTipoEmbGranel);
        grupoBtsTipoEmb.add(radioTipoEmbSaca);
        radioTipoEmbBag.setSelected(true);
        //fim

        //adicionando compontenes jpanel trans
        panelTrans.add(lblPeso, "split 4, gapx 82");
        panelTrans.add(txtPeso);
        panelTrans.add(lblServ);
        panelTrans.add(txtServ, "wrap");
        panelTrans.add(lblTipoEmb, "split 3");
        panelTrans.add(radioTipoEmbBag);
        panelTrans.add(txtTipoEmbBag, "wrap");
        panelTrans.add(radioTipoEmbGranel, "wrap, gapx 118");
        panelTrans.add(radioTipoEmbSaca, "split 2, gapx 118");
        panelTrans.add(txtTipoEmbSaca, "wrap");
        panelTrans.add(btCancelarTrans, "split 2, gapx 80");
        panelTrans.add(btSalvar);
        //fim

        //adicionando componentes jpanel principal
        this.add(scrollTG, "split 2, top");
        this.add(panelBts, "wrap");
        this.add(panelGuias, "split 3, top");
        this.add(scrollTP);
        this.add(btSelecionarProd, "wrap");
        this.add(panelTrans);
        //fim

        listaDadosGuias();
        scrollTP.setVisible(false);
        btSelecionarProd.setVisible(false);
        panelTrans.setVisible(false);

        //evento bts
        btSelecionarGuias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btSelecionarGuiasActionPerformed(e);
            }
        });

        btSelecionarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btSelecionarProducaoActionPerformed(e);
            }
        });

        btCancelarAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelarAllActionPerformed(e);
            }
        });

        btCancelarTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelarTransActionPerformed(e);
            }
        });

        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btSalvarActionPerformed(e);
            }
        });
        //fim
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
                    //  tabelaG.setValueAt(dados.get(i).getNosso_num_guia(), i, 2);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasSaidaTransformacaoGuia.class.getName()).log(Level.SEVERE, null, ex);
        }

        ManipulaJTables.removeLinhasBrancas(tabelaG);
    }

    private void listaDadosProducaoGuia(String num_guia) {
        DAOProducaoGuia daopg = new DAOProducaoGuia();
        DAOServicos daos = new DAOServicos();
        DAOEmpresaGuia daoeg = new DAOEmpresaGuia();
        DAOGuias daog = new DAOGuias();
        List<ProducaoGuia> dados;
        boolean condicao_guia = false;
        boolean condicao_serv = false;
        int id_guia = 0;
        int id_emp = 0;
        int qtd_li = tabelaG.getRowCount();

        try {
            id_emp = daoeg.retornaIdEmp(num_guia);
            id_guia = daog.retornaIdGuiaEmp(num_guia, id_emp);
            dados = daopg.retornaDados(id_guia);
            if (dados.size() == 0) {
                JOptionPane.showMessageDialog(null, "Não há dados!");
            } else {
                String[] linhas = {null, null, null};
                //  panelProducao.setVisible(true);
                // DecimalFormat df = new DecimalFormat("0.##");
                DecimalFormat df = new DecimalFormat("###,###,###.00");
                for (int i = 0; i < dados.size(); i++) {

                    DefaultTableModel dtm = (DefaultTableModel) tabelaP.getModel();
                    dtm.addRow(linhas);
                    tabelaP.setModel(dtm);

                    tabelaP.setValueAt(dados.get(i).getDesc_serv(), i, 0);
                    tabelaP.setValueAt(df.format(dados.get(i).getPeso_pdc()), i, 1);
                    tabelaP.setValueAt(df.format(dados.get(i).getSaldo_pdc()), i, 2);

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PGuiasSaidaNovaViagem.class.getName()).log(Level.SEVERE, null, ex);
        }

        ManipulaJTables.removeLinhasBrancas(tabelaP);
    }

    private void btSelecionarGuiasActionPerformed(java.awt.event.ActionEvent evt) {
        int li = tabelaG.getSelectedRow();

        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Informe uma guia!");
        } else {
            String s_guia = tabelaG.getValueAt(li, 1).toString().trim();
            cont_selec_guias++;
            if (cont_selec_guias == 1) {
                txtGuiaRetirar.setText(s_guia);
            } else if (cont_selec_guias == 2) {
                String s_guia_retirar = txtGuiaRetirar.getText().toString().trim();
                if (s_guia.equals(s_guia_retirar)) {
                    JOptionPane.showMessageDialog(null, "Selecione uma guia diferente!");
                    cont_selec_guias--;
                } else {
                    txtGuiaReceber.setText(s_guia);
                    scrollTP.setVisible(true);
                    btSelecionarProd.setVisible(true);
                    btSelecionarGuias.setEnabled(false);
                    listaDadosProducaoGuia(s_guia_retirar);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }

    private void btSelecionarProducaoActionPerformed(java.awt.event.ActionEvent evt) {
        int li = tabelaP.getSelectedRow();

        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            String s_peso_disponivel = tabelaP.getValueAt(li, 2).toString();
            s_peso_disponivel = s_peso_disponivel.replace(".", "");
            s_peso_disponivel = s_peso_disponivel.replace(",", ".");

            double d_peso_disponivel = Double.parseDouble(s_peso_disponivel);

            if (d_peso_disponivel <= 0) {
                JOptionPane.showMessageDialog(null, "Peso menor ou igual a 0! \n Tente outra produção!");
            } else {
                panelTrans.setVisible(true);
                txtServ.setText(tabelaP.getValueAt(li, 0).toString().trim());
                li_prod = li;
                btSelecionarProd.setEnabled(false);
            }

        }
    }

    private void btCancelarTransActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar esta liga?", "Cancelar liga", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            txtPeso.setText("");
            txtServ.setText("");
            txtTipoEmbBag.setText("0");
            txtTipoEmbSaca.setText("0");
            panelTrans.setVisible(false);
            li_prod = -1;
            btSelecionarProd.setEnabled(true);
        }

    }

    private void btCancelarAllActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar tudo?", "Cancelar tudo", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            limpaCampos();
        }
    }

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_guia_retirar = txtGuiaRetirar.getText().toString().trim();
        String s_guia_receber = txtGuiaReceber.getText().toString().trim();
        String s_serv = txtServ.getText().toString().trim();
        String s_peso = txtPeso.getText().toString().trim();
        String s_dt_atual = ManipulaDatas.retornaDataAtual();
        String s_ano = s_dt_atual.substring(6, 10);
        int i_tipo_emb = -1;

        if (s_peso.isEmpty() || s_peso.equals(null) || s_serv.isEmpty() || s_serv.equals(null)
                || s_guia_receber.isEmpty() || s_guia_receber.equals(null)
                || s_guia_retirar.isEmpty() || s_guia_retirar.equals(null)) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            if (radioTipoEmbBag.isSelected() == true) {
                i_tipo_emb = 1;
            } else if (radioTipoEmbSaca.isSelected() == true) {
                i_tipo_emb = 3;
            } else {
                i_tipo_emb = 2;
            }

            String s_qtd_bbag = txtTipoEmbBag.getText().toString().trim();
            String s_qtd_saca = txtTipoEmbSaca.getText().toString().trim();

            if ((i_tipo_emb == 1) && (s_qtd_bbag.isEmpty() || s_qtd_bbag.equals(null))) {
                JOptionPane.showMessageDialog(null, "Quantidade de big bag nula ou vazia!");
            } else if ((i_tipo_emb == 3) && (s_qtd_saca.isEmpty() || s_qtd_saca.equals(null))) {
                JOptionPane.showMessageDialog(null, "Quantidade de sacaria nula ou vazia!");
            } else {
                s_qtd_bbag = s_qtd_bbag.replace(",", ".");
                s_qtd_saca = s_qtd_saca.replace(",", ".");

                if (s_qtd_bbag.isEmpty() || s_qtd_bbag.equals(null)) {
                    s_qtd_bbag = "0";
                }
                if (s_qtd_saca.isEmpty() || s_qtd_saca.equals(null)) {
                    s_qtd_saca = "0";
                }

                double d_qtd_bbag = Double.parseDouble(s_qtd_bbag);
                double d_qtd_saca = Double.parseDouble(s_qtd_saca);

                if (i_tipo_emb == 1 && d_qtd_bbag <= 0) {
                    JOptionPane.showMessageDialog(null, "Quantidade de big bag inválida!");
                } else if (i_tipo_emb == 3 & d_qtd_saca <= 0) {
                    JOptionPane.showMessageDialog(null, "Quantidade de sacaria inválida!");
                } else {

                    s_peso = s_peso.replace(",", ".");
                    double d_peso = Double.parseDouble(s_peso);
                    if (d_peso <= 0) {
                        JOptionPane.showMessageDialog(null, "Peso inválido! Menor ou igual a 0!");
                    } else {
                        double d_qtd_sacas = (d_peso - (d_qtd_bbag * 5) - (d_qtd_saca * 0.5)) / 60;
                        String s_peso_disp = tabelaP.getValueAt(li_prod, 2).toString();
                        s_peso_disp = s_peso_disp.replace(".", "");
                        System.out.println("PESO DISP 1: " + s_peso_disp);
                        s_peso_disp = s_peso_disp.replace(",", ".");
                        System.out.println("PESO DISP 2: " + s_peso_disp);

                        double d_peso_disp = Double.parseDouble(s_peso_disp);

                        if (d_peso_disp < (d_peso - (d_qtd_bbag * 5) - (d_qtd_saca * 0.5))) {
                            JOptionPane.showMessageDialog(null, "Peso maior que o peso disponível para a produção!");
                        } else {

                                int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente realizar a liga entre guias?", "Realizar liga", JOptionPane.YES_NO_OPTION);

                                if (resp == 0) {
                                    try {
                                        //INICIO ENTRADA
                                        DAOFinanceiro daof = new DAOFinanceiro();
                                        Financeiro f = new Financeiro();
                                        DAOGuias daog = new DAOGuias();
                                        DAOEmpresaGuia daoeg = new DAOEmpresaGuia();
                                        ViagemEnt ve = new ViagemEnt();
                                        DAOViagemEnt daove = new DAOViagemEnt();
                                        DAONfeEnt daone = new DAONfeEnt();
                                        NfeEnt ne = new NfeEnt();
                                        DAOGuiaViagemEnt daogve = new DAOGuiaViagemEnt();
                                        GuiaViagemEnt gve = new GuiaViagemEnt();
                                        DAOPdcUnicaGuia daopugg = new DAOPdcUnicaGuia();
                                        PdcUnicaGuia pugg = new PdcUnicaGuia();
                                        DAOPdcGuiaData daopgd = new DAOPdcGuiaData(); //armazena historico de producao
                                        PdcGuiaData pgd = new PdcGuiaData();
                                        DAOServicos daos = new DAOServicos();
                                        DAOProducaoGuia daopgg = new DAOProducaoGuia();
                                        ProducaoGuia pgg = new ProducaoGuia();

                                        int id_financeiro = daof.retornaUltimaID();
                                        String s_nosso_num_receber = daog.retornaNossoNumGuia(s_guia_receber);
                                        int id_emp = daoeg.retornaIdEmp(s_guia_receber);
                                        int id_guia_receber = daog.retornaIdGuiaEmp(s_guia_receber, id_emp);
                                        int id_viagem_ent = daogve.retornaIdMaisUm(id_guia_receber);
                                        int id_tipo_cafe = daos.retornaIDPorNome(s_serv);
                                        boolean condicao_existe_servico = daopgg.retornaExiste(id_tipo_cafe, id_guia_receber);
                                        
                                        
                                        

                                       
                                        //objeto viagem entrada
                                        ve.setDoc_viagem_ent(s_nosso_num_receber + "-E" + id_viagem_ent);
                                        ve.setDt_ent(ManipulaDatas.converteStrSql(s_dt_atual));
                                        ve.setNome_motor_ent("T" + s_guia_retirar);
                                        ve.setNum_nota("PENDENTE");
                                        ve.setPeso_ent(d_peso);
                                        ve.setPlaca_cam_ent("");
                                        ve.setQtd_sacas_ent(d_qtd_sacas);
                                        ve.setTipo_cafe_ent(s_serv);
                                        ve.setUsuario_cad_ent(ManipulaArquivo.lerArquivoUsuario());
                                        ve.setVlr_frete_total(0);
                                        ve.setVlr_frete_unit(0);
                                        ve.setTipo_frete_ent(3); //nenhum frete
                                        //fim

                                        //montando objeto da nfe
                                        ne.setDt_nfe_ent(ManipulaDatas.converteStrSql(s_dt_atual));
                                        ne.setId_guia(id_guia_receber);
                                        ne.setId_viagem_ent(id_viagem_ent);
                                        ne.setNum_nota("PENDENTE");
                                        ne.setStatus(0);
                                        ne.setPeso_nfe_ent(d_peso);
                                        ne.setQtd_sacas_ent_fiscal(d_qtd_sacas);
                                        //fim

                                        //alterando o peso para a producao da guia
                                        double peso_atual = daopugg.retornaPesoAtualPdc(id_guia_receber); //pegando o peso atual
                                        peso_atual = peso_atual + d_peso; //somando com o peso da viagem
                                        daopugg.alteraPesoPdc(id_guia_receber, peso_atual); //alterando o peso

                                        double saldo_atual = daopugg.retornaSaldoAtualPdc(id_guia_receber);
                                        saldo_atual = saldo_atual + d_peso;
                                        daopugg.alteraSaldoPdc(id_guia_receber, saldo_atual);
                                        //fim

                                        //montando objeto guia viagem ent
                                        gve.setId_guia(id_guia_receber);
                                        gve.setId_viagem(id_viagem_ent);
                                        gve.setNum_guia(s_guia_receber);
                                        //fim

                                        //inserções de entrada
                                        daogve.inserir(gve);
                                        daove.inserir(ve);
                                        daone.inserir(ne);
                                        //fim
                                        
                                        //alterando qtd sacas a receber
                                        double d_qtd_sacas_receber = daog.retornaQtdSacas(id_guia_receber);
                                        d_qtd_sacas_receber = d_qtd_sacas_receber + d_qtd_sacas;
                                        daog.alterarQtdSacas(d_qtd_sacas_receber, id_guia_receber);
                                        //fim

                                        //montando objeto de historico de producao
                                        pgd.setData_pdc(ManipulaDatas.converteStrSql(s_dt_atual));
                                        pgd.setId_guia(id_guia_receber);
                                        pgd.setPeso(d_peso);
                                        pgd.setTipo_cafe(id_tipo_cafe);
                                        pgd.setUsuario_cad_pdc(ManipulaArquivo.lerArquivoUsuario());
                                        //fim

                                        //entrada na produção da guia
                                        if (condicao_existe_servico == true) {
                                            int id_pdc = daopgg.retornaIdPorTipoGuia(id_tipo_cafe, id_guia_receber);
                                            double d_peso_atual = daopgg.retornaPeso(id_pdc);
                                            double d_saldo_atual = daopgg.retornaSaldo(id_pdc);

                                            d_peso_atual = d_peso_atual + d_peso - (d_qtd_bbag * 5) - (d_qtd_saca * 0.5);
                                            d_saldo_atual = d_saldo_atual + d_peso - (d_qtd_bbag * 5) - (d_qtd_saca * 0.5);
                                            daopgg.alteraPeso(id_pdc, d_peso_atual);
                                            daopgg.alteraSaldo(id_pdc, d_saldo_atual);

                                            double d_saldo_atual_pug = daopugg.retornaSaldoAtualPdc(id_guia_receber);
                                            daopgd.inserir(pgd); //inserindo historico de producao
                                            daopugg.alteraSaldoPdc(id_guia_receber, d_saldo_atual_pug + d_peso);
                                        } else {
                                            ProducaoGuia pgp = new ProducaoGuia();
                                            pgp.setId_guia(id_guia_receber);
                                            pgp.setDt_producao_pdc(ManipulaDatas.converteStrSql(s_dt_atual));
                                            pgp.setPeso_pdc(d_peso - (d_qtd_bbag * 5) - (d_qtd_saca * 0.5));
                                            pgp.setQtd_sacas_pdc(d_peso - (d_qtd_bbag * 5) - (d_qtd_saca * 0.5) / 60);
                                            pgp.setSaldo_pdc(d_peso - (d_qtd_bbag * 5) - (d_qtd_saca * 0.5));
                                            pgp.setTipo_cafe_pdc(id_tipo_cafe);

                                            double d_saldo_atual_pug = daopugg.retornaSaldoAtualPdc(id_guia_receber);

                                            daopgd.inserir(pgd); //inserindo historico de producao
                                            daopugg.alteraSaldoPdc(id_guia_receber, d_saldo_atual_pug + d_peso - (d_qtd_bbag * 5) - (d_qtd_saca * 0.5));
                                            daopgg.inserir(pgp);
                                        }
                                        //fim entrada produção guia

                                        //entrada nas embalagens
                                        if (d_qtd_bbag > 0) {
                                            DAOCtrlEmbEmp daocee = new DAOCtrlEmbEmp();
                                            DAOCtrlEmbViagemEnt daoceve = new DAOCtrlEmbViagemEnt();
                                            CtrlEmbEmp cee = new CtrlEmbEmp();
                                            CtrlEmbViagemEnt ceve = new CtrlEmbViagemEnt();

                                            ceve.setId_guia(id_guia_receber);
                                            ceve.setId_tipo_emb(1);
                                            ceve.setId_viagem(id_viagem_ent);
                                            ceve.setQtd_emb(d_qtd_bbag);

                                            double calc_t = daocee.retornaQtdTotal(1, id_emp) + d_qtd_bbag;
                                            daocee.alteraQtdTotal(id_emp, 1, calc_t);
                                            daoceve.inserir(ceve);

                                        }

                                        if (d_qtd_saca > 0) {
                                            DAOCtrlEmbEmp daocee = new DAOCtrlEmbEmp();
                                            DAOCtrlEmbViagemEnt daoceve = new DAOCtrlEmbViagemEnt();
                                            CtrlEmbEmp cee = new CtrlEmbEmp();
                                            CtrlEmbViagemEnt ceve = new CtrlEmbViagemEnt();

                                            ceve.setId_guia(id_guia_receber);
                                            ceve.setId_tipo_emb(3);
                                            ceve.setId_viagem(id_viagem_ent);
                                            ceve.setQtd_emb(d_qtd_saca);

                                            double calc_t = daocee.retornaQtdTotal(3, id_emp) + d_qtd_saca;
                                            daocee.alteraQtdTotal(id_emp, 3, calc_t);
                                            daoceve.inserir(ceve);
                                        }

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

                                        int id_guia_sai = daog.retornaIdGuiaEmp(s_guia_retirar, id_emp);
                                        int id_viagem_sai = daogvs.retornaIdMaisUm(id_guia_sai);
                                        String nosso_num_sai = daog.retornaNossoNumId(id_guia_sai);

                                        //montando objeto guia viagem saida
                                        gvs.setId_guia(id_guia_sai);
                                        gvs.setId_viagem(id_viagem_sai);
                                        gvs.setNum_guia(s_guia_retirar);
                                        //fim

                                        //motando objeto viagem saida
                                        vs.setDoc_viagem_sai(nosso_num_sai + "-S" + id_viagem_sai);
                                        vs.setDt_sai(ManipulaDatas.converteStrSql(s_dt_atual));
                                        vs.setNome_motor_sai("T" + s_guia_receber);
                                        vs.setNum_nota("PENDENTE");
                                        vs.setPeso_sai(d_peso);
                                        vs.setPlaca_cam_sai("");
                                        vs.setQtd_sacas_sai(d_qtd_sacas);
                                        vs.setTipo_cafe_sai(id_tipo_cafe);
                                        vs.setTipo_frete_sai(3);
                                        vs.setUsuario_cad_sai(ManipulaArquivo.lerArquivoUsuario());
                                        vs.setVlr_frete_unit(0);
                                        vs.setVlr_frete_total(0 * (d_qtd_sacas));
                                        //fim

                                        //montando objeto nfe saida
                                        ns.setDt_nfe_sai(ManipulaDatas.converteStrSql(s_dt_atual));
                                        ns.setId_guia(id_guia_sai);
                                        ns.setId_viagem_sai(id_viagem_sai);
                                        ns.setNum_nota("PENDENTE");
                                        ns.setStatus_nfe(0);
                                        ns.setQtd_sacas_sai_fiscal(d_qtd_sacas);
                                        ns.setPeso_nfe_sai(d_peso);
                                        //fim

                                        //dando saida no tipo de cafe
                                        tcv.setId_guia(id_guia_sai);
                                        tcv.setId_viagem(id_viagem_sai);
                                        tcv.setTipo_viagem(2);
                                        tcv.setTipo_cafe(id_tipo_cafe);
                                        tcv.setPeso(d_peso);
                                        //fim

                                        d_peso = d_peso - (d_qtd_bbag * 5) - (d_qtd_saca * 0.5);
                                        //dando baixa na produção
                                        int id_pdc = daopgs.retornaIdPorTipoGuia(id_tipo_cafe, id_guia_sai);
                                        double d_saldo_pdc = daopgs.retornaSaldo(id_pdc);
                                        d_saldo_pdc = d_saldo_pdc - d_peso;
                                        daopgs.alteraSaldo(id_pdc, d_saldo_pdc);
                                        //fim baixa produção

                                        //dando baixa no controle de embalagens saída
                                        if (d_qtd_bbag > 0) {
                                            DAOCtrlEmbEmp daocee = new DAOCtrlEmbEmp();
                                            DAOCtrlEmbViagemSai daocees = new DAOCtrlEmbViagemSai();
                                            CtrlEmbViagemSai cees = new CtrlEmbViagemSai();

                                            cees.setId_guia(id_guia_sai);
                                            cees.setId_viagem(id_viagem_sai);
                                            cees.setId_tipo_emb(1);
                                            cees.setQtd_emb(d_qtd_bbag);

                                            double d_qtd_baixada = daocee.retornaQtdBaixada(id_emp, 1);
                                            double calc = d_qtd_baixada + d_qtd_bbag;
                                            daocees.inserir(cees);
                                            daocee.alterarQtdBaixadas(1, calc, id_emp);

                                        }
                                        if (d_qtd_saca > 0) {
                                            DAOCtrlEmbEmp daocee = new DAOCtrlEmbEmp();
                                            DAOCtrlEmbViagemSai daocees = new DAOCtrlEmbViagemSai();
                                            CtrlEmbViagemSai cees = new CtrlEmbViagemSai();

                                            cees.setId_guia(id_guia_sai);
                                            cees.setId_viagem(id_viagem_sai);
                                            cees.setId_tipo_emb(3);
                                            cees.setQtd_emb(d_qtd_saca);

                                            double d_qtd_baixada = daocee.retornaQtdBaixada(id_emp, 3);
                                            double calc = d_qtd_baixada + d_qtd_saca;
                                            daocees.inserir(cees);
                                            daocee.alterarQtdBaixadas(3, calc, id_emp);

                                        }
                                        //fim da baixa controle de embalagens saida

                                        daogvs.inserir(gvs);
                                        daons.inserir(ns);
                                        daovs.inserir(vs);

                                        //FIM SAÍDA
                                        JOptionPane.showMessageDialog(null, "Liga entre guias realizada com sucesso!");
                                        limpaCampos();

                                    } catch (SQLException ex) {
                                        Logger.getLogger(PGuiasSaidaLigaGuiaExistente.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                

                            }
                        }

                    }

                }
            }
        }
    }

    private void limpaCampos() {
        txtGuiaReceber.setText("");
        txtGuiaRetirar.setText("");
        txtPeso.setText("");
        txtServ.setText("");
        txtTipoEmbBag.setText("0");
        txtTipoEmbSaca.setText("0");
        btSelecionarProd.setVisible(false);
        panelTrans.setVisible(false);
        scrollTP.setVisible(false);
        cont_selec_guias = 0;
        btSelecionarGuias.setEnabled(true);
        DefaultTableModel dtm = (DefaultTableModel)tabelaP.getModel();
        dtm.setNumRows(0);
        ManipulaJTables.removeLinhasBrancas(tabelaP);
        li_prod = -1;
        btSelecionarProd.setEnabled(true);
    }

    private javax.swing.JTable tabelaG;
    private javax.swing.JScrollPane scrollTG;
    private javax.swing.table.DefaultTableModel modelG;
    private javax.swing.JTable tabelaP;
    private javax.swing.table.DefaultTableModel modelTP;
    private javax.swing.JScrollPane scrollTP;
    private javax.swing.JButton btSelecionarGuias;
    private javax.swing.JButton btSelecionarProd;
    private javax.swing.JLabel lblGuiaRetirar;
    private javax.swing.JLabel lblGuiaReceber;
    private javax.swing.JTextField txtGuiaRetirar;
    private javax.swing.JTextField txtGuiaReceber;
    private javax.swing.JPanel panelGuias;
    private javax.swing.JPanel panelTrans;
    private javax.swing.JPanel panelBts;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JLabel lblServ;
    private javax.swing.JTextField txtServ;
    private javax.swing.JRadioButton radioTipoEmbBag;
    private javax.swing.JRadioButton radioTipoEmbSaca;
    private javax.swing.JRadioButton radioTipoEmbGranel;
    private javax.swing.JTextField txtTipoEmbBag;
    private javax.swing.JTextField txtTipoEmbSaca;
    private javax.swing.ButtonGroup grupoBtsTipoEmb;
    private javax.swing.JLabel lblTipoEmb;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btCancelarTrans;
    private javax.swing.JButton btCancelarAll;
    private int cont_selec_guias = 0;
    private int li_prod = -1;
}
