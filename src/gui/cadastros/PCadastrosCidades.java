/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cadastros;

import classes.ComboBoxModel;
import dao.cadastros.DAOCidades;
import dao.cadastros.DAOEstados;
import gui.guias.entrada.nova.guia.PGuiasEntradaNovaGuia;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.cadastros.Cidades;
import model.cadastros.Estados;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PCadastrosCidades extends javax.swing.JPanel {

    public PCadastrosCidades() {
        initComponents();
    }

    private void initComponents() {
        //configurações panel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CADASTRO DE CIDADES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel principal
        panelDados = new javax.swing.JPanel();
        //fim

        //configuração componentes jpanel principal
        panelDados.setLayout(new MigLayout());
        panelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NOVA CIDADE", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel dados
        lblEst = new javax.swing.JLabel("Estado:");
        lblCid = new javax.swing.JLabel("Cidade:");
        comboEst = new javax.swing.JComboBox<>();
        txtCid = new javax.swing.JTextField(25);
        btCancelar = new javax.swing.JButton("Cancelar");
        btSalvar = new javax.swing.JButton("Salvar");
        //fim

        //configuração componentes panel dados
        ComboBoxModel cbm_est = new ComboBoxModel();

        cbm_est.montaCombo();
        comboEst = cbm_est;
        //fim

        //adicionando compontens jpanel dados
        panelDados.add(lblCid, "split 2");
        panelDados.add(txtCid, "wrap");
        panelDados.add(lblEst, "split 2");
        panelDados.add(comboEst, "wrap");
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
        
        listaDados();
    }

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro?", "Cancelar cadastro", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            limpaCampos();
            listaDados();
        }
    }

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_cid = txtCid.getText().toString().toUpperCase().trim();
        String s_est = comboEst.getSelectedItem().toString().toUpperCase().trim();

        if (s_cid.isEmpty() || s_cid.equals(null) || s_est.isEmpty() || s_est.equals(null)) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            DAOCidades daoc = new DAOCidades();
            Cidades c = new Cidades();
            DAOEstados daoe = new DAOEstados();

            try {
                int id_est = daoe.retornaIdPorNome(s_est);
                boolean condicao_existe_cidade = daoc.existeCid(s_cid, id_est);

                if (condicao_existe_cidade == true) {
                    JOptionPane.showMessageDialog(null, "Cidade já existente para este estado!");
                } else {
                    c.setEst_cid(id_est);
                    c.setNome_cid(s_cid);

                    daoc.inserir(c);

                    JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
                    limpaCampos();
                    listaDados();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PCadastrosCidades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void limpaCampos() {
        txtCid.setText("");
        comboEst.removeAllItems();
    }

    private void listaDados() {
        List<Estados> dados;

        try {
            dados = daoe.retornaNomes();

            for (int i = 0; i < dados.size(); i++) {
                comboEst.addItem(dados.get(i).getNome_est().toString().trim());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PCadastrosCidades.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private javax.swing.JPanel panelDados;
    private javax.swing.JLabel lblEst;
    private javax.swing.JComboBox comboEst;
    private javax.swing.JLabel lblCid;
    private javax.swing.JTextField txtCid;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btSalvar;
    private DAOEstados daoe = new DAOEstados();
}
