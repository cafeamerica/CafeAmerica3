/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.guias.saida.nova.viagem;

import classes.ComboBoxModel;
import classes.ManipulaArquivo;
import classes.ManipulaDatas;
import classes.ManipulaJTables;
import classes.NumerosFloatCVirgula;
import dao.cadastros.DAOEmpresas;
import dao.cadastros.DAOUsuarios;
import dao.entradas.DAOCtrlEmbEmp;
import dao.entradas.DAOEmpresaGuia;
import dao.entradas.DAOGuiaViagemEnt;
import dao.entradas.DAOGuias;
import dao.entradas.DAONfeEnt;
import dao.entradas.DAOPdcGuiaData;
import dao.entradas.DAOPdcUnicaGuia;
import dao.entradas.DAOProducaoGuia;
import dao.entradas.DAOServicos;
import dao.entradas.DAOTrabViagem;
import dao.entradas.DAOViagemEnt;
import dao.financeiro.DAOFinanceiro;
import dao.saidas.DAOCtrlEmbViagemSai;
import dao.saidas.DAOGuiaViagemSai;
import dao.saidas.DAONfeSai;
import dao.saidas.DAOTipoCafeViagem;
import dao.saidas.DAOViagemSai;
import gui.guias.entrada.nova.guia.PGuiasEntradaNovaGuia;
import gui.guias.entrada.nova.viagem.PGuiasEntradaNovaViagem;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import model.entradas.PdcGuiaData;
import model.entradas.ProducaoGuia;
import model.entradas.TrabViagem;
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
public class PGuiasSaidaNovaViagem extends javax.swing.JPanel {

    public PGuiasSaidaNovaViagem() {
        initComponents();
    }

    private void initComponents() {
        //configuração jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SAÍDA DE CAFÉ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim
        //inicializando componentes jpane principal
        panelConsultar = new javax.swing.JPanel();
        btDarSaida = new javax.swing.JButton("Saída");
        panelFrete = new javax.swing.JPanel();
        panelViagem = new javax.swing.JPanel();
        panelChapas = new javax.swing.JPanel();
        panelFiscal = new javax.swing.JPanel();
        panelEsq = new javax.swing.JPanel();
        btExcluirChapa = new javax.swing.JButton("Excluir");
        btExcluirVol = new javax.swing.JButton("X");
        panelInfo = new javax.swing.JPanel();
        //fim

        //configuração componentes jpanel principal
        panelEsq.setLayout(new MigLayout());
        panelInfo.setLayout(new MigLayout());
        panelConsultar.setLayout(new MigLayout());
        panelConsultar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar guia", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelFrete.setLayout(new MigLayout());
        panelFiscal.setLayout(new MigLayout());
        panelFiscal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados fiscais", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelViagem.setLayout(new MigLayout());
        panelChapas.setLayout(new MigLayout());
        //  panelConsultar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar guia", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelFrete.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do frete", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelViagem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados de saída real", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelChapas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar chapa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelChapas.setVisible(false);
        //fim

        //inicializando componentes jpanel consultar
        lblNumGuia = new javax.swing.JLabel("Informe o número da guia:");
        txtNumGuia = new javax.swing.JTextField(10);
        btConsultar = new javax.swing.JButton("Consultar");
        comboEmpGuia = new javax.swing.JComboBox<>();
        lblFiscal = new javax.swing.JLabel("Saída fiscal");
        checkBoxFiscal = new javax.swing.JCheckBox();
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
        String[] columnNames = {"Tipo café", "Peso total", "Peso disponível", "Peso atual"};
        Object[][] data = {};
        modelT = new javax.swing.table.DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabela = new javax.swing.JTable(modelT);
        tabela.setPreferredScrollableViewportSize(new java.awt.Dimension(480, 77));
        tabela.getColumnModel().getColumn(0).setMaxWidth(120);
        tabela.getColumnModel().getColumn(0).setMinWidth(120);
        tabela.getColumnModel().getColumn(1).setMaxWidth(120);
        tabela.getColumnModel().getColumn(1).setMinWidth(120);
        tabela.getColumnModel().getColumn(2).setMaxWidth(120);
        tabela.getColumnModel().getColumn(2).setMinWidth(120);
        tabela.getColumnModel().getColumn(3).setMaxWidth(120);
        tabela.getColumnModel().getColumn(3).setMinWidth(120);

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

        //Tabela de volume
        String[] columnNamesv = {"Tipo", "Bag", "Sacaria", "Peso"};
        Object[][] datav = {};
        modelV = new javax.swing.table.DefaultTableModel(datav, columnNamesv) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabelaV = new javax.swing.JTable(modelV);
        tabelaV.setPreferredScrollableViewportSize(new java.awt.Dimension(360, 77));
        tabelaV.getColumnModel().getColumn(0).setMaxWidth(160);
        tabelaV.getColumnModel().getColumn(0).setMinWidth(160);
        tabelaV.getColumnModel().getColumn(1).setMaxWidth(60);
        tabelaV.getColumnModel().getColumn(1).setMinWidth(60);
        tabelaV.getColumnModel().getColumn(2).setMaxWidth(60);
        tabelaV.getColumnModel().getColumn(2).setMinWidth(60);
        tabelaV.getColumnModel().getColumn(3).setMaxWidth(80);
        tabelaV.getColumnModel().getColumn(3).setMinWidth(80);

        scrollV = new javax.swing.JScrollPane(tabelaV);
        scrollV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Volumes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));

        tabelaV.getColumnModel().getColumn(0).setCellRenderer(r);
        tabelaV.getColumnModel().getColumn(1).setCellRenderer(r);
        tabelaV.getColumnModel().getColumn(2).setCellRenderer(r);
        tabelaV.getColumnModel().getColumn(3).setCellRenderer(r);

        //adicionando componentes jpanel consultar
        panelConsultar.add(lblNumGuia, "split 2");
        panelConsultar.add(txtNumGuia, "wrap");
        panelConsultar.add(comboEmpGuia, "wrap");
        panelConsultar.add(lblFiscal, "split 3");
        panelConsultar.add(checkBoxFiscal);
        panelConsultar.add(btConsultar);
        //fim

        //inicializando componentes jpanel frete
        lblNomeMotor = new javax.swing.JLabel("Motorista:");

        lblPlacaCam = new javax.swing.JLabel("Placa:");
        lblTipoFrete = new javax.swing.JLabel("Tipo frete:");
        txtNomeMotor = new javax.swing.JTextField(12);
        txtPlacaCam = new javax.swing.JTextField(7);
        radioTipoFreteCA = new javax.swing.JRadioButton("Café América");
        radioTipoFreteEmp = new javax.swing.JRadioButton("Empresa");
        grupoBtsFrete = new javax.swing.ButtonGroup();
        txtVlrFrete = new javax.swing.JTextField(6);
        //fim

        //configuração componentes jpanel frete
        grupoBtsFrete.add(radioTipoFreteCA);
        grupoBtsFrete.add(radioTipoFreteEmp);
        radioTipoFreteEmp.setSelected(true);
        txtVlrFrete.setDocument(new NumerosFloatCVirgula());
        txtVlrFrete.setText("0");
        //fim

        //adicionando componentes jpanel frete
        panelFrete.add(lblNomeMotor, "split 2");
        panelFrete.add(txtNomeMotor, "wrap");
        panelFrete.add(lblPlacaCam, "split 2, gapx 23");
        panelFrete.add(txtPlacaCam, "wrap");
        panelFrete.add(lblTipoFrete, "split 3, gapx 1");
        panelFrete.add(radioTipoFreteCA);
        panelFrete.add(txtVlrFrete, "wrap");
        panelFrete.add(radioTipoFreteEmp, "gapx 63");
        //fim

        //inicializando componentes jpanel fiscal
        txtQtdSacasFiscal = new javax.swing.JTextField(7);
        lblQtdSacasFiscal = new javax.swing.JLabel("Qtd sacas NFe:");
        txtPesoFiscal = new javax.swing.JTextField(7);
        lblPesoFiscal = new javax.swing.JLabel("Peso fiscal:");
        lblNumNota = new javax.swing.JLabel("Nº NFe:");
        txtNumNota = new javax.swing.JTextField(7);
        //fim

        //adicionando components jpanel fiscal
        panelFiscal.add(lblNumNota, "split 2, gapx 46");
        panelFiscal.add(txtNumNota, "wrap");
        panelFiscal.add(lblQtdSacasFiscal, "split 2");
        panelFiscal.add(txtQtdSacasFiscal, "wrap");
        panelFiscal.add(lblPesoFiscal, "split 2, gapx 19");
        panelFiscal.add(txtPesoFiscal);
        //fim

        //inicializando componentes jpanel viagem
        lblVolReal = new javax.swing.JLabel("Volume real:");
        txtVolReal = new javax.swing.JTextField(6);
        lblDocViagem = new javax.swing.JLabel("Documento:");
        txtDocViagem = new javax.swing.JTextField(8);
        txtQtdBag = new javax.swing.JTextField(6);
        txtQtdSacas = new javax.swing.JTextField(6);
        lblPesoReal = new javax.swing.JLabel("Peso real:");
        txtPesoReal = new javax.swing.JTextField(7);
        btNovoChapa = new javax.swing.JButton("Novo chapa");
        btFinalizarViagem = new javax.swing.JButton("Finalizar viagem");
        btCancelarViagem = new javax.swing.JButton("Cancelar");
        lblQtdBag = new javax.swing.JLabel("Qtd de bbags:");
        lblQtdSacas = new javax.swing.JLabel("Qtd de sacas:");
        lblData = new javax.swing.JLabel("Data:");
        txtData = new javax.swing.JFormattedTextField();
        lblObs = new javax.swing.JLabel("Obs:");
        txtObs = new javax.swing.JTextField(35);
        grupoBtsTipoEmb = new javax.swing.ButtonGroup();
        radioTipoEmbBag = new javax.swing.JRadioButton("BigBag");
        radioTipoEmbGranel = new javax.swing.JRadioButton("Granel");
        radioTipoEmbSacaria = new javax.swing.JRadioButton("Sacaria");
        lblTipoVol = new javax.swing.JLabel("Volume:");
        comboTipoCafe = new javax.swing.JComboBox<>();
        lblTipoCafe = new javax.swing.JLabel("Tipo café:");
        btAddVolume = new javax.swing.JButton("Add volume");
        lblPesoVol = new javax.swing.JLabel("Peso volume:");
        txtPesoVol = new javax.swing.JTextField(7);
        //fim

        //configuração componentes jpanel viagem
        txtPesoVol.setDocument(new NumerosFloatCVirgula());
        txtDocViagem.setEditable(false);
        txtVolReal.setDocument(new NumerosFloatCVirgula());
        txtPesoReal.setDocument(new NumerosFloatCVirgula());
        txtPesoReal.setEditable(false);
        //  txtPesoReal.setText("");
        // txtVolReal.setText("");
        txtVolReal.setEditable(false);
        txtQtdBag.setDocument(new NumerosFloatCVirgula());
        txtQtdSacas.setDocument(new NumerosFloatCVirgula());
        txtQtdSacasFiscal.setDocument(new NumerosFloatCVirgula());
        txtQtdBag.setText("0");
        txtQtdSacas.setText("0");
        grupoBtsTipoEmb.add(radioTipoEmbBag);
        grupoBtsTipoEmb.add(radioTipoEmbGranel);
        grupoBtsTipoEmb.add(radioTipoEmbSacaria);
        radioTipoEmbBag.setSelected(true);
        try {
            txtData.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
            txtData.setPreferredSize(new java.awt.Dimension(70, 24));
            //fim
        } catch (ParseException ex) {
            Logger.getLogger(PGuiasEntradaNovaViagem.class.getName()).log(Level.SEVERE, null, ex);
        }

        ComboBoxModel cbm_serv = new ComboBoxModel();
        cbm_serv.montaCombo();
        comboTipoCafe = cbm_serv;

        //fim
        //adicionando componentes jpanel viagem
        //esta parte foi implementada no metodo constroiPanelViagem(int status_check_fiscal);
        //fim
        //inicializando compoentes panel cadastro chapas
        lblNomeChapa = new javax.swing.JLabel("Nome:");
        lblVlrSaca = new javax.swing.JLabel("Valor p/ saca:");
        lblEmpChapa = new javax.swing.JLabel("Empresa:");
        lblQtdSaca = new javax.swing.JLabel("Qtd de saca:");
        txtNomeChapa = new javax.swing.JTextField(12);
        txtVlrSaca = new javax.swing.JTextField(6);
        txtQtdSaca = new javax.swing.JTextField(6);
        txtEmpChapa = new javax.swing.JTextField(12);
        btCancelarCadChapa = new javax.swing.JButton("Cancelar");
        btSalvarChapa = new javax.swing.JButton("Salvar");
        //fim

        //configuração componentes jpanel chapas
        txtVlrSaca.setDocument(new NumerosFloatCVirgula());
        txtQtdSaca.setDocument(new NumerosFloatCVirgula());
        //fim

        //adicionando componentes jpanel chapas
        panelChapas.add(lblNomeChapa, "split 2, gapx 40");
        panelChapas.add(txtNomeChapa, "wrap");
        panelChapas.add(lblEmpChapa, "split 2, gapx 23");
        panelChapas.add(txtEmpChapa, "wrap");
        panelChapas.add(lblQtdSaca, "split 2, gapx 6");
        panelChapas.add(txtQtdSaca, "wrap");
        panelChapas.add(lblVlrSaca, "split 2");
        panelChapas.add(txtVlrSaca, "wrap");
        panelChapas.add(btCancelarCadChapa, "split 2");
        panelChapas.add(btSalvarChapa, "gapx 10");
        //fim

        //Tabela config
        String[] columnNamess = {"Nome", "Empresa", "Vlr p/ saca", "Qtd saca", "Custo R$"};
        Object[][] dataa = {};
        modelTC = new javax.swing.table.DefaultTableModel(dataa, columnNamess) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabelaC = new javax.swing.JTable(modelTC);
        tabelaC.setPreferredScrollableViewportSize(new java.awt.Dimension(730, 100));
        tabelaC.getColumnModel().getColumn(0).setMaxWidth(200);
        tabelaC.getColumnModel().getColumn(0).setMinWidth(200);
        tabelaC.getColumnModel().getColumn(1).setMaxWidth(200);
        tabelaC.getColumnModel().getColumn(1).setMinWidth(200);
        tabelaC.getColumnModel().getColumn(2).setMaxWidth(120);
        tabelaC.getColumnModel().getColumn(2).setMinWidth(120);
        tabelaC.getColumnModel().getColumn(3).setMaxWidth(110);
        tabelaC.getColumnModel().getColumn(3).setMinWidth(110);
        tabelaC.getColumnModel().getColumn(4).setMaxWidth(100);
        tabelaC.getColumnModel().getColumn(4).setMinWidth(100);

        scrollTC = new javax.swing.JScrollPane(tabelaC);
        scrollTC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chapas viagem", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));

        //alinhando os valores das celulas da Tabela à direita
        tabelaC.getColumnModel().getColumn(0).setCellRenderer(r);
        tabelaC.getColumnModel().getColumn(1).setCellRenderer(r);
        tabelaC.getColumnModel().getColumn(2).setCellRenderer(r);
        tabelaC.getColumnModel().getColumn(3).setCellRenderer(r);
        tabelaC.getColumnModel().getColumn(4).setCellRenderer(r);
        //fim

        //evento bts
        btConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btConsultarActionPerformed(e);
            }
        });

        btDarSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btDarSaidaActionPerformed(e);
            }
        });

        btCancelarViagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelarViagemActionPerformed(e);
            }
        });

        btNovoChapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btNovoChapaActionPerformed(e);
            }
        });

        btCancelarCadChapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelarChapaActionPerformed(e);
            }
        });

        btSalvarChapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btSalvarChapaActionPerformed(e);
            }
        });

        btExcluirChapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btExcluirChapaActionPerformed(e);
            }
        });

        btFinalizarViagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btFinalizarViagemActionPerformed(e);
            }
        });

        btAddVolume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btAddVolumeActionPerformed(e);
            }
        });

        btExcluirVol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btExcluirVolActionPerformed(e);
            }
        });

        txtPesoReal.addKeyListener(new java.awt.event.KeyListener() {
            public void keyTyped(java.awt.event.KeyEvent e) {

            }

            public void keyPressed(java.awt.event.KeyEvent e) {
            }

            public void keyReleased(java.awt.event.KeyEvent e) {
                String s_peso = txtPesoReal.getText().toString();
                s_peso = s_peso.replace(",", ".");

                if (s_peso.isEmpty() || s_peso.equals(null)) {

                } else {
                    double d_calc = Double.parseDouble(s_peso);
                    d_calc = d_calc / 60;
                    DecimalFormat df = new DecimalFormat("#.00");
                    txtVolReal.setText("" + df.format(d_calc));

                }

            }
        });
        txtNumNota.addKeyListener(new java.awt.event.KeyListener() {
            public void keyTyped(java.awt.event.KeyEvent e) {

            }

            public void keyPressed(java.awt.event.KeyEvent e) {
            }

            public void keyReleased(java.awt.event.KeyEvent e) {
                DAONfeSai daons = new DAONfeSai();
                String s_num_nota = txtNumNota.getText().toString().trim();
                try {
                    boolean cond_nota = daons.consultaNumNota(s_num_nota);

                    if (cond_nota == true && !s_num_nota.equals("PENDENTE")) {
                        JOptionPane.showMessageDialog(null, "Já existe esta nota fiscal no sistema!");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(PGuiasSaidaNovaViagem.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );

        //fim
        panelEsq.add(panelFiscal,
                "wrap");
        panelEsq.add(panelFrete);

        this.add(panelConsultar,
                "top, split 3");

        this.add(scrollT,
                "top");

        this.add(panelInfo,
                "wrap, top");
        //  this.add(btDarSaida, "wrap");

        this.add(panelEsq,
                "split 4, top");

        this.add(panelViagem,
                "top");

        this.add(scrollV,
                "top");

        this.add(btExcluirVol,
                "wrap, top");

        this.add(panelChapas,
                "split 3, top");

        this.add(scrollTC,
                "top");

        this.add(btExcluirChapa);

        btExcluirVol.setVisible(
                false);
        panelInfo.setVisible(
                false);
        scrollT.setVisible(
                false);
        scrollV.setVisible(
                false);
        btDarSaida.setVisible(
                false);
        // panelFrete.setVisible(false);
        panelViagem.setVisible(
                false);
        panelChapas.setVisible(
                false);
        scrollTC.setVisible(
                false);
        btExcluirChapa.setVisible(
                false);
        panelEsq.setVisible(
                false);
    }

    private void montaPanelInfo(int id_guia) {
        DAOGuias daog = new DAOGuias();
        DAOGuiaViagemSai daogvs = new DAOGuiaViagemSai();
        DAOViagemSai daovs = new DAOViagemSai();

        //inicializando componentes panel info
        lblGuiaUlt = new javax.swing.JLabel("Guia:");
        lblGuiaUltR = new javax.swing.JLabel(txtNumGuia.getText().toString());
        lblPesoGuiaUlt = new javax.swing.JLabel("Peso total calculado:");
        lblPesoRealUlt = new javax.swing.JLabel("Peso total saída:");
        lblDocUlt = new javax.swing.JLabel("Última saída:");
        lblNfeUlt = new javax.swing.JLabel("NFe:");

        try {
            double d_peso_guia = daog.retornaPeso(id_guia);
            double d_peso_total_entrada = daogvs.retornaQtdSacasTotal(id_guia) * 60;
            String doc = daovs.retornaUltimoDocViagemGuia(id_guia);
            ViagemSai vs = daovs.retornaDadosInfo(doc);

            lblPesoGuiaUltR = new javax.swing.JLabel("" + NumberFormat.getIntegerInstance().format(d_peso_guia));
            lblPesoRealUltR = new javax.swing.JLabel("" + NumberFormat.getIntegerInstance().format(d_peso_total_entrada));
            lblDocUltR = new javax.swing.JLabel("" + doc);
            lblNfeUltR = new javax.swing.JLabel("" + vs.getNum_nota());

            lblGuiaUltR.setForeground(Color.BLUE);
            lblPesoGuiaUltR.setForeground(Color.GREEN);
            lblDocUltR.setForeground(Color.BLUE);
            lblNfeUltR.setForeground(Color.BLUE);

            if (d_peso_guia < d_peso_total_entrada) {
                lblPesoRealUltR.setForeground(Color.RED);
            } else {
                lblPesoRealUltR.setForeground(Color.GREEN);

            }

            //fim
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasEntradaNovaViagem.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        //adicionando componentes jpanel info
        panelInfo.add(lblGuiaUlt, "split 6");
        panelInfo.add(lblGuiaUltR);
        panelInfo.add(lblPesoGuiaUlt, "gapx 30");
        panelInfo.add(lblPesoGuiaUltR);
        panelInfo.add(lblPesoRealUlt, "gapx 30");
        panelInfo.add(lblPesoRealUltR, "wrap");
        panelInfo.add(lblDocUlt, "split 4");
        panelInfo.add(lblDocUltR);
        panelInfo.add(lblNfeUlt, "gapx 30");
        panelInfo.add(lblNfeUltR);

        //fim
        panelInfo.setVisible(true);
    }

    private void constroiPanelViagem(int status_check_fiscal) {

        if (status_check_fiscal == 1) {
            panelViagem.removeAll();
            panelViagem.add(lblDocViagem, "split 2, gapx 4");
            panelViagem.add(txtDocViagem, "wrap");
            panelViagem.add(lblPesoReal, "split 4, gapx 15");
            panelViagem.add(txtPesoReal);
            panelViagem.add(lblVolReal);
            panelViagem.add(txtVolReal, "wrap");
            panelViagem.add(lblData, "split 2, gapx 43");
            panelViagem.add(txtData, "wrap");
            panelViagem.add(lblObs,"split 2");
            panelViagem.add(txtObs,"wrap");
            panelViagem.add(btCancelarViagem, "split 3");
            panelViagem.add(btFinalizarViagem);

            scrollV.setVisible(false);
            scrollTC.setVisible(false);
            panelChapas.setVisible(false);
            btExcluirVol.setVisible(false);
            btExcluirChapa.setVisible(false);
            txtPesoReal.setEditable(true);

        } else {
            txtPesoReal.setEditable(false);
            panelViagem.removeAll();
            panelViagem.add(lblDocViagem, "split 2, gapx 4");
            panelViagem.add(txtDocViagem, "wrap");
            panelViagem.add(lblPesoReal, "split 4, gapx 15");
            panelViagem.add(txtPesoReal);
            panelViagem.add(lblVolReal);
            panelViagem.add(txtVolReal, "wrap");
            panelViagem.add(lblData, "split 2, gapx 43");
            panelViagem.add(txtData, "wrap");
            panelViagem.add(lblObs,"split 2");
            panelViagem.add(txtObs,"wrap");
            panelViagem.add(lblTipoVol, "split 3");
            panelViagem.add(radioTipoEmbBag);
            panelViagem.add(txtQtdBag, "wrap, gapx 8");
            panelViagem.add(radioTipoEmbSacaria, "split 2, gapx 51");
            panelViagem.add(txtQtdSacas, "wrap");
            panelViagem.add(radioTipoEmbGranel, "wrap, gapx 51");
            panelViagem.add(lblTipoCafe, "split 2");
            panelViagem.add(comboTipoCafe, "wrap");
            panelViagem.add(lblPesoVol, "split 3");
            panelViagem.add(txtPesoVol);
            panelViagem.add(btAddVolume, "wrap");
            panelViagem.add(btNovoChapa, "wrap");
            panelViagem.add(btCancelarViagem, "split 3");
            panelViagem.add(btNovoChapa);
            panelViagem.add(btFinalizarViagem);
        }
    }

    private void limpaCampos() {
        txtQtdSacas.setEditable(true);
        id_g = 0;
        id_e = 0;
        scrollT.setVisible(false);
        scrollV.setVisible(false);
        DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
        dtm.setNumRows(0);
        DefaultTableModel dtmc = (DefaultTableModel) tabelaC.getModel();
        dtmc.setNumRows(0);
        DefaultTableModel dtmv = (DefaultTableModel) tabelaV.getModel();
        dtmv.setNumRows(0);
        btDarSaida.setVisible(false);
        txtDocViagem.setText("");
        txtNomeMotor.setText("");
        txtNumNota.setText("");
        txtPesoReal.setText("");
        txtPlacaCam.setText("");
        txtQtdBag.setText("0");
        txtQtdSacas.setText("0");
        txtData.setText("");
        txtVlrFrete.setText("");
        txtVolReal.setText("");
        txtPesoFiscal.setText("");
        txtNumGuia.setEditable(true);
        txtQtdSacasFiscal.setText("");
        linhas_tabela = 0;
        li_select = -1;
        id_v = 0;
        txtObs.setText("");
        panelInfo.removeAll();
        panelInfo.setVisible(false);
        panelEsq.setVisible(false);
        scrollTC.setVisible(false);
        btExcluirChapa.setVisible(false);
        btExcluirVol.setVisible(false);
        panelViagem.setVisible(false);
        btConsultar.setEnabled(true);
        btDarSaida.setEnabled(true);
        radioTipoEmbBag.setSelected(true);
        cond_chapa = false;
        status_check_fiscal = 0;
        linhas_tabela_v = 0;
        comboTipoCafe.removeAllItems();
    }

    private void btAddVolumeActionPerformed(java.awt.event.ActionEvent evt) {
        int id_tipo_volume = 0;
        String s_qtd_bag = txtQtdBag.getText().toString().trim();
        String s_qtd_saca = txtQtdSacas.getText().toString().trim();
        String s_peso_vol = txtPesoVol.getText().toString().trim();
        int linha_tab_producao = 0;
        boolean condicao_volume_maior_peso_atual = false;

        if (radioTipoEmbBag.isSelected() == true) {
            id_tipo_volume = 1;
        } else if (radioTipoEmbSacaria.isSelected() == true) {
            id_tipo_volume = 3;
        } else {
            id_tipo_volume = 2;
        }
        if (s_peso_vol.isEmpty() || s_peso_vol.equals(null)) {
            JOptionPane.showMessageDialog(null, "Peso inválido ou nulo!");
        } else {
            s_peso_vol = s_peso_vol.replace(",", ".");
            double d_peso_vol = Double.parseDouble(s_peso_vol);
            if (d_peso_vol <= 0) {
                JOptionPane.showMessageDialog(null, "Peso inválido!");
            } else {
                if (id_tipo_volume == 1 && (s_qtd_bag.isEmpty() || s_qtd_bag.equals(null))) {
                    JOptionPane.showMessageDialog(null, "Informe uma quantidade de big bag!");
                } else if (id_tipo_volume == 3 && (s_qtd_saca.isEmpty() || s_qtd_saca.equals(null))) {
                    JOptionPane.showMessageDialog(null, "Informe uma quantidade de sacaria!");
                } else {

                    s_qtd_bag = s_qtd_bag.replace(",", ".");
                    s_qtd_saca = s_qtd_saca.replace(",", ".");

                    double d_qtd_bag = 0;
                    double d_qtd_saca = 0;

                    if (id_tipo_volume == 1) {
                        d_qtd_bag = Double.parseDouble(s_qtd_bag);
                    }
                    if (id_tipo_volume == 3) {
                        d_qtd_saca = Double.parseDouble(s_qtd_saca);
                    }
                    if (id_tipo_volume == 1 && d_qtd_bag <= 0) {
                        JOptionPane.showMessageDialog(null, "Qtd de big bag inválida! Menor ou igual a 0!");
                    } else if (id_tipo_volume == 3 && d_qtd_saca <= 0) {
                        JOptionPane.showMessageDialog(null, "Qtd de sacaria inválida! Menor ou igual a 0!");
                    } else {
                        d_peso_vol = d_peso_vol - (d_qtd_bag * 5) - (d_qtd_saca * 0.5);
                        if (d_peso_vol < 0) {
                            JOptionPane.showMessageDialog(null, "Peso do volume maior que o peso disponivel!");
                        } else {
                            String s_servico = comboTipoCafe.getSelectedItem().toString().trim();

                            for (int k = 0; k < tabela.getRowCount(); k++) {
                                if (s_servico == tabela.getValueAt(k, 0).toString().trim()) {
                                    double d_peso_atual = Double.parseDouble(tabela.getValueAt(k, 3).toString().trim());

                                    if (d_peso_vol > d_peso_atual) {
                                        condicao_volume_maior_peso_atual = true;
                                    } else {
                                        linha_tab_producao = k;
                                    }
                                }
                            }

                            if (condicao_volume_maior_peso_atual == true) {
                                JOptionPane.showMessageDialog(null, "Peso do volume maior que o peso disponivel!");
                                condicao_volume_maior_peso_atual = false;
                            } else {

                                //atualizando a tabela de produção, com o peso atual disponivel
                                double d_peso_atual = Double.parseDouble(tabela.getValueAt(linha_tab_producao, 3).toString().trim());
                                d_peso_atual = d_peso_atual - d_peso_vol;
                                tabela.setValueAt(d_peso_atual, linha_tab_producao, 3);
                        //fim

                                //atualizando o campo peso real
                                DecimalFormat df = new DecimalFormat("#.00");
                                String s_peso_real_total = txtPesoReal.getText().toString().trim();
                                if (s_peso_real_total.isEmpty() || s_peso_real_total.equals(null)) {
                                    s_peso_real_total = "0";
                                }
                                s_peso_real_total = s_peso_real_total.replace(",", ".");
                                double d_peso_real_total = Double.parseDouble(s_peso_real_total.trim());
                                d_peso_real_total = d_peso_real_total + d_peso_vol;
                                txtPesoReal.setText("" + df.format(d_peso_real_total));
                                txtVolReal.setText("" + df.format(d_peso_real_total / 60));
                                //fim

                                boolean condicao = false;

                                String[] linhas = {null, null, null, null};
                                DefaultTableModel dtm = (DefaultTableModel) tabelaV.getModel();

                                if (linhas_tabela_v > 0) {
                                    for (int i = 0; i < linhas_tabela_v; i++) {
                                        if (s_servico == tabelaV.getValueAt(i, 0).toString().trim()) {
                                            if (id_tipo_volume == 1) {
                                                String s_bag = tabelaV.getValueAt(i, 1).toString();
                                                s_bag = s_bag.replace(",", ".");
                                                String s_peso = tabelaV.getValueAt(i, 3).toString().trim();
                                                s_peso = s_peso.replace(",", ".");
                                                double d_peso_v = Double.parseDouble(s_peso) + d_peso_vol;
                                                double d_bag = Double.parseDouble(s_bag);
                                                tabelaV.setValueAt(d_qtd_bag + d_bag, i, 1);
                                                tabelaV.setValueAt(d_peso_v, i, 3);
                                                txtQtdBag.setText("");
                                                txtQtdSacas.setText("");
                                                txtPesoVol.setText("");
                                                condicao = true;
                                                break;
                                            } else if (id_tipo_volume == 3) {
                                                String s_peso = tabelaV.getValueAt(i, 3).toString().trim();
                                                s_peso = s_peso.replace(",", ".");
                                                double d_peso_v = Double.parseDouble(s_peso) + d_peso_vol;
                                                String s_saca = tabelaV.getValueAt(i, 2).toString();
                                                tabelaV.setValueAt(d_peso_v, i, 3);
                                                s_saca = s_saca.replace(",", ".");
                                                double d_saca = Double.parseDouble(s_saca);
                                                tabelaV.setValueAt(d_qtd_saca + d_saca, i, 2);
                                                txtQtdBag.setText("");
                                                txtQtdSacas.setText("");
                                                txtPesoVol.setText("");
                                                condicao = true;
                                                break;
                                            } else {
                                                String s_peso = tabelaV.getValueAt(i, 3).toString().trim();
                                                s_peso = s_peso.replace(",", ".");
                                                double d_peso_v = Double.parseDouble(s_peso) + d_peso_vol;

                                                tabelaV.setValueAt(d_peso_v, i, 3);
                                                
                                                txtQtdBag.setText("");
                                                txtQtdSacas.setText("");
                                                txtPesoVol.setText("");
                                                condicao = true;
                                                break;
                                            }
                                        }
                                    }
                                }

                                if (condicao == false) {
                                    dtm.addRow(linhas);
                                    tabelaV.setModel(dtm);
                                    if (id_tipo_volume == 1) {
                                        tabelaV.setValueAt(s_servico, linhas_tabela_v, 0);
                                        tabelaV.setValueAt(d_qtd_bag, linhas_tabela_v, 1);
                                        tabelaV.setValueAt(0, linhas_tabela_v, 2);
                                        tabelaV.setValueAt(d_peso_vol, linhas_tabela_v, 3);
                                        txtQtdBag.setText("");
                                        txtQtdSacas.setText("");
                                        txtPesoVol.setText("");
                                    } else if (id_tipo_volume == 3) {
                                        tabelaV.setValueAt(s_servico, linhas_tabela_v, 0);
                                        tabelaV.setValueAt(0, linhas_tabela_v, 1);
                                        tabelaV.setValueAt(d_qtd_saca, linhas_tabela_v, 2);
                                        tabelaV.setValueAt(d_peso_vol, linhas_tabela_v, 3);
                                        txtQtdBag.setText("");
                                        txtQtdSacas.setText("");
                                        txtPesoVol.setText("");
                                    } else {
                                        tabelaV.setValueAt(s_servico, linhas_tabela_v, 0);
                                        tabelaV.setValueAt(0, linhas_tabela_v, 1);
                                        tabelaV.setValueAt(0, linhas_tabela_v, 2);
                                        tabelaV.setValueAt(d_peso_vol, linhas_tabela_v, 3);
                                        txtQtdBag.setText("");
                                        txtQtdSacas.setText("");
                                        txtPesoVol.setText("");
                                    }
                                    linhas_tabela_v++;
                                }

                            }
                        }
                    }
                }
            }
        }

    }

    private void limpaCamposViagem() {
        btDarSaida.setEnabled(true);
        txtDocViagem.setText("");
        txtNomeMotor.setText("");
        txtNumNota.setText("");
        txtPesoReal.setText("");
        txtPlacaCam.setText("");
        txtQtdBag.setText("");
        txtQtdSacas.setText("");
        txtVlrFrete.setText("");
        txtData.setText("");
        panelFrete.setVisible(false);
        panelViagem.setVisible(false);
        scrollTC.setVisible(false);
        DefaultTableModel dtmc = (DefaultTableModel) tabelaC.getModel();
        dtmc.setNumRows(0);
        linhas_tabela = 0;
        cond_chapa = false;
        li_select = -1;
        btExcluirChapa.setVisible(false);
    }

    private void limpaCamposChapa() {
        txtEmpChapa.setText("");
        txtQtdSaca.setText("");
        txtNomeChapa.setText("");
        txtVlrSaca.setText("");
        panelChapas.setVisible(false);
        btFinalizarViagem.setEnabled(true);
        btCancelarViagem.setEnabled(true);
        btNovoChapa.setEnabled(true);
    }

    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {

        String s_num_guia = txtNumGuia.getText().toString();
        DAOEmpresaGuia daoeg = new DAOEmpresaGuia();
        DAOGuiaViagemSai daogvs = new DAOGuiaViagemSai();
        DAOGuias daog = new DAOGuias();
        DAOUsuarios daou = new DAOUsuarios();
        try {
            boolean condicao_usuario = false;

            if (checkBoxFiscal.isSelected() == true) {
                int id_usuario = Integer.parseInt(ManipulaArquivo.lerArquivoUsuario().trim());
                int tipo_usuario = daou.retornaTipoUsuario(id_usuario);
                if ((tipo_usuario == 4) || (tipo_usuario == 1)) {
                    condicao_usuario = true;
                } else {
                    condicao_usuario = false;
                }
            } else {
                condicao_usuario = true;
            }

            if (condicao_usuario == true) {
                int id_emp = daoe.retornaIDPorNome(comboEmpGuia.getSelectedItem().toString());
                if (daoeg.consultaGuiaEmp(s_num_guia, id_emp) == false) {
                    JOptionPane.showMessageDialog(null, "Essa guia não existe para a empresa " + comboEmpGuia.getSelectedItem().toString());
                } else {
                    int id_guia = daog.retornaIdGuiaEmp(s_num_guia, id_emp);
                    id_g = id_guia;
                    listaDados(id_g);
                    if ((listaDados(id_g) == false) && (checkBoxFiscal.isSelected() == false)) {
                        JOptionPane.showMessageDialog(null, "Não há produção de café para esta guia!!");
                    } else {
                        int id_viagem = daogvs.retornaIdMaisUm(id_guia);
                        id_v = id_viagem;

                        id_e = id_emp;

                        btConsultar.setEnabled(false);
                        txtNumGuia.setEditable(false);

                        String s_nosso_num = daog.retornaNossoNum(txtNumGuia.getText().toString(), id_e);
                        String s_doc = s_nosso_num + "-S" + id_viagem;
                        txtDocViagem.setText(s_doc);
                        btDarSaida.setEnabled(false);
                        montaPanelInfo(id_guia);
                        panelFrete.setVisible(true);
                        panelViagem.setVisible(true);
                        btExcluirVol.setVisible(true);
                        panelEsq.setVisible(true);
                        scrollTC.setVisible(true);
                        txtDocViagem.setEditable(false);
                        li_select = tabela.getSelectedRow();
                        btExcluirChapa.setVisible(true);
                        scrollV.setVisible(true);
                        scrollT.setVisible(true);
                        btDarSaida.setVisible(true);

                        if (checkBoxFiscal.isSelected() == true) {
                            status_check_fiscal = 1;
                            constroiPanelViagem(status_check_fiscal);
                        } else {
                            status_check_fiscal = 0;
                            constroiPanelViagem(status_check_fiscal);
                        }
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Você não tem permissão para dar esse tipo de saída!");

            }

        } catch (SQLException ex) {
            Logger.getLogger(PGuiasEntradaNovaViagem.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean listaDados(int id_guia) {
        DAOProducaoGuia daopg = new DAOProducaoGuia();
        List<ProducaoGuia> dados;
        boolean condicao = false;

        try {
            dados = daopg.retornaDados(id_guia);
            if (dados.size() == 0) {

                condicao = false;
            } else {
                condicao = true;
                String[] linhas = {null, null, null, null};

                comboTipoCafe.removeAllItems();
                for (int i = 0; i < dados.size(); i++) {
                    DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
                    dtm.addRow(linhas);
                    tabela.setModel(dtm);
                    tabela.setValueAt(dados.get(i).getDesc_serv(), i, 0);
                    tabela.setValueAt(dados.get(i).getPeso_pdc(), i, 1);
                    tabela.setValueAt(dados.get(i).getSaldo_pdc(), i, 2);
                    tabela.setValueAt(dados.get(i).getSaldo_pdc(), i, 3);

                    comboTipoCafe.addItem(tabela.getValueAt(i, 0));

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasSaidaNovaViagem.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        //fim
        ManipulaJTables.removeLinhasBrancas(tabela);

        return condicao;
    }

    private void btExcluirVolActionPerformed(java.awt.event.ActionEvent evt) {
        int li = tabelaV.getSelectedRow();

        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            int resp = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este volume?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);

            if (resp == 0) {
                DefaultTableModel dtm = (DefaultTableModel) tabelaV.getModel();
                DecimalFormat df = new DecimalFormat("#.0");

                String s_peso_v = tabelaV.getValueAt(li, 3).toString().trim();
                s_peso_v = s_peso_v.replace(",", ".");
                double d_peso_v = Double.parseDouble(s_peso_v);

                String s_peso_r = txtPesoReal.getText().toString();
                s_peso_r = s_peso_r.replace(",", ".");
                double d_peso_r = Double.parseDouble(s_peso_r);

                d_peso_r = d_peso_r - d_peso_v;
                txtPesoReal.setText("" + df.format(d_peso_r));
                txtVolReal.setText("" + df.format(d_peso_r / 60));

                for (int i = 0; i < tabela.getRowCount(); i++) {
                    if (tabela.getValueAt(i, 0).toString().trim() == tabelaV.getValueAt(li, 0).toString().trim()) {
                        String s_peso_prod = tabela.getValueAt(i, 3).toString().trim();
                        s_peso_prod = s_peso_prod.replace(",", ".");
                        double d_peso_prod = Double.parseDouble(s_peso_prod);

                        String s_peso_vol = tabelaV.getValueAt(li, 3).toString().trim();
                        s_peso_vol = s_peso_vol.replace(",", ".");
                        double d_peso_vol = Double.parseDouble(s_peso_vol);

                        d_peso_prod = d_peso_prod + d_peso_vol;
                        tabela.setValueAt(d_peso_prod, i, 3);
                    }
                }

                dtm.removeRow(li);
                linhas_tabela_v--;
            }
        }
    }

    private void btDarSaidaActionPerformed(java.awt.event.ActionEvent evt) {
        int li = tabela.getSelectedRow();
        DAOGuiaViagemSai daogvs = new DAOGuiaViagemSai();
        DAOGuias daog = new DAOGuias();
        double dd_peso = Double.parseDouble(tabela.getValueAt(li, 2).toString());
        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            if (dd_peso <= 0) {
                JOptionPane.showMessageDialog(null, "Peso menor ou igual a 0!");
            } else {
                try {
                    int id_viagem = daogvs.retornaIdMaisUm(id_g);
                    String s_nosso_num = daog.retornaNossoNum(txtNumGuia.getText().toString(), id_e);
                    String s_doc = s_nosso_num + "-S" + id_viagem;
                    txtDocViagem.setText(s_doc);
                    btDarSaida.setEnabled(false);
                    panelFrete.setVisible(true);
                    panelViagem.setVisible(true);
                    panelEsq.setVisible(true);
                    scrollTC.setVisible(true);
                    txtDocViagem.setEditable(false);
                    li_select = tabela.getSelectedRow();
                    btExcluirChapa.setVisible(true);

                } catch (SQLException ex) {
                    Logger.getLogger(PGuiasSaidaNovaViagem.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    private void btCancelarViagemActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja cancelar a viagem?", "Cancelar viagem", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            limpaCampos();
        }
    }

    private void btNovoChapaActionPerformed(java.awt.event.ActionEvent evt) {
        String s_peso = txtPesoReal.getText().toString();
        String s_qtd_sacas = txtQtdSacas.getText().toString();
        double d_qtd_sacas = 0;

        for (int i = 0; i < linhas_tabela_v; i++) {
            String s_qtd_sacaria = tabelaV.getValueAt(i, 2).toString().trim();
            s_qtd_sacaria = s_qtd_sacaria.replace(",", ".");

            d_qtd_sacas = d_qtd_sacas + Double.parseDouble(s_qtd_sacaria);
        }

        if (s_peso.isEmpty() || s_peso.equals(null)) {
            JOptionPane.showMessageDialog(null, "Informe um valor para o peso real!");
        } else {
            s_peso = s_peso.replace(",", ".");
            double d_peso = Double.parseDouble(s_peso);

            if (d_peso <= 0 || d_qtd_sacas <= 0) {
                JOptionPane.showMessageDialog(null, "Peso e/ou qtde de sacas zerado(a)!");
            } else {
                if (d_peso < (d_qtd_sacas * 60)) {
                    JOptionPane.showMessageDialog(null, "Qtd de sacas excedendo o peso!");
                } else {
                    double peso_total = 0;
                    String s_peso_total = txtPesoReal.getText().toString();
                    s_peso_total = s_peso_total.replace(",", ".");

                    peso_total = Double.parseDouble(s_peso_total);

                    if (d_peso > peso_total) {
                        JOptionPane.showMessageDialog(null, "Peso da viagem excedendo o peso da produção!");
                    } else {
                        if (cond_chapa == false) {
                            int resp = JOptionPane.showConfirmDialog(null, "Você confirma a qtd de sacas e o peso total? \n" + "Sacas: " + d_qtd_sacas + " \n" + " Peso: " + d_peso + "KG", "Fechar peso", JOptionPane.YES_NO_OPTION);
                            cond_chapa = true;
                            if (resp == 0) {
                                panelChapas.setVisible(true);
                                txtPesoReal.setEditable(false);
                                txtQtdSacas.setEditable(false);
                                btFinalizarViagem.setEnabled(false);
                                btCancelarViagem.setEnabled(false);
                                btNovoChapa.setEnabled(false);
                            }
                        } else {
                            panelChapas.setVisible(true);
                            txtPesoReal.setEditable(false);
                            txtQtdSacas.setEditable(false);
                            btFinalizarViagem.setEnabled(false);
                            btCancelarViagem.setEnabled(false);
                            btNovoChapa.setEnabled(false);
                        }
                    }

                }

            }
        }

    }

    private void btCancelarChapaActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja cancelar o chapa?", "Cancelar chapa", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            limpaCamposChapa();
            int li = tabelaC
                    .getRowCount();
            if (li == 0) {
                //txtPesoReal.setEditable(true);
                txtQtdSacas.setEditable(true);
                cond_chapa = false;
            }
        }

    }

    private void btSalvarChapaActionPerformed(java.awt.event.ActionEvent evt) {
        String s_nome = txtNomeChapa.getText().toString().toUpperCase();
        String s_emp_c = txtEmpChapa.getText().toString().toUpperCase();
        String s_vlr_s = txtVlrSaca.getText().toString();
        String s_qtd_s = txtQtdSaca.getText().toString();

        if (s_nome.isEmpty() || s_nome.equals(null) || s_vlr_s.isEmpty() || s_vlr_s.equals(null)
                || s_qtd_s.isEmpty() || s_qtd_s.equals(null)) {
            JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
        } else {
            s_vlr_s = s_vlr_s.replace(",", ".");
            double d_vlr_s = Double.parseDouble(s_vlr_s);

            double d_saca_total = 0;
            //double d_saca_total = Double.parseDouble(txtQtdSacas.getText().toString());
            for (int i = 0; i < tabelaV.getRowCount(); i++) {
                String s_qtd_saca = tabelaV.getValueAt(i, 2).toString();
                s_qtd_saca = s_qtd_saca.replace(",", ".");
                d_saca_total = d_saca_total + Double.parseDouble(s_qtd_saca);
            }

            double d_qtd_s = Double.parseDouble(s_qtd_s);
            if (d_qtd_s > d_saca_total) {
                JOptionPane.showMessageDialog(null, "Quantidade de sacas está excedendo a viagem!");
            } else {
                String[] linhas = {null, null, null, null, null};
                DefaultTableModel dtm = (DefaultTableModel) tabelaC.getModel();
                dtm.addRow(linhas);
                DecimalFormat df = new DecimalFormat("0.##");
                tabelaC.setModel(dtm);
                tabelaC.setValueAt(s_nome, linhas_tabela, 0);
                tabelaC.setValueAt(s_emp_c, linhas_tabela, 1);
                tabelaC.setValueAt(df.format(d_vlr_s), linhas_tabela, 2);
                tabelaC.setValueAt(d_qtd_s, linhas_tabela, 3);
                tabelaC.setValueAt(df.format(d_vlr_s * d_qtd_s), linhas_tabela, 4);

                ManipulaJTables.removeLinhasBrancas(tabela);
                linhas_tabela++;
                limpaCamposChapa();
            }

        }
    }

    private void btExcluirChapaActionPerformed(java.awt.event.ActionEvent evt) {
        int li = tabelaC.getSelectedRow();
        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            DefaultTableModel dtm = (DefaultTableModel) tabelaC.getModel();
            dtm.removeRow(li);
            linhas_tabela--;
            if (linhas_tabela == 0) {
                //  txtPesoReal.setEditable(true);
                txtQtdSacas.setEditable(true);
                cond_chapa = false;
            }
        }
    }

    private void btFinalizarViagemActionPerformed(java.awt.event.ActionEvent evt) {
        String s_num_guia = txtNumGuia.getText().toString().trim();
        String s_nome_motor = txtNomeMotor.getText().toString().toUpperCase().trim();
        String s_peso = txtPesoReal.getText().toString().trim();
        String s_placa = txtPlacaCam.getText().toString().toUpperCase().trim();
        String s_doc = txtDocViagem.getText().toString().trim();
        String s_nfe = txtNumNota.getText().toString().trim();
        String s_qtd_bag = txtQtdBag.getText().toString().trim();
        // String s_qtd_saca = txtQtdSacas.getText().toString();
        String s_qtd_sacaria = txtQtdSacas.getText().toString().trim();
        String s_vlr_frete = txtVlrFrete.getText().toString().trim();
        String s_peso_fiscal = txtPesoFiscal.getText().toString().trim();
        String s_data = ManipulaDatas.retornaDataAtual();
        String ss_data = txtData.getText().toString().trim();
        String s_qtd_sacas_fiscal = txtQtdSacasFiscal.getText().toString().trim();
        String s_qtd_sacas_real = txtVolReal.getText().toString().trim();
        String s_ano = s_data.substring(6, 10).trim();
        String s_obs_sai = txtObs.getText().toString().toUpperCase().trim();

        double d_qtd_bag = 0;
        double d_qtd_sacaria = 0;

        int qtd_linhas_tab_vol = tabelaV.getRowCount();
        int tipo_emb = -1;
        int tipo_frete = -1;
        if (s_nome_motor.isEmpty() || s_nome_motor.equals(null) || s_peso.isEmpty() || s_peso.equals(null) || ss_data.isEmpty() || ss_data.equals(null) || s_nfe.isEmpty()
                || s_nfe.equals(null) || s_peso_fiscal.isEmpty() || s_peso_fiscal.equals(null)) {
            JOptionPane.showMessageDialog(null, "Preencha os campos!");
        } else {
            if (ManipulaDatas.validaData(ss_data) == false) {
                JOptionPane.showMessageDialog(null, "Data inválida!");
            } else {
                s_peso = s_peso.replace(",", ".");
                double d_peso = Double.parseDouble(s_peso);
                if (radioTipoFreteCA.isSelected() == true) {
                    tipo_frete = 1;
                } else if (radioTipoFreteEmp.isSelected() == true) {
                    tipo_frete = 2;
                } else {
                    tipo_frete = 3;
                }

                if ((tipo_frete == 1) && (s_vlr_frete.isEmpty() || s_vlr_frete.equals(null))) {
                    JOptionPane.showMessageDialog(null, "Informe um valor para o frete!");
                } else {

                    if (s_vlr_frete.isEmpty() || s_vlr_frete.equals(null)) {
                        s_vlr_frete = "" + 0;
                    }
                    s_vlr_frete = s_vlr_frete.replace(",", ".");
                    double d_vlr_frete = Double.parseDouble(s_vlr_frete);
                    if (tipo_frete == 1 && d_vlr_frete <= 0) {
                        JOptionPane.showMessageDialog(null, "Valor do frete não pode ser negativo nem nulo!");
                    } else {

                        if (d_peso <= 0) {
                            JOptionPane.showMessageDialog(null, "Peso menor ou igual a 0");
                        } else {

                            DAONfeSai daons = new DAONfeSai();
                            try {
                                boolean cond_nota = daons.consultaNumNota(s_nfe);
                                if (s_qtd_sacas_fiscal.isEmpty() || s_qtd_sacas_fiscal.equals(null)) {
                                    JOptionPane.showMessageDialog(null, "Qtd de sacas fiscal nula!");
                                } else {
                                    s_qtd_sacas_fiscal = s_qtd_sacas_fiscal.replace(",", ".");
                                    double d_qtd_sacas_fiscal = Double.parseDouble(s_qtd_sacas_fiscal);
                                    if (d_qtd_sacas_fiscal <= 0) {
                                        JOptionPane.showMessageDialog(null, "Qtd de sacas fiscal menor ou igual a 0!");
                                    } else {
                                        if (cond_nota == true && !s_nfe.equals("PENDENTE")) {
                                            JOptionPane.showMessageDialog(null, "Já existe esta nota fiscal no sistema!");
                                        } else {
                                            DAOPdcUnicaGuia daopug = new DAOPdcUnicaGuia();
                                            if ((qtd_linhas_tab_vol <= 0) && (status_check_fiscal != 1)) {
                                                JOptionPane.showMessageDialog(null, "Informe uma qtd de café e volumes!");
                                            } else {
                                                if (daopug.retornaSaldoAtualPdc(id_g) < d_peso && (status_check_fiscal == 1)) {
                                                    JOptionPane.showMessageDialog(null, "Peso da viagem maior que o peso disponivel para guia!");
                                                } else {
                                                    s_peso_fiscal = s_peso_fiscal.replace(",", ".");
                                                    double d_peso_fiscal = Double.parseDouble(s_peso_fiscal);

                                                    if (d_peso_fiscal <= 0) {
                                                        JOptionPane.showMessageDialog(null, "Peso fiscal inválido! Peso menor ou igual a 0!");
                                                    } else {
                                                        if ((tabelaV.getRowCount() <= 0) && (status_check_fiscal != 1)) {
                                                            JOptionPane.showMessageDialog(null, "Informe o(s) tipo(s) de café e sua(s) embalagem(ns)!");
                                                        } else {
                                                            s_qtd_sacas_real = s_qtd_sacas_real.replace(",", ".");

                                                            double d_vol_real = Double.parseDouble(s_qtd_sacas_real);
                                                            int resp = JOptionPane.showConfirmDialog(null, "Você confirma esta viagem? \n" + "Peso real: " + d_peso + "KG" + "\n" + "Peso fiscal: " + d_peso_fiscal + "KG" + "\n" + "Nº NFe: " + s_nfe, "Confirmar viagem", JOptionPane.YES_NO_OPTION);

                                                            if (resp == 0) {
                                                                //cadastrando...
                                                                DAOServicos daos = new DAOServicos();
                                                                DAOGuiaViagemSai daogvs = new DAOGuiaViagemSai();
                                                                DAOViagemSai daovs = new DAOViagemSai();
                                                                ViagemSai vs = new ViagemSai();
                                                                DAOProducaoGuia daopg = new DAOProducaoGuia();
                                                                GuiaViagemSai gvs = new GuiaViagemSai();
                                                                NfeSai ns = new NfeSai();
                                                                DAOFinanceiro daof = new DAOFinanceiro();
                                                                Financeiro f = new Financeiro();
                                                                int id_serv = 0;
                                                                int id_financeiro = daof.retornaUltimaID();

                                                                //montando objeto guia viagem saida
                                                                gvs.setId_guia(id_g);
                                                                gvs.setId_viagem(id_v);
                                                                gvs.setNum_guia(s_num_guia);
                                                            //fim

                                                                //montando objeto nfe saida
                                                                ns.setDt_nfe_sai(ManipulaDatas.converteStrSql(ss_data));
                                                                ns.setId_guia(id_g);
                                                                ns.setId_viagem_sai(id_v);
                                                                ns.setNum_nota(s_nfe);
                                                                ns.setStatus_nfe(0);
                                                                ns.setQtd_sacas_sai_fiscal(d_qtd_sacas_fiscal);
                                                                ns.setStatus_nfe(1);
                                                                ns.setPeso_nfe_sai(d_peso_fiscal);
                                                                //fim

                                                                int cont_tab = 0;

                                                                //cadastrando os trabalhadores
                                                                for (int i = 0; i < tabelaC.getRowCount(); i++) {
                                                                    DAOTrabViagem daotv = new DAOTrabViagem();
                                                                    TrabViagem tv = new TrabViagem();
                                                                    tv.setDocumento_viagem(s_doc);
                                                                    tv.setId_guia(id_g);
                                                                    tv.setId_viagem(id_v);
                                                                    tv.setId_tipo_viagem(2);
                                                                    tv.setNome_trab_tv(tabelaC.getValueAt(cont_tab, 0).toString());
                                                                    tv.setEmpresa_tv(tabelaC.getValueAt(cont_tab, 1).toString());
                                                                    String s_vlr_p_saca = tabelaC.getValueAt(cont_tab, 2).toString();
                                                                    s_vlr_p_saca = s_vlr_p_saca.replace(",", ".");
                                                                    double d_vlr_p_saca = Double.parseDouble(s_vlr_p_saca);
                                                                    tv.setVlr_saca_tv(d_vlr_p_saca);
                                                                    double d_qtd_saca_chapa = Double.parseDouble(tabelaC.getValueAt(cont_tab, 3).toString());
                                                                    tv.setQtd_saca_tv(d_qtd_saca_chapa);
                                                                    tv.setVlr_total_tv(d_vlr_p_saca * d_qtd_saca_chapa);
                                                                    daotv.inserir(tv);

                                                                    int id_financeiroc = daof.retornaUltimaID(); //id para chapas

                                                                    f.setCredito(d_vlr_p_saca * d_qtd_saca_chapa);
                                                                    f.setDebito(0);
                                                                    f.setDocumento("VSC" + id_financeiroc + s_ano);
                                                                    f.setDocumento_referencia(s_doc);
                                                                    f.setDt_financeiro(ManipulaDatas.converteStrSql(ss_data));
                                                                    f.setTipo_financeiro(2);
                                                                    f.setTipo_operacao(5);
                                                                    f.setUsuario_cria(Integer.parseInt(ManipulaArquivo.lerArquivoUsuario().trim()));
                                                                    f.setVlr_financeiro(d_vlr_p_saca * d_qtd_saca_chapa);
                                                                    f.setEmpresa(id_e);
                                                                    daof.inserir(f);

                                                                    cont_tab++; //contador linhas jtable
                                                                }

                                                                //fim
                                                                for (int i = 0; i < tabelaV.getRowCount(); i++) {
                                                                    String s_bag = tabelaV.getValueAt(i, 1).toString().trim();
                                                                    String s_saca = tabelaV.getValueAt(i, 2).toString().trim();

                                                                    s_bag = s_bag.replace(",", ".");
                                                                    s_saca = s_saca.replace(",", ".");

                                                                    if (s_bag.isEmpty() || s_bag.equals(null)) {
                                                                        s_bag = "0";
                                                                    }
                                                                    if (s_saca.isEmpty() || s_saca.equals(null)) {
                                                                        s_saca = "0";
                                                                    }

                                                                    d_qtd_bag = d_qtd_bag + Double.parseDouble(s_bag);
                                                                    d_qtd_sacaria = d_qtd_sacaria + Double.parseDouble(s_saca);
                                                                }

                                                                //atualizando o peso bruto somando as embalagens
                                                                d_peso = d_peso + (d_qtd_bag * 5) + (d_qtd_sacaria * 0.5);

                                                                if (status_check_fiscal != 1) {

                                                                    for (int i = 0; i < tabelaV.getRowCount(); i++) {
                                                                        /* String s_bag = tabelaV.getValueAt(i, 1).toString().trim();
                                                                         String s_saca = tabelaV.getValueAt(i, 2).toString().trim();

                                                                         s_bag = s_bag.replace(",", ".");
                                                                         s_saca = s_saca.replace(",", ".");

                                                                         d_qtd_bag = d_qtd_bag + Double.parseDouble(s_bag);
                                                                         d_qtd_sacaria = d_qtd_sacaria + Double.parseDouble(s_saca); */

                                                                        //inserindo na tabela tipo_cafe...
                                                                        TipoCafeViagem tcv = new TipoCafeViagem();
                                                                        DAOTipoCafeViagem daotcv = new DAOTipoCafeViagem();
                                                                        int id_servv = daos.retornaIDPorNome(tabelaV.getValueAt(i, 0).toString().trim());
                                                                        String s_peso_v = tabelaV.getValueAt(i, 3).toString().trim();
                                                                        s_peso_v = s_peso_v.replace(",", ".");
                                                                        double d_peso_v = Double.parseDouble(s_peso_v);

                                                                        tcv.setId_guia(id_g);
                                                                        tcv.setId_viagem(id_v);
                                                                        tcv.setTipo_viagem(2);
                                                                        tcv.setTipo_cafe(id_servv);
                                                                        tcv.setPeso(d_peso_v);
                                                                        daotcv.inserir(tcv);

                                                                        //fim
                                                                        //dando baixa na produção do servico da guia
                                                                        int id_pdc = daopg.retornaIdPorTipoGuia(id_servv, id_g);
                                                                        double d_saldo_pdc = daopg.retornaSaldo(id_pdc);
                                                                        d_saldo_pdc = d_saldo_pdc - d_peso_v;
                                                                        daopg.alteraSaldo(id_pdc, d_saldo_pdc);
                                                                        //fim
                                                                    }

                                                                    if (d_qtd_bag > 0) {
                                                                        DAOCtrlEmbEmp daocee = new DAOCtrlEmbEmp();
                                                                        DAOCtrlEmbViagemSai daocees = new DAOCtrlEmbViagemSai();
                                                                        CtrlEmbViagemSai cees = new CtrlEmbViagemSai();

                                                                        cees.setId_guia(id_g);
                                                                        cees.setId_viagem(id_v);
                                                                        cees.setId_tipo_emb(1);
                                                                        cees.setQtd_emb(d_qtd_bag);

                                                                        double d_qtd_baixada = daocee.retornaQtdBaixada(id_e, 1);
                                                                        double calc = d_qtd_baixada + d_qtd_bag;
                                                                        daocees.inserir(cees);
                                                                        daocee.alterarQtdBaixadas(1, calc, id_e);

                                                                    }

                                                                    if (d_qtd_sacaria > 0) {
                                                                        DAOCtrlEmbEmp daocee = new DAOCtrlEmbEmp();
                                                                        DAOCtrlEmbViagemSai daocees = new DAOCtrlEmbViagemSai();
                                                                        CtrlEmbViagemSai cees = new CtrlEmbViagemSai();

                                                                        cees.setId_guia(id_g);
                                                                        cees.setId_viagem(id_v);
                                                                        cees.setId_tipo_emb(3);
                                                                        cees.setQtd_emb(d_qtd_sacaria);

                                                                        double d_qtd_baixada = daocee.retornaQtdBaixada(id_e, 3);
                                                                        double calc = d_qtd_baixada + d_qtd_sacaria;
                                                                        daocees.inserir(cees);
                                                                        daocee.alterarQtdBaixadas(3, calc, id_e);

                                                                    }

                                                                } else {
                                                                    //inserindo tipo cafe fiscal
                                                                    TipoCafeViagem tcv = new TipoCafeViagem();
                                                                    DAOTipoCafeViagem daotcv = new DAOTipoCafeViagem();
                                                                    int id_servv = daos.retornaIDPorNome("REBENEFICIO DE CAFE SAI FISCAL");

                                                                    tcv.setId_guia(id_g);
                                                                    tcv.setId_viagem(id_v);
                                                                    tcv.setTipo_viagem(2);
                                                                    tcv.setTipo_cafe(id_servv);
                                                                    daotcv.inserir(tcv);
                                                                    //fim

                                                                    //dando baixa na producao
                                                                    double d_saldoo = daopug.retornaSaldoAtualPdc(id_g);
                                                                    d_saldoo = d_saldoo - d_peso;
                                                                    daopug.alteraSaldoPdc(id_g, d_saldoo);
                                                                    //fim
                                                                }
                                                                //   double d_peso_viagem = (d_peso + (d_qtd_bag * 5) + (d_qtd_sacaria * 0.5));
                                                                double d_calc_vol_real = (d_peso - (d_qtd_bag * 5) - (d_qtd_sacaria * 0.5)) / 60;
                                                                //motando objeto viagem saida
                                                                vs.setDoc_viagem_sai(s_doc);
                                                                vs.setDt_sai(ManipulaDatas.converteStrSql(ss_data));
                                                                vs.setNome_motor_sai(s_nome_motor);
                                                                vs.setNum_nota(s_nfe);
                                                                vs.setPeso_sai(d_peso);
                                                                vs.setPlaca_cam_sai(s_placa);
                                                                vs.setQtd_sacas_sai(d_calc_vol_real);
                                                                vs.setTipo_cafe_sai(0);
                                                                vs.setTipo_frete_sai(tipo_frete);
                                                                vs.setUsuario_cad_sai(ManipulaArquivo.lerArquivoUsuario());
                                                                vs.setVlr_frete_unit(d_vlr_frete);
                                                                vs.setVlr_frete_total(d_vlr_frete * d_calc_vol_real);
                                                                vs.setObs_sai(s_obs_sai);
                                                                //fim

                                                                //fim
                                                                daogvs.inserir(gvs);
                                                                daons.inserir(ns);
                                                                daovs.inserir(vs);
                                                                if (d_vlr_frete > 0) {
                                                                    //montando objeto financeiro

                                                                    f.setCredito(d_vlr_frete * (d_peso / 60));
                                                                    f.setDebito(0);
                                                                    f.setDocumento("VSF" + id_financeiro + s_ano);
                                                                    f.setDocumento_referencia(s_doc);
                                                                    f.setDt_financeiro(ManipulaDatas.converteStrSql(ss_data));
                                                                    f.setTipo_financeiro(1);
                                                                    f.setTipo_operacao(4);
                                                                    f.setUsuario_cria(Integer.parseInt(ManipulaArquivo.lerArquivoUsuario().trim()));
                                                                    f.setVlr_financeiro(d_vlr_frete * (d_peso / 60));
                                                                    f.setEmpresa(id_e);

                                                                    daof.inserir(f);
                                                                    //fim
                                                                }
                                                                JOptionPane.showMessageDialog(null, "Viagem realizada com sucesso!");
                                                                limpaCampos();

                                                            }
                                                        }

                                                    }

                                                }
                                            }

                                        }

                                    }
                                }

                            } catch (SQLException ex) {
                                Logger.getLogger(PGuiasSaidaNovaViagem.class
                                        .getName()).log(Level.SEVERE, null, ex);

                            }

                        }

                    }
                }
            }

        }
    }

    private javax.swing.JLabel lblNomeMotor;
    private javax.swing.JLabel lblPesoReal;
    private javax.swing.JLabel lblTipoFrete;
    private javax.swing.JLabel lblPlacaCam;
    private javax.swing.JLabel lblDocViagem;
    private javax.swing.JLabel lblQtdBag;
    private javax.swing.JLabel lblQtdSacas;
    private javax.swing.JLabel lblNumNota;
    private javax.swing.JLabel lblObs;
    private javax.swing.JTextField txtObs;
    private javax.swing.JTextField txtNumNota;
    private javax.swing.JTextField txtNomeMotor;
    private javax.swing.JTextField txtPesoReal;
    private javax.swing.JTextField txtPlacaCam;
    private javax.swing.JTextField txtDocViagem;
    private javax.swing.JRadioButton radioTipoFreteCA;
    private javax.swing.JRadioButton radioTipoFreteEmp;
    private javax.swing.JTextField txtVlrFrete;
    private javax.swing.JTextField txtQtdBag;
    private javax.swing.JTextField txtQtdSacas;
    private javax.swing.JRadioButton radioTipoEmbBag;
    private javax.swing.JRadioButton radioTipoEmbSacaria;
    private javax.swing.JRadioButton radioTipoEmbGranel;
    private javax.swing.ButtonGroup grupoBtsTipoEmb;
    private javax.swing.JButton btNovoChapa;
    private javax.swing.JButton btCancelarViagem;
    private javax.swing.JButton btFinalizarViagem;
    private javax.swing.JPanel panelFrete;
    private javax.swing.JPanel panelViagem;
    private javax.swing.JPanel panelChapas;
    private javax.swing.JPanel panelConsultar;
    private javax.swing.JLabel lblEmpChapa;
    private javax.swing.JLabel lblNomeChapa;
    private javax.swing.JLabel lblVlrSaca;
    private javax.swing.JLabel lblQtdSaca;
    private javax.swing.JTextField txtEmpChapa;
    private javax.swing.JTextField txtNomeChapa;
    private javax.swing.JTextField txtVlrSaca;
    private javax.swing.JTextField txtQtdSaca;
    private javax.swing.JButton btCancelarCadChapa;
    private javax.swing.JButton btSalvarChapa;
    private javax.swing.ButtonGroup grupoBtsFrete;
    private javax.swing.JLabel lblNumGuia;
    private javax.swing.JTextField txtNumGuia;
    private javax.swing.JComboBox comboEmpGuia;
    private javax.swing.JButton btConsultar;
    private javax.swing.JButton btDarSaida;
    private javax.swing.JTable tabela;
    private javax.swing.table.DefaultTableModel modelT;
    private javax.swing.JScrollPane scrollT;
    private javax.swing.JTable tabelaC;
    private javax.swing.table.DefaultTableModel modelTC;
    private javax.swing.JScrollPane scrollTC;
    private javax.swing.JButton btExcluirChapa;
    private javax.swing.JLabel lblData;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JLabel lblTipoVol;
    private javax.swing.JLabel lblQtdSacasFiscal;
    private javax.swing.JTextField txtQtdSacasFiscal;
    private int id_g = 0;
    private int id_e = 0;
    private int id_v = 0;
    private int status_check_fiscal = 0;
    private int linhas_tabela = 0;
    private boolean cond_chapa = false;
    private int li_select = -1;
    private int linhas_tabela_v = 0;
    private DAOEmpresas daoe = new DAOEmpresas();
    private javax.swing.JPanel panelFiscal;
    private javax.swing.JLabel lblPesoFiscal;
    private javax.swing.JTextField txtPesoFiscal;
    private javax.swing.JPanel panelEsq;
    private javax.swing.JCheckBox checkBoxFiscal;
    private javax.swing.JLabel lblFiscal;
    private javax.swing.JLabel lblVolReal;
    private javax.swing.JTextField txtVolReal;
    private javax.swing.JTable tabelaV;
    private javax.swing.table.DefaultTableModel modelV;
    private javax.swing.JScrollPane scrollV;
    private javax.swing.JComboBox comboTipoCafe;
    private DAOServicos daos = new DAOServicos();
    private javax.swing.JLabel lblTipoCafe;
    private javax.swing.JButton btAddVolume;
    private javax.swing.JLabel lblPesoVol;
    private javax.swing.JTextField txtPesoVol;
    private javax.swing.JButton btExcluirVol;
    private javax.swing.JLabel lblUltimaEnt;
    private javax.swing.JLabel lblGuiaUlt;
    private javax.swing.JLabel lblGuiaUltR;
    private javax.swing.JLabel lblPesoGuiaUlt;
    private javax.swing.JLabel lblPesoGuiaUltR;
    private javax.swing.JLabel lblDocUlt;
    private javax.swing.JLabel lblDocUltR;
    private javax.swing.JLabel lblVolRealUlt;
    private javax.swing.JLabel lblVolRealUltR;
    private javax.swing.JLabel lblPesoRealUlt;
    private javax.swing.JLabel lblPesoRealUltR;
    private javax.swing.JLabel lblNfeUlt;
    private javax.swing.JLabel lblNfeUltR;
    private javax.swing.JPanel panelInfo;
}
