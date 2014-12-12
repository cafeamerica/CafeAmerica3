/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cadastros;

import dao.cadastros.DAOUsuarios;
import dao.sistema.DAOTabCadMenusUsuario;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.cadastros.Usuarios;
import model.sistema.TabCadMenus;
import model.sistema.TabCadMenusUsuario;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class PCadastrosUsuarios extends javax.swing.JPanel {

    public PCadastrosUsuarios() {

        initComponents();
    }

    private void initComponents() {
        //configurações panel principal
        this.setLayout(new MigLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CADASTRO DE USUÁRIOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando compontens jpanel principal
        panelCad = new javax.swing.JPanel();
        //fim

        //configurações componentes jpanel principal
        panelCad.setLayout(new MigLayout());
        panelCad.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NOVO USUÁRIO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 70, 214)));
        //fim

        //inicializando componentes jpanel cadastros
        lblUser = new javax.swing.JLabel("Usuário:");
        lblNomeUser = new javax.swing.JLabel("Nome:");
        lblSenhaUser = new javax.swing.JLabel("Senha:");
        lblSuperSenhaUser = new javax.swing.JLabel("Super senha:");
        lblTipoUser = new javax.swing.JLabel("Tipo de usuário:");
        txtUser = new javax.swing.JTextField(16);
        txtNomeUser = new javax.swing.JTextField(20);
        txtSenhaUser = new javax.swing.JPasswordField(12);
        txtSuperSenhaUser = new javax.swing.JPasswordField(12);
        radioTipoUserAdm = new javax.swing.JRadioButton("Administrador");
        radioTipoUserPdc = new javax.swing.JRadioButton("Produção");
        radioTipoUserCtb = new javax.swing.JRadioButton("Contábil");
        radioTipoUserFin = new javax.swing.JRadioButton("Financeiro");
        radioTipoUserOts = new javax.swing.JRadioButton("Outros");
        grupoBtsTipoUser = new javax.swing.ButtonGroup();
        btCancelar = new javax.swing.JButton("Cancelar");
        btSalvar = new javax.swing.JButton("Salvar");
        lblConfSenha = new javax.swing.JLabel("Confirme a senha:");
        txtConfSenha = new javax.swing.JPasswordField(12);
        //fim

        //configuração componentes jpanel cadastros
        grupoBtsTipoUser.add(radioTipoUserAdm);
        grupoBtsTipoUser.add(radioTipoUserPdc);
        grupoBtsTipoUser.add(radioTipoUserCtb);
        grupoBtsTipoUser.add(radioTipoUserFin);
        grupoBtsTipoUser.add(radioTipoUserOts);
        radioTipoUserPdc.setSelected(true);
        //fim

        //adicionando componentes jpanel cadastros
        panelCad.add(lblUser, "split 2, gapx 28");
        panelCad.add(txtUser, "wrap");
        panelCad.add(lblNomeUser, "split 2, gapx 39");
        panelCad.add(txtNomeUser, "wrap");
        panelCad.add(lblSenhaUser, "split 4, gapx 36");
        panelCad.add(txtSenhaUser);
        panelCad.add(lblConfSenha);
        panelCad.add(txtConfSenha, "wrap");
        panelCad.add(lblSuperSenhaUser, "split 2");
        panelCad.add(txtSuperSenhaUser, "wrap");
        panelCad.add(lblTipoUser, "split 2");
        panelCad.add(radioTipoUserAdm, "wrap");
        panelCad.add(radioTipoUserPdc, "wrap, gapx 95");
        panelCad.add(radioTipoUserCtb, "wrap, gapx 95");
        panelCad.add(radioTipoUserFin, "wrap, gapx 95");
        panelCad.add(radioTipoUserOts, "wrap, gapx 95");
        panelCad.add(btCancelar, "split 2");
        panelCad.add(btSalvar);
        //fim

        this.add(panelCad);

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

    private void limpaCampos() {
        txtConfSenha.setText("");
        txtNomeUser.setText("");
        txtSenhaUser.setText("");
        txtSuperSenhaUser.setText("");
        txtUser.setText("");
        radioTipoUserPdc.setSelected(true);
    }

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar o cadastro de usuário?", "Cancelar usuário", JOptionPane.YES_NO_OPTION);

        if (resp == 0) {
            limpaCampos();
        }
    }

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_usuario = txtUser.getText().toString().toUpperCase();
        String s_senha = txtSenhaUser.getText().toString().toUpperCase();
        String s_super_senha = txtSuperSenhaUser.getText().toString().toUpperCase();
        String s_dpto_user = "";
        String s_nome_user = txtNomeUser.getText().toString().toUpperCase();
        String s_conf_senha = txtConfSenha.getText().toString().toUpperCase();
        int tipo_user = 0;

        if (s_usuario.isEmpty() || s_senha.isEmpty() || s_nome_user.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
        } else {
            if (radioTipoUserAdm.isSelected() == true) {
                tipo_user = 1;
                s_dpto_user = "ADMINISTRADOR";
            } else if (radioTipoUserPdc.isSelected() == true) {
                tipo_user = 2;
                s_dpto_user = "PRODUCAO";
            } else if (radioTipoUserFin.isSelected() == true) {
                tipo_user = 3;
                s_dpto_user = "FINANCEIRO";
            } else if (radioTipoUserCtb.isSelected() == true) {
                tipo_user = 4;
                s_dpto_user = "CONTABIL";
            } else if (radioTipoUserOts.isSelected() == true) {
                tipo_user = 5;
                s_dpto_user = "OUTROS";
            } else {
                tipo_user = 0;
            }
            if (tipo_user == 1 && (s_super_senha.isEmpty() || s_super_senha.equals(null))) {
                JOptionPane.showMessageDialog(null, "Informe a super senha!");
            } else {
                if (!s_conf_senha.equals(s_senha)) {
                    JOptionPane.showMessageDialog(null, "Senha e confirmação de senha não coicidem!");
                } else {
                    DAOUsuarios daou = new DAOUsuarios();
                    try {
                        boolean condicao_ja_existe = daou.consultaUser(s_usuario);
                        if (condicao_ja_existe == true) {
                            JOptionPane.showMessageDialog(null, "Usuário já existe!");
                        } else {
                            if (tipo_user != 1) {
                                s_super_senha = "";
                            }
                            TabCadMenus tcm = new TabCadMenus();
                            DAOTabCadMenusUsuario daotcmu = new DAOTabCadMenusUsuario();
                            List<TabCadMenus> dados;
                            Usuarios u = new Usuarios();

                            u.setDpto_usuario(s_dpto_user);
                            u.setNome_usuario(s_nome_user);
                            u.setSenha(s_senha);
                            u.setSuper_senha(s_super_senha);
                            u.setTipo_usuario(tipo_user);
                            u.setUsuario(s_usuario);
                            u.setStatus_usuario(1);
                            daou.inserir(u);
                            limpaCampos();
                            JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso!");
                            /*  try {
                             dados = daotcmu.retornaTodosMenus();
                             daou.inserir(u);
                             JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso!");
                             int id_usuario_mais_um = daou.retornaUltimaID();
                             TabCadMenusUsuario tcmu = new TabCadMenusUsuario();
                             for (int i = 0; i < dados.size(); i++) {
                             tcmu.setId_tab_cad_menu(dados.get(i).getId_tab_cad_menu());
                             tcmu.setId_usuario(id_usuario_mais_um);
                             tcmu.setStatus(0);
                             daotcmu.inserir(tcmu);
                             }
                             limpaCampos();
                             } catch (SQLException ex) {
                             Logger.getLogger(PCadastrosUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                             } */
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(PCadastrosUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        }
    }

    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblNomeUser;
    private javax.swing.JLabel lblSenhaUser;
    private javax.swing.JLabel lblSuperSenhaUser;
    private javax.swing.JLabel lblTipoUser;
    private javax.swing.JLabel lblConfSenha;
    private javax.swing.JPasswordField txtConfSenha;
    private javax.swing.JTextField txtUser;
    private javax.swing.JTextField txtNomeUser;
    private javax.swing.JPasswordField txtSenhaUser;
    private javax.swing.JPasswordField txtSuperSenhaUser;
    private javax.swing.JRadioButton radioTipoUserAdm;
    private javax.swing.JRadioButton radioTipoUserPdc;
    private javax.swing.JRadioButton radioTipoUserFin;
    private javax.swing.JRadioButton radioTipoUserCtb;
    private javax.swing.JRadioButton radioTipoUserOts;
    private javax.swing.ButtonGroup grupoBtsTipoUser;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JPanel panelCad;
}
