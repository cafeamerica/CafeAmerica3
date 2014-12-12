/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cadastros;

import classes.ComboBoxModel;
import classes.NumerosFloatCVirgula;
import dao.financeiro.DAOCtasBancarias;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.financeiro.CtasBancarias;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PCadastrosCtsBancarias extends javax.swing.JPanel {

    public PCadastrosCtsBancarias() {
        initComponents();
    }

    private void initComponents() {
        //configurações jpanel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CADASTRO DE CONTAS BANCÁRIAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel principal
        panelCadastro = new javax.swing.JPanel();
        //fim

        //configurações componentes jpanel cadastro
        panelCadastro.setLayout(new MigLayout());
        panelCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NOVA CONTA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel cadastro
        lblAgencia = new javax.swing.JLabel("Agência:");
        lblBanco = new javax.swing.JLabel("Banco:");
        lblDigito = new javax.swing.JLabel("Digíto:");
        lblNumero = new javax.swing.JLabel("Número:");
        lblSaldo = new javax.swing.JLabel("Saldo atual:");
        lblTipoCta = new javax.swing.JLabel("Tipo de conta:");
        lblTitular = new javax.swing.JLabel("Titular:");
        txtAgencia = new javax.swing.JTextField(5);
        txtBanco = new javax.swing.JTextField(16);
        txtDigito = new javax.swing.JTextField(2);
        txtNumero = new javax.swing.JTextField(6);
        txtSaldo = new javax.swing.JTextField(6);
        comboTipoCta = new javax.swing.JComboBox<>();
        txtTitular = new javax.swing.JTextField(30);
        btCadastrar = new javax.swing.JButton("Cadastrar");
        btCancelar = new javax.swing.JButton("Cancelar");
        //fim

        //configurações componentes jpanel cadastro
        txtSaldo.setDocument(new NumerosFloatCVirgula());
        ComboBoxModel cbm_tipo_cta = new ComboBoxModel();
        cbm_tipo_cta.montaCombo();
        comboTipoCta = cbm_tipo_cta;
        comboTipoCta.addItem("CORRENTE");
        comboTipoCta.addItem("POUPANÇA");
        comboTipoCta.addItem("SALÁRIO");
        //fim

        //adicionando componentes jpanel cadastros
        panelCadastro.add(lblBanco, "split 4, gapx 39");
        panelCadastro.add(txtBanco);
        panelCadastro.add(lblAgencia);
        panelCadastro.add(txtAgencia, "wrap");
        panelCadastro.add(lblTitular, "split 2, gapx 39");
        panelCadastro.add(txtTitular, "wrap");
        panelCadastro.add(lblNumero, "split 4, gapx 30");
        panelCadastro.add(txtNumero);
        panelCadastro.add(lblDigito);
        panelCadastro.add(txtDigito, "wrap");
        panelCadastro.add(lblTipoCta, "split 2");
        panelCadastro.add(comboTipoCta, "wrap");
        panelCadastro.add(lblSaldo, "split 2, gapx 12");
        panelCadastro.add(txtSaldo, "wrap");
        panelCadastro.add(btCancelar, "split 2");
        panelCadastro.add(btCadastrar, "gapx 70");
        //fim

        this.add(panelCadastro);

        //evento bts
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelarActionPerformed(e);
            }
        });

        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCadastrarActionPerformed(e);
            }
        });
        //fim

    }

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar esta nova conta?", "Cancelar nova conta", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            limpaCampos();
        }
    }

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_banco = txtBanco.getText().toString().toUpperCase().trim();
        String s_agencia = txtAgencia.getText().toString().toUpperCase().trim();
        String s_digito = txtDigito.getText().toString().toUpperCase().trim();
        String s_numero = txtNumero.getText().toString().toUpperCase().trim();
        String s_saldo = txtSaldo.getText().toString().trim();
        String s_titular = txtTitular.getText().toString().toUpperCase().trim();
        String s_tipo_cta = comboTipoCta.getSelectedItem().toString().toUpperCase().trim();

        if (s_banco.isEmpty() || s_banco.equals(null) || s_agencia.isEmpty() || s_agencia.equals(null) || s_digito.isEmpty() || s_digito.equals(null)
                || s_numero.isEmpty() || s_numero.equals(null) || s_saldo.isEmpty() || s_saldo.equals(null) || s_titular.isEmpty() || s_titular.equals(null)
                || s_tipo_cta.isEmpty() || s_tipo_cta.equals(null)) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            s_saldo = s_saldo.replace(",", ".");
            double d_saldo = Double.parseDouble(s_saldo);
            DAOCtasBancarias daocb = new DAOCtasBancarias();
            CtasBancarias cb = new CtasBancarias();
            try {
                boolean condicao_existe = daocb.consultaCtaExiste(s_titular, s_numero);
                if (condicao_existe == true) {
                    JOptionPane.showMessageDialog(null, "Já existe esta conta no sistema!");
                } else {
                    cb.setAgencia(s_agencia);
                    cb.setBanco(s_banco);
                   // cb.setDigito(s_digito);
                    cb.setNumero(s_numero+"-"+s_digito);
                    cb.setSaldo(d_saldo);
                    cb.setTipo_conta(s_tipo_cta);
                    cb.setTitular(s_titular);

                    int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente realizar o cadastro? \n" + "Titular: " + s_titular + "\n" + "Número: " + s_numero+"-"+s_digito, "Confirma cadastro", JOptionPane.YES_NO_OPTION);
                    if (resp == 0) {
                        daocb.inserir(cb);

                        JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!");
                        limpaCampos();
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(PCadastrosCtsBancarias.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void limpaCampos() {
        txtAgencia.setText("");
        txtBanco.setText("");
        txtDigito.setText("");
        txtNumero.setText("");
        txtSaldo.setText("");
        txtTitular.setText("");
    }

    private javax.swing.JLabel lblBanco;
    private javax.swing.JLabel lblAgencia;
    private javax.swing.JLabel lblTitular;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblDigito;
    private javax.swing.JLabel lblTipoCta;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JTextField txtBanco;
    private javax.swing.JTextField txtAgencia;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtDigito;
    private javax.swing.JTextField txtTitular;
    private javax.swing.JComboBox comboTipoCta;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JPanel panelCadastro;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btCadastrar;
}
