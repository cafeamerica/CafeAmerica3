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
import dao.entradas.DAOProducaoGuia;
import dao.entradas.DAOServicos;
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
import model.entradas.ProducaoGuia;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PGuiasProducaoAltProd extends javax.swing.JPanel { //vai aproveitar muita coisa da outra classe porem escrever alguns outros metodos

    public PGuiasProducaoAltProd() {
        initComponents();
    }

    private void initComponents() {
        //configuração jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ALTERAR PRODUÇÃO DE CAFÉ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpane principal
        panelConsultar = new javax.swing.JPanel();
        panelBts = new javax.swing.JPanel();
        panelDados = new javax.swing.JPanel();
        //FIM

        //configuração componentes jpanel principal
        panelConsultar.setLayout(new MigLayout());
        panelConsultar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar guia", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelBts.setLayout(new MigLayout());
        panelDados.setLayout(new MigLayout());
        panelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));

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
            Logger.getLogger(PGuiasProducaoAltProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        //fim

        //adicionando componentes jpanel consultar
        panelConsultar.add(lblNumGuia, "split 2");
        panelConsultar.add(txtNumGuia, "wrap");
        panelConsultar.add(lblCliente, "split 3");
        panelConsultar.add(comboEmpGuia);
        panelConsultar.add(btConsultar);
        //fim

        //Tabela config
        //alinhando os valores das celulas da Tabela à direita
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);

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
        scrollTP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produção", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));

        tabelaP.getColumnModel().getColumn(0).setCellRenderer(r);
        tabelaP.getColumnModel().getColumn(1).setCellRenderer(r);
        tabelaP.getColumnModel().getColumn(2).setCellRenderer(r);
        //fim

        //inicializando componentes jpanel bts
        btCancelarAll = new javax.swing.JButton("Cancelar tudo");
        btSelecionarProd = new javax.swing.JButton("Selecionar produção");
        //fim

        //adicionando componentes jpanel bts
        panelBts.add(btSelecionarProd, "wrap");
        panelBts.add(btCancelarAll);
        //fim

        //inicializando compontenes jpanel dados
        lblPeso = new javax.swing.JLabel("Peso:");
        lblServAntigo = new javax.swing.JLabel("Anterior:");
        lblServNovo = new javax.swing.JLabel("Novo:");
        comboTipoCafe = new javax.swing.JComboBox<>();
        txtPeso = new javax.swing.JTextField(8);
        txtServAntigo = new javax.swing.JTextField(12);
        btSalvar = new javax.swing.JButton("Salvar");
        btCancelar = new javax.swing.JButton("Cancelar");
        //fim

        //configuração componentes jpanel dados
        txtPeso.setDocument(new NumerosFloatCVirgula());
        txtServAntigo.setEditable(false);
        try {
            ArrayList servicos = daos.consultarProdutores();
            ComboBoxModel cbm_serv = new ComboBoxModel();
            cbm_serv.montaCombo();
            cbm_serv.listaDadosCombo(servicos);
            comboTipoCafe = cbm_serv;
        } catch (SQLException ex) {
            Logger.getLogger(PGuiasProducaoAltProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        //fim

        //adicionando componentes jpanel dados
        panelDados.add(lblServAntigo, "split 2");
        panelDados.add(txtServAntigo, "wrap");
        panelDados.add(lblServNovo, "split 2, gapx 17");
        panelDados.add(comboTipoCafe, "wrap");
        panelDados.add(lblPeso, "split 2, gapx 17");
        panelDados.add(txtPeso, "wrap");
        panelDados.add(btCancelar, "split 2, gapx 40");
        panelDados.add(btSalvar);
        //fim

        //adicionando components jpanel principal
        this.add(panelConsultar, "wrap");
        this.add(scrollTP, "split 2");
        this.add(panelBts, "wrap");
        this.add(panelDados);
        //fim

        scrollTP.setVisible(false);
        panelBts.setVisible(false);
        panelDados.setVisible(false);

        //evento bts
        btConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btConsultarActionPerformed(e);
            }
        });

        btCancelarAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelarAllActionPerformed(e);
            }
        });

        btSelecionarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btSelecionarProdActionPerformed(e);
            }
        });

        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelarActionPerformed(e);
            }
        });

        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btSalvarActionPerformed(e);
            }
        });
        //fim
    }

    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_num_guia = txtNumGuia.getText().toString();
        DAOEmpresaGuia daoeg = new DAOEmpresaGuia();
        DAOGuiaViagemEnt daogve = new DAOGuiaViagemEnt();
        DAOGuias daog = new DAOGuias();
        try {
            int id_emp = daoe.retornaIDPorNome(comboEmpGuia.getSelectedItem().toString());
            if (daoeg.consultaGuiaEmp(s_num_guia, id_emp) == false) {
                JOptionPane.showMessageDialog(null, "Essa guia não existe para a empresa " + comboEmpGuia.getSelectedItem().toString());
            } else {
                int id_guiaa = daog.retornaIdGuiaEmp(s_num_guia, id_emp);
                listaDados(id_guiaa);
                id_guia = id_guiaa;
                scrollTP.setVisible(true);
                panelBts.setVisible(true);
                btConsultar.setEnabled(false);
                txtNumGuia.setEditable(false);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PGuiasProducaoAltProd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            txtPeso.setText("");
            txtServAntigo.setText("");
            btSelecionarProd.setEnabled(true);
            panelDados.setVisible(false);
            li_tab_pdc = -1;
        }
    }

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_peso = txtPeso.getText().toString().trim();
        String s_serv_anterior = txtServAntigo.getText().toString().trim();
        String s_serv_novo = comboTipoCafe.getSelectedItem().toString().trim();

        if (s_peso.isEmpty() || s_peso.equals(null) || s_serv_anterior.isEmpty()
                || s_serv_anterior.equals(null) | s_serv_novo.isEmpty() || s_serv_novo.equals(null)) {
            JOptionPane.showMessageDialog(null, "Preencha os campos!");
        } else {
            s_peso = s_peso.replace(",", ".");
            double d_peso = Double.parseDouble(s_peso);
            if (d_peso <= 0) {
                JOptionPane.showMessageDialog(null, "Peso inválido! Menor ou igual a 0!");
            } else {
                String s_peso_anterior = tabelaP.getValueAt(li_tab_pdc, 2).toString().trim();
                s_peso_anterior = s_peso_anterior.replace(".", "");
                s_peso_anterior = s_peso_anterior.replace(",", ".");
                double d_peso_anterior = Double.parseDouble(s_peso_anterior);

                if (d_peso > d_peso_anterior) {
                    JOptionPane.showMessageDialog(null, "Peso inválido! Maior que o disponível para a alteração!");
                } else {
                    try {
                        DAOProducaoGuia daopg = new DAOProducaoGuia();
                        ProducaoGuia pg = new ProducaoGuia();
                        DAOPdcGuiaData daopgd = new DAOPdcGuiaData(); //armazena historico de producao
                        PdcGuiaData pgd = new PdcGuiaData();

                        int id_serv_anterior = daos.retornaIDPorNome(s_serv_anterior);
                        int id_serv_novo = daos.retornaIDPorNome(s_serv_novo);
                        boolean condicao_existe_servico = daopg.retornaExiste(id_serv_novo, id_guia);

                        if (id_serv_anterior == id_serv_novo) {
                            //selecionou o mesmo tipo de produção antiga e nova
                            JOptionPane.showMessageDialog(null, "Tipos de produção iguais, selecione outro(a)!");
                        } else {
                            int resp = JOptionPane.showConfirmDialog(null, "Deseja confirmar esta alteração?", " Confirmar alteração", JOptionPane.YES_NO_OPTION);

                            if (resp == 0) {
                                if (condicao_existe_servico == true) {//ja existe esta produção.. basta somente alterar o saldo
                                    //alterando o saldo, somando com o já existente do novo..
                                    int id_pdc_novo = daopg.retornaIdPorTipoGuia(id_serv_novo, id_guia);
                                    double d_saldo_novo = daopg.retornaSaldo(id_pdc_novo);
                                    d_saldo_novo = d_saldo_novo + d_peso;
                                    daopg.alteraSaldo(id_pdc_novo, d_saldo_novo);
                                    
                                    double d_peso_novo = daopg.retornaPeso(id_pdc_novo);
                                    d_peso_novo = d_peso_novo + d_peso;
                                    daopg.alteraPeso(id_pdc_novo, d_peso_novo);
                                    
                                    double d_peso_bruto_novo = daopg.retornaPesoBruto(id_pdc_novo);
                                    d_peso_bruto_novo = d_peso_bruto_novo + d_peso;
                                    daopg.alteraPesoBruto(id_pdc_novo, d_peso_bruto_novo);
                                    //fim

                                } else { //nao existe esta produção, entao produz..
                                    pg.setDt_producao_pdc(ManipulaDatas.converteStrSql(ManipulaDatas.retornaDataAtual()));
                                    pg.setId_guia(id_guia);
                                    pg.setPeso_pdc(d_peso);
                                    pg.setPeso_bruto(d_peso);
                                    pg.setSaldo_pdc(d_peso);
                                    pg.setQtd_sacas_pdc(d_peso / 60);
                                    pg.setTipo_cafe_pdc(id_serv_novo);

                                    daopg.inserir(pg);
                                    //fim
                                }

                                //montando objeto de historico de producao
                                pgd.setData_pdc(ManipulaDatas.converteStrSql(ManipulaDatas.retornaDataAtual()));
                                pgd.setId_guia(id_guia);
                                pgd.setPeso(d_peso);
                                pgd.setTipo_cafe(id_serv_novo);
                                pgd.setUsuario_cad_pdc(ManipulaArquivo.lerArquivoUsuario());
                                 //fim

                                //alterando o saldo, subtraindo com o já existente do antigo..
                                int id_pdc_anterior = daopg.retornaIdPorTipoGuia(id_serv_anterior, id_guia);
                                double d_saldo_anterior = daopg.retornaSaldo(id_pdc_anterior);
                                d_saldo_anterior = d_saldo_anterior - d_peso;
                                System.out.println("SALDO ANTERIOR: "+ d_saldo_anterior  );
                                daopg.alteraSaldo(id_pdc_anterior, d_saldo_anterior);
                                
                                //fim

                                JOptionPane.showMessageDialog(null, "Produção alterada com sucesso!");
                                limpaCampos();

                            }

                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(PGuiasProducaoAltProd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
    }

    private void btSelecionarProdActionPerformed(java.awt.event.ActionEvent evt) {
        int li = tabelaP.getSelectedRow();

        if (li == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um registro!");
        } else {
            String s_peso = tabelaP.getValueAt(li, 2).toString().trim();
            s_peso = s_peso.replace(".", "");
            s_peso = s_peso.replace(",", ".");
            double d_peso = Double.parseDouble(s_peso);

            if (d_peso <= 0) {
                JOptionPane.showMessageDialog(null, "Peso inválido! Menor ou igual a 0!");
            } else {
                panelDados.setVisible(true);
                txtServAntigo.setText(tabelaP.getValueAt(li, 0).toString().trim());
                btSelecionarProd.setEnabled(false);
                li_tab_pdc = li;
            }

        }
    }

    private void btCancelarAllActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar tudo?", "Cancelar tudo", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            limpaCampos();
        }
    }

    private void listaDados(int id_guia) {
        DAOProducaoGuia daopg = new DAOProducaoGuia();
        DAOServicos daos = new DAOServicos();
        DAOEmpresaGuia daoeg = new DAOEmpresaGuia();
        DAOGuias daog = new DAOGuias();
        List<ProducaoGuia> dados;
        boolean condicao_guia = false;
        boolean condicao_serv = false;

        int id_emp = 0;

        try {
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
            Logger.getLogger(PGuiasProducaoAltProd.class.getName()).log(Level.SEVERE, null, ex);
        }

        ManipulaJTables.removeLinhasBrancas(tabelaP);
    }

    private void limpaCampos() {
        txtPeso.setText("");
        txtServAntigo.setText("");
        // comboTipoCafe.removeAllItems();
        btSelecionarProd.setEnabled(true);
        btConsultar.setEnabled(true);
        panelDados.setVisible(false);
        panelBts.setVisible(false);
        DefaultTableModel dtm = (DefaultTableModel) tabelaP.getModel();
        dtm.setNumRows(0);
        scrollTP.setVisible(false);
        li_tab_pdc = -1;
        id_guia = 0;
        txtNumGuia.setEditable(true);
    }

    private javax.swing.JPanel panelConsultar;
    private javax.swing.JLabel lblNumGuia;
    private javax.swing.JTextField txtNumGuia;
    private javax.swing.JComboBox comboEmpGuia;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JButton btConsultar;
    private javax.swing.JTable tabelaP;
    private javax.swing.table.DefaultTableModel modelTP;
    private javax.swing.JScrollPane scrollTP;
    private javax.swing.JButton btSelecionarProd;
    private javax.swing.JButton btCancelarAll;
    private javax.swing.JPanel panelBts;
    private javax.swing.JPanel panelDados;
    private javax.swing.JLabel lblServAntigo;
    private javax.swing.JLabel lblServNovo;
    private javax.swing.JTextField txtServAntigo;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JComboBox comboTipoCafe;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btCancelar;
    private int li_tab_pdc = -1;
    private int id_guia = 0;
    private DAOEmpresas daoe = new DAOEmpresas();
    private DAOServicos daos = new DAOServicos();
}
