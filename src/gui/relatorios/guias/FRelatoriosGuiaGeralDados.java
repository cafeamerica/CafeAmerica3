/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.relatorios.guias;

import classes.ManipulaDatas;
import dao.cadastros.DAOUsuarios;
import dao.entradas.DAOGuias;
import dao.financeiro.DAOFinanceiro;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entradas.Guias;
import model.financeiro.Financeiro;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class FRelatoriosGuiaGeralDados extends javax.swing.JFrame {

    public FRelatoriosGuiaGeralDados(int id_guia) {
        initComponents();
        listaDadosGuia(id_guia);
    }

    private void initComponents() {
        //configurações jframe 
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension tamTela = kit.getScreenSize();
        Dimension dimensao = new Dimension();
        int larg = tamTela.width;
        int alt = tamTela.height;
        /*Se você quiser que o seu JFrame ocupe 70% da tela por exemplo  
         multiplique a largura e altura totais por 0,7*/
        int minhaLargura = (int) (larg * 0.99);
        int minhaAltura = (int) (alt * 0.87);
        dimensao = new java.awt.Dimension(minhaLargura, minhaAltura);
        this.setSize(dimensao);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // this.setTitle("Dados da guia " + num_guia_r);
        //incializando componentes jframe
        panelDados = new javax.swing.JPanel();
        panelPrincipal = new javax.swing.JPanel();
        panelFinancerio = new javax.swing.JPanel();
        //fim

        //configuração componentes jframe
        panelDados.setLayout(new MigLayout());
        panelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da guia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelFinancerio.setLayout(new MigLayout());
        panelFinancerio.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Financeiro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        panelPrincipal.setLayout(new MigLayout());
        //fim

        //inicializando componentes jpanel dados
        lblDtEnt = new javax.swing.JLabel("Data:");
        lblNossoNum = new javax.swing.JLabel("Nosso nº:");
        lblNumGuia = new javax.swing.JLabel("Guia:");
        lblPeso = new javax.swing.JLabel("Peso:");
        lblQtdSacas = new javax.swing.JLabel("Qtd sacas:");
        lblStatus = new javax.swing.JLabel("Status:");
        lblUsuarioCria = new javax.swing.JLabel("Usuário criou:");
        txtDtEnt = new javax.swing.JTextField(8);
        txtNossoNum = new javax.swing.JTextField(6);
        txtNumGuia = new javax.swing.JTextField(6);
        txtPeso = new javax.swing.JTextField(8);
        txtQtdSacas = new javax.swing.JTextField(6);
        txtStatus = new javax.swing.JTextField(8);
        txtUsuarioCria = new javax.swing.JTextField(10);
        //fim

        //config componentes jpanel dados
        txtDtEnt.setEditable(false);
        txtNossoNum.setEditable(false);
        txtNumGuia.setEditable(false);
        txtPeso.setEditable(false);
        txtQtdSacas.setEditable(false);
        txtStatus.setEditable(false);
        txtUsuarioCria.setEditable(false);
        //fim

        //adicionando componentes jpanel dados
        panelDados.add(lblNumGuia, "split 6, gapx 51");
        panelDados.add(txtNumGuia);
        panelDados.add(lblDtEnt,"gapx 11");
        panelDados.add(txtDtEnt);
        panelDados.add(lblNossoNum);
        panelDados.add(txtNossoNum, "wrap");
        panelDados.add(lblQtdSacas, "split 6, gapx 18");
        panelDados.add(txtQtdSacas);
        panelDados.add(lblPeso);
        panelDados.add(txtPeso);
        panelDados.add(lblStatus,"gapx 21");
        panelDados.add(txtStatus,"wrap");
        panelDados.add(lblUsuarioCria,"split 2");
        panelDados.add(txtUsuarioCria);
        //fim
        
        //inicializando componentes jpanel financeiro
        lblVlrCredito = new javax.swing.JLabel("Crédito:");
        lblVlrSaldo = new javax.swing.JLabel("Saldo:");
        lblVlrDebito = new javax.swing.JLabel("Débito:");
        txtVlrCredito = new javax.swing.JTextField(7);
        txtVlrSaldo = new javax.swing.JTextField(7);
        txtVlrDebito = new javax.swing.JTextField(7);
        //fim
        
        //configurações componentes jpanel financeiro
        txtVlrCredito.setEditable(false);
        txtVlrSaldo.setEditable(false);
        txtVlrDebito.setEditable(false);
        //fim
        
        //adicionando componentes jpanel financeiro
        panelFinancerio.add(lblVlrCredito,"split 4, gapx 9");
        panelFinancerio.add(txtVlrCredito);
        panelFinancerio.add(lblVlrDebito);
        panelFinancerio.add(txtVlrDebito,"wrap");
        panelFinancerio.add(lblVlrSaldo,"split 2, gapx 18");
        panelFinancerio.add(txtVlrSaldo);
        //fim
        

        //fim
        this.add(panelPrincipal);
        panelPrincipal.add(panelDados);
        panelPrincipal.add(panelFinancerio,"top");

    }

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }
    
    private void listaDadosGuia(int id_guia){
        DAOGuias daog = new DAOGuias();
        Guias g = new Guias();
        DAOUsuarios daou = new DAOUsuarios();
        try {
            g = daog.retornaDados(id_guia);
            String usuario = daou.retornaNomeId(Integer.parseInt(g.getUsuario_cad_guia()));
            DecimalFormat df = new DecimalFormat("###,###,###.00");
            String doc_referencia = g.getNosso_num_guia();
            
            txtDtEnt.setText(""+ManipulaDatas.converteSqlString(g.getDt_entrada_guia()));
            txtNossoNum.setText(doc_referencia);
            txtNumGuia.setText(""+g.getNum_guia());
            txtPeso.setText(""+df.format(g.getPeso_guia()));
            txtQtdSacas.setText(""+df.format(g.getQtd_sacas_guia()));
            txtStatus.setText(""+g.getStr_status_guia());
            txtUsuarioCria.setText(""+usuario);
            
            listaDadosFinanceiro(doc_referencia);
        } catch (SQLException ex) {
            Logger.getLogger(FRelatoriosGuiaGeralDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    private void listaDadosFinanceiro(String doc_referencia){
         DAOFinanceiro daof = new DAOFinanceiro();
         Financeiro f = new Financeiro();
         
         
        try {
            DecimalFormat df = new DecimalFormat("###,###,###.00");
            f = daof.retornaDadosVlr(doc_referencia);
            double d_credito = f.getCredito();
            double d_debito = f.getDebito();
            double d_debitado = d_credito - d_debito;
            
            txtVlrCredito.setText(""+df.format(f.getCredito()));
            if(d_debito <= 0){
                txtVlrDebito.setText("0,00");
            }else{
                txtVlrDebito.setText(""+df.format(d_debito));
            }
            
            txtVlrSaldo.setText(""+df.format(d_debitado));
            
        } catch (SQLException ex) {
            Logger.getLogger(FRelatoriosGuiaGeralDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelDados;
    private javax.swing.JPanel panelFinancerio;
    private javax.swing.JLabel lblNumGuia;
    private javax.swing.JLabel lblNossoNum;
    private javax.swing.JLabel lblDtEnt;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblQtdSacas;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblUsuarioCria;
    private javax.swing.JTextField txtNumGuia;
    private javax.swing.JTextField txtNossoNum;
    private javax.swing.JTextField txtDtEnt;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtQtdSacas;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtUsuarioCria;
    private javax.swing.JLabel lblVlrCredito;
    private javax.swing.JLabel lblVlrDebito;
    private javax.swing.JLabel lblVlrSaldo;
    private javax.swing.JTextField txtVlrCredito;
    private javax.swing.JTextField txtVlrDebito;
    private javax.swing.JTextField txtVlrSaldo;
    private javax.swing.JButton btFechar;
}
