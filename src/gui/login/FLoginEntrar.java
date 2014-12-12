/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.login;

import bd.Conexao;
import classes.ManipulaArquivo;
import classes.PassaCamposComEnter;
import dao.cadastros.DAOUsuarios;
import gui.inicio.FInicio;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Rafael
 */
public class FLoginEntrar extends javax.swing.JFrame {

    public FLoginEntrar() {
        initComponents();
    }

    private void initComponents() {
        panelPrincipal = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel("Usuário:");
        lblSenha = new javax.swing.JLabel("Senha:");
        lblLogo = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        btEntrar = new javax.swing.JButton("Entrar");
        btCancelar = new javax.swing.JButton("Cancelar");
        dimensionTxt = new java.awt.Dimension(200, 25);
        panelBts = new javax.swing.JPanel();
        panelTudo = new javax.swing.JPanel();
        
        
        //configurações do jframe
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("CAFÉ AMÉRICA LTDA - ENTRAR");
        this.setName("CAFE AMERICA LTDA");
        //fim

        //adicionando componente ao jframe
        
        this.add(panelTudo);
        panelTudo.setBackground(Color.WHITE);
        panelTudo.setLayout(new MigLayout());
        panelTudo.add(panelPrincipal,"wrap");
        panelTudo.add(panelBts,"gapx 140");
        //fim

        //adicionando componentes ao panelPrincipal
        panelPrincipal.setLayout(new MigLayout());
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.add(lblLogo, "gapx 65,wrap");
        panelPrincipal.add(lblUsuario, "split 2, gapx 50");
        panelPrincipal.add(txtUsuario, "wrap");
        panelPrincipal.add(lblSenha, "split 2, gapx 58");
        panelPrincipal.add(txtSenha, "wrap");
       // panelPrincipal.add(btCancelar);   
        //fim
        
        //adicionando componentes panel de bts
        panelBts.setBackground(Color.WHITE);
        panelBts.setLayout(new MigLayout());
        panelBts.add(btEntrar);
        //fim

        //configurações dos componentes do panelPrincipal
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/img/cafeamericalogo.jpg")));
        txtUsuario.setPreferredSize(dimensionTxt);
        txtSenha.setPreferredSize(dimensionTxt);
        //fim

        btEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btEntrarActionPerformed(e);
            }
        });

        
        //configurações do panel principal
        //Os textfields vao receber como enter para pular para o proximo textfield
        PassaCamposComEnter pcce = new PassaCamposComEnter();
        pcce.passaCamposComEnter(panelPrincipal);

        getRootPane().setDefaultButton(btEntrar); //Define este botão como principal da tela, sendo executado também
        // pela tecla ENTER
        
        //fim
        
        //fim
        //perguntando ao usuario se deseja fechar o jframe
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //caixa de dialogo retorna um inteiro  
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja sair do sistema?", "SAIR", JOptionPane.YES_NO_OPTION);

                if (resposta == 0) {
                    dispose();
                }

            }
        });

    }

    private void btEntrarActionPerformed(java.awt.event.ActionEvent evt) {
        String s_usuario = txtUsuario.getText().toString().toUpperCase();
        String s_senha = txtSenha.getText().toString().toUpperCase();

        DAOUsuarios daou = new DAOUsuarios();
        boolean condicao = false;
       // int[] vet_aux = new int[16];

        try {
            condicao = daou.consultaUserExiste(s_usuario, s_senha);
            if (condicao == true) {
                System.out.println("Entrou no sistema!");
                int id_usuario = daou.retornaIDPorUsuario(s_usuario);

                ManipulaArquivo.setUsuario(""+id_usuario); //jogando nome de usuario para o arq de config.

              //  DAOTabCadMenusUsuario daotcmu = new DAOTabCadMenusUsuario();
              // vet_aux = daotcmu.retornaMenusUsuario(id_usuario);
               FInicio t = new FInicio(id_usuario);
              //  FInicio t = new FInicio();
                t.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválido(s)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FLoginEntrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FLoginEntrar().setVisible(true);
            }
        });
    }

    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JButton btEntrar;
    private javax.swing.JButton btCancelar;
    private java.awt.Dimension dimensionTxt;
    private javax.swing.JPanel panelBts;
    private javax.swing.JPanel panelTudo;
}
