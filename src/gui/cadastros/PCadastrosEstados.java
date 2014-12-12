/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cadastros;

import dao.cadastros.DAOEstados;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.cadastros.Estados;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PCadastrosEstados extends javax.swing.JPanel {

    public PCadastrosEstados() {
        initComponents();
    }

    private void initComponents() {
        //configurações panel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CADASTRO DE ESTADOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel principal
        panelDados = new javax.swing.JPanel();
        //fim

        //configuração componentes jpanel principal
        panelDados.setLayout(new MigLayout());
        panelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NOVO ESTADO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel estado
        lblEst = new javax.swing.JLabel("Estado:");
        lblSigla = new javax.swing.JLabel("Sigla:");
        txtEst = new javax.swing.JTextField(15);
        txtSigla = new javax.swing.JTextField(2);
        btCancelar = new javax.swing.JButton("Cancelar");
        btSalvar = new javax.swing.JButton("Salvar");
        //fim

        //adicionando compontens jpanel dados
        panelDados.add(lblEst, "split 2");
        panelDados.add(txtEst, "wrap");
        panelDados.add(lblSigla, "split 2, gapx 12");
        panelDados.add(txtSigla, "wrap");
        panelDados.add(btCancelar, "split 2, gapx 40");
        panelDados.add(btSalvar);
        //fim

        //adicionando componentes jpanel principal
        this.add(panelDados);
        //fim

        //evento bts
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

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro?", "Cancelar cadastro", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            limpaCampos();
        }
    }

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_est = txtEst.getText().toString().toUpperCase().trim();
        String s_sigla = txtSigla.getText().toString().toUpperCase().trim();

        if (s_est.isEmpty() || s_est.equals(null) || s_sigla.isEmpty() || s_sigla.equals(null)) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            DAOEstados daoe = new DAOEstados();
            Estados e = new Estados();

            try {
                boolean condicao_existe_estado = daoe.existeEst(s_est, s_sigla);

                if (condicao_existe_estado == true) {
                    JOptionPane.showMessageDialog(null, "Estado ou sigla existente! Tente novamente!");
                } else {
                    e.setNome_est(s_est);
                    e.setSigla_est(s_sigla);
                    
                    daoe.inserir(e);
                    
                    JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
                    limpaCampos();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PCadastrosEstados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void limpaCampos() {
        txtEst.setText("");
        txtSigla.setText("");
    }

    private javax.swing.JPanel panelDados;
    private javax.swing.JLabel lblEst;
    private javax.swing.JTextField txtEst;
    private javax.swing.JLabel lblSigla;
    private javax.swing.JTextField txtSigla;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btSalvar;
}
