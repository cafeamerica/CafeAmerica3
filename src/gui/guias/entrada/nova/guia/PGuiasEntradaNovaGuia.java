/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.guias.entrada.nova.guia;

import classes.ComboBoxModel;
import classes.ManipulaArquivo;
import classes.ManipulaDatas;
import classes.NumerosFloatCVirgula;
import classes.NumerosInt;
import dao.cadastros.DAOEmpresas;
import dao.cadastros.DAOProdutores;
import dao.entradas.DAOEmpresaGuia;
import dao.entradas.DAOGuias;
import dao.entradas.DAOPdcUnicaGuia;
import dao.entradas.DAOProdutorGuia;
import dao.entradas.DAOServicos;
import dao.entradas.DAOServicosGuia;
import dao.financeiro.DAOFinanceiro;
import gui.inicio.FInicio;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import model.entradas.EmpresaGuia;
import model.entradas.Guias;
import model.entradas.PdcUnicaGuia;
import model.entradas.ProdutorGuia;
import model.entradas.ServicosGuia;
import model.financeiro.Financeiro;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PGuiasEntradaNovaGuia extends javax.swing.JPanel {

    public PGuiasEntradaNovaGuia() {
        initComponents();
    }

    private void initComponents() {
        //configurações panel principal
        this.setLayout(new MigLayout());
        //  this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NOVA GUIA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        tabs = new javax.swing.JTabbedPane();

        //fim
        //inicializando componentes panel principal
        panelEmp = new javax.swing.JPanel();
        panelGuia = new javax.swing.JPanel();
        panelAlterar = new javax.swing.JPanel();
        panelPrincipal = new javax.swing.JPanel();
        //fim

        //configurações componentens panel principal
        panelEmp.setLayout(new MigLayout());
        panelEmp.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da empresa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelGuia.setLayout(new MigLayout());
        panelGuia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da guia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelPrincipal.setLayout(new MigLayout());
        panelPrincipal.setName("Nova guia");
        panelAlterar.setName("Alterar guia");
        panelAlterar.setLayout(new MigLayout());
        //fim

        //inicializando compoentes panel emp
        lblEmp = new javax.swing.JLabel("Empresa:");
        lblPdtr = new javax.swing.JLabel("Produtor:");
        comboEmpGuia = new javax.swing.JComboBox<>();
        comboPdtrGuia = new javax.swing.JComboBox<>();
        //fim

        //configuração componentes panel emp
        ComboBoxModel cbm_emp = new ComboBoxModel();
        ComboBoxModel cbm_pdtr = new ComboBoxModel();
        try {
            //combo emp
            ArrayList empresas = daoe.consultarEmpresas();
            cbm_emp.montaCombo();
            cbm_emp.listaDadosCombo(empresas);
            comboEmpGuia = cbm_emp;
            //fim

            //combo pdtr
            ArrayList produtores = daopdtr.consultarProdutores();
            cbm_pdtr.montaCombo();
            cbm_pdtr.listaDadosCombo(produtores);
            comboPdtrGuia = cbm_pdtr;
            //fim
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasEntradaNovaGuia.class.getName()).log(Level.SEVERE, null, ex);
        }
        //fim

        //adicionando componetens panel emp
        panelEmp.add(lblEmp, "split 2");
        panelEmp.add(comboEmpGuia, "wrap");
        panelEmp.add(lblPdtr, "split 2");
        panelEmp.add(comboPdtrGuia);
        //fim

        //inicializando componentes panel guia
        lblNumGuia = new javax.swing.JLabel("Número da guia:");
        lblQtdSacas = new javax.swing.JLabel("Quantidade de sacas:");
        lblServGuia = new javax.swing.JLabel("Serviços da guia:");
        lblVlrGuia = new javax.swing.JLabel("Valor por saca:");
        txtNumGuia = new javax.swing.JTextField(10);
        txtQtdSacas = new javax.swing.JTextField(6);
        txtVlrGuia = new javax.swing.JTextField(6);
        btAddServ = new javax.swing.JButton("Adicionar serviço");
        comboTipoCafe = new javax.swing.JComboBox<>();
        btExcluirLinha = new javax.swing.JButton("X");
        btCancelar = new javax.swing.JButton("Cancelar");
        btGerarGuia = new javax.swing.JButton("Gerar guia");
        lblData = new javax.swing.JLabel("Data:");
        txtData = new javax.swing.JFormattedTextField();
        lblObs = new javax.swing.JLabel("Obs:");
        txtObs = new javax.swing.JTextArea();
        scrollTxtObs = new javax.swing.JScrollPane(txtObs);
        //fim

        //configuração componentes panel guia
        txtObs.setPreferredSize(new Dimension(400,100));
        txtQtdSacas.setDocument(new NumerosFloatCVirgula());
        txtVlrGuia.setDocument(new NumerosFloatCVirgula());
        try {
            txtData.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
            txtData.setPreferredSize(new java.awt.Dimension(70,24));
        } catch (ParseException ex) {
            Logger.getLogger(PGuiasEntradaNovaGuia.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ArrayList servicos = daos.consultarProdutores();
            ComboBoxModel cbm_serv = new ComboBoxModel();
            cbm_serv.montaCombo();
            cbm_serv.listaDadosCombo(servicos);
            comboTipoCafe = cbm_serv;
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasEntradaNovaGuia.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Tabela config
        String[] columnNames = {"Serviços"};
        Object[][] data = {};
        modelT = new javax.swing.table.DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int rowIndex, int colIndwx) {
                return false;
            }
        };
        tabela = new javax.swing.JTable(modelT);
        tabela.setPreferredScrollableViewportSize(new java.awt.Dimension(400, 150));
        tabela.getColumnModel().getColumn(0).setMaxWidth(400);
        tabela.getColumnModel().getColumn(0).setMinWidth(400);
        scrollT = new javax.swing.JScrollPane(tabela);
        //fim
        //fim

        //adicionando componentes panel guia
        panelGuia.add(lblNumGuia, "split 4, gapx 31");
        panelGuia.add(txtNumGuia);
        panelGuia.add(lblData);
        panelGuia.add(txtData,"wrap");
        panelGuia.add(lblQtdSacas, "split 4");
        panelGuia.add(txtQtdSacas);
        panelGuia.add(lblVlrGuia);
        panelGuia.add(txtVlrGuia, "wrap");
        panelGuia.add(lblServGuia, "split 3, gapx 26");
        panelGuia.add(comboTipoCafe);
        panelGuia.add(btAddServ, "wrap");
        panelGuia.add(scrollT, "gapx 60");
        panelGuia.add(btExcluirLinha, "top,wrap");
        panelGuia.add(lblObs,"split 2, top, gapx 30");
        panelGuia.add(scrollTxtObs,"wrap");
        panelGuia.add(btCancelar, "split 2");
        panelGuia.add(btGerarGuia, "gapx 130");
        //fim

        //adicionando componentes panel principal
        panelPrincipal.add(panelEmp, "wrap");
        panelPrincipal.add(panelGuia);

        tabs.add(panelPrincipal);
        tabs.add(panelAlterar);
        tabs.setPreferredSize(FInicio.getDimensionn());
        this.add(tabs);
        //fim

        //evento bts
        btAddServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btAdcServGuiaActionPerformed(e);
            }
        });

        btExcluirLinha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btExcluirLinhaTActionPerfomed(e);
            }
        });

        btGerarGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btGerarGuiaActionPerformed(e);
            }
        });
        //fim
    }

    private void btAdcServGuiaActionPerformed(java.awt.event.ActionEvent evt) {
        String s_desc = comboTipoCafe.getSelectedItem().toString();

        if (s_desc.isEmpty() || s_desc.equals(null)) {
            JOptionPane.showMessageDialog(null, "Informe um valor!");
        } else {
            String[] linha = new String[]{null};
            DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
            dtm.addRow(linha);

            tabela.setModel(dtm);
            tabela.setValueAt(s_desc, linhas_tabela, 0);

            linhas_tabela++;
        }

    }

    private void btExcluirLinhaTActionPerfomed(java.awt.event.ActionEvent evt) {
        int li = tabela.getSelectedRow();
        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
            dtm.removeRow(li);
            linhas_tabela--;
        }
    }

    private void btGerarGuiaActionPerformed(java.awt.event.ActionEvent evt) {
        String s_num_guia = txtNumGuia.getText().toString().trim();
        String s_vlr_saca = txtVlrGuia.getText().toString();
        String s_qtd_saca = txtQtdSacas.getText().toString();
        String s_dt_atual = txtData.getText().toString();
        String s_ano = s_dt_atual.substring(6, 10);
        int qtd_linhas_txt_obs = txtObs.getLineCount();
        String s_obs = "";
        
        
        
        for(int i = 0; i < qtd_linhas_txt_obs; i++){
                try{
                    int inicio = txtObs.getLineStartOffset(i);
                    int fim = txtObs.getLineEndOffset(i);
                    String s_linha_obs = txtObs.getText(inicio, fim - inicio);
                   // String s_linha_obs = txtObs.get
                    s_obs = s_obs+" "+s_linha_obs;
                    System.out.println("OBSERVACAO: " + s_obs);
                }
                catch(BadLocationException ble){
                    // possiveis erros são tratados aqui
                } 
            }   

        if (s_num_guia.isEmpty() || s_num_guia.equals(null) || s_vlr_saca.isEmpty() || s_vlr_saca.equals(null)
                || s_vlr_saca.equals(null) || s_qtd_saca.isEmpty() || s_qtd_saca.equals(null) || s_dt_atual.isEmpty() || s_dt_atual.equals(null)) {
            JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
        } else {
            s_vlr_saca = s_vlr_saca.replace(",", ".");
            double d_vlr_saca = Double.parseDouble(s_vlr_saca);
            if (d_vlr_saca <= 0) {
                JOptionPane.showMessageDialog(null, "Informe um valor não nulo ou negativo!");
            } else {
                s_qtd_saca = s_qtd_saca.replace(",", ".");
                double d_qtd_saca = Double.parseDouble(s_qtd_saca);
                if (d_qtd_saca <= 0) {
                    JOptionPane.showMessageDialog(null, "Informe uma qtd de sacas não nulo ou negativo!");
                } else {
                    int linhas = tabela.getRowCount();
                    if (linhas <= 0) {
                        JOptionPane.showMessageDialog(null, "Informe ao menos um serviço para a guia!");
                    } else {
                        if (ManipulaDatas.validaData(s_dt_atual) == false) {
                            JOptionPane.showMessageDialog(null, "Data inválida!");
                        } else {
                            try {
                                //instanciando daos
                                DAOGuias daog = new DAOGuias();
                                DAOProdutorGuia daopg = new DAOProdutorGuia();
                                DAOEmpresaGuia daoeg = new DAOEmpresaGuia();
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
                                int id_financeiro = daof.retornaUltimaID();
                                int id_pdtr = daop.retornaIDPorNome(comboPdtrGuia.getSelectedItem().toString());
                                int id_emp = daoe.retornaIDPorNome(comboEmpGuia.getSelectedItem().toString());
                                double d_peso = d_qtd_saca * 60;
                                String s_nosso_num = id_guia + "/" + s_ano;

                                if (daoeg.consultaGuiaEmp(s_num_guia, id_emp) == true) {
                                    JOptionPane.showMessageDialog(null, "Esta guia já existe para esta empresa!");
                                } else {

                                //montando objetos
                                    //objeto guia
                                    g.setDt_entrada_guia(ManipulaDatas.converteStrSql(s_dt_atual));
                                    g.setNum_guia(s_num_guia);
                                    g.setPeso_guia(d_peso);
                                    g.setQtd_sacas_guia(d_qtd_saca);
                                    g.setReferencia_guia(0);
                                    g.setStatus_guia(0);
                                    g.setUsuario_cad_guia(ManipulaArquivo.lerArquivoUsuario());
                                    g.setVlr_total_guia(d_vlr_saca * d_qtd_saca);
                                    g.setVlr_unit_guia(d_vlr_saca);
                                    g.setNosso_num_guia(s_nosso_num);
                                    g.setVlr_total_ini_guia(d_vlr_saca * d_qtd_saca);
                                    g.setObs(s_obs);
                                //fim objeto guia

                                    //objeto produtores da guia
                                    pg.setId_guia(id_guia);
                                    pg.setId_pdtr(id_pdtr);
                                    pg.setNum_guia(s_num_guia);
                                //fim objeto produtores da guia

                                    //montando o objeto producao unica guia
                                    pug.setId_guia(id_guia);
                                    pug.setPeso(0);
                                    pug.setSaldo(0);
                                //fim

                                    //montando objeto do financeiro
                                    f.setCredito(d_vlr_saca * d_qtd_saca);
                                    f.setDebito(0);
                                    f.setDocumento("G" + id_financeiro + s_ano);
                                    f.setDocumento_referencia(s_nosso_num);
                                    f.setDt_financeiro(ManipulaDatas.converteStrSql(s_dt_atual));
                                    f.setTipo_financeiro(1); //contas a receber
                                    f.setTipo_operacao(1); //operação para guias
                                    f.setVlr_financeiro(d_vlr_saca * d_qtd_saca);
                                    f.setUsuario_cria(Integer.parseInt(ManipulaArquivo.lerArquivoUsuario().trim()));
                                    f.setEmpresa(id_emp);
                                //fim

                                    //objeto empresa da guia
                                    eg.setId_emp(id_emp);
                                    eg.setId_guia(id_guia);
                                    eg.setNum_guia(s_num_guia);
                                //fim objeto empresa da guia

                                    //objeto serviços da guia
                                    for (int i = 0; i < linhas; i++) {
                                        int id_serv = daos.retornaIDPorNome(tabela.getValueAt(i, 0).toString()); //pegando a id do serviço selecionado

                                        sg.setId_guia(id_guia);
                                        sg.setId_serv(id_serv);
                                        id_serv = 0;
                                        daosg.inserir(sg);
                                    }
                                //fim objeto serviços da guia

                                //fim montagem de objetos
                                    //inserindo 
                                    daopug.inserir(pug);
                                    daog.inserir(g);
                                    daoeg.inserir(eg);
                                    daopg.inserir(pg);
                                    daof.inserir(f);

                                    //fim inserção
                                    JOptionPane.showMessageDialog(null, "Guia gerada com sucesso!");
                                    limpaCampos();
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(PGuiasEntradaNovaGuia.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                }
            }
        }
    }

    private void limpaCampos() {
        txtNumGuia.setText("");
        txtQtdSacas.setText("");
        txtVlrGuia.setText("");
        DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
        dtm.setNumRows(0);
        linhas_tabela = 0;
        txtData.setText("");
    }

    private javax.swing.JLabel lblNumGuia;
    private javax.swing.JLabel lblEmp;
    private javax.swing.JLabel lblPdtr;
    private javax.swing.JLabel lblQtdSacas;
    private javax.swing.JLabel lblVlrGuia;
    private javax.swing.JLabel lblServGuia;
    private javax.swing.JTextField txtNumGuia;
    private javax.swing.JTextField txtVlrGuia;
    private javax.swing.JTextField txtQtdSacas;
    private javax.swing.JButton btGerarGuia;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btAddServ;
    private javax.swing.JComboBox comboTipoCafe;
    private javax.swing.JComboBox comboEmpGuia;
    private javax.swing.JComboBox comboPdtrGuia;
    private javax.swing.JPanel panelEmp;
    private javax.swing.JPanel panelGuia;
    private javax.swing.JTable tabela;
    private javax.swing.table.DefaultTableModel modelT;
    private javax.swing.JScrollPane scrollT;
    private javax.swing.JButton btExcluirLinha;
    private DAOEmpresas daoe = new DAOEmpresas();
    private DAOProdutores daopdtr = new DAOProdutores();
    private DAOServicos daos = new DAOServicos();
    private int linhas_tabela = 0;
    private javax.swing.JPanel panelAlterar;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JLabel lblData;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JPanel panelDadosAlt;
    private javax.swing.JLabel lblNumGuiaAlt;
    private javax.swing.JLabel lblVlrSacaAlt;
    private javax.swing.JLabel lblDataAlt;
    private javax.swing.JLabel lblQtdSacasAlt;
    private javax.swing.JTextField txtNumGuiaAlt;
    private javax.swing.JTextField txtVlrSacaAlt;
    private javax.swing.JTextField txtDataAlt;
    private javax.swing.JTextField txtQtdSacasAlt;
    private javax.swing.JLabel lblObs;
    private javax.swing.JTextArea txtObs;
    private javax.swing.JScrollPane scrollTxtObs;
}
