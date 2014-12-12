/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.guias.producao;

import classes.ComboBoxModel;
import classes.ManipulaArquivo;
import classes.ManipulaDatas;
import classes.ManipulaJTables;
import classes.NumerosFloatCVirgula;
import dao.cadastros.DAOEmpresas;
import dao.entradas.DAOEmpresaGuia;
import dao.entradas.DAOGuiaViagemEnt;
import dao.entradas.DAOGuias;
import dao.entradas.DAOPdcGuiaData;
import dao.entradas.DAOPdcUnicaGuia;
import dao.entradas.DAOProducaoGuia;
import dao.entradas.DAOServicos;
import gui.guias.entrada.nova.guia.PGuiasEntradaNovaGuia;
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
import model.entradas.PdcGuiaData;
import model.entradas.PdcUnicaGuia;
import model.entradas.ProducaoGuia;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PGuiasProducaoDeCafe extends javax.swing.JPanel {

    public PGuiasProducaoDeCafe() {
        initComponents();
    }

    protected void initComponents() {
        //configuração jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUÇÃO DE CAFÉ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim
        //inicializando componentes jpane principal
        panelConsultar = new javax.swing.JPanel();
        panelTabela = new javax.swing.JPanel();
        panelServ = new javax.swing.JPanel();
        panelProducao = new javax.swing.JPanel();
        //fim

        //configuração componentes jpanel principal
        panelConsultar.setLayout(new MigLayout());
        panelTabela.setLayout(new MigLayout());
        panelServ.setLayout(new MigLayout());
        panelProducao.setLayout(new MigLayout());
        panelConsultar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar guia", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel consultar
        lblNumGuia = new javax.swing.JLabel("Informe o número da guia:");
        txtNumGuia = new javax.swing.JTextField(10);
        btConsultar = new javax.swing.JButton("Consultar");
        lblCliente = new javax.swing.JLabel("Cliente:");
        comboEmpGuia = new javax.swing.JComboBox<>();
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
        String[] columnNames = {"Peso total", "Peso baixado", "Peso disponível"};
        Object[][] data = {};
        modelT = new javax.swing.table.DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabela = new javax.swing.JTable(modelT);
        tabela.setPreferredScrollableViewportSize(new java.awt.Dimension(360, 20));
        tabela.getColumnModel().getColumn(0).setMaxWidth(120);
        tabela.getColumnModel().getColumn(0).setMinWidth(120);
        tabela.getColumnModel().getColumn(1).setMaxWidth(120);
        tabela.getColumnModel().getColumn(1).setMinWidth(120);
        tabela.getColumnModel().getColumn(2).setMaxWidth(120);
        tabela.getColumnModel().getColumn(2).setMinWidth(120);

        scrollT = new javax.swing.JScrollPane(tabela);
        scrollT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesos de entrada", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));

        //alinhando os valores das celulas da Tabela à direita
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);

        tabela.getColumnModel().getColumn(0).setCellRenderer(r);
        tabela.getColumnModel().getColumn(1).setCellRenderer(r);
        tabela.getColumnModel().getColumn(2).setCellRenderer(r);
        //fim

        //adicionando componentes jpanel consultar
        panelConsultar.add(lblNumGuia, "split 2");
        panelConsultar.add(txtNumGuia, "wrap");
        panelConsultar.add(lblCliente,"split 3");
        panelConsultar.add(comboEmpGuia);
        panelConsultar.add(btConsultar);
        //fim

        //inicializando componentes panel tabela
        btProduzir = new javax.swing.JButton("Produzir");
        //fim

        //adicionar componentes jpanel tabela
        panelTabela.add(btProduzir, "wrap");
        //fim

        //inicializando componentes jpanel producao
        lblPesoPdc = new javax.swing.JLabel("Peso:");
        lblTipoCafe = new javax.swing.JLabel("Tipo café:");
        txtPesoPdc = new javax.swing.JTextField(10);
        comboTipoCafe = new javax.swing.JComboBox<>();
        btCancelarPdc = new javax.swing.JButton("Cancelar");
        btSalvarPdc = new javax.swing.JButton("Salvar");
        radioTipoEmbBag = new javax.swing.JRadioButton("Bag");
        radioTipoEmbGranel = new javax.swing.JRadioButton("Granel");
        radioTipoEmbSacaria = new javax.swing.JRadioButton("Sacaria");
        grupoBtsEmb = new javax.swing.ButtonGroup();
        lblTipoEmb = new javax.swing.JLabel("Tipo de embalagem:");
        txtQtdBag = new javax.swing.JTextField(6);
        txtQtdSacaria = new javax.swing.JTextField(6);
        //fim

        //configuração componentes jpanel producao
        try {
            ArrayList servicos = daos.consultarProdutores();
            ComboBoxModel cbm_serv = new ComboBoxModel();
            cbm_serv.montaCombo();
            cbm_serv.listaDadosCombo(servicos);
            comboTipoCafe = cbm_serv;
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasEntradaNovaGuia.class.getName()).log(Level.SEVERE, null, ex);
        }

        grupoBtsEmb.add(radioTipoEmbBag);
        grupoBtsEmb.add(radioTipoEmbGranel);
        grupoBtsEmb.add(radioTipoEmbSacaria);
        radioTipoEmbBag.setSelected(true);

        txtQtdBag.setDocument(new NumerosFloatCVirgula());
        txtQtdSacaria.setDocument(new NumerosFloatCVirgula());

        txtPesoPdc.setDocument(new NumerosFloatCVirgula());
        //fim

        //adicionando jpanel producao
        panelProducao.add(lblPesoPdc, "split 2, gapx 81");
        panelProducao.add(txtPesoPdc, "wrap");
        panelProducao.add(lblTipoEmb, "split 3");
        panelProducao.add(radioTipoEmbBag);
        panelProducao.add(txtQtdBag, "wrap");
        panelProducao.add(radioTipoEmbSacaria, "split 2, gapx 118");
        panelProducao.add(txtQtdSacaria, "wrap");
        panelProducao.add(radioTipoEmbGranel, "wrap, gapx 118");
        panelProducao.add(lblTipoCafe, "split 2, gapx 58");
        panelProducao.add(comboTipoCafe, "wrap");
        panelProducao.add(btCancelarPdc, "split 2");
        panelProducao.add(btSalvarPdc, "gapx 54");
        //fim

        //adicionando componentes jpanel consultar
        this.add(panelConsultar, "wrap");
        this.add(scrollT, "split 2");
        this.add(panelTabela);
        this.add(panelServ, "wrap");
        this.add(panelProducao);
        //fim

        panelTabela.setVisible(false);
        scrollT.setVisible(false);
        panelProducao.setVisible(false);
        panelServ.setVisible(true);

        //evento bts
        btConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btConsultarActionPerformed(e);
            }
        });

        btProduzir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btProduzirActionPerformed(e);
            }
        });

        btCancelarPdc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelarPdcActionPerformed(e);
            }
        });

        btSalvarPdc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btSalvarPdcActionPerformed(e);
            }
        });
        //fim
    }

    protected void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {

        String s_num_guia = txtNumGuia.getText().toString();
        DAOEmpresaGuia daoeg = new DAOEmpresaGuia();
        DAOGuiaViagemEnt daogve = new DAOGuiaViagemEnt();
        DAOGuias daog = new DAOGuias();
        try {
            int id_emp = daoe.retornaIDPorNome(comboEmpGuia.getSelectedItem().toString());
            if (daoeg.consultaGuiaEmp(s_num_guia, id_emp) == false) {
                JOptionPane.showMessageDialog(null, "Essa guia não existe para a empresa " + comboEmpGuia.getSelectedItem().toString());
            } else {
                int id_guia = daog.retornaIdGuiaEmp(s_num_guia, id_emp);
                id_g = id_guia;
                panelTabela.setVisible(true);
                scrollT.setVisible(true);
                listaDados(id_guia);
                btConsultar.setEnabled(false);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PGuiasEntradaNovaViagem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void listaDados(int id_guia) {
        DAOPdcUnicaGuia daopug = new DAOPdcUnicaGuia();
        List<PdcUnicaGuia> dados;
        try {
            dados = daopug.retornaDados(id_guia);
            if (dados.size() == 0) {
                JOptionPane.showMessageDialog(null, "Não há dados!");
            } else {
                String[] linhas = {null, null, null};
                DecimalFormat df = new DecimalFormat("#,##0.00");

                DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
                dtm.addRow(linhas);
                tabela.setModel(dtm);
                double dif_peso = dados.get(0).getPeso() - dados.get(0).getSaldo();
                dif_pesoo = dados.get(0).getSaldo();
                tabela.setValueAt(df.format(dados.get(0).getPeso()), 0, 0);
                tabela.setValueAt(df.format(dif_peso), 0, 1);
                tabela.setValueAt(df.format(dados.get(0).getSaldo()), 0, 2);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasProducaoDeCafe.class.getName()).log(Level.SEVERE, null, ex);
        }

        ManipulaJTables.removeLinhasBrancas(tabela);
    }

    protected void limpaCampos() {
        panelTabela.setVisible(false);
        scrollT.setVisible(false);
        panelProducao.setVisible(false);
        panelServ.setVisible(false);
        DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
        dtm.setNumRows(0);
        id_g = 0;
        dif_pesoo = 0;
        d_saldop = 0;
        btProduzir.setEnabled(true);
        btConsultar.setEnabled(true);
        txtPesoPdc.setText("");
        txtQtdBag.setText("");
        txtQtdSacaria.setText("");
        radioTipoEmbBag.setSelected(true);
    }

    protected void btProduzirActionPerformed(java.awt.event.ActionEvent evt) {
        panelProducao.setVisible(true);
        btProduzir.setEnabled(false);

    }

    protected void btCancelarPdcActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja cancelar a produção?", "Cancelar produção", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            panelProducao.setVisible(false);
            txtPesoPdc.setText("");
            btProduzir.setEnabled(true);
        }
    }

    protected void btSalvarPdcActionPerformed(java.awt.event.ActionEvent evt) {
        DAOServicos daos = new DAOServicos();
        DAOProducaoGuia daopg = new DAOProducaoGuia();
        DAOPdcUnicaGuia daopug = new DAOPdcUnicaGuia();
        DAOPdcGuiaData daopgd = new DAOPdcGuiaData(); //armazena historico de producao
        PdcGuiaData pgd = new PdcGuiaData();
        String s_data = ManipulaDatas.retornaDataAtual();
        String s_peso = txtPesoPdc.getText().toString().trim();
        String s_qtd_bag = txtQtdBag.getText().toString().trim();
        String s_qtd_sacaria = txtQtdSacaria.getText().toString().trim();
        int tipo_emb = -1;
        boolean condicao_embalagens = false;

        if (radioTipoEmbBag.isSelected() == true) {
            tipo_emb = 1;
        } else if (radioTipoEmbSacaria.isSelected() == true) {
            tipo_emb = 3;
        } else {
            tipo_emb = 2;
        }

        if (tipo_emb == 1 && (s_qtd_bag.isEmpty() || s_qtd_bag.equals(null))) {
            condicao_embalagens = false;
            JOptionPane.showMessageDialog(null, "Qtd de bbags nula ou zerada!");
        } else if (tipo_emb == 3 && (s_qtd_sacaria.isEmpty() || s_qtd_sacaria.equals(null))) {
            condicao_embalagens = false;
            JOptionPane.showMessageDialog(null, "Qtd de sacarias nula ou zerada!");
        } else {
            condicao_embalagens = true;
        }

        if (condicao_embalagens == true) {
            if (s_qtd_bag.isEmpty() || s_qtd_bag.equals(null)) {
                s_qtd_bag = "0";
            }
            if (s_qtd_sacaria.isEmpty() || s_qtd_sacaria.equals(null)) {
                s_qtd_sacaria = "0";
            }

            s_qtd_bag = s_qtd_bag.replace(",", ".");
            s_qtd_sacaria = s_qtd_sacaria.replace(",", ".");

            boolean condicao_embalagens_2 = false;
            double d_qtd_bag = Double.parseDouble(s_qtd_bag);
            double d_qtd_sacaria = Double.parseDouble(s_qtd_sacaria);

            if (tipo_emb == 1 && d_qtd_bag <= 0) {
                condicao_embalagens_2 = false;
                JOptionPane.showMessageDialog(null, "Qtd de bbags menor ou igual a zero!");
            } else if (tipo_emb == 3 && d_qtd_sacaria <= 0) {
                condicao_embalagens_2 = false;
                JOptionPane.showMessageDialog(null, "Qtd de sacarias menor ou igual a zero!");
            } else {
                condicao_embalagens_2 = true;
            }
            if (condicao_embalagens_2 == true) {
                if (s_peso.isEmpty() || s_peso.equals(null)) {
                    JOptionPane.showMessageDialog(null, "Peso nulo ou em branco!");
                } else {

                    s_peso = s_peso.replace(",", ".");
                    double d_peso_dif = dif_pesoo;
                    double d_peso = Double.parseDouble(s_peso);
                    double d_peso_bruto = d_peso;
                    d_peso = d_peso - (d_qtd_bag * 5) - (d_qtd_sacaria * 0.5); //liquido

                    if ((d_peso <= 0) || (d_peso > d_peso_dif)) {
                        JOptionPane.showMessageDialog(null, "Peso inválido!");
                    } else {
                        try {
                            int id_serv = daos.retornaIDPorNome(comboTipoCafe.getSelectedItem().toString());
                            boolean condicao_existe = daopg.retornaExiste(id_serv, id_g);
                            double d_saldoo = daopug.retornaSaldoAtualPdc(id_g);
                            d_saldoo = d_saldoo - d_peso;

                            //montando objeto de historico de producao
                            pgd.setData_pdc(ManipulaDatas.converteStrSql(s_data));
                            pgd.setId_guia(id_g);
                            pgd.setPeso(d_peso);
                            pgd.setTipo_cafe(id_serv);
                            pgd.setUsuario_cad_pdc(ManipulaArquivo.lerArquivoUsuario());
                            pgd.setPeso_bruto(d_peso_bruto);
                            //fim

                            int resp = JOptionPane.showConfirmDialog(null, "Você confirma a produção de " + d_peso + "KG de " + comboTipoCafe.getSelectedItem().toString() + "?", "Confirmar produção!", JOptionPane.YES_NO_OPTION);

                            if (resp == 0) {
                                if (condicao_existe == true) {
                                    int id_pdc = daopg.retornaIdPorTipoGuia(id_serv, id_g);
                                    double d_peso_atual = daopg.retornaPeso(id_pdc);
                                    double d_saldo_atual = daopg.retornaSaldo(id_pdc);
                                    double d_peso_bruto_atual = daopg.retornaPesoBruto(id_pdc);
                                    double d_qtd_sacas_atual = daopg.retornaQtdSacas(id_pdc);

                                    d_qtd_sacas_atual = d_qtd_sacas_atual + (d_peso / 60);
                                    d_peso_atual = d_peso_atual + d_peso;
                                    d_saldo_atual = d_saldo_atual + d_peso;
                                    d_peso_bruto_atual = d_peso_bruto_atual + d_peso_bruto;

                                    daopg.alteraPeso(id_pdc, d_peso_atual);
                                    daopg.alteraSaldo(id_pdc, d_saldo_atual);
                                    daopg.alteraPesoBruto(id_pdc, d_peso_bruto_atual);
                                    daopg.alteraQtdSacas(id_pdc, d_qtd_sacas_atual);

                                    daopgd.inserir(pgd); //inserindo historico de producao
                                    daopug.alteraSaldoPdc(id_g, d_saldoo);
                                    JOptionPane.showMessageDialog(null, "Produção de café realizada com sucesso!");

                                    listaDados(id_g);
                                    limpaCampos();
                                } else {
                                    ProducaoGuia pg = new ProducaoGuia();
                                    pg.setId_guia(id_g);
                                    pg.setDt_producao_pdc(ManipulaDatas.converteStrSql(s_data));
                                    pg.setPeso_pdc(d_peso);
                                    pg.setQtd_sacas_pdc(d_peso / 60);
                                    pg.setSaldo_pdc(d_peso);
                                    pg.setTipo_cafe_pdc(id_serv);
                                    pg.setPeso_bruto(d_peso_bruto);

                                    daopgd.inserir(pgd); //inserindo historico de producao
                                    daopug.alteraSaldoPdc(id_g, d_saldoo);
                                    daopg.inserir(pg);
                                    JOptionPane.showMessageDialog(null, "Produção de café realizada com sucesso!");
                                    listaDados(id_g);
                                    limpaCampos();
                                }
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(PGuiasProducaoDeCafe.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            }
        }

    }

    protected javax.swing.JPanel panelConsultar;
    protected javax.swing.JLabel lblNumGuia;
    protected javax.swing.JTextField txtNumGuia;
    protected javax.swing.JComboBox comboEmpGuia;
    protected javax.swing.JLabel lblCliente;
    protected javax.swing.JButton btConsultar;
    protected javax.swing.JTable tabela;
    protected javax.swing.table.DefaultTableModel modelT;
    protected javax.swing.JScrollPane scrollT;
    protected javax.swing.JButton btProduzir;
    protected javax.swing.JPanel panelServ;
    protected javax.swing.JPanel panelProducao;
    protected javax.swing.JLabel lblTipoCafe;
    protected javax.swing.JComboBox comboTipoCafe;
    protected javax.swing.JLabel lblPesoPdc;
    protected javax.swing.JTextField txtPesoPdc;
    protected javax.swing.JButton btCancelarPdc;
    protected javax.swing.JButton btSalvarPdc;
    protected javax.swing.JPanel panelTabela;
    protected javax.swing.JRadioButton radioTipoEmbBag;
    protected javax.swing.JRadioButton radioTipoEmbGranel;
    protected javax.swing.JRadioButton radioTipoEmbSacaria;
    protected javax.swing.ButtonGroup grupoBtsEmb;
    protected javax.swing.JTextField txtQtdBag;
    protected javax.swing.JTextField txtQtdSacaria;
    protected javax.swing.JLabel lblTipoEmb;
    protected int id_g = 0;
    protected double dif_pesoo = 0;
    protected double d_saldop = 0;
    protected DAOEmpresas daoe = new DAOEmpresas();
    protected DAOServicos daos = new DAOServicos();

}
