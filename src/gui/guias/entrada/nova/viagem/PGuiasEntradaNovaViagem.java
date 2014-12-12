/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.guias.entrada.nova.viagem;

import classes.ComboBoxModel;
import classes.ManipulaArquivo;
import classes.ManipulaDatas;
import classes.NumerosFloatCVirgula;
import classes.NumerosInt;
import dao.cadastros.DAOEmpresas;
import dao.cadastros.DAOUsuarios;
import dao.entradas.DAOCtrlEmbEmp;
import dao.entradas.DAOCtrlEmbViagemEnt;
import dao.entradas.DAOEmpresaGuia;
import dao.entradas.DAOGuiaViagemEnt;
import dao.entradas.DAOGuias;
import dao.entradas.DAONfeEnt;
import dao.entradas.DAOPdcUnicaGuia;
import dao.entradas.DAOTrabViagem;
import dao.entradas.DAOViagemEnt;
import dao.financeiro.DAOFinanceiro;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import model.entradas.CtrlEmbEmp;
import model.entradas.CtrlEmbViagemEnt;
import model.entradas.GuiaViagemEnt;
import model.entradas.NfeEnt;
import model.entradas.PdcUnicaGuia;
import model.entradas.TrabViagem;
import model.entradas.ViagemEnt;
import model.financeiro.Financeiro;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PGuiasEntradaNovaViagem extends javax.swing.JPanel {

    public PGuiasEntradaNovaViagem() {
        initComponents();
    }

    private void initComponents() {
        //configuração do jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ENTRADA DE CAFÉ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes do jpanel principal
        panelConsultar = new javax.swing.JPanel();
        panelFrete = new javax.swing.JPanel();
        panelViagem = new javax.swing.JPanel();
        panelChapas = new javax.swing.JPanel();
        panelBtsChapa = new javax.swing.JPanel();
        panelFiscal = new javax.swing.JPanel();
        panelEsq = new javax.swing.JPanel();
        panelInfo = new javax.swing.JPanel();
        //fim

        //configuração componentes jpanel principal
        panelConsultar.setLayout(new MigLayout());
        panelFrete.setLayout(new MigLayout());
        panelViagem.setLayout(new MigLayout());
        panelChapas.setLayout(new MigLayout());
        panelFiscal.setLayout(new MigLayout());
        panelEsq.setLayout(new MigLayout());
        panelInfo.setLayout(new MigLayout());
        panelFiscal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados fiscais", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelConsultar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar guia", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelFrete.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do frete", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelViagem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados de entrada real", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelChapas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar chapa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelChapas.setVisible(false);
        panelBtsChapa.setLayout(new MigLayout());
        //fim

        //inicializando componentes jpanel consultar
        lblNumGuia = new javax.swing.JLabel("Informe o número da guia:");
        txtNumGuia = new javax.swing.JTextField(10);
        btConsultar = new javax.swing.JButton("Consultar");
        comboEmpGuia = new javax.swing.JComboBox<>();
        lblCheckFiscal = new javax.swing.JLabel("Entrada fiscal");
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

        //adicionando componentes jpanel consultar
        panelConsultar.add(lblNumGuia, "split 2");
        panelConsultar.add(txtNumGuia, "wrap");
        panelConsultar.add(comboEmpGuia, "wrap");
        panelConsultar.add(lblCheckFiscal, "split 3");
        panelConsultar.add(checkBoxFiscal);
        panelConsultar.add(btConsultar);
        //fim

        //inicializando componentes jpanel fiscal
        txtQtdSacasFiscal = new javax.swing.JTextField(7);
        lblQtdSacasFiscal = new javax.swing.JLabel("Qtd sacas NFe:");
        lblNumNota = new javax.swing.JLabel("Nº NFe:");
        txtNumNota = new javax.swing.JTextField(7);
        lblPesoFiscal = new javax.swing.JLabel("Peso fiscal:");
        txtPesoFiscal = new javax.swing.JTextField(7);
        //fim

        //configuração componentes jpanel fiscal
        txtQtdSacasFiscal.setDocument(new NumerosFloatCVirgula());
        txtPesoFiscal.setDocument(new NumerosFloatCVirgula());
        //fim

        //adicionando componentes jpanel fiscal
        panelFiscal.add(lblNumNota, "split 2, gapx 46");
        panelFiscal.add(txtNumNota, "wrap");
        panelFiscal.add(lblQtdSacasFiscal, "split 2");
        panelFiscal.add(txtQtdSacasFiscal, "wrap");
        panelFiscal.add(lblPesoFiscal, "split 2, gapx 19");
        panelFiscal.add(txtPesoFiscal);
        //fim

        //inicializando componentes jpanel viagem
        lblPesoReal = new javax.swing.JLabel("Peso real:");
        txtPesoReal = new javax.swing.JTextField(7);
        lblDocViagem = new javax.swing.JLabel("Documento:");
        lblTipoCafe = new javax.swing.JLabel("Tipo de café:");
        lblTipoEmb = new javax.swing.JLabel("Tipo de embalagem:");
        txtDocViagem = new javax.swing.JTextField(8);
        txtTipoCafe = new javax.swing.JTextField(8);
        radioTipoEmbBag = new javax.swing.JRadioButton("Bbags");
        radioTipoEmbGranel = new javax.swing.JRadioButton("Granel");
        radioTipoEmbSacaria = new javax.swing.JRadioButton("Sacaria");
        txtQtdBag = new javax.swing.JTextField(6);
        txtQtdSacas = new javax.swing.JTextField(6);
        grupoBtsEmb = new javax.swing.ButtonGroup();
        btNovoChapa = new javax.swing.JButton("Novo chapa");
        btFinalizarViagem = new javax.swing.JButton("Finalizar viagem");
        btCancelarViagem = new javax.swing.JButton("Cancelar");
        lblData = new javax.swing.JLabel("Data:");
        txtData = new javax.swing.JFormattedTextField();
        lblVolReal = new javax.swing.JLabel("Volume real:");
        txtVolReal = new javax.swing.JTextField(7);
        lblObs = new javax.swing.JLabel("Obs:");
        txtObs =new javax.swing.JTextField(30);
        //fim

        //configuração componentes jpanel viagem
        txtPesoReal.setDocument(new NumerosFloatCVirgula());
        txtDocViagem.setEditable(false);
        grupoBtsEmb.add(radioTipoEmbBag);
        grupoBtsEmb.add(radioTipoEmbGranel);
        grupoBtsEmb.add(radioTipoEmbSacaria);
        radioTipoEmbBag.setSelected(true);
        txtQtdBag.setDocument(new NumerosFloatCVirgula());
        txtQtdSacas.setDocument(new NumerosFloatCVirgula());
        txtQtdBag.setText("0");
        txtQtdSacas.setText("0");
        btNovoChapa.setVisible(false);
        txtVolReal.setEditable(false);
        try {
            txtData.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
            txtData.setPreferredSize(new java.awt.Dimension(70, 24));
            //fim
        } catch (ParseException ex) {
            Logger.getLogger(PGuiasEntradaNovaViagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
         //adicionando componentes jpanel viagem
         panelViagem.add(lblDocViagem, "split 2, gapx 4");
         panelViagem.add(txtDocViagem, "wrap");
         panelViagem.add(lblTipoCafe, "split 2");
         panelViagem.add(txtTipoCafe, "wrap");
         panelViagem.add(lblPesoReal, "split 4, gapx 15");
         panelViagem.add(txtPesoReal);
         panelViagem.add(lblVolReal);
         panelViagem.add(txtVolReal, "wrap");
         panelViagem.add(lblData, "split 2, gapx 43");
         panelViagem.add(txtData, "wrap");
         panelViagem.add(lblTipoEmb, "split 3");
         panelViagem.add(radioTipoEmbBag);
         panelViagem.add(txtQtdBag, "wrap");
         panelViagem.add(radioTipoEmbGranel, "wrap, gapx 119");
         panelViagem.add(radioTipoEmbSacaria, "split 3, gapx 119");
         panelViagem.add(txtQtdSacas);
         panelViagem.add(btNovoChapa, "wrap");
         panelViagem.add(btCancelarViagem, "split 2");
         panelViagem.add(btFinalizarViagem, "gapx 50");
         //fim
         */
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
        String[] columnNames = {"Nome", "Empresa", "Vlr p/ saca", "Qtd saca", "Custo R$"};
        Object[][] data = {};
        modelT = new javax.swing.table.DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabela = new javax.swing.JTable(modelT);
        tabela.setPreferredScrollableViewportSize(new java.awt.Dimension(730, 100));
        tabela.getColumnModel().getColumn(0).setMaxWidth(200);
        tabela.getColumnModel().getColumn(0).setMinWidth(200);
        tabela.getColumnModel().getColumn(1).setMaxWidth(200);
        tabela.getColumnModel().getColumn(1).setMinWidth(200);
        tabela.getColumnModel().getColumn(2).setMaxWidth(120);
        tabela.getColumnModel().getColumn(2).setMinWidth(120);
        tabela.getColumnModel().getColumn(3).setMaxWidth(110);
        tabela.getColumnModel().getColumn(3).setMinWidth(110);
        tabela.getColumnModel().getColumn(4).setMaxWidth(100);
        tabela.getColumnModel().getColumn(4).setMinWidth(100);

        scrollT = new javax.swing.JScrollPane(tabela);
        scrollT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chapas viagem", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));

        //alinhando os valores das celulas da Tabela à direita
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);

        tabela.getColumnModel().getColumn(0).setCellRenderer(r);
        tabela.getColumnModel().getColumn(1).setCellRenderer(r);
        tabela.getColumnModel().getColumn(2).setCellRenderer(r);
        tabela.getColumnModel().getColumn(3).setCellRenderer(r);
        tabela.getColumnModel().getColumn(4).setCellRenderer(r);
        //fim

        panelEsq.add(panelFiscal, "wrap");
        panelEsq.add(panelFrete);

        //adicionando componentes jpanel principal
        this.add(panelConsultar, "split 2");
        this.add(panelInfo, "wrap, top");
        //    this.add(panelFiscal,"");
        this.add(panelEsq, "split 3");
        this.add(panelViagem, "top");
        this.add(panelChapas, "wrap, top");
        this.add(scrollT, "split 2");
        this.add(panelBtsChapa);
        //fim

        //
        panelInfo.setVisible(false);
        panelFrete.setVisible(false);
        panelFiscal.setVisible(false);
        panelChapas.setVisible(false);
        panelViagem.setVisible(false);
        scrollT.setVisible(false);
        panelBtsChapa.setVisible(false);
        //

        //inicializando componentes bts chapa
        btExcluirChapa = new javax.swing.JButton("Excluir");
        //fim

        //adicionando componentes panel bts chapa
        panelBtsChapa.add(btExcluirChapa);
        //fim

        //evento bts
        radioTipoFreteCA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                radioTipoFreteCAActionPerformed(e);
            }
        });

        radioTipoFreteEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                radioTipoFreteEmpActionPerformed(e);
            }
        });

        radioTipoEmbBag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                radioTipoEmbBagActionPerformed(e);
            }
        });

        radioTipoEmbGranel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                radioTipoEmbGranelActionPerformed(e);
            }
        });

        radioTipoEmbSacaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                radioTipoEmbSacariaActionPerformed(e);
            }
        });

        btNovoChapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btNovoChapaActionPerformed(e);
            }
        });

        btConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btConsultarActionPerformed(e);
            }
        });

        btCancelarCadChapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelarChapaActionPerformed(e);
            }
        });

        btCancelarViagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelarViagemActionPerformed(e);
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
        //fim

    }

    private void radioTipoFreteCAActionPerformed(java.awt.event.ActionEvent evt) {
        txtVlrFrete.setVisible(true);
        txtVlrFrete.setText("0");
    }

    private void radioTipoFreteEmpActionPerformed(java.awt.event.ActionEvent evt) {
        txtVlrFrete.setVisible(false);
        txtVlrFrete.setText("");
    }

    private void radioTipoEmbBagActionPerformed(java.awt.event.ActionEvent evt) {
        /*    txtQtdBag.setVisible(true);
         txtQtdBag.setText("0");
         txtQtdSacas.setVisible(false);
         txtQtdSacas.setText(""); */
        btNovoChapa.setVisible(false);
    }

    private void radioTipoEmbGranelActionPerformed(java.awt.event.ActionEvent evt) {
        /*  txtQtdSacas.setVisible(false);
         txtQtdBag.setVisible(false);
         txtQtdBag.setText("");
         txtQtdSacas.setText(""); */
        btNovoChapa.setVisible(false);
    }

    private void radioTipoEmbSacariaActionPerformed(java.awt.event.ActionEvent evt) {
        /*  txtQtdBag.setVisible(false);
         txtQtdSacas.setVisible(true);
         txtQtdBag.setText("");
         txtQtdSacas.setText("0");*/
        btNovoChapa.setVisible(true);
    }

    private void montaPanelViagem(int tipo_usuario) {

        if (tipo_usuario == 1) {
            panelViagem.removeAll();
            //adicionando componentes jpanel viagem
            panelViagem.add(lblDocViagem, "split 2, gapx 4");
            panelViagem.add(txtDocViagem, "wrap");
            panelViagem.add(lblTipoCafe, "split 2");
            panelViagem.add(txtTipoCafe, "wrap");
            panelViagem.add(lblPesoReal, "split 4, gapx 15");
            panelViagem.add(txtPesoReal);
            panelViagem.add(lblVolReal);
            panelViagem.add(txtVolReal, "wrap");
            panelViagem.add(lblData, "split 2, gapx 43");
            panelViagem.add(txtData, "wrap");
            panelViagem.add(lblObs,"split 2");
            panelViagem.add(txtObs,"wrap");
            panelViagem.add(btCancelarViagem, "split 2");
            panelViagem.add(btFinalizarViagem, "gapx 50");
            //fim

            txtTipoCafe.setText("BENEFICIADO");
            panelChapas.setVisible(false);
            panelBtsChapa.setVisible(false);
            scrollT.setVisible(false);
            panelViagem.setVisible(true);
        } else {
            panelViagem.removeAll();
            //adicionando componentes jpanel viagem
            panelViagem.add(lblDocViagem, "split 2, gapx 4");
            panelViagem.add(txtDocViagem, "wrap");
            panelViagem.add(lblTipoCafe, "split 2");
            panelViagem.add(txtTipoCafe, "wrap");
            panelViagem.add(lblPesoReal, "split 4, gapx 15");
            panelViagem.add(txtPesoReal);
            panelViagem.add(lblVolReal);
            panelViagem.add(txtVolReal, "wrap");
            panelViagem.add(lblData, "split 2, gapx 43");
            panelViagem.add(txtData, "wrap");
            panelViagem.add(lblObs,"split 2");
            panelViagem.add(txtObs,"wrap");
            panelViagem.add(lblTipoEmb, "split 3");
            panelViagem.add(radioTipoEmbBag);
            panelViagem.add(txtQtdBag, "wrap");
            panelViagem.add(radioTipoEmbGranel, "wrap, gapx 119");
            panelViagem.add(radioTipoEmbSacaria, "split 3, gapx 119");
            panelViagem.add(txtQtdSacas);
            panelViagem.add(btNovoChapa, "wrap");
            panelViagem.add(btCancelarViagem, "split 2");
            panelViagem.add(btFinalizarViagem, "gapx 50");
            txtTipoCafe.setText("BICA");

            panelBtsChapa.setVisible(true);
            scrollT.setVisible(true);
            panelViagem.setVisible(true);
            //fim
        }
    }

    private void montaPanelInfo(int id_guia) {
        DAOGuias daog = new DAOGuias();
        DAOGuiaViagemEnt daogve = new DAOGuiaViagemEnt();
        DAOViagemEnt daove = new DAOViagemEnt();

        //inicializando componentes panel info
        lblGuiaUlt = new javax.swing.JLabel("Guia:");
        lblGuiaUltR = new javax.swing.JLabel(txtNumGuia.getText().toString());
        lblPesoGuiaUlt = new javax.swing.JLabel("Peso total calculado:");
        lblPesoRealUlt = new javax.swing.JLabel("Peso total entrada:");
        lblDocUlt = new javax.swing.JLabel("Última entrada:");
        lblNfeUlt = new javax.swing.JLabel("NFe:");

        try {
            double d_peso_guia = daog.retornaPeso(id_guia);
            double d_peso_total_entrada = daogve.retornaQtdSacasTotal(id_guia) * 60;
            String doc = daove.retornaUltimoDocViagemGuia(id_guia);
            ViagemEnt ve = daove.retornaDadosInfo(doc);

            lblPesoGuiaUltR = new javax.swing.JLabel("" + NumberFormat.getIntegerInstance().format(d_peso_guia));
            lblPesoRealUltR = new javax.swing.JLabel("" + NumberFormat.getIntegerInstance().format(d_peso_total_entrada));
            lblDocUltR = new javax.swing.JLabel("" + daove.retornaUltimoDocViagemGuia(id_guia));
            lblNfeUltR = new javax.swing.JLabel("" + ve.getNum_nota());

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
            Logger.getLogger(PGuiasEntradaNovaViagem.class.getName()).log(Level.SEVERE, null, ex);
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

    private void btNovoChapaActionPerformed(java.awt.event.ActionEvent evt) {
        String s_peso = txtPesoReal.getText().toString();
        String s_qtd_sacaria = txtQtdSacas.getText().toString();

        if (s_peso.isEmpty() || s_peso.equals(null) || s_qtd_sacaria.isEmpty() || s_qtd_sacaria.equals(null)) {
            JOptionPane.showMessageDialog(null, "Campo(s) de peso e/ou qtd de sacaria vazio(s)");
        } else {
            s_peso = s_peso.replace(",", ".");
            double d_peso = Double.parseDouble(s_peso);
            double d_qtd_sacaria = Double.parseDouble(s_qtd_sacaria);

            if (d_qtd_sacaria <= 0 || d_peso <= 0) {
                JOptionPane.showMessageDialog(null, "Valores nulos ou zerados para o peso e/ou a qtd de sacaria!");
            } else {
                if ((d_peso / 60) < d_qtd_sacaria) {
                    JOptionPane.showMessageDialog(null, "Quantidade de sacaria excedendo o peso!");
                } else {
                    int resp = 0;
                    if (cond_ver == false) {
                        resp = JOptionPane.showConfirmDialog(null, "Você confirma a qtd de sacaria e o peso total? \n" + "Qtd de sacarias: " + d_qtd_sacaria + "\n" + "Peso: " + d_peso + "KG", "Confirmar", JOptionPane.YES_NO_OPTION);
                    }
                    if (resp == 0) {
                        cond_ver = true;
                        panelChapas.setVisible(true);
                        txtPesoReal.setEditable(false);
                        txtQtdSacas.setEditable(false);
                        btCancelarViagem.setEnabled(false);
                        btFinalizarViagem.setEnabled(false);
                        btExcluirChapa.setEnabled(false);
                    }

                }

            }

        }

    }

    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_num_guia = txtNumGuia.getText().toString();
        DAOEmpresaGuia daoeg = new DAOEmpresaGuia();
        DAOGuiaViagemEnt daogve = new DAOGuiaViagemEnt();
        DAOGuias daog = new DAOGuias();
        DAOUsuarios daou = new DAOUsuarios();

        if (checkBoxFiscal.isSelected() == true) {
            status_check_fiscal = 1;
        } else {
            status_check_fiscal = 0;
        }

        try {
            int id_emp = daoe.retornaIDPorNome(comboEmpGuia.getSelectedItem().toString());
            if (daoeg.consultaGuiaEmp(s_num_guia, id_emp) == false) {
                JOptionPane.showMessageDialog(null, "Essa guia não existe para a empresa " + comboEmpGuia.getSelectedItem().toString());
            } else {
                if (((daou.retornaTipo(Integer.parseInt(ManipulaArquivo.lerArquivoUsuario())) != 4) && (daou.retornaTipo(Integer.parseInt(ManipulaArquivo.lerArquivoUsuario())) != 1)) && status_check_fiscal == 1) {
                    JOptionPane.showMessageDialog(null, "Você não tem permissão para dar essa saída!");
                } else {
                    panelFrete.setVisible(true);
                    scrollT.setVisible(true);
                    panelFiscal.setVisible(true);
                    btConsultar.setVisible(false);

                    montaPanelViagem(status_check_fiscal);
                    int id_guia = daog.retornaIdGuiaEmp(s_num_guia, id_emp);
                    int id_viagem = daogve.retornaIdMaisUm(id_guia);
                    String s_nosso_num = daog.retornaNossoNum(s_num_guia, id_emp);
                    String s_doc = s_nosso_num + "-E" + id_viagem;
                    txtDocViagem.setText(s_doc);
                    txtNumGuia.setEditable(false);
                    id_v = id_viagem;
                    id_g = id_guia;
                    id_e = id_emp;
                    montaPanelInfo(id_guia);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(PGuiasEntradaNovaViagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btCancelarViagemActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja cancelar a viagem?", "Cancelar viagem", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            limpaCampos();
        }
    }

    private void btCancelarChapaActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja cancelar a inserção de chapa?", "Cancelar chapa", JOptionPane.YES_NO_OPTION);
        int li = tabela.getRowCount();
        boolean cond_cancel = false;
        if (li <= 0) {
            cond_cancel = true;
        }

        if (resp == 0) {
            limpaCamposChapa();
            if (cond_cancel == true) { //se cancelar a insersão de chapa e nao houver registro na tabela, deixa colocar peso e qtd de sacaria outra vez
                txtPesoReal.setEditable(true);
                txtQtdSacas.setEditable(true);
                cond_ver = false;
            }
        }
    }

    private void btSalvarChapaActionPerformed(java.awt.event.ActionEvent evt) {
        String s_nome = txtNomeChapa.getText().toString().toUpperCase();
        String s_emp = txtEmpChapa.getText().toString().toUpperCase();
        String s_vlr_saca = txtVlrSaca.getText().toString();
        String s_qtd_saca = txtQtdSaca.getText().toString();

        if (s_nome.isEmpty() || s_nome.equals(null) || s_emp.isEmpty() || s_emp.equals(null) || s_vlr_saca.isEmpty()
                || s_vlr_saca.equals(null) || s_qtd_saca.isEmpty() || s_qtd_saca.equals(null)) {
            JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
        } else {
            DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
            String[] linha = {null, null, null, null, null};
            s_vlr_saca = s_vlr_saca.replace(",", ".");
            s_qtd_saca = s_qtd_saca.replace(",", ".");
            double d_vlr_saca = Double.parseDouble(s_vlr_saca);
            double d_qtd_saca = Double.parseDouble(s_qtd_saca);

            String s_qtd_sacass = txtQtdSacas.getText().toString().trim();
            s_qtd_sacass = s_qtd_sacass.replace(",", ".");

            double d_qtd_sacaria_ent = Double.parseDouble(s_qtd_sacass);

            if (d_qtd_sacaria_ent < d_qtd_saca) {
                JOptionPane.showMessageDialog(null, "Qtd de sacas inválida!");
            } else {
                dtm.addRow(linha);
                tabela.setModel(dtm);
                DecimalFormat df = new DecimalFormat("0.##");
                tabela.setValueAt(s_nome, linhas_tabela, 0);
                tabela.setValueAt(s_emp, linhas_tabela, 1);
                tabela.setValueAt(df.format(d_vlr_saca), linhas_tabela, 2);
                tabela.setValueAt(d_qtd_saca, linhas_tabela, 3);
                tabela.setValueAt(df.format(d_vlr_saca * d_qtd_saca), linhas_tabela, 4);
                linhas_tabela++;
                limpaCamposChapa();
            }

        }
    }

    private void limpaCampos() {
        txtDocViagem.setText("");
        txtNomeMotor.setText("");
        txtNumGuia.setText("");
        txtPlacaCam.setText("");
        txtQtdBag.setText("0");
        txtQtdSacas.setText("0");
        txtTipoCafe.setText("BENEFICIADO");
        txtVlrFrete.setText("0");
        txtNumNota.setText("");
        txtObs.setText("");
        txtData.setText("");
        txtData.setText("");
        txtPesoReal.setText("");
        txtVolReal.setText("");
        status_check_fiscal = 0;
        txtPesoFiscal.setText("");
        panelInfo.removeAll();
        txtQtdSacasFiscal.setText("");
        txtQtdSacas.setEnabled(true);
        panelChapas.setVisible(false);
        panelViagem.setVisible(false);
        panelFrete.setVisible(false);
        panelBtsChapa.setVisible(false);
        panelFiscal.setVisible(false);
        panelInfo.setVisible(false);
        scrollT.setVisible(false);
        btConsultar.setVisible(true);
        cond_ver = false;
        txtNumGuia.setEditable(true);
        txtPesoReal.setEditable(true);
        txtQtdSacas.setEditable(true);
        radioTipoFreteEmp.setSelected(true);
        id_g = 0;
        id_v = 0;
        id_e = 0;
        txtPesoReal.setText("");
        DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
        dtm.setNumRows(0);
    }

    private void limpaCamposChapa() {
        panelChapas.setVisible(false);
        txtNomeChapa.setText("");
        txtEmpChapa.setText("");
        txtVlrSaca.setText("");
        txtQtdSaca.setText("");
        btFinalizarViagem.setEnabled(true);
        btCancelarViagem.setEnabled(true);
        //   txtQtdSacas.setEditable(true);
        //  txtPesoReal.setEditable(true);
        btExcluirChapa.setEnabled(true);

    }

    private void btExcluirChapaActionPerformed(java.awt.event.ActionEvent evt) {
        int li = tabela.getSelectedRow();

        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            int resp = JOptionPane.showConfirmDialog(null, "Desje excluir o registro?", "Excluir registro", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {
                DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
                dtm.removeRow(li);
                linhas_tabela--;
                int cont_li = tabela.getRowCount();
                if (cont_li <= 0) {
                    txtPesoReal.setEditable(true);
                    txtQtdSacas.setEditable(true);

                }
            }

        }
    }

    private void btFinalizarViagemActionPerformed(java.awt.event.ActionEvent evt) {
        String s_nome_motor = txtNomeMotor.getText().toString().toUpperCase();
        String s_peso = txtPesoReal.getText().toString().trim();
        String s_placa = txtPlacaCam.getText().toString().trim();
        String s_doc = txtDocViagem.getText().toString();
        String s_tipo_cafe = txtTipoCafe.getText().toString().toUpperCase().trim();
        String s_vlr_frete = txtVlrFrete.getText().toString().trim();
        String s_qtd_bag = txtQtdBag.getText().toString().trim();
        String s_qtd_sacaria = txtQtdSacas.getText().toString().trim();
        String s_data = ManipulaDatas.retornaDataAtual();
        String s_num_nota = txtNumNota.getText().toString().trim();
        String ss_data = txtData.getText().toString().trim();
        String s_qtd_sacas_fiscal = txtQtdSacasFiscal.getText().toString().trim();
        String s_peso_fiscal = txtPesoFiscal.getText().toString().trim();
        String s_dt_atual = ManipulaDatas.retornaDataAtual().trim();
        String s_ano = s_dt_atual.substring(6, 10).trim();
        int i_tipo_frete = 0;

        double d_qtd_bag = 0;
        double d_qtd_sacaria = 0;

        if (s_nome_motor.isEmpty() || s_nome_motor.equals(null) || s_peso.isEmpty() || s_peso.equals(null)
                || s_tipo_cafe.isEmpty() || s_tipo_cafe.equals(null)
                || ss_data.isEmpty() || ss_data.equals(null) || s_num_nota.isEmpty() || s_num_nota.equals(null)
                || s_peso_fiscal.isEmpty() || s_peso_fiscal.equals(null)) {
            JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
        } else {
            s_peso = s_peso.replace(",", ".");
            double d_peso = Double.parseDouble(s_peso);
            if (ManipulaDatas.validaData(ss_data) == false) {
                JOptionPane.showMessageDialog(null, "Data inválida!");
            } else {
                if (d_peso <= 0) {
                    JOptionPane.showMessageDialog(null, "Peso inválido!");
                } else {

                    if ((radioTipoFreteCA.isSelected() == true) && (s_vlr_frete.isEmpty() || s_vlr_frete.equals(null))) {
                        JOptionPane.showMessageDialog(null, "Valor do frete inválido ou nulo!");
                    } else {
                        if (s_vlr_frete.isEmpty() || s_vlr_frete.equals(null)) {
                            s_vlr_frete = "0";
                        }
                        s_vlr_frete = s_vlr_frete.replace(",", ".");
                        double d_vlr_frete = Double.parseDouble(s_vlr_frete);
                        if ((radioTipoFreteCA.isSelected() == true) && (d_vlr_frete <= 0)) {
                            JOptionPane.showMessageDialog(null, "O valor do frete não pode ser 0 ou menor!");
                        } else {

                            if ((radioTipoEmbBag.isSelected() == true) && (s_qtd_bag.isEmpty() || s_qtd_bag.equals(null)) && (status_check_fiscal != 1)) {
                                JOptionPane.showMessageDialog(null, "Informe uma quantidade de big bags!");
                            } else if ((radioTipoEmbSacaria.isSelected() == true) && (s_qtd_sacaria.isEmpty() || s_qtd_sacaria.equals(null)) && (status_check_fiscal != 1)) {
                                JOptionPane.showMessageDialog(null, "Informe uma quantidade de big sacaria!");
                            } else {
                                if (s_qtd_bag.isEmpty() || s_qtd_bag.equals(null)) {
                                    s_qtd_bag = "0";
                                }
                                if (s_qtd_sacaria.isEmpty() || s_qtd_sacaria.equals(null)) {
                                    s_qtd_sacaria = "0";
                                }
                                d_qtd_bag = Double.parseDouble(s_qtd_bag);
                                d_qtd_sacaria = Double.parseDouble(s_qtd_sacaria);

                                if ((radioTipoEmbBag.isSelected() == true) && d_qtd_bag <= 0 && (status_check_fiscal != 1)) {
                                    JOptionPane.showMessageDialog(null, "Informe uma qtd de big bag maior que 0!");
                                } else if ((radioTipoEmbSacaria.isSelected() == true) && d_qtd_sacaria <= 0 && (status_check_fiscal != 1)) {
                                    JOptionPane.showMessageDialog(null, "Informe uma qtd de sacaria maior que 0!");
                                } else {
                                    if (radioTipoFreteCA.isSelected() == true) {
                                        i_tipo_frete = 1;
                                    } else if (radioTipoFreteEmp.isSelected() == true) {
                                        i_tipo_frete = 2;
                                    } else {
                                        i_tipo_frete = 3;
                                    }

                                    DAOViagemEnt daove = new DAOViagemEnt();
                                    DAOGuiaViagemEnt daogve = new DAOGuiaViagemEnt();
                                    GuiaViagemEnt gve = new GuiaViagemEnt();
                                    ViagemEnt ve = new ViagemEnt();
                                    DAONfeEnt daone = new DAONfeEnt();
                                    NfeEnt ne = new NfeEnt();
                                    DAOPdcUnicaGuia daopug = new DAOPdcUnicaGuia();
                                    PdcUnicaGuia pug = new PdcUnicaGuia();
                                    DAOFinanceiro daof = new DAOFinanceiro();
                                    Financeiro f = new Financeiro();
                                    DAOGuias daog = new DAOGuias();

                                    try {
                                        boolean cond_num_nota = daone.consultaNumNota(s_num_nota);
                                        int id_financeiro = daof.retornaUltimaID();

                                        if (cond_num_nota == true && !s_num_nota.equals("PENDENTE")) {
                                            JOptionPane.showMessageDialog(null, "Nota fiscal já existe no sistema!");
                                        } else {
                                            if (s_qtd_sacas_fiscal.isEmpty() || s_qtd_sacas_fiscal.equals(null)) {
                                                JOptionPane.showMessageDialog(null, "Qtd de sacas fiscal nula ou inválidas!");
                                            } else {

                                                s_qtd_sacas_fiscal = s_qtd_sacas_fiscal.replace(",", ".");
                                                double d_qtd_sacas_fiscal = Double.parseDouble(s_qtd_sacas_fiscal);

                                                if (d_qtd_sacas_fiscal <= 0) {
                                                    JOptionPane.showMessageDialog(null, "Qtd de sacas fiscal é menor ou igual a 0!");
                                                } else {
                                                    s_peso_fiscal = s_peso_fiscal.replace(",", ".");
                                                    double d_peso_fiscal = Double.parseDouble(s_peso_fiscal);

                                                    if (d_peso_fiscal <= 0) {
                                                        JOptionPane.showMessageDialog(null, "Peso fiscal inválido! Menor ou igual a zero!");
                                                    } else {

                                                        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente finalizar a viagem? " + "\n" + "Peso fiscal: " + d_peso_fiscal + "\n" + "Peso real: " + d_peso + "\n" + "NFe: " + s_num_nota, "Finalizar viagem", JOptionPane.YES_NO_OPTION);

                                                        if (resp == 0) {
                                                            DecimalFormat df = new DecimalFormat("#.0");
                                                            double d_peso_baixado = daogve.retornaQtdSacasTotal(id_g) * 60;
                                                            double d_peso_guia = daog.retornaQtdSacas(id_g) * 60;
                                                            double calc = d_peso_guia - (d_peso_baixado + d_peso);
                                                            boolean condicao_peso_excede = false;

                                                            if (calc < 0) {
                                                                int respp = JOptionPane.showConfirmDialog(null, "O peso atual " + df.format(d_peso_baixado + d_peso) + "\n" + "excede o peso da guia " + df.format(d_peso_guia) + "\n" + "Deseja continuar?", "Peso excedendo", JOptionPane.YES_NO_OPTION);
                                                                if (respp == 0) {
                                                                    condicao_peso_excede = true;
                                                                } else {
                                                                    condicao_peso_excede = false;
                                                                }
                                                            } else {
                                                                condicao_peso_excede = true;
                                                            }

                                                            if (condicao_peso_excede == true) {

                                                                //montando objeto da nfe
                                                                ne.setDt_nfe_ent(ManipulaDatas.converteStrSql(ss_data));
                                                                ne.setId_guia(id_g);
                                                                ne.setId_viagem_ent(id_v);
                                                                ne.setNum_nota(s_num_nota);
                                                                ne.setStatus(1);
                                                                ne.setQtd_sacas_ent_fiscal(d_qtd_sacas_fiscal);
                                                                ne.setPeso_nfe_ent(d_peso_fiscal);
                                                                //fim
                                                                //alterando o peso para a producao da guia
                                                                double peso_atual = daopug.retornaPesoAtualPdc(id_g); //pegando o peso atual
                                                                peso_atual = peso_atual + d_peso; //somando com o peso da viagem
                                                                daopug.alteraPesoPdc(id_g, peso_atual); //alterando o peso

                                                                double saldo_atual = daopug.retornaSaldoAtualPdc(id_g); //pegando o saldo atual
                                                                double calc_saldo = saldo_atual + d_peso; //somando com o peso da viagem
                                                                daopug.alteraSaldoPdc(id_g, calc_saldo); //alterando o saldo
                                                                //fim

                                                                //montando objeto guia viagem ent
                                                                gve.setId_guia(id_g);
                                                                gve.setId_viagem(id_v);
                                                                gve.setNum_guia(txtNumGuia.getText().toString());
                                                                //fim

                                                                int cont_tab = 0;
                                                                //montando obbjetos de trab viagem e inserindo
                                                                for (int i = 0; i < tabela.getRowCount(); i++) {

                                                                    DAOTrabViagem daotv = new DAOTrabViagem();
                                                                    TrabViagem tv = new TrabViagem();
                                                                    tv.setDocumento_viagem(s_doc);
                                                                    tv.setId_guia(id_g);
                                                                    tv.setId_viagem(id_v);
                                                                    tv.setId_tipo_viagem(1);
                                                                    tv.setNome_trab_tv(tabela.getValueAt(cont_tab, 0).toString());
                                                                    tv.setEmpresa_tv(tabela.getValueAt(cont_tab, 1).toString());
                                                                    String s_vlr_p_saca = tabela.getValueAt(cont_tab, 2).toString();
                                                                    s_vlr_p_saca = s_vlr_p_saca.replace(",", ".");
                                                                    double d_vlr_p_saca = Double.parseDouble(s_vlr_p_saca);
                                                                    tv.setVlr_saca_tv(d_vlr_p_saca);
                                                                    double d_qtd_saca_chapa = Double.parseDouble(tabela.getValueAt(cont_tab, 3).toString());
                                                                    tv.setQtd_saca_tv(d_qtd_saca_chapa);
                                                                    tv.setVlr_total_tv(d_vlr_p_saca * d_qtd_saca_chapa);
                                                                    daotv.inserir(tv);

                                                                    int id_financeiroc = daof.retornaUltimaID(); //id para chapas

                                                                    f.setCredito(d_vlr_p_saca * d_qtd_saca_chapa);
                                                                    f.setDebito(0);
                                                                    f.setDocumento("VEC" + id_financeiroc + s_ano);
                                                                    f.setDocumento_referencia(s_doc);
                                                                    f.setDt_financeiro(ManipulaDatas.converteStrSql(ss_data));
                                                                    f.setTipo_financeiro(2);
                                                                    f.setTipo_operacao(3);
                                                                    f.setUsuario_cria(Integer.parseInt(ManipulaArquivo.lerArquivoUsuario().trim()));
                                                                    f.setVlr_financeiro(d_vlr_p_saca * d_qtd_saca_chapa);
                                                                    f.setEmpresa(id_e);
                                                                    
                                                                    daof.inserir(f);

                                                                    cont_tab++; //contador linhas jtable
                                                                }
                                                                //fim

                                                                if (d_qtd_bag > 0) {
                                                                    DAOCtrlEmbEmp daocee = new DAOCtrlEmbEmp();
                                                                    DAOCtrlEmbViagemEnt daoceve = new DAOCtrlEmbViagemEnt();
                                                                    CtrlEmbEmp cee = new CtrlEmbEmp();
                                                                    CtrlEmbViagemEnt ceve = new CtrlEmbViagemEnt();

                                                                    ceve.setId_guia(id_g);
                                                                    ceve.setId_tipo_emb(1);
                                                                    ceve.setId_viagem(id_v);
                                                                    ceve.setQtd_emb(d_qtd_bag);

                                                                    double calc_t = daocee.retornaQtdTotal(1, id_e) + d_qtd_bag;
                                                                    daocee.alteraQtdTotal(id_e, 1, calc_t);
                                                                    daoceve.inserir(ceve);

                                                                }

                                                                if (d_qtd_sacaria > 0) {
                                                                    DAOCtrlEmbEmp daocee = new DAOCtrlEmbEmp();
                                                                    DAOCtrlEmbViagemEnt daoceve = new DAOCtrlEmbViagemEnt();
                                                                    CtrlEmbEmp cee = new CtrlEmbEmp();
                                                                    CtrlEmbViagemEnt ceve = new CtrlEmbViagemEnt();

                                                                    ceve.setId_guia(id_g);
                                                                    ceve.setId_tipo_emb(3);
                                                                    ceve.setId_viagem(id_v);
                                                                    ceve.setQtd_emb(d_qtd_sacaria);

                                                                    double calc_t = daocee.retornaQtdTotal(3, id_e) + d_qtd_sacaria;
                                                                    daocee.alteraQtdTotal(id_e, 3, calc_t);
                                                                    daoceve.inserir(ceve);

                                                                }

                                                                double d_calc_vol_total = 0;
                                                                if (status_check_fiscal == 1) {
                                                                    d_calc_vol_total = d_peso / 60;
                                                                } else {//se nao for fiscal retira o peso dos bags e sacarias
                                                                    d_calc_vol_total = (d_peso - (d_qtd_bag * 5) - (d_qtd_sacaria * 0.5)) / 60;
                                                                } 

                                                                //montando objeto da viagem
                                                                ve.setDoc_viagem_ent(s_doc);
                                                                ve.setDt_ent(ManipulaDatas.converteStrSql(ss_data));
                                                                ve.setNome_motor_ent(s_nome_motor);
                                                                ve.setPeso_ent(d_peso);
                                                                ve.setPlaca_cam_ent(s_placa);
                                                                ve.setQtd_sacas_ent(d_calc_vol_total);
                                                                ve.setTipo_cafe_ent(s_tipo_cafe);
                                                                ve.setTipo_frete_ent(i_tipo_frete);
                                                                ve.setUsuario_cad_ent(ManipulaArquivo.lerArquivoUsuario());
                                                                ve.setVlr_frete_total(d_vlr_frete * (d_calc_vol_total));
                                                                ve.setVlr_frete_unit(d_vlr_frete);
                                                                ve.setNum_nota(s_num_nota);
                                                                //fim objeto viagem

                                                                daogve.inserir(gve);
                                                                daove.inserir(ve);
                                                                daone.inserir(ne);
                                                                if (d_vlr_frete > 0) {
                                                                    //montando objeto financeiro

                                                                    f.setCredito(d_vlr_frete * (d_calc_vol_total));
                                                                    f.setDebito(0);
                                                                    f.setDocumento("VEF" + id_financeiro + s_ano);
                                                                    f.setDocumento_referencia(s_doc);
                                                                    f.setDt_financeiro(ManipulaDatas.converteStrSql(ss_data));
                                                                    f.setTipo_financeiro(1);
                                                                    f.setTipo_operacao(2);
                                                                    f.setUsuario_cria(Integer.parseInt(ManipulaArquivo.lerArquivoUsuario().trim()));
                                                                    f.setVlr_financeiro(d_vlr_frete * (d_calc_vol_total));
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

                                    } catch (SQLException SQL) {
                                        Logger.getLogger(PGuiasEntradaNovaViagem.class.getName()).log(Level.SEVERE, null, SQL);
                                        JOptionPane.showMessageDialog(null, "Erro na inserção de dados!");

                                    }
                                }

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
    private javax.swing.JLabel lblNumGuia;
    private javax.swing.JLabel lblDocViagem;
    private javax.swing.JLabel lblTipoCafe;
    private javax.swing.JLabel lblTipoEmb;
    private javax.swing.JLabel lblNumNota;
    private javax.swing.JTextField txtNumNota;
    private javax.swing.JTextField txtNomeMotor;
    private javax.swing.JTextField txtPesoReal;
    private javax.swing.JTextField txtNumGuia;
    private javax.swing.JTextField txtPlacaCam;
    private javax.swing.JTextField txtDocViagem;
    private javax.swing.JTextField txtTipoCafe;
    private javax.swing.JPanel panelFrete;
    private javax.swing.JPanel panelEsq;
    private javax.swing.JPanel panelConsultar;
    private javax.swing.JPanel panelViagem;
    private javax.swing.JPanel panelChapas;
    private javax.swing.JPanel panelBtsChapa;
    private javax.swing.JButton btConsultar;
    private javax.swing.JLabel lblEmpChapa;
    private javax.swing.JLabel lblNomeChapa;
    private javax.swing.JLabel lblVlrSaca;
    private javax.swing.JLabel lblQtdSaca;
    private javax.swing.JTextField txtEmpChapa;
    private javax.swing.JTextField txtNomeChapa;
    private javax.swing.JTextField txtVlrSaca;
    private javax.swing.JTextField txtQtdSaca;
    private javax.swing.JPanel panelFiscal;
    private javax.swing.JButton btCancelarCadChapa;
    private javax.swing.JButton btSalvarChapa;
    private javax.swing.JButton btExcluirChapa;
    private javax.swing.JComboBox comboEmpGuia;
    private javax.swing.JRadioButton radioTipoFreteCA;
    private javax.swing.JRadioButton radioTipoFreteEmp;
    private javax.swing.JRadioButton radioTipoEmbBag;
    private javax.swing.JRadioButton radioTipoEmbGranel;
    private javax.swing.JRadioButton radioTipoEmbSacaria;
    private javax.swing.ButtonGroup grupoBtsEmb;
    private javax.swing.ButtonGroup grupoBtsFrete;
    private javax.swing.JTextField txtQtdBag;
    private javax.swing.JTextField txtQtdSacas;
    private javax.swing.JTextField txtVlrFrete;
    private javax.swing.JButton btNovoChapa;
    private javax.swing.JButton btCancelarViagem;
    private javax.swing.JButton btFinalizarViagem;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel lblPesoFiscal;
    private javax.swing.JTextField txtPesoFiscal;
    private javax.swing.table.DefaultTableModel modelT;
    private javax.swing.JScrollPane scrollT;
    private javax.swing.JLabel lblData;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JLabel lblQtdSacasFiscal;
    private javax.swing.JTextField txtQtdSacasFiscal;
    private javax.swing.JLabel lblObs;
    private javax.swing.JTextField txtObs;
    private DAOEmpresas daoe = new DAOEmpresas();
    private int linhas_tabela = 0;
    private int id_g = 0;
    private int id_v = 0;
    private int id_e = 0;
    private boolean cond_ver = false; //condicao msg pra add chapa
    private javax.swing.JLabel lblCheckFiscal;
    private javax.swing.JCheckBox checkBoxFiscal;
    private javax.swing.JLabel lblVolReal;
    private javax.swing.JTextField txtVolReal;
    private int status_check_fiscal = 0;
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
